import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SimulatorSharedModule } from 'app/shared/shared.module';
import { CardComponent } from './card.component';
import { CardDetailComponent } from './card-detail.component';
import { CardUpdateComponent } from './card-update.component';
import { CardDeleteDialogComponent } from './card-delete-dialog.component';
import { cardRoute } from './card.route';
import { AngularBootstrapCheckboxGroupModule } from 'angular-bootstrap-checkbox-group';
import { TestCaseLinkComponent } from 'app/entities/card/test-case-link/test-case-link.component';

@NgModule({
  imports: [SimulatorSharedModule, AngularBootstrapCheckboxGroupModule, RouterModule.forChild(cardRoute)],
  declarations: [CardComponent, CardDetailComponent, CardUpdateComponent, CardDeleteDialogComponent, TestCaseLinkComponent],
  entryComponents: [CardDeleteDialogComponent]
})
export class SimulatorCardModule {}
