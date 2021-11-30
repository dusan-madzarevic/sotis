import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class KnowledgeSpaceService {

  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef
  saveSpace(request) {

    return this.http.post('http://localhost:8090/knowledgeSpace/fromGraph', request,      {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });

  }

}
