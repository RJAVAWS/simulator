import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMtiConfig, MtiConfig } from 'app/shared/model/mti-config.model';
import { MtiConfigService } from './mti-config.service';

@Component({
  selector: 'jhi-mti-config-update',
  templateUrl: './mti-config-update.component.html'
})
export class MtiConfigUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    mtiDescription: [
      null,
      [Validators.required, Validators.minLength(3), Validators.maxLength(255), Validators.pattern('[a-zA-Z0-9 _.]*')]
    ],
    tnType: [null, [Validators.required]],
    isoType: [],
    mti: [null, [Validators.required, Validators.pattern('[0-9]*')]],
    repeatMti: [null, [Validators.required, Validators.pattern('[0-9]*')]],
    responseMti: [null, [Validators.required, Validators.pattern('[0-9]*')]],
    responseRepeatMti: [null, [Validators.required, Validators.pattern('[0-9]*')]],
    nmmIdentifierDe: [null, [Validators.pattern('[0-9]*')]],
    nmmIdentifierDeVal: [null, [Validators.pattern('[0-9]*')]],
    responseIdentifierDe: [null, [Validators.required, Validators.pattern('[0-9]*')]],
    responseIdentifierDeVal: [null, [Validators.required, Validators.pattern('[0-9]*')]]
  });

  constructor(protected mtiConfigService: MtiConfigService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mtiConfig }) => {
      this.updateForm(mtiConfig);
    });
  }

  updateForm(mtiConfig: IMtiConfig): void {
    this.editForm.patchValue({
      id: mtiConfig.id,
      mtiDescription: mtiConfig.mtiDescription,
      tnType: mtiConfig.tnType,
      isoType: mtiConfig.isoType,
      mti: mtiConfig.mti,
      repeatMti: mtiConfig.repeatMti,
      responseMti: mtiConfig.responseMti,
      responseRepeatMti: mtiConfig.responseRepeatMti,
      nmmIdentifierDe: mtiConfig.nmmIdentifierDe,
      nmmIdentifierDeVal: mtiConfig.nmmIdentifierDeVal,
      responseIdentifierDe: mtiConfig.responseIdentifierDe,
      responseIdentifierDeVal: mtiConfig.responseIdentifierDeVal
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mtiConfig = this.createFromForm();
    if (mtiConfig.id !== undefined) {
      this.subscribeToSaveResponse(this.mtiConfigService.update(mtiConfig));
    } else {
      this.subscribeToSaveResponse(this.mtiConfigService.create(mtiConfig));
    }
  }

  private createFromForm(): IMtiConfig {
    return {
      ...new MtiConfig(),
      id: this.editForm.get(['id'])!.value,
      mtiDescription: this.editForm.get(['mtiDescription'])!.value,
      tnType: this.editForm.get(['tnType'])!.value,
      isoType: this.editForm.get(['isoType'])!.value,
      mti: this.editForm.get(['mti'])!.value,
      repeatMti: this.editForm.get(['repeatMti'])!.value,
      responseMti: this.editForm.get(['responseMti'])!.value,
      responseRepeatMti: this.editForm.get(['responseRepeatMti'])!.value,
      nmmIdentifierDe: this.editForm.get(['nmmIdentifierDe'])!.value,
      nmmIdentifierDeVal: this.editForm.get(['nmmIdentifierDeVal'])!.value,
      responseIdentifierDe: this.editForm.get(['responseIdentifierDe'])!.value,
      responseIdentifierDeVal: this.editForm.get(['responseIdentifierDeVal'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMtiConfig>>): void {
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
