import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UcenikNavbarComponent } from './ucenik-navbar/ucenik-navbar.component';
import { UcenikSidebarComponent } from './ucenik-sidebar/ucenik-sidebar.component';
import {NgbCollapseModule, NgbDropdownModule} from '@ng-bootstrap/ng-bootstrap';
import {RouterModule} from '@angular/router';



@NgModule({
  declarations: [UcenikNavbarComponent, UcenikSidebarComponent],
  exports: [UcenikNavbarComponent, UcenikSidebarComponent],
  imports: [
    CommonModule,
    NgbCollapseModule,
    RouterModule,
    NgbDropdownModule
  ]
})
export class UcenikCompModule { }
