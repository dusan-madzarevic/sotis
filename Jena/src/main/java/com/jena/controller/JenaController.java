package com.jena.controller;

import com.jena.dto.TestDTO;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;
import org.apache.jena.rdfconnection.RDFConnectionRemoteBuilder;
import org.apache.jena.update.Update;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
