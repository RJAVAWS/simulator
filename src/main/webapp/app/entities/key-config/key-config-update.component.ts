import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IKeyConfig, KeyConfig } from 'app/shared/model/key-config.model';
import { KeyConfigService } from './key-config.service';
import { KvpairService } from 'app/core/keyvaluepair/kvpair.service';
import { appEntitiesRoot } from 'app/shared/constants/app.generic.constants';
import { AccountService } from 'app/core/auth/account.service';

@Component({
  selector: 'jhi-key-config-update',
  templateUrl: './key-config-update.component.html'
})
export class KeyConfigUpdateComponent implements OnInit {
  isSaving = false;
  keyConfig!: IKeyConfig;
  bankKeyValueMap: Map<number, string> = new Map<number, string>();

  editForm = this.fb.group({
    id: [],
    pmType: [],
    key: [null, [Validators.required, Validators.pattern('[a-zA-Z0-9]*')]],
    kcv: [null, [Validators.required, Validators.pattern('[a-zA-Z0-9]*')]],
    status: [],
    de01: [],
    de02: [],
    de03: [],
    de04: [],
    de05: [],
    bankId: []
  });

  constructor(
    protected keyConfigService: KeyConfigService,
    protected activatedRoute: ActivatedRoute,
    protected kvpairService: KvpairService,
    protected accountService: AccountService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ keyConfig }) => {
      this.keyConfig = keyConfig;
      this.updateForm(keyConfig);

      this.kvpairService.getKeyValuePairs(appEntitiesRoot.BANK, this.accountService.getBankId()).subscribe(bankKeyValueMap => {
        this.bankKeyValueMap = bankKeyValueMap;
      });
    });
  }

  updateForm(keyConfig: IKeyConfig): void {
    this.editForm.patchValue({
      id: keyConfig.id,
      pmType: keyConfig.pmType,
      key: keyConfig.key,
      kcv: keyConfig.kcv,
      status: keyConfig.status,
      de01: keyConfig.de01,
      de02: keyConfig.de02,
      de03: keyConfig.de03,
      de04: keyConfig.de04,
      de05: keyConfig.de05,
      bankId: keyConfig.bankId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const keyConfig = this.createFromForm();
    if (keyConfig.id !== undefined) {
      this.subscribeToSaveResponse(this.keyConfigService.update(keyConfig));
    } else {
      this.subscribeToSaveResponse(this.keyConfigService.create(keyConfig));
    }
  }

  private createFromForm(): IKeyConfig {
    return {
      ...new KeyConfig(),
      id: this.editForm.get(['id'])!.value,
      pmType: this.editForm.get(['pmType'])!.value,
      key: this.editForm.get(['key'])!.value,
      kcv: this.editForm.get(['kcv'])!.value,
      status: this.editForm.get(['status'])!.value,
      de01: this.editForm.get(['de01'])!.value,
      de02: this.editForm.get(['de02'])!.value,
      de03: this.editForm.get(['de03'])!.value,
      de04: this.editForm.get(['de04'])!.value,
      de05: this.editForm.get(['de05'])!.value,
      bankId: this.editForm.get(['bankId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IKeyConfig>>): void {
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
