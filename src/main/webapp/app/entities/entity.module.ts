import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'bank',
        loadChildren: () => import('./bank/bank.module').then(m => m.SimulatorBankModule)
      },
      {
        path: 'card',
        loadChildren: () => import('./card/card.module').then(m => m.SimulatorCardModule)
      },
      {
        path: 'emv',
        loadChildren: () => import('./emv/emv.module').then(m => m.SimulatorEmvModule)
      },
      {
        path: 'test-case',
        loadChildren: () => import('./test-case/test-case.module').then(m => m.SimulatorTestCaseModule)
      },
      {
        path: 'mti-config',
        loadChildren: () => import('./mti-config/mti-config.module').then(m => m.SimulatorMtiConfigModule)
      },
      {
        path: 'key-config',
        loadChildren: () => import('./key-config/key-config.module').then(m => m.SimulatorKeyConfigModule)
      },
      {
        path: 'transaction',
        loadChildren: () => import('./transaction/transaction.module').then(m => m.SimulatorTransactionModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class SimulatorEntityModule {}
