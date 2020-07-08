import { Component, OnDestroy, OnInit } from '@angular/core';
import { IBank } from 'app/shared/model/bank.model';
import { TransactionHubService } from 'app/entities/transaction-hub/transaction-hub.service';
import { AccountService } from 'app/core/auth/account.service';

@Component({
  selector: 'jhi-transaction-hub',
  templateUrl: './transaction-hub.component.html'
})
export class TransactionHubComponent implements OnInit, OnDestroy {
  bank!: IBank;

  constructor(private transactionHubService: TransactionHubService, private accountService: AccountService) {}

  loadBank(): void {
    this.transactionHubService.findBank(this.accountService.getBankId()).subscribe(bank => {
      this.bank = bank;
    });
  }

  ngOnDestroy(): void {}

  ngOnInit(): void {
    this.loadBank();
  }
}
