import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NastavnikLayoutComponent} from './nastavnik-layout.component';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FlatpickrModule} from 'angularx-flatpickr';
import {NastavnikCompModule} from '../nastavnik-comp/nastavnik-comp.module';
import {NastavnikLayoutRutes} from './nastavnik-layout.routing';
import {NastavnikProfilComponent} from '../nastavnik-profil/nastavnik-profil.component';
import {MatFormFieldModule, MatSelectModule} from '@angular/material';



@NgModule({
  declarations: [NastavnikLayoutComponent,
    NastavnikProfilComponent
  ],
  exports: [NastavnikLayoutComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(NastavnikLayoutRutes),
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    FlatpickrModule.forRoot(),
    NastavnikCompModule,
    MatFormFieldModule,
    MatSelectModule
  ]
})
export class NastavnikLayoutModule { }
