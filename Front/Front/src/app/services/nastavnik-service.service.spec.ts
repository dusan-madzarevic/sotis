import { TestBed } from '@angular/core/testing';

import { NastavnikServiceService } from './nastavnik-service.service';

describe('NastavnikServiceService', () => {
  let service: NastavnikServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NastavnikServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
