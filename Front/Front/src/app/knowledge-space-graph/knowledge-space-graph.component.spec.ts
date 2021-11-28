import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KnowledgeSpaceGraphComponent } from './knowledge-space-graph.component';

describe('KnowledgeSpaceGraphComponent', () => {
  let component: KnowledgeSpaceGraphComponent;
  let fixture: ComponentFixture<KnowledgeSpaceGraphComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KnowledgeSpaceGraphComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KnowledgeSpaceGraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
