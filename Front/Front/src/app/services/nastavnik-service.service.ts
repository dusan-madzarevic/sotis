import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NastavnikServiceService {

  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef
  registrujMagacionera(user) {
    return this.http.post('http://localhost:8090/magacioner', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  getStanja() {
    return this.http.get('http://localhost:8090/stanje',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  getMagacine() {
    return this.http.get('http://localhost:8090/magacin',      {
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
  registrujStanje(user) {
    return this.http.post('http://localhost:8090/stanje', user,      {
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
  deleteTest(id) {
    return this.http.delete('http://localhost:8090/test/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  kreirajTest(user) {
    return this.http.post('http://localhost:8090/test', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  getSekcije(id) {
    return this.http.get('http://localhost:8090/section/byTest/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  deleteSekcija(sectionId, testId) {
    return this.http.delete('http://localhost:8090/section/' + sectionId + '/' + testId,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  getPitanja(id) {
    return this.http.get('http://localhost:8090/question/bySection/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  getTestoviProfesora(username) {
    return this.http.get('http://localhost:8090/test/byProfessor/' + username,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  kreirajSekciju(section) {
    return this.http.post('http://localhost:8090/section', section,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  kreirajPitanje(question) {
    return this.http.post('http://localhost:8090/question', question,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  obrisiPitanje(pitanjeId, sectionId, problemId) {
    return this.http.delete('http://localhost:8090/question/' + pitanjeId + '/' + sectionId + '/' + problemId,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  deleteOdgovor(odgovorId, questionId) {
    return this.http.delete('http://localhost:8090/answer/' + odgovorId + '/' + questionId,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  getOdgovori(id) {
    return this.http.get('http://localhost:8090/answer/byQuestion/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  kreirajOdgovor(odgovor) {
    return this.http.post('http://localhost:8090/answer', odgovor,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  preuzmiRezultate(id) {
    return this.http.get('http://localhost:8090/testAttempt/' + id + '/results', {responseType: 'blob'});

  }
}
