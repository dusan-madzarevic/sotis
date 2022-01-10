package com.backend.controller;

import com.backend.dto.AnswerDTO;
import com.backend.dto.KnowledgeStateDTO;
import com.backend.dto.KnowledgeStateInitialDTO;
import com.backend.model.*;
import com.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

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

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> setInitialKnowledgeState(KnowledgeStateInitialDTO knowledgeStateInitialDTO) {
        try {
            if(knowledgeStateInitialDTO.getStudentId() == null || knowledgeStateInitialDTO.getStudentId() == 0 || knowledgeStateInitialDTO.getKnowledgeSpaceId() == null || knowledgeStateInitialDTO.getKnowledgeSpaceId() == 0)
                return new ResponseEntity<>(-1, HttpStatus.NOT_MODIFIED);

            KnowledgeSpace knowledgeSpace = knowledgeSpaceService.findById(knowledgeStateInitialDTO.getKnowledgeSpaceId()).orElse(null);
            Student student = studentService.findById(knowledgeStateInitialDTO.getStudentId()).orElse(null);

            if(knowledgeSpace != null)
            {
                Set<Surmise> surmises = knowledgeSpace.getSurmises();
                Double likelihoods = (double) (1 / surmises.size());
                if(student != null)
                {
                    for(Surmise s : surmises)
                    {
                        KnowledgeState knowledgeState = new KnowledgeState();
                        knowledgeState.setLikelihood(likelihoods);
                        knowledgeState.setStudentId(student);
                        knowledgeState.setSurmiseId(s);
                        knowledgeState.setFinalState(false);

                        try
                        {
                            knowledgeStateService.save(knowledgeState);
                        }catch(Exception e)
                        {
                            return new ResponseEntity<>(-1, HttpStatus.NOT_MODIFIED);
                        }
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

    @GetMapping(value = "/first/byStudent/{id}", produces = "application/json")
    public ResponseEntity<Question> getFirstQuestion(@PathVariable("id") Integer studentId) {
        Student student = studentService.findById(studentId).orElse(null);
        if( student != null )
        {
            List<KnowledgeState> knowledgeStates = knowledgeStateService.findByStudentId(student);
            if(knowledgeStates != null)
            {
                Random random = new Random();
                int int_random = random.nextInt(knowledgeStates.size());
                KnowledgeState k = knowledgeStates.get(int_random);
                Set<Question> questions = k.getSurmiseId().getProblemId().getQuestions();
                for (Question q : questions)
                {
                    return new ResponseEntity<>(q, HttpStatus.OK); //prvo pitanje iz random KnowledgeState-a
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/next/byStudent/{id}", consumes = "application/json")
    public ResponseEntity<Question> getNextQuestion(@PathVariable("id") Integer studentId, @RequestBody AnswerDTO answerDTO) {
        Student student = studentService.findById(studentId).orElse(null);
        Question question = questionService.findById(answerDTO.getQuestionId()).orElse(null);
        Problem problem = getWantedProblem(question);

        double theta = 0.1;
        double lnk;
        double lnkq = 0.0;
        boolean flag = false;


        if( student != null )
        {
            List<KnowledgeState> knowledgeStates = knowledgeStateService.findByStudentId(student);
            if(knowledgeStates != null)
            {
                for (KnowledgeState k : knowledgeStates)
                {
                    Set<Problem> problems = k.getSurmiseId().getProblems();
                    for (Problem p : problems)
                    {
                        if(p == problem)
                        {
                            flag = true;
                        }
                    }
                    if(flag)
                    {
                        lnkq+=k.getLikelihood();
                    }
                }

                for (KnowledgeState k : knowledgeStates)
                {
                    Problem problem1 = k.getSurmiseId().getProblemId();
                    if(problem1 == problem && answerDTO.getCorrect())
                    {
                        lnk = k.getLikelihood();
                        k.setLikelihood((1 - theta)*lnk + theta * 1 * (lnk/lnkq));
                        knowledgeStateService.save(k);
                    }// uvecanje likelihooda ako je surmise sa problemIdjem bas taj problem u kom je pitanje sa tacnim odgovorom

                    Set<Problem> prob = k.getSurmiseId().getProblems(); //dodavanje i oduzimanje u odnosu na surmise problems tj. childove
                    for(Problem problem2 : prob)
                    {
                        if(problem2 == problem && answerDTO.getCorrect())
                        {
                            lnk = k.getLikelihood();
                            k.setLikelihood((1 - theta)*lnk + theta * 1 * (lnk/lnkq));
                            knowledgeStateService.save(k);
                        }else
                        {
                            lnk = k.getLikelihood();
                            k.setLikelihood((1 - theta)*lnk);
                            knowledgeStateService.save(k);
                        }

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
                    qu.setQuestionText("Test je zavrsen. Vase stanje znanja je: " + temp.getSurmiseId().getProblemId().getName() + ", sa opisom: " + temp.getSurmiseId().getProblemId().getDescription());
                    return new ResponseEntity<>(qu, HttpStatus.OK);
                }

                Set<Question> questions = temp.getSurmiseId().getProblemId().getQuestions();
                Random random = new Random();
                int int_random = random.nextInt(questions.size());
                int int_flag = 0;
                for(Question question1 : questions)
                {
                    if(int_flag == int_random)
                    {
                        return new ResponseEntity<>(question1, HttpStatus.OK);
                    }
                    int_flag++;
                }
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
}
