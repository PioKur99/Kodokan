import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewMembershipsComponent } from './new-memberships.component';

describe('NewMembershipsComponent', () => {
  let component: NewMembershipsComponent;
  let fixture: ComponentFixture<NewMembershipsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewMembershipsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewMembershipsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
