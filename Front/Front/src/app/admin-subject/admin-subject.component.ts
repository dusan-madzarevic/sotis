import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AdminServiceService} from '../services/admin-service.service';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-admin-subject',
  templateUrl: './admin-subject.component.html',
  styleUrls: ['./admin-subject.component.css']
})
export class AdminSubjectComponent implements OnInit {
  subjects: any = [];
  user: any = [];
  problems: any = [];
  allKnowledgeSpaces: any = [];
  knowledgeSpaces: any = [];
  knowledgeSpace: any = [];
  problem: any = [];
  subject: any = [];
  closeResult: string;
  ProblemForm: FormGroup;
  SubjectForm: FormGroup;
  KnowledgeSpaceForm: FormGroup;
  SurmiseForm: FormGroup;
  pretpostavke: any = [];
  pretpostavka: any = [];
  pretpostavkaProblems: any = [];

  constructor(private router: Router, private adminServiceService: AdminServiceService, private formBuilder: FormBuilder,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.subjects = [];
    this.ucitajPredmete();

    this.SubjectForm = this.formBuilder.group({
      name: [''],
      code: ['']
    });

    this.ProblemForm = this.formBuilder.group({
      subjectId: [''],
      name: [''],
      description: ['']
    });

    this.KnowledgeSpaceForm = this.formBuilder.group({
      name: [''],
      subjectId: ['']
    });

    this.SurmiseForm = this.formBuilder.group({
      knowledgeSpaceId: [''],
      problemId: ['']
    });

    this.ucitajSveProstoreZnanja();
  }

  // tslint:disable-next-line:typedef
  ucitajPredmete() {
    this.adminServiceService.getUser(localStorage.getItem('currentuser').toString())
      .pipe(first())
      .subscribe((data: {}) => {
          this.user = data;
          if (this.user.userType === 'Professor') {
            this.adminServiceService.getSubjectsByProfessor(this.user.id)
              .pipe(first())
              .subscribe(data1 => {
                this.subjects = data1;
              });
          }else {
            this.adminServiceService.getSubjects()
              .pipe(first())
              .subscribe(data1 => {
                this.subjects = data1;
              });
          }
        }
      );


  }

  // tslint:disable-next-line:typedef
  logout() {
    localStorage.removeItem('currentuser');
    this.router.navigate(['/']);
  }

