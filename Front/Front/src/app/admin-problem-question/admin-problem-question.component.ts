import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {AdminServiceService} from '../services/admin-service.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-admin-problem-question',
  templateUrl: './admin-problem-question.component.html',
  styleUrls: ['./admin-problem-question.component.css']
})
export class AdminProblemQuestionComponent implements OnInit {
  ProblemQuestionForm: FormGroup;
  allProblems: any = [];
  allQuestions: any = [];

  constructor(private router: Router, private adminServiceService: AdminServiceService, private formBuilder: FormBuilder,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.ProblemQuestionForm = this.formBuilder.group({
      problemId: [''],
      questions: ['']
    });

    this.ucitajSveProbleme();
    this.ucitajSvaPitanja();
  }


  // tslint:disable-next-line:typedef
  createProblemQuestions() {
    this.adminServiceService.kreirajProblemPitanja(JSON.stringify(this.ProblemQuestionForm.value))
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
  ucitajSvaPitanja() {
    this.adminServiceService.getAllQuestions()
      .pipe(first())
      .subscribe(data => {
        this.allQuestions = data;
      });
  }

}
