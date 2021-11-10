import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {FlatpickrModule} from "angularx-flatpickr";
import {UcenikCompModule} from "../ucenik-comp/ucenik-comp.module";
import {UcenikTestComponent} from "./ucenik-test.component";
import {UcenikPolaganjeComponent} from "../ucenik-polaganje/ucenik-polaganje.component";
import {UcenikTestRouting} from "./ucenik-test.routing";
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";

@NgModule({
  declarations: [UcenikTestComponent,
    UcenikPolaganjeComponent
  ],
  exports: [UcenikTestComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(UcenikTestRouting),
    FormsModule,
    NgbModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatButtonModule,
    MatFormFieldModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    FlatpickrModule.forRoot(),
    UcenikCompModule
  ]
})
export class UcenikTestModule { }
