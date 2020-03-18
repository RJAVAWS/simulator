import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestCaseService } from 'app/entities/test-case/test-case.service';
import { ITestCase, TestCase } from 'app/shared/model/test-case.model';
import { PsdnType } from 'app/shared/model/enumerations/psdn-type.model';
import { ReqResType } from 'app/shared/model/enumerations/req-res-type.model';

describe('Service Tests', () => {
  describe('TestCase Service', () => {
    let injector: TestBed;
    let service: TestCaseService;
    let httpMock: HttpTestingController;
    let elemDefault: ITestCase;
    let expectedResult: ITestCase | ITestCase[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TestCaseService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TestCase(
        0,
        'AAAAAAA',
        PsdnType.PRIMARY,
        ReqResType.REQUEST,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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

      it('should create a TestCase', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TestCase()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TestCase', () => {
        const returnedFromService = Object.assign(
          {
            caseDescription: 'BBBBBB',
            psdnType: 'BBBBBB',
            reqResType: 'BBBBBB',
            mti: 'BBBBBB',
            de001: 'BBBBBB',
            de002: 'BBBBBB',
            de003: 'BBBBBB',
            de004: 'BBBBBB',
            de005: 'BBBBBB',
            de006: 'BBBBBB',
            de007: 'BBBBBB',
            de008: 'BBBBBB',
            de009: 'BBBBBB',
            de010: 'BBBBBB',
            de011: 'BBBBBB',
            de012: 'BBBBBB',
            de013: 'BBBBBB',
            de014: 'BBBBBB',
            de015: 'BBBBBB',
            de016: 'BBBBBB',
            de017: 'BBBBBB',
            de018: 'BBBBBB',
            de019: 'BBBBBB',
            de020: 'BBBBBB',
            de021: 'BBBBBB',
            de022: 'BBBBBB',
            de023: 'BBBBBB',
            de024: 'BBBBBB',
            de025: 'BBBBBB',
            de026: 'BBBBBB',
            de027: 'BBBBBB',
            de028: 'BBBBBB',
            de029: 'BBBBBB',
            de030: 'BBBBBB',
            de031: 'BBBBBB',
            de032: 'BBBBBB',
            de033: 'BBBBBB',
            de034: 'BBBBBB',
            de035: 'BBBBBB',
            de036: 'BBBBBB',
            de037: 'BBBBBB',
            de038: 'BBBBBB',
            de039: 'BBBBBB',
            de040: 'BBBBBB',
            de041: 'BBBBBB',
            de042: 'BBBBBB',
            de043: 'BBBBBB',
            de044: 'BBBBBB',
            de045: 'BBBBBB',
            de046: 'BBBBBB',
            de047: 'BBBBBB',
            de048: 'BBBBBB',
            de049: 'BBBBBB',
            de050: 'BBBBBB',
            de051: 'BBBBBB',
            de052: 'BBBBBB',
            de053: 'BBBBBB',
            de054: 'BBBBBB',
            de055: 'BBBBBB',
            de056: 'BBBBBB',
            de057: 'BBBBBB',
            de058: 'BBBBBB',
            de059: 'BBBBBB',
            de060: 'BBBBBB',
            de061: 'BBBBBB',
            de062: 'BBBBBB',
            de063: 'BBBBBB',
            de064: 'BBBBBB',
            de065: 'BBBBBB',
            de066: 'BBBBBB',
            de067: 'BBBBBB',
            de068: 'BBBBBB',
            de069: 'BBBBBB',
            de070: 'BBBBBB',
            de071: 'BBBBBB',
            de072: 'BBBBBB',
            de073: 'BBBBBB',
            de074: 'BBBBBB',
            de075: 'BBBBBB',
            de076: 'BBBBBB',
            de077: 'BBBBBB',
            de078: 'BBBBBB',
            de079: 'BBBBBB',
            de080: 'BBBBBB',
            de081: 'BBBBBB',
            de082: 'BBBBBB',
            de083: 'BBBBBB',
            de084: 'BBBBBB',
            de085: 'BBBBBB',
            de086: 'BBBBBB',
            de087: 'BBBBBB',
            de088: 'BBBBBB',
            de089: 'BBBBBB',
            de090: 'BBBBBB',
            de091: 'BBBBBB',
            de092: 'BBBBBB',
            de093: 'BBBBBB',
            de094: 'BBBBBB',
            de095: 'BBBBBB',
            de096: 'BBBBBB',
            de097: 'BBBBBB',
            de098: 'BBBBBB',
            de099: 'BBBBBB',
            de100: 'BBBBBB',
            de101: 'BBBBBB',
            de102: 'BBBBBB',
            de103: 'BBBBBB',
            de104: 'BBBBBB',
            de105: 'BBBBBB',
            de106: 'BBBBBB',
            de107: 'BBBBBB',
            de108: 'BBBBBB',
            de109: 'BBBBBB',
            de110: 'BBBBBB',
            de111: 'BBBBBB',
            de112: 'BBBBBB',
            de113: 'BBBBBB',
            de114: 'BBBBBB',
            de115: 'BBBBBB',
            de116: 'BBBBBB',
            de117: 'BBBBBB',
            de118: 'BBBBBB',
            de119: 'BBBBBB',
            de120: 'BBBBBB',
            de121: 'BBBBBB',
            de122: 'BBBBBB',
            de123: 'BBBBBB',
            de124: 'BBBBBB',
            de125: 'BBBBBB',
            de126: 'BBBBBB',
            de127: 'BBBBBB',
            de128: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TestCase', () => {
        const returnedFromService = Object.assign(
          {
            caseDescription: 'BBBBBB',
            psdnType: 'BBBBBB',
            reqResType: 'BBBBBB',
            mti: 'BBBBBB',
            de001: 'BBBBBB',
            de002: 'BBBBBB',
            de003: 'BBBBBB',
            de004: 'BBBBBB',
            de005: 'BBBBBB',
            de006: 'BBBBBB',
            de007: 'BBBBBB',
            de008: 'BBBBBB',
            de009: 'BBBBBB',
            de010: 'BBBBBB',
            de011: 'BBBBBB',
            de012: 'BBBBBB',
            de013: 'BBBBBB',
            de014: 'BBBBBB',
            de015: 'BBBBBB',
            de016: 'BBBBBB',
            de017: 'BBBBBB',
            de018: 'BBBBBB',
            de019: 'BBBBBB',
            de020: 'BBBBBB',
            de021: 'BBBBBB',
            de022: 'BBBBBB',
            de023: 'BBBBBB',
            de024: 'BBBBBB',
            de025: 'BBBBBB',
            de026: 'BBBBBB',
            de027: 'BBBBBB',
            de028: 'BBBBBB',
            de029: 'BBBBBB',
            de030: 'BBBBBB',
            de031: 'BBBBBB',
            de032: 'BBBBBB',
            de033: 'BBBBBB',
            de034: 'BBBBBB',
            de035: 'BBBBBB',
            de036: 'BBBBBB',
            de037: 'BBBBBB',
            de038: 'BBBBBB',
            de039: 'BBBBBB',
            de040: 'BBBBBB',
            de041: 'BBBBBB',
            de042: 'BBBBBB',
            de043: 'BBBBBB',
            de044: 'BBBBBB',
            de045: 'BBBBBB',
            de046: 'BBBBBB',
            de047: 'BBBBBB',
            de048: 'BBBBBB',
            de049: 'BBBBBB',
            de050: 'BBBBBB',
            de051: 'BBBBBB',
            de052: 'BBBBBB',
            de053: 'BBBBBB',
            de054: 'BBBBBB',
            de055: 'BBBBBB',
            de056: 'BBBBBB',
            de057: 'BBBBBB',
            de058: 'BBBBBB',
            de059: 'BBBBBB',
            de060: 'BBBBBB',
            de061: 'BBBBBB',
            de062: 'BBBBBB',
            de063: 'BBBBBB',
            de064: 'BBBBBB',
            de065: 'BBBBBB',
            de066: 'BBBBBB',
            de067: 'BBBBBB',
            de068: 'BBBBBB',
            de069: 'BBBBBB',
            de070: 'BBBBBB',
            de071: 'BBBBBB',
            de072: 'BBBBBB',
            de073: 'BBBBBB',
            de074: 'BBBBBB',
            de075: 'BBBBBB',
            de076: 'BBBBBB',
            de077: 'BBBBBB',
            de078: 'BBBBBB',
            de079: 'BBBBBB',
            de080: 'BBBBBB',
            de081: 'BBBBBB',
            de082: 'BBBBBB',
            de083: 'BBBBBB',
            de084: 'BBBBBB',
            de085: 'BBBBBB',
            de086: 'BBBBBB',
            de087: 'BBBBBB',
            de088: 'BBBBBB',
            de089: 'BBBBBB',
            de090: 'BBBBBB',
            de091: 'BBBBBB',
            de092: 'BBBBBB',
            de093: 'BBBBBB',
            de094: 'BBBBBB',
            de095: 'BBBBBB',
            de096: 'BBBBBB',
            de097: 'BBBBBB',
            de098: 'BBBBBB',
            de099: 'BBBBBB',
            de100: 'BBBBBB',
            de101: 'BBBBBB',
            de102: 'BBBBBB',
            de103: 'BBBBBB',
            de104: 'BBBBBB',
            de105: 'BBBBBB',
            de106: 'BBBBBB',
            de107: 'BBBBBB',
            de108: 'BBBBBB',
            de109: 'BBBBBB',
            de110: 'BBBBBB',
            de111: 'BBBBBB',
            de112: 'BBBBBB',
            de113: 'BBBBBB',
            de114: 'BBBBBB',
            de115: 'BBBBBB',
            de116: 'BBBBBB',
            de117: 'BBBBBB',
            de118: 'BBBBBB',
            de119: 'BBBBBB',
            de120: 'BBBBBB',
            de121: 'BBBBBB',
            de122: 'BBBBBB',
            de123: 'BBBBBB',
            de124: 'BBBBBB',
            de125: 'BBBBBB',
            de126: 'BBBBBB',
            de127: 'BBBBBB',
            de128: 'BBBBBB'
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

      it('should delete a TestCase', () => {
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
