import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UcenikLayoutComponent } from './ucenik-layout.component';
import {UcenikCompModule} from '../ucenik-comp/ucenik-comp.module';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FlatpickrModule} from 'angularx-flatpickr';
import {UcenikLayoutRutes} from './ucenik-layout.routing';
import {UcenikProfilComponent} from '../ucenik-profil/ucenik-profil.component';

@NgModule({
  declarations: [UcenikLayoutComponent,
    UcenikProfilComponent
  ],
  exports: [UcenikLayoutComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(UcenikLayoutRutes),
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    FlatpickrModule.forRoot(),
    UcenikCompModule
  ]
})
export class UcenikLayoutModule { }
