import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UcenikLayoutComponent } from './ucenik-layout.component';

describe('UcenikLayoutComponent', () => {
  let component: UcenikLayoutComponent;
  let fixture: ComponentFixture<UcenikLayoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UcenikLayoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UcenikLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
