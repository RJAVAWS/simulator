import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ICard, Card } from 'app/shared/model/card.model';
import { CardService } from './card.service';
import { IEmv } from 'app/shared/model/emv.model';
import { EmvService } from 'app/entities/emv/emv.service';
import { ITestCase } from 'app/shared/model/test-case.model';
import { TestCaseService } from 'app/entities/test-case/test-case.service';
import { IBank } from 'app/shared/model/bank.model';
import { KvpairService } from 'app/core/keyvaluepair/kvpair.service';
import { AccountService } from 'app/core/auth/account.service';

type SelectableEntity = IEmv | ITestCase | IBank;

@Component({
  selector: 'jhi-card-update',
  templateUrl: './card-update.component.html'
})
export class CardUpdateComponent implements OnInit {
  card!: ICard;
  isSaving = false;
  emvs: IEmv[] = [];
  testcases: ITestCase[] = [];
  isEmv = false;
  emv = 'EMV';
  bankKeyValueMap: Map<any, any> = new Map<any, any>();

  editForm = this.fb.group({
    id: [],
    cardDescription: [
      null,
      [Validators.required, Validators.minLength(3), Validators.maxLength(255), Validators.pattern('[a-zA-Z0-9 _.]*')]
    ],
    scheme: [null, [Validators.required]],
    type: [null, [Validators.required]],
    cardNumber: [null, [Validators.required, Validators.minLength(16), Validators.maxLength(19), Validators.pattern('[0-9]*')]],
    cvv: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(3), Validators.pattern('[0-9]*')]],
    expiry: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(4), Validators.pattern('[0-9]*')]],
    pin: [null, [Validators.required, Validators.minLength(4), Validators.maxLength(4), Validators.pattern('[0-9]*')]],
    track2data: [null, [Validators.required, Validators.pattern('[a-zA-Z0-9=]*')]],
    emvId: [],
    testCases: [],
    bankId: []
  });

  constructor(
    protected cardService: CardService,
    protected emvService: EmvService,
    protected testCaseService: TestCaseService,
    protected kvpairService: KvpairService,
    protected activatedRoute: ActivatedRoute,
    protected accountService: AccountService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ card }) => {
      this.card = card;
      this.updateForm(card);
      this.emvService
        .query({ filter: 'card-is-null' })
        .pipe(
          map((res: HttpResponse<IEmv[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IEmv[]) => {
          if (!card.emvId) {
            this.emvs = resBody;
          } else {
            this.emvService
              .find(card.emvId)
              .pipe(
                map((subRes: HttpResponse<IEmv>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IEmv[]) => (this.emvs = concatRes));
          }
        });

      this.testCaseService.query().subscribe((res: HttpResponse<ITestCase[]>) => (this.testcases = res.body || []));

      this.kvpairService.getBank(this.accountService.getBankId()).subscribe(bankKeyValueMap => {
        this.bankKeyValueMap = bankKeyValueMap;
      });
    });
  }

  updateForm(card: ICard): void {
    if (card.type === this.emv) {
      this.isEmv = true;
    } else {
      this.isEmv = false;
    }
    this.editForm.patchValue({
      id: card.id,
      cardDescription: card.cardDescription,
      scheme: card.scheme,
      type: card.type,
      cardNumber: card.cardNumber,
      cvv: card.cvv,
      expiry: card.expiry,
      pin: card.pin,
      track2data: card.track2data,
      emvId: card.emvId,
      testCases: card.testCases,
      bankId: card.bankId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const card = this.createFromForm();
    if (card.id !== undefined) {
      this.subscribeToSaveResponse(this.cardService.update(card));
    } else {
      this.subscribeToSaveResponse(this.cardService.create(card));
    }
  }

  private createFromForm(): ICard {
    return {
      ...new Card(),
      id: this.editForm.get(['id'])!.value,
      cardDescription: this.editForm.get(['cardDescription'])!.value,
      scheme: this.editForm.get(['scheme'])!.value,
      type: this.editForm.get(['type'])!.value,
      cardNumber: this.editForm.get(['cardNumber'])!.value,
      cvv: this.editForm.get(['cvv'])!.value,
      expiry: this.editForm.get(['expiry'])!.value,
      pin: this.editForm.get(['pin'])!.value,
      track2data: this.editForm.get(['track2data'])!.value,
      emvId: this.editForm.get(['emvId'])!.value,
      testCases: this.editForm.get(['testCases'])!.value,
      bankId: this.editForm.get(['bankId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICard>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: ITestCase[], option: ITestCase): ITestCase {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }

  private onCardTypeChange($event: any): void {
    if ($event.target.value === this.emv) {
      this.isEmv = true;
      this.editForm.get(['emvId'])!.setValidators(Validators.required);
    } else {
      this.isEmv = false;
      this.editForm.get(['emvId'])!.setValue(null);
      this.editForm.get(['emvId'])!.clearValidators();
    }
    this.editForm.get(['emvId'])!.updateValueAndValidity();
  }
}
