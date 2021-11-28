import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {AdminServiceService} from '../services/admin-service.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-admin-problem',
  templateUrl: './admin-problem.component.html',
  styleUrls: ['./admin-problem.component.css']
})
export class AdminProblemComponent implements OnInit {
  SurmiseProblemForm: FormGroup;
  allProblems: any = [];
  allSurmises: any = [];

  constructor(private router: Router, private adminServiceService: AdminServiceService, private formBuilder: FormBuilder,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.SurmiseProblemForm = this.formBuilder.group({
      problems: [''],
      surmiseId: ['']
    });

    this.ucitajSveProbleme();
    this.ucitajSvePretpostavke();
  }

  // tslint:disable-next-line:typedef
  createSurmiseProblem() {
    this.adminServiceService.kreirajPretpostavkinProblem(JSON.stringify(this.SurmiseProblemForm.value))
      .pipe(first())
      .subscribe();
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
  ucitajSvePretpostavke() {
    this.adminServiceService.getAllSurmises()
      .pipe(first())
      .subscribe(data => {
        this.allSurmises = data;
      });
  }

}
