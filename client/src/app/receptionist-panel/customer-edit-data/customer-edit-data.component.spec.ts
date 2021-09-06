import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerEditDataComponent } from './customer-edit-data.component';

describe('CustomerEditDataComponent', () => {
  let component: CustomerEditDataComponent;
  let fixture: ComponentFixture<CustomerEditDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerEditDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerEditDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
