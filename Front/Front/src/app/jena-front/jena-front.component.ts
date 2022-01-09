import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {NastavnikServiceService} from '../services/nastavnik-service.service';
import {KnowledgeSpaceService} from '../services/knowledge-space.service';
import {FormBuilder} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {AdminServiceService} from '../services/admin-service.service';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-jena-front',
  templateUrl: './jena-front.component.html',
  styleUrls: ['./jena-front.component.css']
})
export class JenaFrontComponent implements OnInit {

  constructor(private router: Router, private adminService: AdminServiceService, private formBuilder: FormBuilder,
              private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  akcija1(){

    this.adminService.jenaAkcija1()
      .pipe(first())
      .subscribe(data => {
        alert(data);
      });
  }

  // tslint:disable-next-line:typedef
  akcija2(){
    this.adminService.jenaAkcija2()
      .pipe(first())
      .subscribe(data => {
        alert(data);
      });
  }

  // tslint:disable-next-line:typedef
  akcija3(){
    this.adminService.jenaAkcija3()
      .pipe(first())
      .subscribe(data => {
        alert(data);
      });
  }

  // tslint:disable-next-line:typedef
  akcija4(){
    this.adminService.jenaAkcija4()
      .pipe(first())
      .subscribe(data => {
        alert(data);
      });
  }

  // tslint:disable-next-line:typedef
  akcija5(){
    this.adminService.jenaAkcija5()
      .pipe(first())
      .subscribe(data => {
        alert(data);
      });
  }

  // tslint:disable-next-line:typedef
  akcija6(){
    this.adminService.jenaAkcija6()
      .pipe(first())
      .subscribe(data => {
        alert(data);
      });
  }

  // tslint:disable-next-line:typedef
  akcija7(){
    this.adminService.jenaAkcija7()
      .pipe(first())
      .subscribe(data => {
        alert(data);
      });
  }

  // tslint:disable-next-line:typedef
  akcija8(){
    this.adminService.jenaAkcija8()
      .pipe(first())
      .subscribe(data => {
        alert(data);
      });
  }

  // tslint:disable-next-line:typedef
  akcija9(){
    this.adminService.jenaAkcija9()
      .pipe(first())
      .subscribe(data => {
        alert(data);
      });
  }

  // tslint:disable-next-line:typedef
  akcija10(){
    this.adminService.jenaAkcija10()
      .pipe(first())
      .subscribe(data => {
        alert(data);
      });
  }

}
