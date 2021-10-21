import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NastavnikNavbarComponent } from './nastavnik-navbar.component';

describe('NastavnikNavbarComponent', () => {
  let component: NastavnikNavbarComponent;
  let fixture: ComponentFixture<NastavnikNavbarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NastavnikNavbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NastavnikNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
