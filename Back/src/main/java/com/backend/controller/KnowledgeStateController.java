package com.backend.controller;

import com.backend.dto.AnswerDTO;
import com.backend.dto.KnowledgeStateDTO;
import com.backend.dto.KnowledgeStateInitialDTO;
import com.backend.dto.request.FirstQuestionRequest;
import com.backend.model.*;
import com.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(value = "/knowledgeState")
public class KnowledgeStateController {
    @Autowired
    private KnowledgeStateService knowledgeStateService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SurmiseService surmiseService;

    @Autowired
    private KnowledgeSpaceService knowledgeSpaceService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private AnswerService answerService;

    @PostMapping(consumes = "application/json")
    @Transactional
    public ResponseEntity<Integer> setInitialKnowledgeState(@RequestBody KnowledgeStateInitialDTO knowledgeStateInitialDTO) {
        try {
            System.out.println(knowledgeStateInitialDTO.getStudentUsername());
            System.out.println(knowledgeStateInitialDTO.getKnowledgeSpaceId());
            if(knowledgeStateInitialDTO.getStudentUsername() == null || Objects.equals(knowledgeStateInitialDTO.getStudentUsername(), "") || knowledgeStateInitialDTO.getKnowledgeSpaceId() == null || knowledgeStateInitialDTO.getKnowledgeSpaceId() == 0)
                return new ResponseEntity<>(-1, HttpStatus.NOT_MODIFIED);

            KnowledgeSpace knowledgeSpace = knowledgeSpaceService.findById(knowledgeStateInitialDTO.getKnowledgeSpaceId()).orElse(null);
            Student student = studentService.findByUsername(knowledgeStateInitialDTO.getStudentUsername()).orElse(null);
            List<Problem> rootProblems = new ArrayList<>();
            HashMap<Integer, List<Integer>> problemsInOrder = new HashMap<>();

            if(knowledgeSpace != null)
            {
                List<Problem> problems = problemService.findBySubject(knowledgeSpace.getSubjectId());
                Set<Surmise> surmises = knowledgeSpace.getSurmises();
                if(student != null)
                {
                    knowledgeStateService.resetStates(student.getId());
                    for(Surmise s : surmises)
                    {
                        if(isRootProblem(s.getProblemId(), knowledgeSpace, rootProblems)){
                            System.out.println(s.getProblemId().getName());
                            KnowledgeState knowledgeState = new KnowledgeState();
                            knowledgeState.setStudentId(student);
                            knowledgeState.getProblems().add(s.getProblemId());
                            knowledgeState.setFinalState(false);
                            knowledgeStateService.save(knowledgeState);

                            for (Problem next: s.getProblems()
                                 ) {
                                    problemsInOrder.put(next.getId(), new ArrayList<>());
                                    problemsInOrder.get(next.getId()).add(s.getProblemId().getId());
                                }

                        }
                    }

                    for (Surmise s : surmises){
                        System.out.println("Not Root Svi:"+s.getProblemId().getName());
                        if (!isRootProblem(s.getProblemId(), knowledgeSpace, rootProblems)){
                            System.out.println("Not Root:"+s.getProblemId().getName());
                                if(s.getProblems().size() != 0) {
                                    Problem getProblem = null;
                                    for(Problem oneProb : s.getProblems()){
                                        getProblem = oneProb;
                                    }
                                    if(surmiseService.findByProblemId(getProblem).size() != 0) {


                                        for (Problem next : s.getProblems()
                                        ) {
                                            problemsInOrder.put(next.getId(), new ArrayList<>());
                                            problemsInOrder.get(next.getId()).add(s.getProblemId().getId());
                                            for (Integer i :
                                                    problemsInOrder.get(s.getProblemId().getId())) {
                                                problemsInOrder.get(next.getId()).add(i);
                                            }
                                        }

                                        KnowledgeState knowledgeState = new KnowledgeState();
                                        knowledgeState.setStudentId(student);
                                        knowledgeState.getProblems().add(s.getProblemId());
                                        knowledgeState.setFinalState(false);
                                        for (Integer i :
                                                problemsInOrder.get(s.getProblemId().getId())) {
                                            knowledgeState.getProblems().add(problemService.findById(i).orElse(null));
                                        }
                                        knowledgeStateService.save(knowledgeState);
                                    }
                                }

                        }

                    }

                    for(Surmise s: surmises){
                        if(s.getProblems().size() == 1) {
                            Problem getProblem = null;
                            for (Problem oneProb : s.getProblems()) {
                                getProblem = oneProb;
                            }
                            if (surmiseService.findByProblemId(getProblem).size() == 0) {
                                KnowledgeState knowledgeState = new KnowledgeState();
                                knowledgeState.setStudentId(student);
                                knowledgeState.getProblems().add(s.getProblemId());
                                knowledgeState.setFinalState(false);
                                for (Integer i :
                                        problemsInOrder.get(s.getProblemId().getId())) {
                                    System.out.println(i);
                                    knowledgeState.getProblems().add(problemService.findById(i).orElse(null));
                                }
                                knowledgeStateService.save(knowledgeState);
                            }
                        }
                    }

                    for (Problem root :
                            rootProblems) {
                        if (root.getId() != 1) {
                            System.out.println("Root problem: " + root.getName());
                            List<KnowledgeState> containingRoot = knowledgeStateService.findWithProblem(root.getId());

                            for (Problem root2 : rootProblems) {

                                if (root2.getId() != root.getId()) {

                                    List<KnowledgeState> containingRoot2 = knowledgeStateService.findWithProblem(root2.getId());

                                    for (KnowledgeState ks :
                                            containingRoot) {
                                        for (KnowledgeState ks2 :
                                                containingRoot2) {
                                            if (ks.getProblems().size() + ks2.getProblems().size() != problems.size() - 1) {
                                                KnowledgeState combinedState = new KnowledgeState();
                                                combinedState.setFinalState(false);
                                                combinedState.setStudentId(student);
                                                combinedState.getProblems().addAll(ks.getProblems());
                                                combinedState.getProblems().addAll(ks2.getProblems());
                                                knowledgeStateService.save(combinedState);
                                            }
                                        }

                                    }

                                }

                            }


                        }
                    }

                    // Kreiranje praznog KS
                    KnowledgeState empty = new KnowledgeState();
                    empty.setStudentId(student);
                    empty.setFinalState(false);
                    knowledgeStateService.save(empty);

                    // Kreiranje KS sa svim problemima
                    KnowledgeState all = new KnowledgeState();
                    all.setStudentId(student);
                    all.setFinalState(false);
                    all.setProblems(problems);
                    knowledgeStateService.save(all);

                    List<KnowledgeState> allStates = knowledgeStateService.findByStudentId(student);

                    for (KnowledgeState state :
                            allStates) {
                        state.setLikelihood(1.0 / allStates.size());
                        knowledgeStateService.save(state);
                    }


                }
                else
                {
                    return new ResponseEntity<>(-1, HttpStatus.NOT_MODIFIED);
                }
            }
            else
            {
                return new ResponseEntity<>(-1, HttpStatus.NOT_MODIFIED);
            }
            return new ResponseEntity<>(1, HttpStatus.CREATED);


        }catch(Exception e){
            return new ResponseEntity<>(-1,HttpStatus.NOT_MODIFIED);
        }
    }

