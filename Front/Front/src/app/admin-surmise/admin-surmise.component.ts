import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AdminServiceService} from '../services/admin-service.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-admin-surmise',
  templateUrl: './admin-surmise.component.html',
  styleUrls: ['./admin-surmise.component.css']
})
export class AdminSurmiseComponent implements OnInit {
  SurmiseForm: FormGroup;
  allKnowledgeSpaces: any = [];
  allProblems: any = [];

  constructor(private router: Router, private adminServiceService: AdminServiceService, private formBuilder: FormBuilder,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.SurmiseForm = this.formBuilder.group({
      knowledgeSpaceId: [''],
      problemId: ['']
    });

    this.ucitajSveProstoreZnanja();
    this.ucitajSveProbleme();
  }

  // tslint:disable-next-line:typedef
  logout() {
    localStorage.removeItem('currentuser');
    this.router.navigate(['/']);
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
  ucitajSveProbleme() {
    this.adminServiceService.getAllProblems()
      .pipe(first())
      .subscribe(data => {
        this.allProblems = data;
      });
  }

  // tslint:disable-next-line:typedef
  createSurmise() {
    this.adminServiceService.kreirajPretpostavku(JSON.stringify(this.SurmiseForm.value))
      .pipe(first())
      .subscribe();
  }

}
