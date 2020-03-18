import { ITestCase } from 'app/shared/model/test-case.model';

export interface ICard {
  id?: number;
  cardDescription?: string;
  cardNumber?: string;
  cvv?: string;
  expiry?: string;
  pin?: string;
  track2data?: string;
  emvId?: number;
  testCases?: ITestCase[];
  bankId?: number;
}

export class Card implements ICard {
  constructor(
    public id?: number,
    public cardDescription?: string,
    public cardNumber?: string,
    public cvv?: string,
    public expiry?: string,
    public pin?: string,
    public track2data?: string,
    public emvId?: number,
    public testCases?: ITestCase[],
    public bankId?: number
  ) {}
}
