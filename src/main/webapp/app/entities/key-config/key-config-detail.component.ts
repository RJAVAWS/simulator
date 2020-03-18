import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKeyConfig } from 'app/shared/model/key-config.model';

@Component({
  selector: 'jhi-key-config-detail',
  templateUrl: './key-config-detail.component.html'
})
export class KeyConfigDetailComponent implements OnInit {
  keyConfig: IKeyConfig | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ keyConfig }) => (this.keyConfig = keyConfig));
  }

  previousState(): void {
    window.history.back();
  }
}
