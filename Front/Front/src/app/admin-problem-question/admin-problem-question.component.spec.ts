import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminProblemQuestionComponent } from './admin-problem-question.component';

describe('AdminProblemQuestionComponent', () => {
  let component: AdminProblemQuestionComponent;
  let fixture: ComponentFixture<AdminProblemQuestionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminProblemQuestionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminProblemQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
