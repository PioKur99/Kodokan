import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Packages1Component } from './packages1.component';

describe('Packages1Component', () => {
  let component: Packages1Component;
  let fixture: ComponentFixture<Packages1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Packages1Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Packages1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
