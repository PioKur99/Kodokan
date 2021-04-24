import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalData1Component } from './personal-data1.component';

describe('PersonalData1Component', () => {
  let component: PersonalData1Component;
  let fixture: ComponentFixture<PersonalData1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonalData1Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonalData1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
