package com.backend.controller;

import com.backend.dto.KnowledgeSpaceDTO;
import com.backend.dto.ProblemDTO;
import com.backend.dto.SurmiseLinkDTO;
import com.backend.dto.request.KnowledgeSpaceGraphRequest;
import com.backend.model.KnowledgeSpace;
import com.backend.model.Problem;
import com.backend.model.Subject;
import com.backend.model.Surmise;
import com.backend.service.*;
import com.backend.service.KnowledgeSpaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/knowledgeSpace")
public class KnowledgeSpaceController {
    @Autowired
    private KnowledgeSpaceService knowledgeSpaceService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private SurmiseService surmiseService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveKnowledgeSpace(@RequestBody KnowledgeSpaceDTO knowledgeSpaceDTO, HttpServletRequest httpServletRequest) {
        try {
            KnowledgeSpace knowledgeSpace = new KnowledgeSpace();
            if(knowledgeSpaceDTO.getName().equals("") || knowledgeSpaceDTO.getSubjectId() == 0 ||  knowledgeSpaceDTO.getSubjectId() == null)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            knowledgeSpace.setName(knowledgeSpaceDTO.getName());

            Optional<Subject> subject = subjectService.findById(knowledgeSpaceDTO.getSubjectId());
            if(subject.isPresent() ) {
                subject.ifPresent(subject1 -> {
                    knowledgeSpace.setSubjectId(subject1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            knowledgeSpaceService.save(knowledgeSpace);
            return new ResponseEntity<>(knowledgeSpace.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<KnowledgeSpace>> getKnowladgeSpaces() {
        List<KnowledgeSpace> knowledgeSpaces = knowledgeSpaceService.findAll();
        if(knowledgeSpaces != null)
        {
            return new ResponseEntity<>(knowledgeSpaces, HttpStatus.OK);
        }
        return new ResponseEntity<>(knowledgeSpaces, HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/bySubject/{id}", produces = "application/json")
    public ResponseEntity<List<KnowledgeSpaceDTO>> getSubjectKnowledgeSpace(@PathVariable("id") Integer subjectId) {
        Optional<Subject> subject = subjectService.findById(subjectId);
        List<KnowledgeSpaceDTO> response = new ArrayList<>();

        if(subject.isPresent())
        {
            List<KnowledgeSpace> knowledgeSpaces = knowledgeSpaceService.findBySubject(subject.get());

            for (KnowledgeSpace ks : knowledgeSpaces)
            {
                response.add(new KnowledgeSpaceDTO(ks.getId(), ks.getName(), ks.getSubjectId().getId(), ks.getSurmises()));
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/{knowledgeId}/{subjectId}")
    public ResponseEntity<Void> deleteKnowledgeSpace( @PathVariable("knowledgeId") Integer knowledgeId, @PathVariable("subjectId") Integer subjectId) {
        Optional<Subject> subject = subjectService.findById(subjectId);
        Optional<KnowledgeSpace> knowledgeSpace = knowledgeSpaceService.findById(knowledgeId);
        if(subject.isPresent() ) {
            subject.ifPresent(subject1 -> {
                subject1.getKnowledgeSpaces().remove(knowledgeSpace.get());
            });
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        knowledgeSpaceService.remove(knowledgeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/fromGraph", consumes = "application/json")
    public ResponseEntity<Integer> saveKnowledgeSpaceFromGraph(@RequestBody KnowledgeSpaceGraphRequest request, HttpServletRequest httpServletRequest) {
        try {
            KnowledgeSpace knowledgeSpace = new KnowledgeSpace();
            if(request.getName().equals("") || request.getSubjectId() == 0)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            knowledgeSpace.setName(request.getName());

            Optional<Subject> subject = subjectService.findById(request.getSubjectId());
            if(subject.isPresent() ) {
                subject.ifPresent(subject1 -> {
                    knowledgeSpace.setSubjectId(subject1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            knowledgeSpaceService.save(knowledgeSpace);
            List<Problem> tempList = new ArrayList<>();
            request.getProblems().sort(Comparator.comparing(ProblemDTO::getId));
            for (ProblemDTO dto :
                    request.getProblems()) {

                Subject s = subjectService.findById(dto.getSubjectId()).orElse(null);
                if(s != null){
                    System.out.println(dto.getName());
                    Problem p = new Problem();
                    p.setName(dto.getName());
                    p.setDescription("desc");
                    p.setSubject(s);

                    problemService.save(p);
                    tempList.add(p);
                }
            }

            HashMap<Integer, Set<Integer>> links = new HashMap<>();

            for (SurmiseLinkDTO link :
                    request.getLinks()) {
                if(links.get(link.getFrom()) != null)
                    links.get(link.getFrom()).add(link.getTo());
                else{
                    Set<Integer> to = new HashSet<>();
                    to.add(link.getTo());
                    links.put(link.getFrom(), to);
                }
            }

            for (Integer key :
                    links.keySet()) {
                System.out.println(key);
                Surmise s = new Surmise();
                s.setKnowledgeSpaceId(knowledgeSpace);
                s.setProblemId(tempList.get(key));
                Set<Problem> to = new HashSet<>();
                for (Integer toKey :
                        links.get(key)) {
                    System.out.println("val "+toKey);
                    to.add(tempList.get(toKey));
                }
                s.setProblems(to);
                surmiseService.save(s);
            }
            System.out.println("Here");

            return new ResponseEntity<>(knowledgeSpace.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }
    }
}
