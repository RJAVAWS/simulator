import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IEmv, Emv } from 'app/shared/model/emv.model';
import { EmvService } from './emv.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { appEntitiesRoot } from 'app/shared/constants/app.generic.constants';
import { AccountService } from 'app/core/auth/account.service';
import { KvpairService } from 'app/core/keyvaluepair/kvpair.service';

@Component({
  selector: 'jhi-emv-update',
  templateUrl: './emv-update.component.html'
})
export class EmvUpdateComponent implements OnInit {
  isSaving = false;
  emv!: IEmv;
  bankKeyValueMap: Map<number, string> = new Map<number, string>();

  editForm = this.fb.group({
    id: [],
    emvDescription: [
      null,
      [Validators.required, Validators.minLength(3), Validators.maxLength(255), Validators.pattern('[a-zA-Z0-9 _.]*')]
    ],
    de5F2A: [],
    de82: [],
    de84: [],
    de95: [],
    de9A: [],
    de9C: [],
    de9F02: [],
    de9F03: [],
    de9F09: [],
    de9F10: [],
    de9F1A: [],
    de9F1E: [],
    de9F26: [],
    de9F27: [],
    de9F33: [],
    de9F34: [],
    de9F35: [],
    de9F36: [],
    de9F37: [],
    de9F41: [],
    de9F53: [],
    de8A: [],
    de71: [],
    de72: [],
    de91: [],
    others: [],
    bankId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected emvService: EmvService,
    protected activatedRoute: ActivatedRoute,
    protected kvpairService: KvpairService,
    protected accountService: AccountService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ emv }) => {
      this.emv = emv;
      this.updateForm(emv);
      this.kvpairService.getKeyValuePairs(appEntitiesRoot.BANK, this.accountService.getBankId()).subscribe(bankKeyValueMap => {
        this.bankKeyValueMap = bankKeyValueMap;
      });
    });
  }

  updateForm(emv: IEmv): void {
    this.editForm.patchValue({
      id: emv.id,
      emvDescription: emv.emvDescription,
      de5F2A: emv.de5F2A,
      de82: emv.de82,
      de84: emv.de84,
      de95: emv.de95,
      de9A: emv.de9A,
      de9C: emv.de9C,
      de9F02: emv.de9F02,
      de9F03: emv.de9F03,
      de9F09: emv.de9F09,
      de9F10: emv.de9F10,
      de9F1A: emv.de9F1A,
      de9F1E: emv.de9F1E,
      de9F26: emv.de9F26,
      de9F27: emv.de9F27,
      de9F33: emv.de9F33,
      de9F34: emv.de9F34,
      de9F35: emv.de9F35,
      de9F36: emv.de9F36,
      de9F37: emv.de9F37,
      de9F41: emv.de9F41,
      de9F53: emv.de9F53,
      de8A: emv.de8A,
      de71: emv.de71,
      de72: emv.de72,
      de91: emv.de91,
      others: emv.others,
      bankId: emv.bankId
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('simulatorApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const emv = this.createFromForm();
    if (emv.id !== undefined) {
      this.subscribeToSaveResponse(this.emvService.update(emv));
    } else {
      this.subscribeToSaveResponse(this.emvService.create(emv));
    }
  }

  private createFromForm(): IEmv {
    return {
      ...new Emv(),
      id: this.editForm.get(['id'])!.value,
      emvDescription: this.editForm.get(['emvDescription'])!.value,
      de5F2A: this.editForm.get(['de5F2A'])!.value,
      de82: this.editForm.get(['de82'])!.value,
      de84: this.editForm.get(['de84'])!.value,
      de95: this.editForm.get(['de95'])!.value,
      de9A: this.editForm.get(['de9A'])!.value,
      de9C: this.editForm.get(['de9C'])!.value,
      de9F02: this.editForm.get(['de9F02'])!.value,
      de9F03: this.editForm.get(['de9F03'])!.value,
      de9F09: this.editForm.get(['de9F09'])!.value,
      de9F10: this.editForm.get(['de9F10'])!.value,
      de9F1A: this.editForm.get(['de9F1A'])!.value,
      de9F1E: this.editForm.get(['de9F1E'])!.value,
      de9F26: this.editForm.get(['de9F26'])!.value,
      de9F27: this.editForm.get(['de9F27'])!.value,
      de9F33: this.editForm.get(['de9F33'])!.value,
      de9F34: this.editForm.get(['de9F34'])!.value,
      de9F35: this.editForm.get(['de9F35'])!.value,
      de9F36: this.editForm.get(['de9F36'])!.value,
      de9F37: this.editForm.get(['de9F37'])!.value,
      de9F41: this.editForm.get(['de9F41'])!.value,
      de9F53: this.editForm.get(['de9F53'])!.value,
      de8A: this.editForm.get(['de8A'])!.value,
      de71: this.editForm.get(['de71'])!.value,
      de72: this.editForm.get(['de72'])!.value,
      de91: this.editForm.get(['de91'])!.value,
      others: this.editForm.get(['others'])!.value,
      bankId: this.editForm.get(['bankId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmv>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