    @PostMapping(value = "/first/byStudent", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Question> getFirstQuestion(@RequestBody FirstQuestionRequest request) {
        Student student = studentService.findByUsername(request.getStudentUsername()).orElse(null);
        KnowledgeSpace knowledgeSpace = knowledgeSpaceService.findById(request.getKnowledgeSpaceId()).orElse(null);
        if(knowledgeSpace != null) {
            List<Problem> problems = problemService.findBySubject(knowledgeSpace.getSubjectId());
            if (student != null) {
                Random random = new Random();
                int int_random = random.nextInt(problems.size());
                Problem p = problems.get(int_random);
                Set<Question> questions = p.getQuestions();
                for (Question q : questions) {
                    return new ResponseEntity<>(q, HttpStatus.OK); //prvo pitanje iz random KnowledgeState-a
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/next/byStudent", consumes = "application/json")
    public ResponseEntity<Question> getNextQuestion(@RequestBody AnswerDTO answerDTO) {
        Student student = studentService.findByUsername(answerDTO.getStudentUsername()).orElse(null);
        Question question = questionService.findById(answerDTO.getQuestionId()).orElse(null);
        Problem problem = getWantedProblem(question);
        Answer answer = answerService.findById(answerDTO.getId()).orElse(null);
        double theta = 0.6;
        double lnk;
        double lnkq = 0.0;
        double lnkq_no = 0.0;
        boolean flag = false;


        if( student != null && answer != null)
        {
            List<KnowledgeState> knowledgeStates = knowledgeStateService.findByStudentId(student);
            if(knowledgeStates != null)
            {
                for (KnowledgeState k : knowledgeStates)
                {
                    if(k.getProblems().stream().map(Problem::getId).collect(Collectors.toList()).contains(problem.getId())){
                        lnkq += k.getLikelihood();
                    }else{
                        lnkq_no += k.getLikelihood();
                    }

                }

                for (KnowledgeState k : knowledgeStates)
                {
                    lnk = k.getLikelihood();
                    int r = 0;
                    if(answer.getCorrect())
                        r = 1;
                    System.out.println(r);
                    if(k.getProblems().stream().map(Problem::getId).collect(Collectors.toList()).contains(problem.getId()))
                    {
                        System.out.println("State contains problem");
                        k.setLikelihood((1 - theta)*lnk + theta * r * (lnk/lnkq));
                        knowledgeStateService.save(k);
                    }// uvecanje likelihooda ako je surmise sa problemIdjem bas taj problem u kom je pitanje sa tacnim odgovorom
                    else{
                        System.out.println("State doesn't contain problem");
                        k.setLikelihood((1 - theta)*lnk + theta * (1-r) * (lnk/lnkq_no));
                        knowledgeStateService.save(k);
                    }

                }

                KnowledgeState temp = new KnowledgeState();
                temp.setLikelihood(0.0);
                for (KnowledgeState ks : knowledgeStates)
                {
                    if(ks.getLikelihood() > temp.getLikelihood())
                    temp = ks;
                }

                if(temp.getLikelihood() >= 0.9)
                {
                    Question qu = new Question();
                    StringBuilder sb = new StringBuilder();
                    for (Problem p :
                            temp.getProblems()) {
                        sb.append(p.getName()).append(", ");
                    }
                    qu.setQuestionText("Test je zavrsen. Problemi koje ste savladali, odnosno Vaše stanje znanja:" +sb.toString());
                    return new ResponseEntity<>(qu, HttpStatus.OK);
                }
                double least_diff = Double.MAX_VALUE;
                Problem selected = null;
                for (Problem p :
                        question.getSectionId().getTestId().getSubjectId().getProblems()) {
                    double lnkp = 0.0;
                    double diff = 0.0;
                    for (KnowledgeState ks :
                            knowledgeStates) {
                        if(ks.getProblems().stream().map(Problem::getId).collect(Collectors.toList()).contains(p.getId())){
                            lnkp += ks.getLikelihood();
                        }
                    }
                    diff = Math.abs(lnkp - (1 - lnkp));
                    if(diff < least_diff){
                        least_diff = diff;
                        selected = p;
                    }
                }

                Random random = new Random();
                int int_random = random.nextInt(selected.getQuestions().size());
                List<Question> questions = new ArrayList<>(selected.getQuestions());
                Question q = questions.get(int_random);
                return new ResponseEntity<>(q, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
    }


    public Problem getWantedProblem(Question question)
    {
        List<Problem> problems = problemService.findAll();
        Problem problem = new Problem();
        for(Problem p : problems)
        {
            for (Question q : p.getQuestions())
            {
                if(q == question)
                {
                    problem.setId(p.getId());
                    problem.setName(p.getName());
                    problem.setDescription(p.getDescription());
                    problem.setSubject(p.getSubject());
                    problem.setSurmises(p.getSurmises());
                    problem.setLearnedProblems(p.getLearnedProblems());
                    problem.setQuestions(p.getQuestions());
                    return problem;
                }
            }
        }
        return null;
    }

    public boolean isRootProblem(Problem p, KnowledgeSpace ks, List<Problem> rootProblems){
        Set<Surmise> surmises = ks.getSurmises();
        for (Surmise s:
             surmises) {
            if(s.getProblems().contains(p)){
                return false;
            }
        }
        if(!rootProblems.contains(p))
            rootProblems.add(p);
        return true;

    }
}
