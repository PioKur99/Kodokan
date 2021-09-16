import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerShowPassesComponent } from './customer-show-passes.component';

describe('CustomerShowPassesComponent', () => {
  let component: CustomerShowPassesComponent;
  let fixture: ComponentFixture<CustomerShowPassesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerShowPassesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerShowPassesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
