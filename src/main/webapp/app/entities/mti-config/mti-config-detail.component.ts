import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMtiConfig } from 'app/shared/model/mti-config.model';

@Component({
  selector: 'jhi-mti-config-detail',
  templateUrl: './mti-config-detail.component.html'
})
export class MtiConfigDetailComponent implements OnInit {
  mtiConfig: IMtiConfig | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mtiConfig }) => (this.mtiConfig = mtiConfig));
  }

  previousState(): void {
    window.history.back();
  }
}
