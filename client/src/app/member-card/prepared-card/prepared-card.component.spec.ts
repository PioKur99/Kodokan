import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreparedCardComponent } from './prepared-card.component';

describe('PreparedCardComponent', () => {
  let component: PreparedCardComponent;
  let fixture: ComponentFixture<PreparedCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreparedCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreparedCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
