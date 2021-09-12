import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerSendMailComponent } from './customer-send-mail.component';

describe('CustomerSendMailComponent', () => {
  let component: CustomerSendMailComponent;
  let fixture: ComponentFixture<CustomerSendMailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerSendMailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerSendMailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
