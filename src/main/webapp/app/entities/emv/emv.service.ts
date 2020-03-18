import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEmv } from 'app/shared/model/emv.model';

type EntityResponseType = HttpResponse<IEmv>;
type EntityArrayResponseType = HttpResponse<IEmv[]>;

@Injectable({ providedIn: 'root' })
export class EmvService {
  public resourceUrl = SERVER_API_URL + 'api/emvs';

  constructor(protected http: HttpClient) {}

  create(emv: IEmv): Observable<EntityResponseType> {
    return this.http.post<IEmv>(this.resourceUrl, emv, { observe: 'response' });
  }

  update(emv: IEmv): Observable<EntityResponseType> {
    return this.http.put<IEmv>(this.resourceUrl, emv, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEmv>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEmv[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
