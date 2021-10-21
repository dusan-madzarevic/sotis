import { TestBed } from '@angular/core/testing';

import { UcenikServiceService } from './ucenik-service.service';

describe('UcenikServiceService', () => {
  let service: UcenikServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UcenikServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
