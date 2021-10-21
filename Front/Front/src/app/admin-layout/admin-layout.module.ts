import { NgModule } from '@angular/core'; //
import { CommonModule } from '@angular/common'; //
import { AdminLayoutComponent } from './admin-layout.component'; //
import {RouterModule} from '@angular/router'; //
import {FormsModule, ReactiveFormsModule} from '@angular/forms'; //
import {NgbModule} from '@ng-bootstrap/ng-bootstrap'; //
import {FlatpickrModule} from 'angularx-flatpickr'; //
import {AdminCompModule} from '../admin-comp/admin-comp.module'; //
import {AdminLayoutRutes} from './admin-layout.routing';
import {AdminProfilComponent} from '../admin-profil/admin-profil.component';
import {MatFormFieldModule, MatSelectModule} from '@angular/material';



@NgModule({
  declarations: [AdminLayoutComponent,
    AdminProfilComponent,
    // AdminKancelarijaComponent,
    ],
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRutes),
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    FlatpickrModule.forRoot(),
    AdminCompModule,
    MatFormFieldModule,
    MatSelectModule
  ],
  exports: [AdminLayoutComponent
  ]
})
export class AdminLayoutModule { }
