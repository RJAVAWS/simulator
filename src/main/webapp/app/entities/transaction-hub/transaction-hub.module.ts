import { NgModule } from '@angular/core';
import { SimulatorSharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { transactionHubRoute } from 'app/entities/transaction-hub/transaction-hub.route';
import { TransactionHubComponent } from 'app/entities/transaction-hub/transaction-hub.component';
import { HubEntityComponent } from 'app/entities/transaction-hub/hub-entity/hub-entity.component';

@NgModule({
  imports: [SimulatorSharedModule, RouterModule.forChild(transactionHubRoute)],
  declarations: [TransactionHubComponent, HubEntityComponent]
})
export class SimulatorTransactionHubModule {}
