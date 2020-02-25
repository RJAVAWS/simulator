import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { SimulatorSharedModule } from 'app/shared/shared.module';
import { SimulatorCoreModule } from 'app/core/core.module';
import { SimulatorAppRoutingModule } from './app-routing.module';
import { SimulatorHomeModule } from './home/home.module';
import { SimulatorEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    SimulatorSharedModule,
    SimulatorCoreModule,
    SimulatorHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    SimulatorEntityModule,
    SimulatorAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent]
})
export class SimulatorAppModule {}
