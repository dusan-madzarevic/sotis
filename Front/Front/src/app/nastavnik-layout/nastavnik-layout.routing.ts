import {Routes} from '@angular/router';
import {NastavnikProfilComponent} from '../nastavnik-profil/nastavnik-profil.component';
import {NastavnikTestComponent} from '../nastavnik-test/nastavnik-test.component';
import {AdminSubjectComponent} from '../admin-subject/admin-subject.component';
import {AdminProblemQuestionComponent} from '../admin-problem-question/admin-problem-question.component';
import {AdminProblemComponent} from '../admin-problem/admin-problem.component';
import {AdminSurmiseComponent} from '../admin-surmise/admin-surmise.component';

export const NastavnikLayoutRutes: Routes = [
  { path: 'nastavnikProfil',      component: NastavnikProfilComponent },
  { path: 'nastavnikTest',      component: NastavnikTestComponent },
  { path: 'nastavnikPredmeti',      component: AdminSubjectComponent },
  { path: 'nastavnikProblemPitanja', component: AdminProblemQuestionComponent},
  { path: 'problemiPretpostavke', component: AdminProblemComponent},
  { path: 'prostorZnanjaPretpostavke', component: AdminSurmiseComponent},
];
