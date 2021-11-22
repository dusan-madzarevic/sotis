import { Component, OnInit } from '@angular/core';

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [
  { path: '/admin/profilAdmin', title: 'Profil',  icon: '', class: '' },
  { path: '/admin/predmetiAdmin', title: 'Predmeti',  icon: '', class: '' },
  { path: '/admin/pretpostavkeAdmin', title: 'Pretpostavke',  icon: '', class: '' },
  { path: '/admin/problemiAdmin', title: 'Problemi pretpostavki',  icon: '', class: '' },
  { path: '/admin/predmetProfesoriAdmin', title: 'Predmetni profesori',  icon: '', class: '' },
];

@Component({
  selector: 'app-admin-sidebar',
  templateUrl: './admin-sidebar.component.html',
  styleUrls: ['./admin-sidebar.component.css']
})
export class AdminSidebarComponent implements OnInit {

  menuItems: any[];
  constructor() { }

  // tslint:disable-next-line:typedef
  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }

}
