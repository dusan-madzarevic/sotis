package com.jena.controller;

import com.jena.dto.TestDTO;
import org.apache.jena.query.*;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;
import org.apache.jena.rdfconnection.RDFConnectionRemoteBuilder;
import org.apache.jena.update.Update;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@CrossOrigin
@RequestMapping(value = "/jena")
public class JenaController {

    @Value("${sotis.prefix}")
    private String sotisPrefix;

    @Value("${rdfs.prefix}")
    private String rdfsPrefix;

    @Value("${rdfs.ns.prefix}")
    private String rdfNSPrefix;

    @Value("${owl.prefix}")
    private String owlPrefix;

    @Value("${sem.web.prefix}")
    private String semWebPrefix;

    @Value("${aiiso.prefix}")
    private String aiisoPrefix;

    @PostMapping(value = "/saveTest")
    public String insertTest(@RequestBody TestDTO dto){

        RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create()
                .destination("http://localhost:3030/Education/update");

        System.out.println(dto.getTestName());
        System.out.println("Ovde sam!");

        UpdateRequest u = UpdateFactory.create("prefix sotis: "+sotisPrefix+
                "prefix rdfs: "+rdfsPrefix+
                "prefix rdf-ns: "+rdfNSPrefix+
                "prefix owl: "+owlPrefix+
                "prefix sem_web: "+semWebPrefix+
                "INSERT DATA { sotis:"+dto.getTestName()+" rdf-ns:type owl:NamedIndividual;" +
                "                                          rdf-ns:type sotis:Test  };");

        // In this variation, a connection is built each time.
        try ( RDFConnectionFuseki conn = (RDFConnectionFuseki)builder.build() ) {
            conn.update(u);
        }

        return "Success";
    }

    @GetMapping(value = "/coursesForProfessor")
    public String getCoursesForProfessor(@RequestParam String professorName){

        System.out.println(professorName);
        System.out.println("Ovde sam!");

        QueryExecution q = QueryExecutionFactory.sparqlService("http://localhost:3030/Education","prefix sotis: "+sotisPrefix+
                "prefix rdfs: "+rdfsPrefix+
                "prefix rdf-ns: "+rdfNSPrefix+
                "prefix owl: "+owlPrefix+
                "prefix sem_web: "+semWebPrefix+
                "select ?Professor_Name "+
                "{ sem_web:"+professorName.replace(" ","_")+" sotis:teaches ?Professor_Name}");

        ResultSet results = q.execSelect();
        String output = ResultSetFormatter.asText(results);
        q.close();
        return output.replace('<',' ').replace('>',' ');
    }

    @GetMapping(value = "/coursesWithSubject")
    public String getCoursesWithSubject(@RequestParam String subjectName){

        RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create()
                .destination("http://localhost:3030/Education");

        System.out.println(subjectName);
        System.out.println("Ovde sam!");

        QueryExecution q = QueryExecutionFactory.sparqlService("http://localhost:3030/Education", "prefix sotis: "+sotisPrefix+
                "prefix rdfs: "+rdfsPrefix+
                "prefix rdf-ns: "+rdfNSPrefix+
                "prefix owl: "+owlPrefix+
                "prefix sem_web: "+semWebPrefix+
                "select ?Naziv_predmeta ?ESPB "+
                "{\n" +
                "  ?Naziv_predmeta sem_web:contains_Subject sem_web:"+subjectName+".\n" +
                "  ?Naziv_predmeta sem_web:espb ?ESPB.\n" +
                "}");

        ResultSet results = q.execSelect();
        String output = ResultSetFormatter.asText(results);
        q.close();
        return output.replace('<',' ').replace('>',' ');
    }

    @GetMapping(value = "/professorsForCourse")
    public String getProfessorsForCourse(@RequestParam String courseName){


        System.out.println(courseName);
        System.out.println("Ovde sam!");

        QueryExecution q = QueryExecutionFactory.sparqlService("http://localhost:3030/Education","prefix sotis: "+sotisPrefix+
                "prefix rdfs: "+rdfsPrefix+
                "prefix rdf-ns: "+rdfNSPrefix+
                "prefix owl: "+owlPrefix+
                "prefix sem_web: "+semWebPrefix+
                "select ?Professor_name "+
                "{ ?Professor_name sotis:teaches sem_web:"+courseName.replace(" ","_")+"}");

        ResultSet results = q.execSelect();
        String output = ResultSetFormatter.asText(results);
        q.close();
        return output.replace('<',' ').replace('>',' ');
    }

