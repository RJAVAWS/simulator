import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMtiConfig, MtiConfig } from 'app/shared/model/mti-config.model';
import { MtiConfigService } from './mti-config.service';
import { MtiConfigComponent } from './mti-config.component';
import { MtiConfigDetailComponent } from './mti-config-detail.component';
import { MtiConfigUpdateComponent } from './mti-config-update.component';

@Injectable({ providedIn: 'root' })
export class MtiConfigResolve implements Resolve<IMtiConfig> {
  constructor(private service: MtiConfigService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMtiConfig> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mtiConfig: HttpResponse<MtiConfig>) => {
          if (mtiConfig.body) {
            return of(mtiConfig.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MtiConfig());
  }
}

export const mtiConfigRoute: Routes = [
  {
    path: '',
    component: MtiConfigComponent,
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'simulatorApp.mtiConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MtiConfigDetailComponent,
    resolve: {
      mtiConfig: MtiConfigResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'simulatorApp.mtiConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MtiConfigUpdateComponent,
    resolve: {
      mtiConfig: MtiConfigResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'simulatorApp.mtiConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MtiConfigUpdateComponent,
    resolve: {
      mtiConfig: MtiConfigResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'simulatorApp.mtiConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
