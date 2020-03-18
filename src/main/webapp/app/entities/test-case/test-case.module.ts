import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SimulatorSharedModule } from 'app/shared/shared.module';
import { TestCaseComponent } from './test-case.component';
import { TestCaseDetailComponent } from './test-case-detail.component';
import { TestCaseUpdateComponent } from './test-case-update.component';
import { TestCaseDeleteDialogComponent } from './test-case-delete-dialog.component';
import { testCaseRoute } from './test-case.route';

@NgModule({
  imports: [SimulatorSharedModule, RouterModule.forChild(testCaseRoute)],
  declarations: [TestCaseComponent, TestCaseDetailComponent, TestCaseUpdateComponent, TestCaseDeleteDialogComponent],
  entryComponents: [TestCaseDeleteDialogComponent]
})
export class SimulatorTestCaseModule {}
