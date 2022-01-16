package com.backend.controller;

import com.backend.dto.QuestionDTO;
import com.backend.dto.SectionDTO;
import com.backend.dto.SurmiseDTO;
import com.backend.model.*;
import com.backend.service.ProblemService;
import com.backend.service.QuestionService;
import com.backend.service.SectionService;
import com.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


@RestController
@CrossOrigin
@RequestMapping(value = "/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ProblemService problemService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveQuestion(@RequestBody QuestionDTO questionDTO, HttpServletRequest httpServletRequest) {
        try {
            Question question = new Question();
            if(questionDTO.getQuestionText().equals("") || questionDTO.getScore() == null)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            question.setQuestionText(questionDTO.getQuestionText());
            question.setScore(questionDTO.getScore());

            Optional<Section> section = sectionService.findById(questionDTO.getSectionId());

            if(section.isPresent() ) {
                section.ifPresent(section1 -> {
                    question.setSectionId(section1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            questionService.save(question);
            return new ResponseEntity<>(question.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Question>> getQuestion() {
        List<Question> questions = questionService.findAll();
        if(questions != null)
        {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
        return new ResponseEntity<>(questions, HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/bySection/{id}", produces = "application/json")
    public ResponseEntity<List<Question>> getSectionQuestions(@PathVariable("id") Integer sectionId) {
        Optional<Section> section = sectionService.findById(sectionId);

        if(section.isPresent())
        {
            List<Question> questions = questionService.findBySection(section.get());
            return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping(value = "/byProblem/{id}", produces = "application/json")
    public ResponseEntity<List<Question>> getQuestionsByProblem(@PathVariable("id") Integer problemId) {
        Optional<Problem> problem = problemService.findById(problemId);

        if(problem.isPresent())
        {
            List<Question> questions = questionService.findByProblemId(problem.get());
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/{questionId}/{sectionId}/{problemId}")
    public ResponseEntity<Void> deleteQuestion( @PathVariable("questionId") Integer questionId, @PathVariable("sectionId") Integer sectionId, @PathVariable("problemId") Integer problemId) {
        Optional<Section> section = sectionService.findById(sectionId);
        Optional<Problem> problem = problemService.findById(problemId);
        Optional<Question> question = questionService.findById(questionId);
        if(section.isPresent() ) {
            section.ifPresent(section1 -> {
                section1.getQuestions().remove(question.get());
            });
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        if(problem.isPresent() ) {
            problem.ifPresent(problem1 -> {
                problem1.getQuestions().remove(question.get());
            });
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        questionService.remove(questionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(value = "/qti", produces = "application/json")
    public FileSystemResource qti(@RequestBody QuestionDTO questionDTO, HttpServletRequest httpServletRequest)
    {
        Question question = questionService.findById(questionDTO.getId()).orElse(null);
        Answer correctAnswer = new Answer();
        String filePath = new File("").getAbsolutePath() + "\\src\\main\\java\\com\\backend\\xml\\xmlfile.xml";
        //String xmlFilePath = ".\\src\\main\\java\\com.backend\\xml\\xmlfile.xml";
        if(question!= null)
        {
            Set<Answer> answers = question.getAnswers();
            for(Answer a : answers)
            {
                if(a.getCorrect())
                {
                    correctAnswer = a;
                }
            }
            try{
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();

                document.createTextNode("\n");

                Element root = document.createElement("qti-assessment-item");

                Attr attr = document.createAttribute("xmlns");
                attr.setValue("http://www.imsglobal.org/xsd/qti/imsqtiasi_v3p0");
                root.setAttributeNode(attr);

                Attr attr1 = document.createAttribute("xmlns:xsi");
                attr1.setValue("http://www.w3.org/2001/XMLSchema-instance");
                root.setAttributeNode(attr1);

                Attr attr2 = document.createAttribute("xsi:schemaLocation");
                attr2.setValue("http://www.imsglobal.org/xsd/imsqtiasi_v3p0 https://purl.imsglobal.org/spec/qti/v3p0/schema/xsd/imsqti_asiv3p0_v1p0.xsd");
                root.setAttributeNode(attr2);

                Attr attr3 = document.createAttribute("identifier");
                attr3.setValue("firstexample");
                root.setAttributeNode(attr3);

                Attr attr4 = document.createAttribute("time-dependent");
                attr4.setValue("false");
                root.setAttributeNode(attr4);

                Attr attr5 = document.createAttribute("xml:lang");
                attr5.setValue("en-US");
                root.setAttributeNode(attr5);

                document.appendChild(root);

                document.createTextNode("\n");

                Element qtiDeclaration = document.createElement("qti-response-declaration");

                Attr attr6 = document.createAttribute("base-type");
                attr6.setValue("identifier");
                qtiDeclaration.setAttributeNode(attr6);

                Attr attr7 = document.createAttribute("cardinality");
                attr7.setValue("single");
                qtiDeclaration.setAttributeNode(attr7);

                Attr attr8 = document.createAttribute("identifier");
                attr8.setValue("RESPONSE");
                qtiDeclaration.setAttributeNode(attr8);

                root.appendChild(qtiDeclaration);
                document.createTextNode("\n");

                Element qtiResponse = document.createElement("qti-correct-response");

                qtiDeclaration.appendChild(qtiResponse);

                Element qtiValue = document.createElement("qti-value");
                qtiValue.appendChild(document.createTextNode(correctAnswer.getId().toString()));

                qtiResponse.appendChild(qtiValue);

                Element qtiOutcome = document.createElement("qti-outcome-declaration");

                Attr attr9 = document.createAttribute("base-type");
                attr9.setValue("integer");
                qtiOutcome.setAttributeNode(attr9);

                Attr attr10 = document.createAttribute("cardinality");
                attr10.setValue("single");
                qtiOutcome.setAttributeNode(attr10);

                Attr attr11 = document.createAttribute("identifier");
                attr11.setValue("SCORE");
                qtiOutcome.setAttributeNode(attr11);

                root.appendChild(qtiOutcome);

                Element qtiDefault = document.createElement("qti-default-value");
                qtiOutcome.appendChild(qtiDefault);

                Element qtiValue1 = document.createElement("qti-value");
                qtiValue1.appendChild(document.createTextNode(correctAnswer.getScore().toString()));

                qtiDefault.appendChild(qtiValue1);

                Element body = document.createElement("qti-item-body");

                root.appendChild(body);

                Element p = document.createElement("p");
                p.appendChild(document.createTextNode(question.getQuestionText()));

                body.appendChild(p);

                Element qtiChoices = document.createElement("qti-choice-interaction");

                Attr attr12 = document.createAttribute("max-choices");
                attr12.setValue("1");
                qtiChoices.setAttributeNode(attr12);

                Attr attr13 = document.createAttribute("min-choices");
                attr13.setValue("1");
                qtiChoices.setAttributeNode(attr13);

                Attr attr14 = document.createAttribute("response-identifier");
                attr14.setValue("RESPONSE");
                qtiChoices.setAttributeNode(attr14);

                body.appendChild(qtiChoices);

                for(Answer a : answers)
                {
                    Element qtiChoice = document.createElement("qti-simple-choice");

                    Attr attr15 = document.createAttribute("identifier");
                    attr15.setValue(a.getId().toString());
                    qtiChoice.setAttributeNode(attr15);

                    qtiChoice.appendChild(document.createTextNode(a.getAnswerText()));

                    qtiChoices.appendChild(qtiChoice);
                }

                Element qtiProcessing = document.createElement("qti-response-processing");

                Attr attr16 = document.createAttribute("template");
                attr16.setValue("https://purl.imsglobal.org/spec/qti/v3p0/rptemplates/match_correct");
                qtiProcessing.setAttributeNode(attr16);

                body.appendChild(qtiProcessing);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);

                StreamResult streamResult = new StreamResult(new File(filePath));

                transformer.transform(domSource, streamResult);

            }catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
        }
        return new FileSystemResource(filePath);
    }
}
