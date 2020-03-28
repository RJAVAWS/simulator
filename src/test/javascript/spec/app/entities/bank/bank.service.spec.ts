import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BankService } from 'app/entities/bank/bank.service';
import { IBank, Bank } from 'app/shared/model/bank.model';
import { IsoVersions } from 'app/shared/model/enumerations/iso-versions.model';

describe('Service Tests', () => {
  describe('Bank Service', () => {
    let injector: TestBed;
    let service: BankService;
    let httpMock: HttpTestingController;
    let elemDefault: IBank;
    let expectedResult: IBank | IBank[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BankService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Bank(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        IsoVersions.GCCISO87,
        false,
        false,
        false,
        false,
        false,
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

      it('should create a Bank', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Bank()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Bank', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            code: 'BBBBBB',
            logo: 'BBBBBB',
            ip: 'BBBBBB',
            port: 'BBBBBB',
            isoType: 'BBBBBB',
            signOnOffFlag: true,
            pinExchangeFlag: true,
            macExchangeFlag: true,
            echoFlag: true,
            cutoverFlag: true,
            masterKey: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Bank', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            code: 'BBBBBB',
            logo: 'BBBBBB',
            ip: 'BBBBBB',
            port: 'BBBBBB',
            isoType: 'BBBBBB',
            signOnOffFlag: true,
            pinExchangeFlag: true,
            macExchangeFlag: true,
            echoFlag: true,
            cutoverFlag: true,
            masterKey: 'BBBBBB'
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

      it('should delete a Bank', () => {
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
