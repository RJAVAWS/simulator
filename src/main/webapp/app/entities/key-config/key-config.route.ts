import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IKeyConfig, KeyConfig } from 'app/shared/model/key-config.model';
import { KeyConfigService } from './key-config.service';
import { KeyConfigComponent } from './key-config.component';
import { KeyConfigDetailComponent } from './key-config-detail.component';
import { KeyConfigUpdateComponent } from './key-config-update.component';

@Injectable({ providedIn: 'root' })
export class KeyConfigResolve implements Resolve<IKeyConfig> {
  constructor(private service: KeyConfigService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IKeyConfig> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((keyConfig: HttpResponse<KeyConfig>) => {
          if (keyConfig.body) {
            return of(keyConfig.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new KeyConfig());
  }
}

export const keyConfigRoute: Routes = [
  {
    path: '',
    component: KeyConfigComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'simulatorApp.keyConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: KeyConfigDetailComponent,
    resolve: {
      keyConfig: KeyConfigResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'simulatorApp.keyConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: KeyConfigUpdateComponent,
    resolve: {
      keyConfig: KeyConfigResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'simulatorApp.keyConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: KeyConfigUpdateComponent,
    resolve: {
      keyConfig: KeyConfigResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'simulatorApp.keyConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
