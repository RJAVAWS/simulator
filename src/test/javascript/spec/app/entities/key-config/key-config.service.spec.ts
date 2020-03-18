import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { KeyConfigService } from 'app/entities/key-config/key-config.service';
import { IKeyConfig, KeyConfig } from 'app/shared/model/key-config.model';
import { PinMacType } from 'app/shared/model/enumerations/pin-mac-type.model';

describe('Service Tests', () => {
  describe('KeyConfig Service', () => {
    let injector: TestBed;
    let service: KeyConfigService;
    let httpMock: HttpTestingController;
    let elemDefault: IKeyConfig;
    let expectedResult: IKeyConfig | IKeyConfig[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(KeyConfigService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new KeyConfig(0, PinMacType.PIN, 'AAAAAAA', 'AAAAAAA', false, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a KeyConfig', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new KeyConfig()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a KeyConfig', () => {
        const returnedFromService = Object.assign(
          {
            pmType: 'BBBBBB',
            key: 'BBBBBB',
            kcv: 'BBBBBB',
            status: true,
            de01: 'BBBBBB',
            de02: 'BBBBBB',
            de03: 'BBBBBB',
            de04: 'BBBBBB',
            de05: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of KeyConfig', () => {
        const returnedFromService = Object.assign(
          {
            pmType: 'BBBBBB',
            key: 'BBBBBB',
            kcv: 'BBBBBB',
            status: true,
            de01: 'BBBBBB',
            de02: 'BBBBBB',
            de03: 'BBBBBB',
            de04: 'BBBBBB',
            de05: 'BBBBBB'
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

      it('should delete a KeyConfig', () => {
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
