import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMtiConfig } from 'app/shared/model/mti-config.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { MtiConfigService } from './mti-config.service';
import { MtiConfigDeleteDialogComponent } from './mti-config-delete-dialog.component';

@Component({
  selector: 'jhi-mti-config',
  templateUrl: './mti-config.component.html'
})
export class MtiConfigComponent implements OnInit, OnDestroy {
  mtiConfigs: IMtiConfig[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected mtiConfigService: MtiConfigService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.mtiConfigs = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.mtiConfigService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IMtiConfig[]>) => this.paginateMtiConfigs(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.mtiConfigs = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMtiConfigs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMtiConfig): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMtiConfigs(): void {
    this.eventSubscriber = this.eventManager.subscribe('mtiConfigListModification', () => this.reset());
  }

  delete(mtiConfig: IMtiConfig): void {
    const modalRef = this.modalService.open(MtiConfigDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.mtiConfig = mtiConfig;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateMtiConfigs(data: IMtiConfig[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.mtiConfigs.push(data[i]);
      }
    }
  }
}
