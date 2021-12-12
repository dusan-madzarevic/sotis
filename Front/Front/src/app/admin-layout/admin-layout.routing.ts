import {Routes} from '@angular/router';
import {AdminProfilComponent} from '../admin-profil/admin-profil.component';
import {AdminSubjectComponent} from '../admin-subject/admin-subject.component';
import {AdminSurmiseComponent} from '../admin-surmise/admin-surmise.component';
import {AdminProblemComponent} from '../admin-problem/admin-problem.component';
import {AdminSubjectProfessorsComponent} from '../admin-subject-professors/admin-subject-professors.component';
import {KnowledgeSpaceGraphComponent} from '../knowledge-space-graph/knowledge-space-graph.component';
import {AdminProblemQuestionComponent} from '../admin-problem-question/admin-problem-question.component';

export const AdminLayoutRutes: Routes = [
  { path: 'profilAdmin',      component: AdminProfilComponent },
  { path: 'predmetiAdmin',      component: AdminSubjectComponent },
  { path: 'prostorZnanjaProblemiAdmin',      component: AdminSurmiseComponent },
  { path: 'problemiAdmin',      component: AdminProblemComponent },
  { path: 'predmetProfesoriAdmin',      component: AdminSubjectProfessorsComponent },
  { path: 'knowledgeSpaceAdmin/:subjectId', component: KnowledgeSpaceGraphComponent},
  { path: 'problemPitanjaAdmin',      component: AdminProblemQuestionComponent }
];
