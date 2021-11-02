import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {NastavnikServiceService} from '../services/nastavnik-service.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-nastavnik-test',
  templateUrl: './nastavnik-test.component.html',
  styleUrls: ['./nastavnik-test.component.css']
})
export class NastavnikTestComponent implements OnInit {
  testovi: any = [];
  closeResult: string;
  TestForm: FormGroup;
  constructor(private router: Router, private nastavnikService: NastavnikServiceService, private formBuilder: FormBuilder,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.ucitajTestove();

    this.TestForm = this.formBuilder.group({
      title: [''],
      maxScore: [''],
      passPercentage: [''],
      username: [localStorage.getItem('currentuser').toString()]
    });
  }

  // tslint:disable-next-line:typedef
  ucitajTestove() {
    this.nastavnikService.getTestovi()
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
  delete(test) {
    this.nastavnikService.deleteTest(test.id)
      .pipe(first())
      .subscribe();
    this.ucitajTestove();
  }

  // tslint:disable-next-line:typedef
  noviTest(addTest){
    this.modalService.open(addTest, {size: 'xl'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed`;
    });
  }

  // tslint:disable-next-line:typedef
  create(){
    this.nastavnikService.kreirajTest(JSON.stringify(this.TestForm.value))
      .pipe(first())
      .subscribe();
    this.ucitajTestove();
    this.modalService.dismissAll();
  }

}