    @GetMapping(value = "/best3fortest")
    public String getBest3forTest(@RequestParam int testId){

        System.out.println(testId);
        System.out.println("Ovde sam!");

        QueryExecution q = QueryExecutionFactory.sparqlService("http://localhost:3030/Education","prefix sotis: "+sotisPrefix+
                "prefix rdfs: "+rdfsPrefix+
                "prefix rdf-ns: "+rdfNSPrefix+
                "prefix owl: "+owlPrefix+
                "prefix sem_web: "+semWebPrefix+
                "select ?Student_name ?Points\n" +
                "{\n" +
                "  ?x rdf-ns:type sotis:Test.\n" +
                "  ?x sem_web:id "+testId+".\n" +
                "  ?y sem_web:testTaken ?x.\n" +
                "  ?y sem_web:questionee ?Student_name.\n" +
                "  ?y sem_web:points ?Points\n" +
                "} ORDER BY DESC (?Points) LIMIT 3");

        ResultSet results = q.execSelect();
        String output = ResultSetFormatter.asText(results);
        q.close();
        return output.replace('<',' ').replace('>',' ');
    }

    @GetMapping(value = "/studentsOnCourse")
    public String getStudentsOnCourse(@RequestParam String courseName){

        System.out.println(courseName);
        System.out.println("Ovde sam!");

        QueryExecution q = QueryExecutionFactory.sparqlService("http://localhost:3030/Education","prefix sotis: "+sotisPrefix+
                "prefix rdfs: "+rdfsPrefix+
                "prefix rdf-ns: "+rdfNSPrefix+
                "prefix owl: "+owlPrefix+
                "prefix sem_web: "+semWebPrefix+
                "select ?Student_Name\n" +
                "WHERE\n" +
                "{\n" +
                "  ?Student_Name rdf-ns:type sotis:Student.\n" +
                "  ?Student_Name sem_web:takes_Course sem_web:"+courseName+"\n" +
                "  \n" +
                "}");

        ResultSet results = q.execSelect();
        String output = ResultSetFormatter.asText(results);
        q.close();
        return output.replace('<',' ').replace('>',' ');
    }

    @GetMapping(value = "/studentsThatFailedTest")
    public String getStudentsThatFailedTest(@RequestParam int testId){

        System.out.println(testId);
        System.out.println("Ovde sam!");

        QueryExecution q = QueryExecutionFactory.sparqlService("http://localhost:3030/Education","prefix sotis: "+sotisPrefix+
                "prefix rdfs: "+rdfsPrefix+
                "prefix rdf-ns: "+rdfNSPrefix+
                "prefix owl: "+owlPrefix+
                "prefix sem_web: "+semWebPrefix+
                "select ?Student_Name\n" +
                "WHERE\n" +
                "{\n" +
                "  ?Student_Name rdf-ns:type sotis:Student.\n" +
                "  ?attempt rdf-ns:type sotis:TestAttempt.\n" +
                "  ?attempt sem_web:questionee ?Student_Name.\n" +
                "  ?test rdf-ns:type sotis:Test.\n" +
                "  ?test sem_web:id "+testId+".\n" +
                "  ?attempt sem_web:testTaken ?test.\n" +
                "  ?attempt sem_web:points ?points.\n" +
                "  ?test sem_web:maxPoints ?max_points\n" +
                "  FILTER (?points < ?max_points/2)\n" +
                "  \n" +
                "} ");

        ResultSet results = q.execSelect();
        String output = ResultSetFormatter.asText(results);
        q.close();
        return output.replace('<',' ').replace('>',' ');
    }

    @GetMapping(value = "/testsForCourse")
    public String getTestsForCourse(@RequestParam String courseName){

        System.out.println(courseName);
        System.out.println("Ovde sam!");

        QueryExecution q = QueryExecutionFactory.sparqlService("http://localhost:3030/Education","prefix sotis: "+sotisPrefix+
                "prefix rdfs: "+rdfsPrefix+
                "prefix rdf-ns: "+rdfNSPrefix+
                "prefix owl: "+owlPrefix+
                "prefix sem_web: "+semWebPrefix+
                "select ?Test_Name\n" +
                "WHERE\n" +
                "{\n" +
                "\t?Test_Name rdf-ns:type sotis:Test.\n" +
                "\t?Test_Name sem_web:test_Course sem_web:"+courseName+"\n" +
                "} ");

        ResultSet results = q.execSelect();
        String output = ResultSetFormatter.asText(results);
        q.close();
        return output.replace('<',' ').replace('>',' ');
    }

