import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';

@Injectable({ providedIn: 'root' })
export class KvpairService {
  constructor(private http: HttpClient) {}

  getKeyValuePairs(module: string, id?: any): Observable<Map<number, string>> {
    let options: HttpParams = new HttpParams();
    if (id) {
      options = options.set('id', id);
    }
    return this.http.get<Map<number, string>>(SERVER_API_URL + 'api/' + module + '/kvpairs', { params: options });
  }
}
