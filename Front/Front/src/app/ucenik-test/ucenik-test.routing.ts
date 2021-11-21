import {Routes} from '@angular/router';
import {UcenikPolaganjeComponent} from '../ucenik-polaganje/ucenik-polaganje.component';

export const UcenikTestRouting: Routes = [
  { path: 'ucenikPolaganje/:attemptID', component: UcenikPolaganjeComponent }
];
