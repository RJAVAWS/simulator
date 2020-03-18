import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SimulatorSharedModule } from 'app/shared/shared.module';
import { BankComponent } from './bank.component';
import { BankDetailComponent } from './bank-detail.component';
import { BankUpdateComponent } from './bank-update.component';
import { BankDeleteDialogComponent } from './bank-delete-dialog.component';
import { UiSwitchModule } from 'ngx-toggle-switch';
import { bankRoute } from './bank.route';

@NgModule({
  imports: [SimulatorSharedModule, UiSwitchModule, RouterModule.forChild(bankRoute)],
  declarations: [BankComponent, BankDetailComponent, BankUpdateComponent, BankDeleteDialogComponent],
  entryComponents: [BankDeleteDialogComponent]
})
export class SimulatorBankModule {}
