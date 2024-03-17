import { TestBed } from '@angular/core/testing';

import { GroceteriaService } from './groceteria.service';

describe('GroceteriaService', () => {
  let service: GroceteriaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GroceteriaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
