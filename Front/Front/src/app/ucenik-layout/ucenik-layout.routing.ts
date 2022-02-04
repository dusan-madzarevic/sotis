import {Routes} from '@angular/router';
import {UcenikProfilComponent} from '../ucenik-profil/ucenik-profil.component';
import {UcenikTestComponent} from '../ucenik-test/ucenik-test.component';

export const UcenikLayoutRutes: Routes = [
  { path: 'ucenikProfil', component: UcenikProfilComponent },
  {path: 'ucenikTest', component: UcenikTestComponent}
];
