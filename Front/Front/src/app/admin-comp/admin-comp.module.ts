import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminNavbarComponent } from './admin-navbar/admin-navbar.component';
import { AdminSidebarComponent } from './admin-sidebar/admin-sidebar.component';
import {NgbCollapseModule, NgbDropdownModule} from '@ng-bootstrap/ng-bootstrap';
import {RouterModule} from '@angular/router';



@NgModule({
  declarations: [AdminNavbarComponent, AdminSidebarComponent],
  exports: [AdminNavbarComponent,
            AdminSidebarComponent],
  imports: [
    CommonModule,
    NgbCollapseModule,
    RouterModule,
    NgbDropdownModule
  ]
})
export class AdminCompModule { }
