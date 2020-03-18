import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EmvService } from 'app/entities/emv/emv.service';
import { IEmv, Emv } from 'app/shared/model/emv.model';

describe('Service Tests', () => {
  describe('Emv Service', () => {
    let injector: TestBed;
    let service: EmvService;
    let httpMock: HttpTestingController;
    let elemDefault: IEmv;
    let expectedResult: IEmv | IEmv[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EmvService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Emv(
        0,
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

      it('should create a Emv', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Emv()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Emv', () => {
        const returnedFromService = Object.assign(
          {
            emvDescription: 'BBBBBB',
            de5F2A: 'BBBBBB',
            de82: 'BBBBBB',
            de84: 'BBBBBB',
            de95: 'BBBBBB',
            de9A: 'BBBBBB',
            de9C: 'BBBBBB',
            de9F02: 'BBBBBB',
            de9F03: 'BBBBBB',
            de9F09: 'BBBBBB',
            de9F10: 'BBBBBB',
            de9F1A: 'BBBBBB',
            de9F1E: 'BBBBBB',
            de9F26: 'BBBBBB',
            de9F27: 'BBBBBB',
            de9F33: 'BBBBBB',
            de9F34: 'BBBBBB',
            de9F35: 'BBBBBB',
            de9F36: 'BBBBBB',
            de9F37: 'BBBBBB',
            de9F41: 'BBBBBB',
            de9F53: 'BBBBBB',
            de8A: 'BBBBBB',
            de71: 'BBBBBB',
            de72: 'BBBBBB',
            de91: 'BBBBBB',
            others: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Emv', () => {
        const returnedFromService = Object.assign(
          {
            emvDescription: 'BBBBBB',
            de5F2A: 'BBBBBB',
            de82: 'BBBBBB',
            de84: 'BBBBBB',
            de95: 'BBBBBB',
            de9A: 'BBBBBB',
            de9C: 'BBBBBB',
            de9F02: 'BBBBBB',
            de9F03: 'BBBBBB',
            de9F09: 'BBBBBB',
            de9F10: 'BBBBBB',
            de9F1A: 'BBBBBB',
            de9F1E: 'BBBBBB',
            de9F26: 'BBBBBB',
            de9F27: 'BBBBBB',
            de9F33: 'BBBBBB',
            de9F34: 'BBBBBB',
            de9F35: 'BBBBBB',
            de9F36: 'BBBBBB',
            de9F37: 'BBBBBB',
            de9F41: 'BBBBBB',
            de9F53: 'BBBBBB',
            de8A: 'BBBBBB',
            de71: 'BBBBBB',
            de72: 'BBBBBB',
            de91: 'BBBBBB',
            others: 'BBBBBB'
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

      it('should delete a Emv', () => {
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
