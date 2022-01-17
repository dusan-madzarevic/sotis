import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {
  sastavnica: any = [];
  stavkaSas: any = [];
  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef
  login(user) {
    return this.http.post('http://localhost:8090/user/login', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  getUser(username) {
    return this.http.get('http://localhost:8090/user/' + username,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  getSastavnica(){
    return this.sastavnica;
  }

  // tslint:disable-next-line:typedef
  registrujAdmina(user) {
    return this.http.post('http://localhost:8090/administrator', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujStudent(user) {
    return this.http.post('http://localhost:8090/student', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujProfessor(user) {
    return this.http.post('http://localhost:8090/professor', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  getSubjects() {
    return this.http.get('http://localhost:8090/subject',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  getProblemsBySubject(id) {
    return this.http.get('http://localhost:8090/problem/bySubject/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  getSubjectsByProfessor(id) {
    return this.http.get('http://localhost:8090/subject/byProfessor/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  getKnowledgeSpaceBySubjectId(id) {
    return this.http.get('http://localhost:8090/knowledgeSpace/bySubject/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  getAllKnowledgeSpaces() {
    return this.http.get('http://localhost:8090/knowledgeSpace',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  getAllProblems() {
    return this.http.get('http://localhost:8090/problem',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  getAllQuestions() {
    return this.http.get('http://localhost:8090/question',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  getTipSastavnica() {
    return this.http.get('http://localhost:8090/tip_sastavnica',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  getMaterijali() {
    return this.http.get('http://localhost:8090/materijal',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }


  // tslint:disable-next-line:typedef
  deleteKancelarija(id) {
    return this.http.delete('http://localhost:8090/kancelarija/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  deleteMagacin(id) {
    return this.http.delete('http://localhost:8090/magacin/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  deletePogon(id) {
    return this.http.delete('http://localhost:8090/pogon/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  deleteStavkaRN(id, id1) {
    return this.http.delete('http://localhost:8090/stavka_rn/' + id + '/' + id1,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  deleteStavkaSas(id, id1) {
    return this.http.delete('http://localhost:8090/stavka_sas/' + id + '/' + id1,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujKancelariju(user) {
    return this.http.post('http://localhost:8090/kancelarija', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujMaterijal(user) {
    return this.http.post('http://localhost:8090/materijal', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujTipSastavnica(user) {
    return this.http.post('http://localhost:8090/tip_sastavnica', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  dodajPodsastavnicu(user, id) {
    return this.http.put('http://localhost:8090/sastavnica/' + id + '/rekurzija', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  dodajVezu(user, id , id1) {
    return this.http.put('http://localhost:8090/stavka_rn/' + id + '/' + id1 + '/srnssas', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  getSPs() {
    return this.http.get('http://localhost:8090/sektor_pogona',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  deleteSp(id) {
    return this.http.delete('http://localhost:8090/sektor_pogona/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujMontazu(user) {
    return this.http.post('http://localhost:8090/montaza', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  getTipoviMultipola() {
    return this.http.get('http://localhost:8090/tos_multipol',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  registrujTOS(user) {
    return this.http.post('http://localhost:8090/tip_os', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  getTipoviTestova() {
    return this.http.get('http://localhost:8090/tip_test',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  kreirajProblem(problem) {
    return this.http.post('http://localhost:8090/problem', problem,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  kreirajPretpostavku(pretpostavka) {
    return this.http.post('http://localhost:8090/surmise', pretpostavka,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  kreirajProstorZnanja(prostorZnanja) {
    return this.http.post('http://localhost:8090/knowledgeSpace', prostorZnanja,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  deleteSubject(id) {
    return this.http.delete('http://localhost:8090/subject/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  kreirajPredmet(subject) {
    return this.http.post('http://localhost:8090/subject', subject,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  deleteProblem(problemId, subjectId) {
    return this.http.delete('http://localhost:8090/problem/' + problemId + '/' + subjectId,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  deletePretpostavka(pretpostavkaId, knowledgeSpaceId, problemId) {
    return this.http.delete('http://localhost:8090/surmise/' + pretpostavkaId + '/' + problemId + '/' + knowledgeSpaceId,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  deleteKnowledgeSpace(knowledgeSpaceId, subjectId) {
    return this.http.delete('http://localhost:8090/knowledgeSpace/' + knowledgeSpaceId + '/' + subjectId,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }


  // tslint:disable-next-line:typedef
  getSurmiseByProblemId(id) {
    return this.http.get('http://localhost:8090/surmise/byProblem/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  getSurmiseByKnowledgeSpaceId(id) {
    return this.http.get('http://localhost:8090/surmise/byKnowledgeSpace/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  getAllSurmises() {
    return this.http.get('http://localhost:8090/surmise',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  getAllSubjects() {
    return this.http.get('http://localhost:8090/subject',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  getAllProfessors() {
    return this.http.get('http://localhost:8090/professor',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  kreirajPretpostavkinProblem(pretpostavkinProblem) {
    return this.http.put('http://localhost:8090/surmise/problems', pretpostavkinProblem,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  kreirajPredmetneProfesore(predmetniProfesori) {
    return this.http.put('http://localhost:8090/subject/professors', predmetniProfesori,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  kreirajProblemPitanja(problemQuestions) {
    return this.http.put('http://localhost:8090/problem/questions', problemQuestions,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  getPitanjaPoProblemu(id) {
    return this.http.get('http://localhost:8090/question/byProblem/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  obrisiPitanje(pitanjeId, sectionId, problemId) {
    return this.http.delete('http://localhost:8090/question/' + pitanjeId + '/' + sectionId + '/' + problemId,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  getOdgovori(id) {
    return this.http.get('http://localhost:8090/answer/byQuestion/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  deleteOdgovor(odgovorId, questionId) {
    return this.http.delete('http://localhost:8090/answer/' + odgovorId + '/' + questionId,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  kreirajOdgovor(odgovor) {
    return this.http.post('http://localhost:8090/answer', odgovor,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  getTestovi() {
    return this.http.get('http://localhost:8090/test',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  jenaAkcija1() {
    return this.http.get('http://localhost:8091/jena/akcija1/',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  jenaAkcija2() {
    return this.http.get('http://localhost:8091/jena/akcija2/',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  jenaAkcija3() {
    return this.http.get('http://localhost:8091/jena/akcija3/',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  jenaAkcija4() {
    return this.http.get('http://localhost:8091/jena/akcija4/',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }
  // tslint:disable-next-line:typedef
  jenaAkcija5() {
    return this.http.get('http://localhost:8091/jena/akcija5/',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  jenaAkcija6() {
    return this.http.get('http://localhost:8091/jena/akcija6/',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  jenaAkcija7() {
    return this.http.get('http://localhost:8091/jena/akcija7/',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  jenaAkcija8() {
    return this.http.get('http://localhost:8091/jena/akcija8/',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  jenaAkcija9() {
    return this.http.get('http://localhost:8091/jena/akcija9/',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  jenaAkcija10() {
    return this.http.get('http://localhost:8091/jena/akcija10/',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

}
