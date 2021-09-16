import { TestBed } from '@angular/core/testing';

import { AddPackageReceptionistService } from './add-package-receptionist.service';

describe('AddPackageReceptionistService', () => {
  let service: AddPackageReceptionistService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddPackageReceptionistService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
