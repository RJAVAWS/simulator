import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IKeyConfig } from 'app/shared/model/key-config.model';

type EntityResponseType = HttpResponse<IKeyConfig>;
type EntityArrayResponseType = HttpResponse<IKeyConfig[]>;

@Injectable({ providedIn: 'root' })
export class KeyConfigService {
  public resourceUrl = SERVER_API_URL + 'api/key-configs';

  constructor(protected http: HttpClient) {}

  create(keyConfig: IKeyConfig): Observable<EntityResponseType> {
    return this.http.post<IKeyConfig>(this.resourceUrl, keyConfig, { observe: 'response' });
  }

  update(keyConfig: IKeyConfig): Observable<EntityResponseType> {
    return this.http.put<IKeyConfig>(this.resourceUrl, keyConfig, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IKeyConfig>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IKeyConfig[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
