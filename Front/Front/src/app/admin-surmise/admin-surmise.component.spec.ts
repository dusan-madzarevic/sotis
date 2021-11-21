import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSurmiseComponent } from './admin-surmise.component';

describe('AdminSurmiseComponent', () => {
  let component: AdminSurmiseComponent;
  let fixture: ComponentFixture<AdminSurmiseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminSurmiseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminSurmiseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
