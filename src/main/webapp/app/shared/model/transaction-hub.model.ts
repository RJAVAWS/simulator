export interface ITransactionHub {
  id?: number;
}

export class TransactionHub implements ITransactionHub {
  constructor(public id?: number) {}
}
