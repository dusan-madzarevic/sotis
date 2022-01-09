import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JenaFrontComponent } from './jena-front.component';

describe('JenaFrontComponent', () => {
  let component: JenaFrontComponent;
  let fixture: ComponentFixture<JenaFrontComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JenaFrontComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JenaFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
