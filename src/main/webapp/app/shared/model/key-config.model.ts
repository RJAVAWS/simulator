import { PinMacType } from 'app/shared/model/enumerations/pin-mac-type.model';

export interface IKeyConfig {
  id?: number;
  pmType?: PinMacType;
  key?: string;
  kcv?: string;
  status?: boolean;
  de01?: string;
  de02?: string;
  de03?: string;
  de04?: string;
  de05?: string;
  bankId?: number;
}

export class KeyConfig implements IKeyConfig {
  constructor(
    public id?: number,
    public pmType?: PinMacType,
    public key?: string,
    public kcv?: string,
    public status?: boolean,
    public de01?: string,
    public de02?: string,
    public de03?: string,
    public de04?: string,
    public de05?: string,
    public bankId?: number
  ) {
    this.status = this.status || false;
  }
}
