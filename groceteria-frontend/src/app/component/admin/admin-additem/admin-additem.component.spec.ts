import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAdditemComponent } from './admin-additem.component';

describe('AdminAdditemComponent', () => {
  let component: AdminAdditemComponent;
  let fixture: ComponentFixture<AdminAdditemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminAdditemComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminAdditemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
