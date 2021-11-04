import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {first} from 'rxjs/operators';
import {NastavnikServiceService} from '../services/nastavnik-service.service';
import {UcenikServiceService} from '../services/ucenik-service.service';
import {AdminServiceService} from '../services/admin-service.service';

@Component({
  selector: 'app-registracion',
  templateUrl: './registracion.component.html',
  styleUrls: ['./registracion.component.css']
})
export class RegistracionComponent implements OnInit {
  korisnikForm: FormGroup;

  constructor(    private formBuilder: FormBuilder,
                  private router: Router,
                  private nastavnikService: NastavnikServiceService,
                  private ucenikService: UcenikServiceService,
                  private adminService: AdminServiceService) { }

  ngOnInit(): void {
    this.korisnikForm = this.formBuilder.group({
      name: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      userType: ['Student']
    });
  }

  // tslint:disable-next-line:typedef
  get formControls() { return this.korisnikForm.controls; }

  // tslint:disable-next-line:typedef
  get myUloga() {
    console.log(this.korisnikForm.get('userType'));
    return this.korisnikForm.get('userType');
  }

  // tslint:disable-next-line:typedef
  onSubmit() {
    if (this.myUloga.value === 'Administrator'){
      this.adminService.registrujAdmina(JSON.stringify(this.korisnikForm.value))
        .pipe(first())
        .subscribe();
      this.router.navigate(['/']);
    }
    if (this.myUloga.value === 'Student'){
      this.adminService.registrujStudent(JSON.stringify(this.korisnikForm.value))
        .pipe(first())
        .subscribe();
      this.router.navigate(['/']);
    }
    if (this.myUloga.value === 'Professor'){
      this.adminService.registrujProfessor(JSON.stringify(this.korisnikForm.value))
        .pipe(first())
        .subscribe();
      this.router.navigate(['/']);
    }

  }

  // tslint:disable-next-line:typedef
  MakeVote() {
    this.router.navigate(['/']);
  }
}
