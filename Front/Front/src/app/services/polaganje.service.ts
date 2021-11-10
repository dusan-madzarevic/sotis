import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PolaganjeService {

  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef
  startTest(request) {

    return this.http.post('http://localhost:8090/testAttempt/start', request,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });

  }

  // tslint:disable-next-line:typedef
  submitTest(submitRequest: { testAttemptId: number; answers: any[] }) {

    return this.http.post('http://localhost:8090/testAttempt/submit', submitRequest,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });

  }
}