  // tslint:disable-next-line:typedef
  otvoriProbleme(openCom, subject){
    this.subject = subject;
    this.problems = [];
    this.ucitajProbleme();

    this.modalService.open(openCom, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  otvoriProstorZnanja(knowledgeSpaceCom, subject){
    this.subject = subject;
    this.knowledgeSpaces = [];
    this.ucitajProstoreZnanja();

    this.modalService.open(knowledgeSpaceCom, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  ucitajProbleme() {
    this.adminServiceService.getProblemsBySubject(this.subject.id)
      .pipe(first())
      .subscribe(data => {
        this.problems = data;
      });
  }

  // tslint:disable-next-line:typedef
  ucitajProstoreZnanja() {
    this.adminServiceService.getKnowledgeSpaceBySubjectId(this.subject.id)
      .pipe(first())
      .subscribe(data => {
        this.knowledgeSpaces = data;
      });
  }

  // tslint:disable-next-line:typedef
  ucitajSveProstoreZnanja() {
    this.adminServiceService.getAllKnowledgeSpaces()
      .pipe(first())
      .subscribe(data => {
        this.allKnowledgeSpaces = data;
      });
  }

  // tslint:disable-next-line:typedef
  dodajProblem(subject, addProblem){
    this.subject = subject;
    this.modalService.open(addProblem, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }


  // tslint:disable-next-line:typedef
  dodajProstorZnanja(subject, addKnowledgeSpace){
    this.subject = subject;
    this.modalService.open(addKnowledgeSpace, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  createProblem() {
    this.ProblemForm.value.subjectId = this.subject.id;
    this.adminServiceService.kreirajProblem(JSON.stringify(this.ProblemForm.value))
      .pipe(first())
      .subscribe();
    this.problems = [];
    this.ucitajProbleme();
    this.modalService.dismissAll();
  }

  // tslint:disable-next-line:typedef
  createKnowledgeSpace() {
    this.KnowledgeSpaceForm.value.subjectId = this.subject.id;
    this.adminServiceService.kreirajProstorZnanja(JSON.stringify(this.KnowledgeSpaceForm.value))
      .pipe(first())
      .subscribe();
    this.knowledgeSpaces = [];
    this.ucitajProstoreZnanja();
    this.modalService.dismissAll();
  }

  // tslint:disable-next-line:typedef
  deleteSubject(subject) {
    this.adminServiceService.deleteSubject(subject.id)
      .pipe(first())
      .subscribe();
    this.subjects = [];
    this.ucitajPredmete();
    this.laganReload();
  }

  // tslint:disable-next-line:typedef
  noviPredmet(addSubject){
    this.adminServiceService.getUser(localStorage.getItem('currentuser').toString())
      .pipe(first())
      .subscribe((data: {}) => {
        this.user = data;
        if (this.user.userType === 'Administrator') {
          this.modalService.open(addSubject, {size: 'xl'}).result.then((result) => {
            this.closeResult = `Closed with: ${result}`;
          }, (reason) => {
            this.closeResult = `Dismissed`;
          });
        }
      });
  }

  // tslint:disable-next-line:typedef
  createSubject() {
    this.adminServiceService.kreirajPredmet(JSON.stringify(this.SubjectForm.value))
      .pipe(first())
      .subscribe();
    this.subjects = [];
    this.ucitajPredmete();
    this.modalService.dismissAll();
    this.laganReload();
  }

  // tslint:disable-next-line:typedef
  laganReload(){
    window.setTimeout(
      // tslint:disable-next-line:only-arrow-functions typedef
      function() {
        location.reload(true);
      },
      500
    );
  }

  // tslint:disable-next-line:typedef
  deleteProblem(problem) {
    this.adminServiceService.deleteProblem(problem.id, this.subject.id)
      .pipe(first())
      .subscribe();
    this.problems = [];
    this.ucitajProbleme();
    this.modalService.dismissAll();
  }

  // tslint:disable-next-line:typedef
  deletePretpostavka(pretpostavka) {
    this.adminServiceService.deletePretpostavka(pretpostavka.id, pretpostavka.knowledgeSpaceId, pretpostavka.problemId)
      .pipe(first())
      .subscribe();
    this.pretpostavke = [];
    this.ucitajPretpostavke();
    this.modalService.dismissAll();
  }

  // tslint:disable-next-line:typedef
  deleteKnowledgeSpace(knowledgeSpace) {
    this.adminServiceService.deleteKnowledgeSpace(knowledgeSpace.id, this.subject.id)
      .pipe(first())
      .subscribe();
    this.knowledgeSpaces = [];
    this.ucitajProstoreZnanja();
    this.modalService.dismissAll();
  }

  // tslint:disable-next-line:typedef
  otvoriPretpostavku(openCom1, problem){
    this.problem = problem;
    this.pretpostavke = [];
    this.ucitajPretpostavke();

    this.modalService.open(openCom1, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  otvoriPretpostavljeneProbleme(openCom2, pretpostavka){
    this.pretpostavka = pretpostavka;
    this.pretpostavkaProblems = pretpostavka.problems;
    console.log(this.pretpostavkaProblems);

    this.modalService.open(openCom2, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  otvoriPretpostavkuByKnowledgeSpace(openCom1, knowledgeSpace){
    this.knowledgeSpace = knowledgeSpace;
    this.pretpostavke = [];
    this.ucitajPretpostavkeByKnowledgeSpace();

    this.modalService.open(openCom1, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  ucitajPretpostavke() {
    this.adminServiceService.getSurmiseByProblemId(this.problem.id)
      .pipe(first())
      .subscribe(data => {
        this.pretpostavke = data;
      });
  }

  // tslint:disable-next-line:typedef
  ucitajPretpostavkeByKnowledgeSpace() {
    this.adminServiceService.getSurmiseByKnowledgeSpaceId(this.knowledgeSpace.id)
      .pipe(first())
      .subscribe(data => {
        this.pretpostavke = data;
      });
  }

  // tslint:disable-next-line:typedef
  dodajPretpostavku(problem, addSurmiseByProblem){
    console.log(this.allKnowledgeSpaces);
    this.problem = problem;
    this.modalService.open(addSurmiseByProblem, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  createSurmiseByProblem() {
    this.SurmiseForm.value.problemId = this.problem.id;
    this.adminServiceService.kreirajPretpostavku(JSON.stringify(this.SurmiseForm.value))
      .pipe(first())
      .subscribe();
    this.pretpostavke = [];
    this.ucitajPretpostavke();
    this.modalService.dismissAll();
  }

  dodajProstorZnanjaGraficki(subject: any) {
    this.router.navigate(["/knowledgeSpaceAdmin"]);
  }
}
