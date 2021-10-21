import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NastavnikSidebarComponent } from './nastavnik-sidebar.component';

describe('NastavnikSidebarComponent', () => {
  let component: NastavnikSidebarComponent;
  let fixture: ComponentFixture<NastavnikSidebarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NastavnikSidebarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NastavnikSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
