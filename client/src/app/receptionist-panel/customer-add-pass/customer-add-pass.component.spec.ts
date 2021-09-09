import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerAddPassComponent } from './customer-add-pass.component';

describe('CustomerAddPassComponent', () => {
  let component: CustomerAddPassComponent;
  let fixture: ComponentFixture<CustomerAddPassComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerAddPassComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerAddPassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
