import {Routes, RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {LoginComponent} from './login/login.component';
import {RegistracionComponent} from './registracion/registracion.component';
import {AdminLayoutComponent} from './admin-layout/admin-layout.component';
import {NastavnikLayoutComponent} from './nastavnik-layout/nastavnik-layout.component';
import {UcenikLayoutComponent} from './ucenik-layout/ucenik-layout.component';

const routes: Routes = [

  {
    path: '',
    component: LoginComponent,
    children: [
    ]},
  {
    path: 'signup',
    component: RegistracionComponent,
    children: [
    ]},
  {
    path: 'admin',
    component: AdminLayoutComponent,
    children: [
      {
        path: '',
        loadChildren: './admin-layout/admin-layout.module#AdminLayoutModule'
      }]
  },
  {
    path: 'nastavnik',
    component: NastavnikLayoutComponent,
    children: [
      {
        path: '',
        loadChildren: './nastavnik-layout/nastavnik-layout.module#NastavnikLayoutModule'
      }]
  },
  {
    path: 'ucenik',
    component: UcenikLayoutComponent,
    children: [
      {
        path: '',
        loadChildren: './ucenik-layout/ucenik-layout.module#UcenikLayoutModule'
      },
      {
        path: '',
        loadChildren: './ucenik-test/ucenik-test.module#UcenikTestModule'
      }]
  },
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
  ],
})

export class AppRoutingModule { }
