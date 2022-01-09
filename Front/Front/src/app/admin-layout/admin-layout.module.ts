import { NgModule } from '@angular/core'; //
import { CommonModule } from '@angular/common'; //
import { AdminLayoutComponent } from './admin-layout.component'; //
import {RouterModule} from '@angular/router'; //
import {FormsModule, ReactiveFormsModule} from '@angular/forms'; //
import {NgbModule} from '@ng-bootstrap/ng-bootstrap'; //
import {FlatpickrModule} from 'angularx-flatpickr'; //
import {AdminCompModule} from '../admin-comp/admin-comp.module'; //
import {AdminLayoutRutes} from './admin-layout.routing';
import {AdminProfilComponent} from '../admin-profil/admin-profil.component';
import {MatFormFieldModule, MatSelectModule} from '@angular/material';
import {AdminSubjectComponent} from '../admin-subject/admin-subject.component';
import {AdminSurmiseComponent} from '../admin-surmise/admin-surmise.component';
import {AdminProblemComponent} from '../admin-problem/admin-problem.component';
import {AdminSubjectProfessorsComponent} from '../admin-subject-professors/admin-subject-professors.component';
import {KnowledgeSpaceGraphComponent} from '../knowledge-space-graph/knowledge-space-graph.component';
import { AdminProblemQuestionComponent } from '../admin-problem-question/admin-problem-question.component';
import {JenaFrontComponent} from '../jena-front/jena-front.component';



@NgModule({
  declarations: [AdminLayoutComponent,
    AdminProfilComponent,
    AdminSubjectComponent,
    AdminSurmiseComponent,
    AdminProblemComponent,
    AdminSubjectProfessorsComponent,
    KnowledgeSpaceGraphComponent,
    AdminProblemQuestionComponent,
    JenaFrontComponent
    ],
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRutes),
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    FlatpickrModule.forRoot(),
    AdminCompModule,
    MatFormFieldModule,
    MatSelectModule
  ],
  exports: [AdminLayoutComponent
  ]
})
export class AdminLayoutModule { }
