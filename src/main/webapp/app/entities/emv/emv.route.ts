import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEmv, Emv } from 'app/shared/model/emv.model';
import { EmvService } from './emv.service';
import { EmvComponent } from './emv.component';
import { EmvDetailComponent } from './emv-detail.component';
import { EmvUpdateComponent } from './emv-update.component';

@Injectable({ providedIn: 'root' })
export class EmvResolve implements Resolve<IEmv> {
  constructor(private service: EmvService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEmv> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((emv: HttpResponse<Emv>) => {
          if (emv.body) {
            return of(emv.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Emv());
  }
}

export const emvRoute: Routes = [
  {
    path: '',
    component: EmvComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'simulatorApp.emv.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EmvDetailComponent,
    resolve: {
      emv: EmvResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'simulatorApp.emv.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EmvUpdateComponent,
    resolve: {
      emv: EmvResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'simulatorApp.emv.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EmvUpdateComponent,
    resolve: {
      emv: EmvResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'simulatorApp.emv.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
