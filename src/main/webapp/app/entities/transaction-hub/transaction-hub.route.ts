import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { TransactionHubComponent } from 'app/entities/transaction-hub/transaction-hub.component';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITransactionHub, TransactionHub } from 'app/shared/model/transaction-hub.model';
import { EMPTY, Observable, of } from 'rxjs';
import { flatMap } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { TransactionHubService } from 'app/entities/transaction-hub/transaction-hub.service';

export class TransactionHubResolve implements Resolve<ITransactionHub> {
  constructor(private service: TransactionHubService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITransactionHub> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((transactionHub: HttpResponse<TransactionHub>) => {
          if (transactionHub.body) {
            return of(transactionHub.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TransactionHub());
  }
}

export const transactionHubRoute: Routes = [
  {
    path: '',
    component: TransactionHubComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'simulatorApp.transaction-hub.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
