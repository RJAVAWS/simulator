import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKeyConfig } from 'app/shared/model/key-config.model';
import { KeyConfigService } from './key-config.service';

@Component({
  templateUrl: './key-config-delete-dialog.component.html'
})
export class KeyConfigDeleteDialogComponent {
  keyConfig?: IKeyConfig;

  constructor(protected keyConfigService: KeyConfigService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.keyConfigService.delete(id).subscribe(() => {
      this.eventManager.broadcast('keyConfigListModification');
      this.activeModal.close();
    });
  }
}
