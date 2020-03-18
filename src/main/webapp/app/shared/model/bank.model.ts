import { ICard } from 'app/shared/model/card.model';
import { IKeyConfig } from 'app/shared/model/key-config.model';
import { ITransaction } from 'app/shared/model/transaction.model';
import { IsoVersions } from 'app/shared/model/enumerations/iso-versions.model';

export interface IBank {
  id?: number;
  name?: string;
  code?: number;
  logo?: string;
  ip?: string;
  port?: string;
  isoType?: IsoVersions;
  signOnOffFlag?: boolean;
  pinExchangeFlag?: boolean;
  macExchangeFlag?: boolean;
  echoFlag?: boolean;
  cutoverFlag?: boolean;
  masterKey?: string;
  cards?: ICard[];
  keyConfigs?: IKeyConfig[];
  transactions?: ITransaction[];
}

export class Bank implements IBank {
  constructor(
    public id?: number,
    public name?: string,
    public code?: number,
    public logo?: string,
    public ip?: string,
    public port?: string,
    public isoType?: IsoVersions,
    public signOnOffFlag?: boolean,
    public pinExchangeFlag?: boolean,
    public macExchangeFlag?: boolean,
    public echoFlag?: boolean,
    public cutoverFlag?: boolean,
    public masterKey?: string,
    public cards?: ICard[],
    public keyConfigs?: IKeyConfig[],
    public transactions?: ITransaction[]
  ) {
    this.signOnOffFlag = this.signOnOffFlag || false;
    this.pinExchangeFlag = this.pinExchangeFlag || false;
    this.macExchangeFlag = this.macExchangeFlag || false;
    this.echoFlag = this.echoFlag || false;
    this.cutoverFlag = this.cutoverFlag || false;
  }
}
