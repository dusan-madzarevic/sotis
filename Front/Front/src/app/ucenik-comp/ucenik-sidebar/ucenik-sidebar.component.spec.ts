import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UcenikSidebarComponent } from './ucenik-sidebar.component';

describe('UcenikSidebarComponent', () => {
  let component: UcenikSidebarComponent;
  let fixture: ComponentFixture<UcenikSidebarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UcenikSidebarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UcenikSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
