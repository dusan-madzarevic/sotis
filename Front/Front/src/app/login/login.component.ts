import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AdminServiceService} from '../services/admin-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  korisnik;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private service: AdminServiceService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      korisnickoIme: ['', Validators.required],
      lozinka: ['', Validators.required]
    });
  }
  // tslint:disable-next-line:typedef
  onSubmit() {
    this.service.login(this.loginForm.value)
      .subscribe( data => {
        this.korisnik = data;
        if (this.korisnik.aktivan && this.korisnik.radnikRId.radnikTip === 'Admin'){
          localStorage.setItem('currentuser', this.loginForm.value.korisnickoIme);
          this.router.navigate(['/admin']);
        }
        if (this.korisnik.aktivan && this.korisnik.radnikRId.radnikTip === 'Magacioner'){
          localStorage.setItem('currentuser', this.loginForm.value.korisnickoIme);
          this.router.navigate(['/magacioner']);
        }
        if (this.korisnik.aktivan && this.korisnik.radnikRId.radnikTip === 'Operater'){
          localStorage.setItem('currentuser', this.loginForm.value.korisnickoIme);
          this.router.navigate(['/operater']);
        }
      })
    ;
  }

}
