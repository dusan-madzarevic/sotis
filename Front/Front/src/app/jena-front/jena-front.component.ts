import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {NastavnikServiceService} from '../services/nastavnik-service.service';
import {KnowledgeSpaceService} from '../services/knowledge-space.service';
import {FormBuilder} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AdminServiceService} from '../services/admin-service.service';
import {first} from 'rxjs/operators';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-jena-front',
  templateUrl: './jena-front.component.html',
  styleUrls: ['./jena-front.component.css']
})
export class JenaFrontComponent implements OnInit {
  queryText = ""
  resultText = ""
  actionInput1 = ""
  actionInput2 = ""
  actionInput3 = ""
  actionInput4 = ""
  actionInput5 = ""
  actionInput6 = 0
  actionInput7 = 0
  constructor(private router: Router, private adminService: AdminServiceService, private formBuilder: FormBuilder,
              private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  akcija1(){

    this.adminService.coursesWithSubject(this.actionInput1)
      .pipe(first())
      .subscribe(data => {
        this.queryText = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "prefix rdf-ns: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
        "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
        "prefix sotis: <http://www.semanticweb.org/dusan/ontologies/2021/11/sotis-model#>\n" +
        "prefix sem_web: <http://www.semanticweb.org/dusan/ontologies/2021/10/SEM_ONT#>\n" +
        "prefix aiiso: <http://purl.org/vocab/aiiso/schema#>\n" +
        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
        "select ?Naziv_predmeta ?ESPB "+
        "{\n" +
        "  ?Naziv_predmeta sem_web:contains_Subject sem_web:subjectName.\n" +
        "  ?Naziv_predmeta sem_web:espb ?ESPB.\n" +
        "}";

        this.resultText = data;
        
      });
  }

  // tslint:disable-next-line:typedef
  akcija2(){
    this.adminService.professorsForCourse(this.actionInput2)
      .pipe(first())
      .subscribe(data => {
        this.queryText = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "prefix rdf-ns: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
        "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
        "prefix sotis: <http://www.semanticweb.org/dusan/ontologies/2021/11/sotis-model#>\n" +
        "prefix sem_web: <http://www.semanticweb.org/dusan/ontologies/2021/10/SEM_ONT#>\n" +
        "prefix aiiso: <http://purl.org/vocab/aiiso/schema#>\n" +
        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
        "select ?Professor_name "+
        "{ ?Professor_name sotis:teaches sem_web:courseName}";

        this.resultText = data;
      });
  }

  // tslint:disable-next-line:typedef
  akcija3(){
    this.adminService.studentsOnCourse(this.actionInput3)
      .pipe(first())
      .subscribe(data => {
        this.queryText = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "prefix rdf-ns: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
        "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
        "prefix sotis: <http://www.semanticweb.org/dusan/ontologies/2021/11/sotis-model#>\n" +
        "prefix sem_web: <http://www.semanticweb.org/dusan/ontologies/2021/10/SEM_ONT#>\n" +
        "prefix aiiso: <http://purl.org/vocab/aiiso/schema#>\n" +
        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
        "select ?Student_Name\n" +
                "WHERE\n" +
                "{\n" +
                "  ?Student_Name rdf-ns:type sotis:Student.\n" +
                "  ?Student_Name sem_web:takes_Course sem_web:courseName\n" +
                "  \n" +
                "}"

        this.resultText = data;
      });
  }

  // tslint:disable-next-line:typedef
  akcija4(){
    this.adminService.testsForCourse(this.actionInput4)
      .pipe(first())
      .subscribe(data => {
        this.queryText = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "prefix rdf-ns: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
        "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
        "prefix sotis: <http://www.semanticweb.org/dusan/ontologies/2021/11/sotis-model#>\n" +
        "prefix sem_web: <http://www.semanticweb.org/dusan/ontologies/2021/10/SEM_ONT#>\n" +
        "prefix aiiso: <http://purl.org/vocab/aiiso/schema#>\n" +
        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
        "select ?Test_Name\n" +
                "WHERE\n" +
                "{\n" +
                "\t?Test_Name rdf-ns:type sotis:Test.\n" +
                "\t?Test_Name sem_web:test_Course sem_web:courseName\n" +
                "} "
        this.resultText = data;
      });
  }

  // tslint:disable-next-line:typedef
  akcija5(){
    this.adminService.coursesForProfessor(this.actionInput5)
      .pipe(first())
      .subscribe(data => {
        this.queryText = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "prefix rdf-ns: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
        "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
        "prefix sotis: <http://www.semanticweb.org/dusan/ontologies/2021/11/sotis-model#>\n" +
        "prefix sem_web: <http://www.semanticweb.org/dusan/ontologies/2021/10/SEM_ONT#>\n" +
        "prefix aiiso: <http://purl.org/vocab/aiiso/schema#>\n" +
        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
        "select ?Professor_Name "+
                "{ sem_web:professorName sotis:teaches ?Professor_Name}";

        this.resultText = data;
      });
  }

  // tslint:disable-next-line:typedef
  akcija6(){
    this.adminService.best3fortest(this.actionInput6)
      .pipe(first())
      .subscribe(data => {
        this.queryText = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "prefix rdf-ns: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
        "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
        "prefix sotis: <http://www.semanticweb.org/dusan/ontologies/2021/11/sotis-model#>\n" +
        "prefix sem_web: <http://www.semanticweb.org/dusan/ontologies/2021/10/SEM_ONT#>\n" +
        "prefix aiiso: <http://purl.org/vocab/aiiso/schema#>\n" +
        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
        "select ?Student_name ?Points\n" +
                "{\n" +
                "  ?x rdf-ns:type sotis:Test.\n" +
                "  ?x sem_web:id testId.\n" +
                "  ?y sem_web:testTaken ?x.\n" +
                "  ?y sem_web:questionee ?Student_name.\n" +
                "  ?y sem_web:points ?Points\n" +
                "} ORDER BY DESC (?Points) LIMIT 3"

        this.resultText = data;
      });
  }

  // tslint:disable-next-line:typedef
  akcija7(){
    this.adminService.studentsThatFailedTest(this.actionInput7)
      .pipe(first())
      .subscribe(data => {
        this.queryText = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "prefix rdf-ns: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
        "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
        "prefix sotis: <http://www.semanticweb.org/dusan/ontologies/2021/11/sotis-model#>\n" +
        "prefix sem_web: <http://www.semanticweb.org/dusan/ontologies/2021/10/SEM_ONT#>\n" +
        "prefix aiiso: <http://purl.org/vocab/aiiso/schema#>\n" +
        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
        "select ?Student_Name\n" +
                "WHERE\n" +
                "{\n" +
                "  ?Student_Name rdf-ns:type sotis:Student.\n" +
                "  ?attempt rdf-ns:type sotis:TestAttempt.\n" +
                "  ?attempt sem_web:questionee ?Student_Name.\n" +
                "  ?test rdf-ns:type sotis:Test.\n" +
                "  ?test sem_web:id testId.\n" +
                "  ?attempt sem_web:testTaken ?test.\n" +
                "  ?attempt sem_web:points ?points.\n" +
                "  ?test sem_web:maxPoints ?max_points\n" +
                "  FILTER (?points < ?max_points/2)\n" +
                "  \n" +
                "} "

        this.resultText = data;
      });
  }

  // tslint:disable-next-line:typedef
  akcija8(){
    this.adminService.coursesWithMoreProfs()
      .pipe(first())
      .subscribe(data => {
        this.queryText = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "prefix rdf-ns: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
        "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
        "prefix sotis: <http://www.semanticweb.org/dusan/ontologies/2021/11/sotis-model#>\n" +
        "prefix sem_web: <http://www.semanticweb.org/dusan/ontologies/2021/10/SEM_ONT#>\n" +
        "prefix aiiso: <http://purl.org/vocab/aiiso/schema#>\n" +
        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
        "select ?Course_Name (COUNT(*) as ?count)\n" +
                "WHERE\n" +
                "{\n" +
                "\t?Course_Name rdf-ns:type aiiso:Course.\n" +
                "  \t?prof sotis:teaches ?Course_Name\n" +
                "} group by ?Course_Name\n" +
                "having (?count > 1)"

        this.resultText = data;
      });
  }

  // tslint:disable-next-line:typedef
  akcija9(){
    this.adminService.hardCourses()
      .pipe(first())
      .subscribe(data => {
        this.queryText = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "prefix rdf-ns: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
        "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
        "prefix sotis: <http://www.semanticweb.org/dusan/ontologies/2021/11/sotis-model#>\n" +
        "prefix sem_web: <http://www.semanticweb.org/dusan/ontologies/2021/10/SEM_ONT#>\n" +
        "prefix aiiso: <http://purl.org/vocab/aiiso/schema#>\n" +
        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
        "select ?Course_Name ?espb\n" +
                "WHERE\n" +
                "{\n" +
                "\t?Course_Name rdf-ns:type aiiso:Course.\n" +
                "  \t?Course_Name sem_web:espb ?espb.\n" +
                "  FILTER (xsd:integer(?espb) > 7)\n" +
                "} "

        this.resultText = data;
      });
  }

  // tslint:disable-next-line:typedef
  akcija10(){
    this.adminService.studentsPassedAll()
      .pipe(first())
      .subscribe(data => {
        this.queryText = "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "prefix rdf-ns: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
        "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
        "prefix sotis: <http://www.semanticweb.org/dusan/ontologies/2021/11/sotis-model#>\n" +
        "prefix sem_web: <http://www.semanticweb.org/dusan/ontologies/2021/10/SEM_ONT#>\n" +
        "prefix aiiso: <http://purl.org/vocab/aiiso/schema#>\n" +
        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
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
                "having(?count = ?test_count)"
        this.resultText = data;
      });
  }

}
