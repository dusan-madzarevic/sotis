import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {
  radniNalog: any = [];
  sastavnica: any = [];
  stavkaSas: any = [];
  stavkaRN: any = [];
  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef
  registrujKorisnika(user) {
    return this.http.post('http://localhost:8090/korisnik', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

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
  setRadniNalog(radniNalog){
    this.radniNalog = radniNalog;
  }

  // tslint:disable-next-line:typedef
  setSastavnica(sastavnica){
    this.sastavnica = sastavnica;
  }

  // tslint:disable-next-line:typedef
  setSTavkaSas(stavkaSas){
    this.stavkaSas = stavkaSas;
  }

  // tslint:disable-next-line:typedef
  settStavkaRN(stavkaRN){
    this.stavkaRN = stavkaRN;
  }

  // tslint:disable-next-line:typedef
  gettStavkaRN(){
    return this.stavkaRN;
  }

  // tslint:disable-next-line:typedef
  getRadniNalog(){
    return this.radniNalog;
  }

  // tslint:disable-next-line:typedef
  getSastavnica(){
    return this.sastavnica;
  }

  // tslint:disable-next-line:typedef
  getStavkaSass(){
    return this.stavkaSas;
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
  getKancelarijas() {
    return this.http.get('http://localhost:8090/kancelarija',      {
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
  getPogone() {
    return this.http.get('http://localhost:8090/pogon',      {
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
  getStanjaPoMat(id) {
    return this.http.get('http://localhost:8090/stanje/' + id ,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  getRadneNaloge() {
    return this.http.get('http://localhost:8090/radni_nalog',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  getSastavnice() {
    return this.http.get('http://localhost:8090/sastavnica',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  getSastavniceSemNje(id) {
    return this.http.get('http://localhost:8090/sastavnica/' + id + '/semnje',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  getStavkaRN(id) {
    return this.http.get('http://localhost:8090/stavka_rn/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  getStavkaSas(id) {
    return this.http.get('http://localhost:8090/stavka_sas/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  getSveStavkeSas() {
    return this.http.get('http://localhost:8090/stavka_sas',      {
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
  deleteMaterijal(id) {
    return this.http.delete('http://localhost:8090/materijal/' + id,      {
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
  deleteRadniNalog(id) {
    return this.http.delete('http://localhost:8090/radni_nalog/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  deleteSastavnica(id) {
    return this.http.delete('http://localhost:8090/sastavnica/' + id,      {
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
  registrujMagacin(user) {
    return this.http.post('http://localhost:8090/magacin', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujPogon(user) {
    return this.http.post('http://localhost:8090/pogon', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujRadniNalog(user) {
    return this.http.post('http://localhost:8090/radni_nalog', user,      {
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
  registrujSastavnicu(user) {
    return this.http.post('http://localhost:8090/sastavnica', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujStavkuRN(user) {
    return this.http.post('http://localhost:8090/stavka_rn', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujStavkuSastavnice(user) {
    return this.http.post('http://localhost:8090/stavka_sas', user,      {
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
  dodajMatUStavku(user, id) {
    return this.http.put('http://localhost:8090/sastavnica/' + id + '/sasmat', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  dodajTrebovanje(user, id , id1) {
    return this.http.put('http://localhost:8090/stavka_sas/' + id + '/' + id1 + '/ssasstanje', user,      {
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
  registrujTipMultipola(user) {
    return this.http.post('http://localhost:8090/tos_multipol' , user,      {
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
  getSPs() {
    return this.http.get('http://localhost:8090/sektor_pogona',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
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
  registrujRivet(user) {
    return this.http.post('http://localhost:8090/rivet', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujTestLiniju(user) {
    return this.http.post('http://localhost:8090/test_linija', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujReparaciju(user) {
    return this.http.post('http://localhost:8090/reparacija', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujMultipol(user) {
    return this.http.post('http://localhost:8090/multipol', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujPrintCentar(user) {
    return this.http.post('http://localhost:8090/print_centar', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujPakeraj(user) {
    return this.http.post('http://localhost:8090/pakeraj', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujQSKontrolu(user) {
    return this.http.post('http://localhost:8090/qs_kontrola', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  getTipoviOs() {
    return this.http.get('http://localhost:8090/tip_os',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
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

  // tslint:disable-next-line:typedef
  deleteTOS(id) {
    return this.http.delete('http://localhost:8090/tip_os/' + id,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  getTipoviTestova() {
    return this.http.get('http://localhost:8090/tip_test',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  deleteTipTest(id, id1) {
    return this.http.delete('http://localhost:8090/tip_test/' + id + '/' + id1,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  getTipKontrola() {
    return this.http.get('http://localhost:8090/tip_kontrola',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  deleteTipKontrola(id, id1) {
    return this.http.delete('http://localhost:8090/tip_kontrola/' + id + '/' + id1,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujTipTest(user) {
    return this.http.post('http://localhost:8090/tip_test', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  registrujTipKontrola(user) {
    return this.http.post('http://localhost:8090/tip_kontrola', user,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  // tslint:disable-next-line:typedef
  getQsKontrole() {
    return this.http.get('http://localhost:8090/qs_kontrola',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  // tslint:disable-next-line:typedef
  getTestLinije() {
    return this.http.get('http://localhost:8090/test_linija',      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }
}
