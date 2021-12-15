package com.backend.controller;

import com.backend.dto.*;
import com.backend.dto.request.StartAttemptRequest;
import com.backend.dto.request.SubmitAttemptRequest;
import com.backend.model.*;
import com.backend.service.AnswerService;
import com.backend.service.StudentService;
import com.backend.service.TestAttemptService;
import com.backend.service.TestService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/testAttempt")
public class TestAttemptController {
    @Autowired
    private TestAttemptService testAttemptService;

    @Autowired
    private TestService testService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AnswerService answerService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveTestAttempt(@RequestBody TestAttemptDTO testAttemptDTO, HttpServletRequest httpServletRequest) {
        try {
            TestAttempt testAttempt = new TestAttempt();
            if (testAttemptDTO.getStudentId() == null || testAttemptDTO.getStudentId() == 0 || testAttemptDTO.getTestTypeId() == null || testAttemptDTO.getTestTypeId() == 0 ||
                    testAttemptDTO.getStartTime().equals("") || testAttemptDTO.getEndTime().equals("") || testAttemptDTO.getFinalScore().equals("") || testAttemptDTO.getPassed() == null) {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            testAttempt.setStartTime(testAttemptDTO.getStartTime());
            testAttempt.setEndTime(testAttemptDTO.getEndTime());
            testAttempt.setFinalScore(testAttemptDTO.getFinalScore());
            testAttempt.setPassed(testAttemptDTO.getPassed());

            Optional<Student> student = studentService.findById(testAttemptDTO.getStudentId());
            if (student.isPresent()) {
                student.ifPresent(student1 -> {
                    testAttempt.setStudentId(student1);
                });
            } else {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            Optional<Test> testType = testService.findById(testAttemptDTO.getTestTypeId());
            if (testType.isPresent()) {
                testType.ifPresent(testType1 -> {
                    testAttempt.setTestTypeId(testType1);
                });
            } else {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            testAttemptService.save(testAttempt);
            return new ResponseEntity<>(testAttempt.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
        }

    }

    @PostMapping(value = "/start", produces = "application/json")
    public ResponseEntity<StartAttemptDTO> startAttempt(@RequestBody StartAttemptRequest request){

        TestAttempt testAttempt = new TestAttempt();
        StartAttemptDTO dto = new StartAttemptDTO();
        List<AttemptQuestionDTO> questionDTOS = new ArrayList<>();

        if (request.getStudentUsername() == null || Objects.equals(request.getStudentUsername(), "") || request.getTestTypeId() == null || request.getTestTypeId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

        testAttempt.setStartTime(sdf.format(Date.from(Instant.now())));

        Optional<Student> student = studentService.findByUsername(request.getStudentUsername());
        if (student.isPresent()) {
            student.ifPresent(student1 -> {
                testAttempt.setStudentId(student1);
            });
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }

        Optional<Test> testType = testService.findById(request.getTestTypeId());
        if (testType.isPresent()) {
            testType.ifPresent(testType1 -> {
                testAttempt.setTestTypeId(testType1);
            });
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }

        testAttemptService.save(testAttempt);

        dto.setAttemptID(testAttempt.getId());
        dto.setTestName(testAttempt.getTestTypeId().getTitle());

        for (Section section :
                testAttempt.getTestTypeId().getSections()) {

            for (Question question :
                    section.getQuestions()) {
                AttemptQuestionDTO questionDto = new AttemptQuestionDTO();
                questionDto.setSection(section.getName());
                List<AttemptAnswerDTO> attemptAnswerDTOS = new ArrayList<>();

                questionDto.setQuestionID(question.getId());
                questionDto.setQuestionText(question.getQuestionText());
                questionDto.setQuestionScore(question.getScore());

                for (Answer answer:
                        question.getAnswers()) {

                    AttemptAnswerDTO answerDto = new AttemptAnswerDTO();
                    answerDto.setAnswerID(answer.getId());
                    answerDto.setAnswerText(answer.getAnswerText());

                    attemptAnswerDTOS.add(answerDto);

                }
                questionDto.setAnswers(attemptAnswerDTOS);

                questionDTOS.add(questionDto);

            }


        }
        dto.setQuestions(questionDTOS);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @PostMapping(value = "/submit", produces = "application/json")
    public ResponseEntity<TestAttempt> submitAttempt(@RequestBody SubmitAttemptRequest request){

        TestAttempt testAttempt = testAttemptService.findById(request.getTestAttemptId()).orElse(null);

        if(testAttempt == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }
        Integer totalScore = 0;
        for (ChosenAnswerDTO dto :
                request.getAnswers()) {
            Answer answer = answerService.findById(dto.getAnswerId()).orElse(null);
            if(answer == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
            }

            testAttempt.getChosenAnswers().add(answer);
            totalScore += answer.getScore();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

        testAttempt.setEndTime(sdf.format(Date.from(Instant.now())));
        testAttempt.setFinalScore(String.valueOf(totalScore));

        if( (Double.parseDouble(testAttempt.getFinalScore()) / testAttempt.getTestTypeId().getMaxScore()) * 100 < testAttempt.getTestTypeId().getPassPercentage() ){
            testAttempt.setPassed(false);
        }else{
            testAttempt.setPassed(true);
        }
        testAttemptService.save(testAttempt);

        return new ResponseEntity<>(testAttempt, HttpStatus.OK);

    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<List<TestAttempt>> getTestAttempts() {
        List<TestAttempt> testAttempts = testAttemptService.findAll();
        if(testAttempts != null)
        {
            return new ResponseEntity<>(testAttempts, HttpStatus.OK);
        }
        return new ResponseEntity<>(testAttempts, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTestAttempt(@PathVariable Integer id) {
        testAttemptService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/results", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource getResults(@PathVariable Integer id) throws IOException {

        Test test = testService.findById(id).orElse(null);
        if(test != null) {
            List<TestAttempt> attempts = testAttemptService.findAllByTest(test);

            PrintWriter writer = new PrintWriter(test.getTitle()+".csv", "UTF-8");
            writer.println("Ime,Prezime,Bodovi,Polozio?");
            for (TestAttempt attempt :
                    attempts) {
                writer.println(attempt.getStudentId().getName()+","+attempt.getStudentId().getLastName()+","+attempt.getFinalScore()+","+attempt.getPassed());
            }
            writer.close();

            return new FileSystemResource(test.getTitle()+".csv");

        }else{
            return null;
        }

    }

    @GetMapping(value = "/{id}/resultsJson", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<TestAttemptIITAResponse> getResultsJson(@PathVariable Integer id) throws IOException {

        Test test = testService.findById(id).orElse(null);
        if(test != null) {
            List<TestAttempt> attempts = testAttemptService.findAllByTest(test);
            TestAttemptIITAResponse response = new TestAttemptIITAResponse();
            List<TestAttemptIITADto> dtos = new ArrayList<>();
            for (TestAttempt attempt :
                    attempts) {
                TestAttemptIITADto dto = new TestAttemptIITADto();
                dto.setStudentName(attempt.getStudentId().getName());
                for (Answer a :
                        attempt.getChosenAnswers()) {
                    if (a.getCorrect())
                        dto.getAnswers().add(1);
                    else
                        dto.getAnswers().add(0);
                }
                dtos.add(dto);
            }
            response.setResults(dtos);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return null;
        }

    }
}
