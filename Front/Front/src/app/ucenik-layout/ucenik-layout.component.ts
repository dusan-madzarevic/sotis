import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {first} from 'rxjs/operators';
import {FormBuilder} from '@angular/forms';
import {AdminServiceService} from '../services/admin-service.service';

@Component({
  selector: 'app-ucenik-layout',
  templateUrl: './ucenik-layout.component.html',
  styleUrls: ['./ucenik-layout.component.css']
})
export class UcenikLayoutComponent implements OnInit {
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
          if (this.admin.radnikTip !== 'Ucenik') {
          this.router.navigate(['/']);
        }
        }
      );
  }

}
