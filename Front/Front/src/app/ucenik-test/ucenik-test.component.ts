import { Component, OnInit } from '@angular/core';
import {first} from "rxjs/operators";
import {Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {UcenikServiceService} from "../services/ucenik-service.service";

@Component({
  selector: 'app-ucenik-test',
  templateUrl: './ucenik-test.component.html',
  styleUrls: ['./ucenik-test.component.css']
})
export class UcenikTestComponent implements OnInit {
  testovi: any = [];
  constructor(private router: Router, private ucenikService: UcenikServiceService, private formBuilder: FormBuilder,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.testovi = [];
    this.ucitajTestove();
  }

  // tslint:disable-next-line:typedef
  ucitajTestove() {
    this.ucenikService.getTestovi()
      .pipe(first())
      .subscribe(data => {
        this.testovi = data;
      });
  }

  // tslint:disable-next-line:typedef
  logout() {
    localStorage.removeItem('currentuser');
    this.router.navigate(['/']);
  }

  // tslint:disable-next-line:typedef
  startAttempt(id) {

    this.router.navigate(['ucenik/ucenikPolaganje/' + id]);

  }
}
