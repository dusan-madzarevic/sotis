import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { KnowledgeSpace } from '../model/KnowledgeSpace';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KnowledgeSpaceService {

  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef
  saveSpace(request) {

    return this.http.post('http://localhost:8090/knowledgeSpace/fromGraph', request,{
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });

  }

  saveRealSpace(request){

    return this.http.post('http://localhost:8090/knowledgeSpace/fromIita', request,{
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });

  }

  getSpacesForGraph(subjectId) {

    return this.http.get<KnowledgeSpace[]>('http://localhost:8090/knowledgeSpace/forGraph/'+subjectId,{
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });

  }

}
