import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder} from '@angular/forms';
import {AdminServiceService} from '../services/admin-service.service';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  styleUrls: ['./admin-layout.component.css']
})
export class AdminLayoutComponent implements OnInit {
  admin: any = [];
  korisnik: any = [];
  constructor(private formBuilder: FormBuilder,
              private router: Router, private adminService: AdminServiceService) { }

  ngOnInit(): void {
    this.ucitajAdmina();
  }

  // tslint:disable-next-line:typedef
  ucitajAdmina() {
    this.adminService.getUser(localStorage.getItem('currentuser').toString())
      .pipe(first())
      .subscribe((data: {}) => {
          this.korisnik = data;
          this.admin = this.korisnik.radnikRId;
          if (this.admin.radnikTip !== 'Admin') {
            this.router.navigate(['/']);
          }
        }
      );
  }

}