    @GetMapping(value = "/coursesWithMoreProfs")
    public String getCoursesWithMoreProfs(){

        System.out.println("Ovde sam!");

        QueryExecution q = QueryExecutionFactory.sparqlService("http://localhost:3030/Education","prefix sotis: "+sotisPrefix+
                "prefix rdfs: "+rdfsPrefix+
                "prefix rdf-ns: "+rdfNSPrefix+
                "prefix owl: "+owlPrefix+
                "prefix sem_web: "+semWebPrefix+
                "prefix aiiso: "+aiisoPrefix+
                "select ?Course_Name (COUNT(*) as ?count)\n" +
                "WHERE\n" +
                "{\n" +
                "\t?Course_Name rdf-ns:type aiiso:Course.\n" +
                "  \t?prof sotis:teaches ?Course_Name\n" +
                "} group by ?Course_Name\n" +
                "having (?count > 1)");

        ResultSet results = q.execSelect();
        String output = ResultSetFormatter.asText(results);
        q.close();
        return output.replace('<',' ').replace('>',' ');
    }

    @GetMapping(value = "/hardCourses")
    public String getHardCourses(){

        System.out.println("Ovde sam!");

        QueryExecution q = QueryExecutionFactory.sparqlService("http://localhost:3030/Education","prefix sotis: "+sotisPrefix+
                "prefix rdfs: "+rdfsPrefix+
                "prefix rdf-ns: "+rdfNSPrefix+
                "prefix owl: "+owlPrefix+
                "prefix sem_web: "+semWebPrefix+
                "prefix aiiso: <http://purl.org/vocab/aiiso/schema#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                "select ?Course_Name ?espb\n" +
                "WHERE\n" +
                "{\n" +
                "\t?Course_Name rdf-ns:type aiiso:Course.\n" +
                "  \t?Course_Name sem_web:espb ?espb.\n" +
                "  FILTER (xsd:integer(?espb) > 7)\n" +
                "} ");

        ResultSet results = q.execSelect();
        String output = ResultSetFormatter.asText(results);
        q.close();
        return output.replace('<',' ').replace('>',' ');
    }

    @GetMapping(value = "/studentsPassedAll")
    public String getStudentsPassedAll(){

        System.out.println("Ovde sam!");

        QueryExecution q = QueryExecutionFactory.sparqlService("http://localhost:3030/Education","prefix sotis: "+sotisPrefix+
                "prefix rdfs: "+rdfsPrefix+
                "prefix rdf-ns: "+rdfNSPrefix+
                "prefix owl: "+owlPrefix+
                "prefix sem_web: "+semWebPrefix+
                "prefix aiiso: "+aiisoPrefix+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                "select ?Student_Name (count(*) as ?count) \n" +
                "WHERE\n" +
                "{\n" +
                "  {  select (count(?allTests) as ?test_count)\n" +
                "  \tWHERE{\n" +
                "\n" +
                "    ?allTests rdf-ns:type sotis:Test\n" +
                "\n" +
                "  }}\n" +
                "\t?Student_Name rdf-ns:type sotis:Student.\n" +
                "  \t?attempt sem_web:questionee ?Student_Name.\n" +
                "  \t?attempt sem_web:testTaken ?test.\n" +
                "  \t?attempt sem_web:points ?points.\n" +
                "  \t?test sem_web:maxPoints ?maxPoints\n" +
                "  FILTER (?points > (?maxPoints / 2))\n" +
                "\n" +
                "} group by ?Student_Name ?test_count\n" +
                "having(?count = ?test_count)");

        ResultSet results = q.execSelect();
        String output = ResultSetFormatter.asText(results);
        q.close();
        return output.replace('<',' ').replace('>',' ');
    }

    @GetMapping(value = "/profsLowPassRate")
    public String getProfsLowPassRate(){

        System.out.println("Ovde sam!");

        QueryExecution q = QueryExecutionFactory.sparqlService("http://localhost:3030/Education","prefix sotis: "+sotisPrefix+
                "prefix rdfs: "+rdfsPrefix+
                "prefix rdf-ns: "+rdfNSPrefix+
                "prefix owl: "+owlPrefix+
                "prefix sem_web: "+semWebPrefix+
                "prefix aiiso: <http://purl.org/vocab/aiiso/schema#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                "select ?Course_Name ?espb\n" +
                "WHERE\n" +
                "{\n" +
                "\t?Course_Name rdf-ns:type aiiso:Course.\n" +
                "  \t?Course_Name sem_web:espb ?espb.\n" +
                "  FILTER (xsd:integer(?espb) > 7)\n" +
                "} ");

        ResultSet results = q.execSelect();
        String output = ResultSetFormatter.asText(results);

        return output.replace('<',' ').replace('>',' ');
    }

}