import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITestCase } from 'app/shared/model/test-case.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { TestCaseService } from './test-case.service';
import { TestCaseDeleteDialogComponent } from './test-case-delete-dialog.component';

@Component({
  selector: 'jhi-test-case',
  templateUrl: './test-case.component.html'
})
export class TestCaseComponent implements OnInit, OnDestroy {
  testCases: ITestCase[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected testCaseService: TestCaseService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.testCases = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.testCaseService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ITestCase[]>) => this.paginateTestCases(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.testCases = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTestCases();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITestCase): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInTestCases(): void {
    this.eventSubscriber = this.eventManager.subscribe('testCaseListModification', () => this.reset());
  }

  delete(testCase: ITestCase): void {
    const modalRef = this.modalService.open(TestCaseDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.testCase = testCase;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateTestCases(data: ITestCase[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.testCases.push(data[i]);
      }
    }
  }
}
