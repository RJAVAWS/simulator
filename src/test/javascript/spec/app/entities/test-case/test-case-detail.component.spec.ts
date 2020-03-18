import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { SimulatorTestModule } from '../../../test.module';
import { TestCaseDetailComponent } from 'app/entities/test-case/test-case-detail.component';
import { TestCase } from 'app/shared/model/test-case.model';

describe('Component Tests', () => {
  describe('TestCase Management Detail Component', () => {
    let comp: TestCaseDetailComponent;
    let fixture: ComponentFixture<TestCaseDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ testCase: new TestCase(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SimulatorTestModule],
        declarations: [TestCaseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TestCaseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TestCaseDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load testCase on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.testCase).toEqual(jasmine.objectContaining({ id: 123 }));
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
