import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ITestCase } from 'app/shared/model/test-case.model';

@Component({
  selector: 'jhi-test-case-detail',
  templateUrl: './test-case-detail.component.html'
})
export class TestCaseDetailComponent implements OnInit {
  testCase: ITestCase | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ testCase }) => (this.testCase = testCase));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
