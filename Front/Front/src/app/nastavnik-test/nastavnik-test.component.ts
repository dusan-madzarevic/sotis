import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {NastavnikServiceService} from '../services/nastavnik-service.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-nastavnik-test',
  templateUrl: './nastavnik-test.component.html',
  styleUrls: ['./nastavnik-test.component.css']
})
export class NastavnikTestComponent implements OnInit {
  testovi: any = [];
  closeResult: string;
  TestForm: FormGroup;
  SectionForm: FormGroup;
  QuestionForm: FormGroup;
  AnswerForm: FormGroup;

  test: any = [];
  sekcije: any = [];

  pitanja: any = [];
  sekcija: any = [];

  odgovori: any = [];

  pitanje: any = [];
  odgovor: any = [];

  myVar1 = false;
  constructor(private router: Router, private nastavnikService: NastavnikServiceService, private formBuilder: FormBuilder,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.testovi = [];
    this.ucitajTestove();

    this.TestForm = this.formBuilder.group({
      title: [''],
      maxScore: [''],
      passPercentage: [''],
      username: [''],
      subjectId: ['']
    });

    this.SectionForm = this.formBuilder.group({
      name: [''],
      testId: ['']
    });

    this.QuestionForm = this.formBuilder.group({
      sectionId: [''],
      questionText: [''],
      score: ['']
    });

    this.AnswerForm = this.formBuilder.group({
      id: [''],
      questionId: [''],
      answerText: [''],
      correct: [''],
      score: ['']
    });
  }

  // tslint:disable-next-line:typedef
  ucitajTestove() {
    this.nastavnikService.getTestoviProfesora(localStorage.getItem('currentuser').toString())
      .pipe(first())
      .subscribe(data => {
        this.testovi = data;
      });
  }

  // tslint:disable-next-line:typedef
  logout() {
    localStorage.removeItem('currentuser');
    this.router.navigate(['/']);
  }

  // tslint:disable-next-line:typedef
  delete(test) {
    this.nastavnikService.deleteTest(test.id)
      .pipe(first())
      .subscribe();
    this.testovi = [];
    this.ucitajTestove();
  }

  // tslint:disable-next-line:typedef
  noviTest(addTest){
    this.modalService.open(addTest, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  create() {
    this.TestForm.value.username = localStorage.getItem('currentuser').toString();
    this.nastavnikService.kreirajTest(JSON.stringify(this.TestForm.value))
      .pipe(first())
      .subscribe();
    this.testovi = [];
    this.ucitajTestove();
    this.modalService.dismissAll();
    this.laganReload();
  }

  // tslint:disable-next-line:typedef
  dodajSekciju(test, addSection){
    this.test = test;
    this.modalService.open(addSection, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  createSekciju() {
    this.SectionForm.value.testId = this.test.id;
    this.nastavnikService.kreirajSekciju(JSON.stringify(this.SectionForm.value))
      .pipe(first())
      .subscribe();
    this.sekcije = [];
    this.ucitajSekcije();
    this.modalService.dismissAll();
  }

  // tslint:disable-next-line:typedef
  dodajPitanje(sekcija, addQuestion){
    this.sekcija = sekcija;
    this.modalService.open(addQuestion, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  createPitanje() {
    this.QuestionForm.value.sectionId = this.sekcija.id;
    this.nastavnikService.kreirajPitanje(JSON.stringify(this.QuestionForm.value))
      .pipe(first())
      .subscribe();
    this.pitanja = [];
    this.ucitajPitanja();
    this.modalService.dismissAll();
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
  otvoriSekcije(openCom, test){
    this.test = test;
    this.sekcije = [];
    this.ucitajSekcije();

    this.modalService.open(openCom, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  ucitajSekcije() {
    this.nastavnikService.getSekcije(this.test.id)
      .pipe(first())
      .subscribe(data => {
        this.sekcije = data;
      });
  }

  // tslint:disable-next-line:typedef
  deleteSekcija(sekcija) {
    this.nastavnikService.deleteSekcija(sekcija.id, this.test.id)
      .pipe(first())
      .subscribe();
    this.sekcije = [];
    this.ucitajSekcije();
    this.modalService.dismissAll();
  }

  // tslint:disable-next-line:typedef
  otvoriPitanja(openCom, sekcija){
    this.sekcija = sekcija;
    this.pitanja = [];
    this.ucitajPitanja();

    this.modalService.open(openCom, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  ucitajPitanja() {
    this.nastavnikService.getPitanja(this.sekcija.id)
      .pipe(first())
      .subscribe(data => {
        this.pitanja = data;
      });
  }

  // tslint:disable-next-line:typedef
  deletePitanje(pitanje) {
    this.pitanje = pitanje;
    this.nastavnikService.obrisiPitanje(pitanje.id, pitanje.sectionId, pitanje.problemId)
      .pipe(first())
      .subscribe();
    this.pitanja = [];
    this.ucitajPitanja();
    this.modalService.dismissAll();
  }

  // tslint:disable-next-line:typedef
  deleteOdgovor(odgovor) {
    this.odgovor = odgovor;
    this.nastavnikService.deleteOdgovor(odgovor.id, odgovor.questionId)
      .pipe(first())
      .subscribe();
    this.odgovori = [];
    this.ucitajOdgovore();
    this.modalService.dismissAll();
  }

  // tslint:disable-next-line:typedef
  ucitajOdgovore() {
    this.nastavnikService.getOdgovori(this.pitanje.id)
      .pipe(first())
      .subscribe(data => {
        this.odgovori = data;
      });
  }

  // tslint:disable-next-line:typedef
  prikaziOdgovore(openCom2, pitanje){
    this.pitanje = pitanje;
    this.odgovori = [];
    this.ucitajOdgovore();

    this.modalService.open(openCom2, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  dodajOdgovor(pitanje, addQuestion){
    this.pitanje = pitanje;
    this.modalService.open(addQuestion, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  createOdgovor() {
    this.AnswerForm.value.questionId = this.pitanje.id;
    this.AnswerForm.value.correct = this.myVar1;
    this.nastavnikService.kreirajOdgovor(JSON.stringify(this.AnswerForm.value))
      .pipe(first())
      .subscribe();
    this.odgovori = [];
    this.ucitajOdgovore();
    this.modalService.dismissAll();
  }

  // tslint:disable-next-line:typedef
  preuzmiRezultate(id) {
    this.nastavnikService.preuzmiRezultate(id).subscribe(res => {
      const blob = new Blob([res], { type: 'application/octet-stream' });
      const url = window.URL.createObjectURL(blob);
      const anchor = document.createElement('a');
      anchor.download = 'testResults' + id + '.csv';
      anchor.href = url;
      anchor.click();
    });
  }

  obradiRezultate(id) {
    this.nastavnikService.preuzmiRezultateJson(id).subscribe(res => {
        console.log(res);
        this.nastavnikService.iitaObradaRezultata(res).subscribe(response => {

        console.log('Pozvan IITA algoritam');
      });

     });
  }
}
