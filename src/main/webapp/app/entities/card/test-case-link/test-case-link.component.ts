import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ITestCase } from 'app/shared/model/test-case.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { TestCaseService } from 'app/entities/test-case/test-case.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CardService } from 'app/entities/card/card.service';
import { ICard } from 'app/shared/model/card.model';

@Component({
  selector: 'jhi-test-case',
  templateUrl: './test-case-link.component.html'
})
export class TestCaseLinkComponent implements OnInit, OnDestroy {
  card!: ICard;
  testCases: ITestCase[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;
  toggleAll: boolean;

  constructor(
    protected activatedRoute: ActivatedRoute,
    private router: Router,
    protected testCaseService: TestCaseService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks,
    protected cardService: CardService
  ) {
    this.testCases = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
    this.toggleAll = false;
  }

  loadAll(): void {
    this.testCaseService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
        useCase: this.card.useCase
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
    this.activatedRoute.data.subscribe(({ card }) => {
      this.card = card;
    });
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
        if (data[i].id) {
          data[i].isLinked = false;
          if (this.card.testCases) {
            for (let j = 0; j < this.card.testCases.length; j++) {
              if (this.card.testCases[j].id === data[i].id) {
                data[i].isLinked = true;
              }
            }
          }
        }
        this.testCases.push(data[i]);
      }
    }
  }

  private save(): void {
    this.card.testCases = [];
    this.testCases.forEach(testcase => {
      if (testcase.isLinked) this.card.testCases!.push(testcase);
    });
    console.warn(this.card.testCases);
    this.cardService.update(this.card).subscribe(() => {
      this.router.navigate(['/card/' + this.card.id + '/link']);
    });
  }

  private checkUncheckAll(): void {
    if (this.toggleAll) {
      this.testCases.forEach(testcase => {
        testcase.isLinked = true;
      });
    } else {
      this.testCases.forEach(testcase => {
        testcase.isLinked = false;
      });
    }
  }
}
