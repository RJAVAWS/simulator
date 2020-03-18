import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IKeyConfig } from 'app/shared/model/key-config.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { KeyConfigService } from './key-config.service';
import { KeyConfigDeleteDialogComponent } from './key-config-delete-dialog.component';

@Component({
  selector: 'jhi-key-config',
  templateUrl: './key-config.component.html'
})
export class KeyConfigComponent implements OnInit, OnDestroy {
  keyConfigs: IKeyConfig[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected keyConfigService: KeyConfigService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.keyConfigs = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.keyConfigService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IKeyConfig[]>) => this.paginateKeyConfigs(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.keyConfigs = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInKeyConfigs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IKeyConfig): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInKeyConfigs(): void {
    this.eventSubscriber = this.eventManager.subscribe('keyConfigListModification', () => this.reset());
  }

  delete(keyConfig: IKeyConfig): void {
    const modalRef = this.modalService.open(KeyConfigDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.keyConfig = keyConfig;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateKeyConfigs(data: IKeyConfig[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.keyConfigs.push(data[i]);
      }
    }
  }
}
