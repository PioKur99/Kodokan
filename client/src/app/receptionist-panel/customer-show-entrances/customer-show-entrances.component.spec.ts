import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerShowEntrancesComponent } from './customer-show-entrances.component';

describe('CustomerShowEntrancesComponent', () => {
  let component: CustomerShowEntrancesComponent;
  let fixture: ComponentFixture<CustomerShowEntrancesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerShowEntrancesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerShowEntrancesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
