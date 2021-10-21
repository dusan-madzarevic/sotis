import { Component, OnInit } from '@angular/core';

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [
  { path: '/ucenik/operaterProfil', title: 'Profil',  icon: '', class: '' },
  { path: '/ucenik/operaterRadniNalog', title: 'Radni nalozi pogona',  icon: '', class: '' },
  { path: '/ucenik/operaterOsigurac', title: 'Osigurači',  icon: '', class: '' },
  { path: '/ucenik/operaterTest', title: 'Testovi',  icon: '', class: '' },
  { path: '/ucenik/operaterKontrola', title: 'Kontrole',  icon: '', class: '' }
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
