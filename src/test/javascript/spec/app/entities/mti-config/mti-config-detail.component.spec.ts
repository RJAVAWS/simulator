import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SimulatorTestModule } from '../../../test.module';
import { MtiConfigDetailComponent } from 'app/entities/mti-config/mti-config-detail.component';
import { MtiConfig } from 'app/shared/model/mti-config.model';

describe('Component Tests', () => {
  describe('MtiConfig Management Detail Component', () => {
    let comp: MtiConfigDetailComponent;
    let fixture: ComponentFixture<MtiConfigDetailComponent>;
    const route = ({ data: of({ mtiConfig: new MtiConfig(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SimulatorTestModule],
        declarations: [MtiConfigDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MtiConfigDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MtiConfigDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load mtiConfig on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mtiConfig).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
