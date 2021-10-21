import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UcenikProfilComponent } from './ucenik-profil.component';

describe('UcenikProfilComponent', () => {
  let component: UcenikProfilComponent;
  let fixture: ComponentFixture<UcenikProfilComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UcenikProfilComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UcenikProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
