import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMtiConfig } from 'app/shared/model/mti-config.model';
import { MtiConfigService } from './mti-config.service';

@Component({
  templateUrl: './mti-config-delete-dialog.component.html'
})
export class MtiConfigDeleteDialogComponent {
  mtiConfig?: IMtiConfig;

  constructor(protected mtiConfigService: MtiConfigService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mtiConfigService.delete(id).subscribe(() => {
      this.eventManager.broadcast('mtiConfigListModification');
      this.activeModal.close();
    });
  }
}
