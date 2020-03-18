import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITestCase, TestCase } from 'app/shared/model/test-case.model';
import { TestCaseService } from './test-case.service';
import { TestCaseComponent } from './test-case.component';
import { TestCaseDetailComponent } from './test-case-detail.component';
import { TestCaseUpdateComponent } from './test-case-update.component';

@Injectable({ providedIn: 'root' })
export class TestCaseResolve implements Resolve<ITestCase> {
  constructor(private service: TestCaseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITestCase> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((testCase: HttpResponse<TestCase>) => {
          if (testCase.body) {
            return of(testCase.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TestCase());
  }
}

export const testCaseRoute: Routes = [
  {
    path: '',
    component: TestCaseComponent,
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'simulatorApp.testCase.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TestCaseDetailComponent,
    resolve: {
      testCase: TestCaseResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'simulatorApp.testCase.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TestCaseUpdateComponent,
    resolve: {
      testCase: TestCaseResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'simulatorApp.testCase.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TestCaseUpdateComponent,
    resolve: {
      testCase: TestCaseResolve
    },
    data: {
      authorities: ['ROLE_ADMIN'],
      pageTitle: 'simulatorApp.testCase.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
