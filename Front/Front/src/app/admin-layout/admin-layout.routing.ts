import {Routes} from '@angular/router';
import {AdminProfilComponent} from '../admin-profil/admin-profil.component';
import {AdminSubjectComponent} from '../admin-subject/admin-subject.component';
import {AdminSurmiseComponent} from '../admin-surmise/admin-surmise.component';
import {AdminProblemComponent} from '../admin-problem/admin-problem.component';

export const AdminLayoutRutes: Routes = [
  { path: 'profilAdmin',      component: AdminProfilComponent },
  { path: 'predmetiAdmin',      component: AdminSubjectComponent },
  { path: 'pretpostavkeAdmin',      component: AdminSurmiseComponent },
  { path: 'problemiAdmin',      component: AdminProblemComponent },

];
