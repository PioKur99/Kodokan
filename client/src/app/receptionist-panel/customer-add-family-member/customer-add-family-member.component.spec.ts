import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerAddFamilyMemberComponent } from './customer-add-family-member.component';

describe('CustomerAddFamilyMemberComponent', () => {
  let component: CustomerAddFamilyMemberComponent;
  let fixture: ComponentFixture<CustomerAddFamilyMemberComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerAddFamilyMemberComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerAddFamilyMemberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
