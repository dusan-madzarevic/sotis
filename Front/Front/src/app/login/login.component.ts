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
  user;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private service: AdminServiceService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }
  // tslint:disable-next-line:typedef
  onSubmit() {
    this.service.login(this.loginForm.value)
      .subscribe( data => {
        this.user = data;
        if (this.user.userType === 'Administrator'){
          localStorage.setItem('currentuser', this.loginForm.value.username);
          this.router.navigate(['/admin']);
        }
        if (this.user.userType === 'Student'){
          localStorage.setItem('currentuser', this.loginForm.value.username);
          this.router.navigate(['/ucenik']);
        }
        if (this.user.userType === 'Professor'){
          localStorage.setItem('currentuser', this.loginForm.value.username);
          this.router.navigate(['/nastavnik']);
        }
      })
    ;
  }

}
