import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {Router} from '@angular/router';
import {AdminServiceService} from '../services/admin-service.service';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-nastavnik-profil',
  templateUrl: './nastavnik-profil.component.html',
  styleUrls: ['./nastavnik-profil.component.css']
})
export class NastavnikProfilComponent implements OnInit {
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
        }
      );
  }

  // tslint:disable-next-line:typedef
  logout() {
    localStorage.removeItem('currentuser');
    this.router.navigate(['/']);

  }

}
