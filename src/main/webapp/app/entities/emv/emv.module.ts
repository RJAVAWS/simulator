import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SimulatorSharedModule } from 'app/shared/shared.module';
import { EmvComponent } from './emv.component';
import { EmvDetailComponent } from './emv-detail.component';
import { EmvUpdateComponent } from './emv-update.component';
import { EmvDeleteDialogComponent } from './emv-delete-dialog.component';
import { emvRoute } from './emv.route';

@NgModule({
  imports: [SimulatorSharedModule, RouterModule.forChild(emvRoute)],
  declarations: [EmvComponent, EmvDetailComponent, EmvUpdateComponent, EmvDeleteDialogComponent],
  entryComponents: [EmvDeleteDialogComponent]
})
export class SimulatorEmvModule {}
