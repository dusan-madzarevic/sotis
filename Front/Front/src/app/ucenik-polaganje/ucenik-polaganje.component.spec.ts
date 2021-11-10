import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UcenikPolaganjeComponent } from './ucenik-polaganje.component';

describe('UcenikPolaganjeComponent', () => {
  let component: UcenikPolaganjeComponent;
  let fixture: ComponentFixture<UcenikPolaganjeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UcenikPolaganjeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UcenikPolaganjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
