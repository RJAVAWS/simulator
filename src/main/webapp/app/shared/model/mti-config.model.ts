import { TranType } from 'app/shared/model/enumerations/tran-type.model';
import { IsoVersions } from 'app/shared/model/enumerations/iso-versions.model';

export interface IMtiConfig {
  id?: number;
  mtiDescription?: string;
  tnType?: TranType;
  isoType?: IsoVersions;
  mti?: string;
  repeatMti?: string;
  responseMti?: string;
  responseRepeatMti?: string;
  nmmIdentifierDe?: string;
  nmmIdentifierDeVal?: string;
  responseIdentifierDe?: string;
  responseIdentifierDeVal?: string;
}

export class MtiConfig implements IMtiConfig {
  constructor(
    public id?: number,
    public mtiDescription?: string,
    public tnType?: TranType,
    public isoType?: IsoVersions,
    public mti?: string,
    public repeatMti?: string,
    public responseMti?: string,
    public responseRepeatMti?: string,
    public nmmIdentifierDe?: string,
    public nmmIdentifierDeVal?: string,
    public responseIdentifierDe?: string,
    public responseIdentifierDeVal?: string
  ) {}
}
