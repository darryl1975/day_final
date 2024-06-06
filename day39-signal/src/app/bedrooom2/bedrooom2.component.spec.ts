import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Bedrooom2Component } from './bedrooom2.component';

describe('Bedrooom2Component', () => {
  let component: Bedrooom2Component;
  let fixture: ComponentFixture<Bedrooom2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Bedrooom2Component]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Bedrooom2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
