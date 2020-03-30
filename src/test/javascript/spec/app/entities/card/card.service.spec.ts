import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CardService } from 'app/entities/card/card.service';
import { ICard, Card } from 'app/shared/model/card.model';
import { CardScheme } from 'app/shared/model/enumerations/card-scheme.model';
import { CardType } from 'app/shared/model/enumerations/card-type.model';

describe('Service Tests', () => {
  describe('Card Service', () => {
    let injector: TestBed;
    let service: CardService;
    let httpMock: HttpTestingController;
    let elemDefault: ICard;
    let expectedResult: ICard | ICard[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CardService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Card(0, 'AAAAAAA', CardScheme.MASTER, CardType.MAGNETIC, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Card', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Card()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Card', () => {
        const returnedFromService = Object.assign(
          {
            cardDescription: 'BBBBBB',
            scheme: 'BBBBBB',
            type: 'BBBBBB',
            cardNumber: 'BBBBBB',
            cvv: 'BBBBBB',
            expiry: 'BBBBBB',
            pin: 'BBBBBB',
            track2data: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Card', () => {
        const returnedFromService = Object.assign(
          {
            cardDescription: 'BBBBBB',
            scheme: 'BBBBBB',
            type: 'BBBBBB',
            cardNumber: 'BBBBBB',
            cvv: 'BBBBBB',
            expiry: 'BBBBBB',
            pin: 'BBBBBB',
            track2data: 'BBBBBB'
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

      it('should delete a Card', () => {
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
