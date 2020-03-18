import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SimulatorSharedModule } from 'app/shared/shared.module';
import { MtiConfigComponent } from './mti-config.component';
import { MtiConfigDetailComponent } from './mti-config-detail.component';
import { MtiConfigUpdateComponent } from './mti-config-update.component';
import { MtiConfigDeleteDialogComponent } from './mti-config-delete-dialog.component';
import { mtiConfigRoute } from './mti-config.route';

@NgModule({
  imports: [SimulatorSharedModule, RouterModule.forChild(mtiConfigRoute)],
  declarations: [MtiConfigComponent, MtiConfigDetailComponent, MtiConfigUpdateComponent, MtiConfigDeleteDialogComponent],
  entryComponents: [MtiConfigDeleteDialogComponent]
})
export class SimulatorMtiConfigModule {}
