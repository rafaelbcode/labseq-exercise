import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LabseqComponent } from './labseq.component';

describe('LabseqComponent', () => {
  let component: LabseqComponent;
  let fixture: ComponentFixture<LabseqComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LabseqComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LabseqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
