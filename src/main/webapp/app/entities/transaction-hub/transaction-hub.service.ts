import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { ITransactionHub } from 'app/shared/model/transaction-hub.model';
import { SERVER_API_URL } from 'app/app.constants';
import { Observable } from 'rxjs';

type EntityResponseType = HttpResponse<ITransactionHub>;

@Injectable({ providedIn: 'root' })
export class TransactionHubService {
  public resourceUrl = SERVER_API_URL + 'api/transactionsHub';

  constructor(private http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITransactionHub>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
