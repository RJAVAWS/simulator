import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MtiConfigService } from 'app/entities/mti-config/mti-config.service';
import { IMtiConfig, MtiConfig } from 'app/shared/model/mti-config.model';
import { TranType } from 'app/shared/model/enumerations/tran-type.model';
import { IsoVersions } from 'app/shared/model/enumerations/iso-versions.model';

describe('Service Tests', () => {
  describe('MtiConfig Service', () => {
    let injector: TestBed;
    let service: MtiConfigService;
    let httpMock: HttpTestingController;
    let elemDefault: IMtiConfig;
    let expectedResult: IMtiConfig | IMtiConfig[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MtiConfigService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MtiConfig(
        0,
        'AAAAAAA',
        TranType.NMM,
        IsoVersions.GCCISO87,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a MtiConfig', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new MtiConfig()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a MtiConfig', () => {
        const returnedFromService = Object.assign(
          {
            mtiDescription: 'BBBBBB',
            tnType: 'BBBBBB',
            isoType: 'BBBBBB',
            mti: 'BBBBBB',
            repeatMti: 'BBBBBB',
            responseMti: 'BBBBBB',
            responseRepeatMti: 'BBBBBB',
            nmmIdentifierDe: 'BBBBBB',
            nmmIdentifierDeVal: 'BBBBBB',
            responseIdentifierDe: 'BBBBBB',
            responseIdentifierDeVal: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of MtiConfig', () => {
        const returnedFromService = Object.assign(
          {
            mtiDescription: 'BBBBBB',
            tnType: 'BBBBBB',
            isoType: 'BBBBBB',
            mti: 'BBBBBB',
            repeatMti: 'BBBBBB',
            responseMti: 'BBBBBB',
            responseRepeatMti: 'BBBBBB',
            nmmIdentifierDe: 'BBBBBB',
            nmmIdentifierDeVal: 'BBBBBB',
            responseIdentifierDe: 'BBBBBB',
            responseIdentifierDeVal: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a MtiConfig', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
