import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UcenikNavbarComponent } from './ucenik-navbar.component';

describe('UcenikNavbarComponent', () => {
  let component: UcenikNavbarComponent;
  let fixture: ComponentFixture<UcenikNavbarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UcenikNavbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UcenikNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
