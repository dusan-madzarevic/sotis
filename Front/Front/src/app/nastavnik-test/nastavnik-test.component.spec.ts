import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NastavnikTestComponent } from './nastavnik-test.component';

describe('NastavnikTestComponent', () => {
  let component: NastavnikTestComponent;
  let fixture: ComponentFixture<NastavnikTestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NastavnikTestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NastavnikTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
