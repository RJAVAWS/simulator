export interface IEmv {
  id?: number;
  emvDescription?: string;
  de5F2A?: any;
  de82?: any;
  de84?: any;
  de95?: any;
  de9A?: any;
  de9C?: any;
  de9F02?: any;
  de9F03?: any;
  de9F09?: any;
  de9F10?: any;
  de9F1A?: any;
  de9F1E?: any;
  de9F26?: any;
  de9F27?: any;
  de9F33?: any;
  de9F34?: any;
  de9F35?: any;
  de9F36?: any;
  de9F37?: any;
  de9F41?: any;
  de9F53?: any;
  de8A?: any;
  de71?: any;
  de72?: any;
  de91?: any;
  others?: any;
  bankId?: number;
}

export class Emv implements IEmv {
  constructor(
    public id?: number,
    public emvDescription?: string,
    public de5F2A?: any,
    public de82?: any,
    public de84?: any,
    public de95?: any,
    public de9A?: any,
    public de9C?: any,
    public de9F02?: any,
    public de9F03?: any,
    public de9F09?: any,
    public de9F10?: any,
    public de9F1A?: any,
    public de9F1E?: any,
    public de9F26?: any,
    public de9F27?: any,
    public de9F33?: any,
    public de9F34?: any,
    public de9F35?: any,
    public de9F36?: any,
    public de9F37?: any,
    public de9F41?: any,
    public de9F53?: any,
    public de8A?: any,
    public de71?: any,
    public de72?: any,
    public de91?: any,
    public others?: any,
    public bankId?: number
  ) {}
}
