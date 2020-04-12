import { ICard } from 'app/shared/model/card.model';
import { IEmv } from 'app/shared/model/emv.model';
import { IKeyConfig } from 'app/shared/model/key-config.model';
import { ITransaction } from 'app/shared/model/transaction.model';
import { IsoVersions } from 'app/shared/model/enumerations/iso-versions.model';

export interface IBank {
  id?: number;
  name?: string;
  code?: string;
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
  emvs?: IEmv[];
  keyConfigs?: IKeyConfig[];
  transactions?: ITransaction[];
}

export class Bank implements IBank {
  constructor(
    public id?: number,
    public name?: string,
    public code?: string,
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
    public emvs?: IEmv[],
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
