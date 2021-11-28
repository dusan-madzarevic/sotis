import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {AdminServiceService} from '../services/admin-service.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-admin-subject-professors',
  templateUrl: './admin-subject-professors.component.html',
  styleUrls: ['./admin-subject-professors.component.css']
})
export class AdminSubjectProfessorsComponent implements OnInit {
  SubjectProfessorsForm: FormGroup;
  allSubjects: any = [];
  allProfessors: any = [];
  constructor(private router: Router, private adminServiceService: AdminServiceService, private formBuilder: FormBuilder,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.SubjectProfessorsForm = this.formBuilder.group({
      subjectId: [''],
      professors: ['']
    });

    this.ucitajSvePredmete();
    this.ucitajSveProfesore();
  }

  // tslint:disable-next-line:typedef
  SubjectProfessors() {
    this.adminServiceService.kreirajPredmetneProfesore(JSON.stringify(this.SubjectProfessorsForm.value))
      .pipe(first())
      .subscribe();
  }

  // tslint:disable-next-line:typedef
  ucitajSvePredmete() {
    this.adminServiceService.getAllSubjects()
      .pipe(first())
      .subscribe(data => {
        this.allSubjects = data;
      });
  }

  // tslint:disable-next-line:typedef
  ucitajSveProfesore() {
    this.adminServiceService.getAllProfessors()
      .pipe(first())
      .subscribe(data => {
        this.allProfessors = data;
      });
  }

}
