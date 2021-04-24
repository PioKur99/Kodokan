import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientPanel1Component } from './client-panel1.component';

describe('ClientPanel1Component', () => {
  let component: ClientPanel1Component;
  let fixture: ComponentFixture<ClientPanel1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientPanel1Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientPanel1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
