import {Routes} from '@angular/router';
import {NastavnikProfilComponent} from '../nastavnik-profil/nastavnik-profil.component';
import {NastavnikTestComponent} from '../nastavnik-test/nastavnik-test.component';

export const NastavnikLayoutRutes: Routes = [
  { path: 'nastavnikProfil',      component: NastavnikProfilComponent },
  { path: 'nastavnikTest',      component: NastavnikTestComponent }
];
