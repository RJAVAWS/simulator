import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMtiConfig } from 'app/shared/model/mti-config.model';

type EntityResponseType = HttpResponse<IMtiConfig>;
type EntityArrayResponseType = HttpResponse<IMtiConfig[]>;

@Injectable({ providedIn: 'root' })
export class MtiConfigService {
  public resourceUrl = SERVER_API_URL + 'api/mti-configs';

  constructor(protected http: HttpClient) {}

  create(mtiConfig: IMtiConfig): Observable<EntityResponseType> {
    return this.http.post<IMtiConfig>(this.resourceUrl, mtiConfig, { observe: 'response' });
  }

  update(mtiConfig: IMtiConfig): Observable<EntityResponseType> {
    return this.http.put<IMtiConfig>(this.resourceUrl, mtiConfig, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMtiConfig>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMtiConfig[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
