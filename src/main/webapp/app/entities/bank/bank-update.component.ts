import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { fontAwesomeIcons } from '../../core/icons/font-awesome-icons';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBank, Bank } from 'app/shared/model/bank.model';
import { BankService } from './bank.service';

@Component({
  selector: 'jhi-bank-update',
  templateUrl: './bank-update.component.html'
})
export class BankUpdateComponent implements OnInit {
  isSaving = false;
  iconsList = fontAwesomeIcons;
  selectedIcon: string | undefined = '';
  private defaultIcon = 'landmark';

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(150), Validators.pattern('[a-zA-Z ]*')]],
    code: [null, [Validators.required, Validators.minLength(9), Validators.maxLength(150), Validators.pattern('[0-9]*')]],
    logo: [],
    ip: [
      null,
      [
        Validators.required,
        Validators.pattern('(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))')
      ]
    ],
    port: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(10), Validators.pattern('[0-9]*')]],
    isoType: [null, [Validators.required]],
    signOnOffFlag: [null, [Validators.required]],
    pinExchangeFlag: [null, [Validators.required]],
    macExchangeFlag: [null, [Validators.required]],
    echoFlag: [null, [Validators.required]],
    cutoverFlag: [null, [Validators.required]],
    masterKey: [null, [Validators.required, Validators.pattern('^[a-zA-Z0-9]+$')]]
  });

  constructor(protected bankService: BankService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bank }) => {
      this.updateForm(bank);
    });
  }

  updateForm(bank: IBank): void {
    if (bank.logo !== null && bank.logo !== '' && bank.logo !== undefined) {
      this.selectedIcon = bank.logo;
    } else {
      this.selectedIcon = this.defaultIcon;
    }
    this.editForm.patchValue({
      id: bank.id,
      name: bank.name,
      code: bank.code,
      logo: bank.logo === null || (bank.logo === '' && bank.logo !== undefined) ? this.defaultIcon : bank.logo,
      ip: bank.ip,
      port: bank.port,
      isoType: bank.isoType,
      signOnOffFlag: bank.signOnOffFlag,
      pinExchangeFlag: bank.pinExchangeFlag,
      macExchangeFlag: bank.macExchangeFlag,
      echoFlag: bank.echoFlag,
      cutoverFlag: bank.cutoverFlag,
      masterKey: bank.masterKey
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bank = this.createFromForm();
    if (bank.id !== undefined) {
      this.subscribeToSaveResponse(this.bankService.update(bank));
    } else {
      this.subscribeToSaveResponse(this.bankService.create(bank));
    }
  }

  private createFromForm(): IBank {
    return {
      ...new Bank(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      code: this.editForm.get(['code'])!.value,
      logo: this.editForm.get(['logo'])!.value,
      ip: this.editForm.get(['ip'])!.value,
      port: this.editForm.get(['port'])!.value,
      isoType: this.editForm.get(['isoType'])!.value,
      signOnOffFlag: this.editForm.get(['signOnOffFlag'])!.value,
      pinExchangeFlag: this.editForm.get(['pinExchangeFlag'])!.value,
      macExchangeFlag: this.editForm.get(['macExchangeFlag'])!.value,
      echoFlag: this.editForm.get(['echoFlag'])!.value,
      cutoverFlag: this.editForm.get(['cutoverFlag'])!.value,
      masterKey: this.editForm.get(['masterKey'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBank>>): void {
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

  private onSelectIcon(selectedIcon: string): void {
    this.editForm.patchValue({ logo: selectedIcon });
    this.selectedIcon = selectedIcon;
  }
}
