import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { SimulatorTestModule } from '../../../test.module';
import { EmvDetailComponent } from 'app/entities/emv/emv-detail.component';
import { Emv } from 'app/shared/model/emv.model';

describe('Component Tests', () => {
  describe('Emv Management Detail Component', () => {
    let comp: EmvDetailComponent;
    let fixture: ComponentFixture<EmvDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ emv: new Emv(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SimulatorTestModule],
        declarations: [EmvDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EmvDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EmvDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load emv on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.emv).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
