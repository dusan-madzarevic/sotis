package com.backend.controller;

import com.backend.dto.KnowledgeStateDTO;
import com.backend.model.KnowledgeState;
import com.backend.service.KnowledgeStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/knowledgeState")
public class KnowledgeStateController {
    @Autowired
    private KnowledgeStateService knowladgeStateService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveKnowledgeState(@RequestBody KnowledgeStateDTO knowledgeStateDTO, HttpServletRequest httpServletRequest) {
        try {
            KnowledgeState knowledgeState = new KnowledgeState();
            if(knowledgeStateDTO.getName().equals(""))
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            knowledgeState.setName(knowledgeStateDTO.getName());

            Optional<KnowledgeState> knowledgeState1 = knowladgeStateService.findById(knowledgeStateDTO.getPrevious());
            if(knowledgeState1.isPresent() ) {
                knowledgeState1.ifPresent(knowledgeState2 -> {
                    knowledgeState.getPredecessors().add(knowledgeState2);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            Optional<KnowledgeState> knowledgeState3 = knowladgeStateService.findById(knowledgeStateDTO.getNext());
            if(knowledgeState3.isPresent() ) {
                knowledgeState3.ifPresent(knowledgeState4 -> {
                    knowledgeState.getFollowers().add(knowledgeState4);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            knowladgeStateService.save(knowledgeState);
            return new ResponseEntity<>(knowledgeState.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<KnowledgeState>> getKnowledgeStates() {
        List<KnowledgeState> knowledgeStates = knowladgeStateService.findAll();
        if(knowledgeStates != null)
        {
            return new ResponseEntity<>(knowledgeStates, HttpStatus.OK);
        }
        return new ResponseEntity<>(knowledgeStates, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{knowladgeStateId}")
    public ResponseEntity<Void> deleteKnowledgeState( @PathVariable("knowladgeStateId") Integer knowladgeStateId) {
        knowladgeStateService.remove(knowladgeStateId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
