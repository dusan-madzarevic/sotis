import { TestBed } from '@angular/core/testing';

import { KnowledgeSpaceService } from './knowledge-space.service';

describe('KnowledgeSpaceServiceService', () => {
  let service: KnowledgeSpaceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KnowledgeSpaceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
