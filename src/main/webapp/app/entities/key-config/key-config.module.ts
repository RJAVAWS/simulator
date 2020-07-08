import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SimulatorSharedModule } from 'app/shared/shared.module';
import { KeyConfigComponent } from './key-config.component';
import { KeyConfigDetailComponent } from './key-config-detail.component';
import { KeyConfigUpdateComponent } from './key-config-update.component';
import { KeyConfigDeleteDialogComponent } from './key-config-delete-dialog.component';
import { keyConfigRoute } from './key-config.route';
import { UiSwitchModule } from 'ngx-toggle-switch';

@NgModule({
  imports: [SimulatorSharedModule, UiSwitchModule, RouterModule.forChild(keyConfigRoute)],
  declarations: [KeyConfigComponent, KeyConfigDetailComponent, KeyConfigUpdateComponent, KeyConfigDeleteDialogComponent],
  entryComponents: [KeyConfigDeleteDialogComponent]
})
export class SimulatorKeyConfigModule {}
