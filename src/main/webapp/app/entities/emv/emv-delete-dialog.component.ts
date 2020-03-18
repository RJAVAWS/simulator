import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEmv } from 'app/shared/model/emv.model';
import { EmvService } from './emv.service';

@Component({
  templateUrl: './emv-delete-dialog.component.html'
})
export class EmvDeleteDialogComponent {
  emv?: IEmv;

  constructor(protected emvService: EmvService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.emvService.delete(id).subscribe(() => {
      this.eventManager.broadcast('emvListModification');
      this.activeModal.close();
    });
  }
}
