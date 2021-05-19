import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAClientComponent } from './add-a-client.component';

describe('AddAClientComponent', () => {
  let component: AddAClientComponent;
  let fixture: ComponentFixture<AddAClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddAClientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
