import { Component, OnInit } from '@angular/core';

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [
  { path: '/ucenik/ucenikProfil', title: 'Profil',  icon: '', class: '' },
  { path: '/ucenik/ucenikTest', title: 'Testovi',  icon: '', class: '' }
];

@Component({
  selector: 'app-ucenik-sidebar',
  templateUrl: './ucenik-sidebar.component.html',
  styleUrls: ['./ucenik-sidebar.component.css']
})
export class UcenikSidebarComponent implements OnInit {

  menuItems: any[];
  constructor() { }

  // tslint:disable-next-line:typedef
  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }

}
