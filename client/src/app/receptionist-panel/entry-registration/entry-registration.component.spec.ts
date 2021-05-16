import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntryRegistrationComponent } from './entry-registration.component';

describe('EntryRegistrationComponent', () => {
  let component: EntryRegistrationComponent;
  let fixture: ComponentFixture<EntryRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntryRegistrationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EntryRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
