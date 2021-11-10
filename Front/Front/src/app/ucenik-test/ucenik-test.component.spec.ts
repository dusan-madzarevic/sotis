import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UcenikTestComponent } from './ucenik-test.component';

describe('UcenikTestComponent', () => {
  let component: UcenikTestComponent;
  let fixture: ComponentFixture<UcenikTestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UcenikTestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UcenikTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
