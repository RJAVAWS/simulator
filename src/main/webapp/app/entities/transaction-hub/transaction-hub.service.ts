import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { ITransactionHub } from 'app/shared/model/transaction-hub.model';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';
import { IBank } from 'app/shared/model/bank.model';

type EntityResponseType = HttpResponse<ITransactionHub>;

@Injectable({ providedIn: 'root' })
export class TransactionHubService {
  public resourceUrl = SERVER_API_URL + 'api/transactionsHub';
  public bankResourceUrl = SERVER_API_URL + 'api/banks';

  constructor(private http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITransactionHub>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findBank(id: number): Observable<IBank> {
    return this.http.get<IBank>(`${this.bankResourceUrl}/${id}`);
  }
}
