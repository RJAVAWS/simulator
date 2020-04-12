import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICard } from 'app/shared/model/card.model';

type EntityResponseType = HttpResponse<ICard>;
type EntityArrayResponseType = HttpResponse<ICard[]>;

@Injectable({ providedIn: 'root' })
export class CardService {
  public resourceUrl = SERVER_API_URL + 'api/cards';

  constructor(protected http: HttpClient) {}

  create(card: ICard): Observable<EntityResponseType> {
    return this.http.post<ICard>(this.resourceUrl, card, { observe: 'response' });
  }

  update(card: ICard): Observable<EntityResponseType> {
    return this.http.put<ICard>(this.resourceUrl, card, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICard>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findLinkedTestCase(id: any): Observable<Array<number>> {
    let options: HttpParams = new HttpParams();
    if (id) {
      options = options.set('id', id);
    }
    return this.http.get<Array<number>>(this.resourceUrl + '/linkedtestcases', { params: options });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICard[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
