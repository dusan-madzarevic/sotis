import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NastavnikNavbarComponent } from './nastavnik-navbar/nastavnik-navbar.component';
import { NastavnikSidebarComponent } from './nastavnik-sidebar/nastavnik-sidebar.component';
import {NgbCollapseModule, NgbDropdownModule} from '@ng-bootstrap/ng-bootstrap';
import {RouterModule} from '@angular/router';



@NgModule({
  declarations: [NastavnikNavbarComponent, NastavnikSidebarComponent],
  exports: [NastavnikNavbarComponent, NastavnikSidebarComponent],
  imports: [
    CommonModule,
    NgbCollapseModule,
    RouterModule,
    NgbDropdownModule
  ]
})
export class NastavnikCompModule { }
