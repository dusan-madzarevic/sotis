import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSubjectProfessorsComponent } from './admin-subject-professors.component';

describe('AdminSubjectProfessorsComponent', () => {
  let component: AdminSubjectProfessorsComponent;
  let fixture: ComponentFixture<AdminSubjectProfessorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminSubjectProfessorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminSubjectProfessorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
