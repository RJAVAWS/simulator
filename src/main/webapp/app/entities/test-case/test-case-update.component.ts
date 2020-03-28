import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ITestCase, TestCase } from 'app/shared/model/test-case.model';
import { TestCaseService } from './test-case.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-test-case-update',
  templateUrl: './test-case-update.component.html'
})
export class TestCaseUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    caseDescription: [
      null,
      [Validators.required, Validators.minLength(3), Validators.maxLength(255), Validators.pattern('[a-zA-Z0-9 _.]*')]
    ],
    psdnType: [null, [Validators.required]],
    reqResType: [null, [Validators.required]],
    mti: [],
    de001: [],
    de002: [],
    de003: [],
    de004: [],
    de005: [],
    de006: [],
    de007: [],
    de008: [],
    de009: [],
    de010: [],
    de011: [],
    de012: [],
    de013: [],
    de014: [],
    de015: [],
    de016: [],
    de017: [],
    de018: [],
    de019: [],
    de020: [],
    de021: [],
    de022: [],
    de023: [],
    de024: [],
    de025: [],
    de026: [],
    de027: [],
    de028: [],
    de029: [],
    de030: [],
    de031: [],
    de032: [],
    de033: [],
    de034: [],
    de035: [],
    de036: [],
    de037: [],
    de038: [],
    de039: [],
    de040: [],
    de041: [],
    de042: [],
    de043: [],
    de044: [],
    de045: [],
    de046: [],
    de047: [],
    de048: [],
    de049: [],
    de050: [],
    de051: [],
    de052: [],
    de053: [],
    de054: [],
    de055: [],
    de056: [],
    de057: [],
    de058: [],
    de059: [],
    de060: [],
    de061: [],
    de062: [],
    de063: [],
    de064: [],
    de065: [],
    de066: [],
    de067: [],
    de068: [],
    de069: [],
    de070: [],
    de071: [],
    de072: [],
    de073: [],
    de074: [],
    de075: [],
    de076: [],
    de077: [],
    de078: [],
    de079: [],
    de080: [],
    de081: [],
    de082: [],
    de083: [],
    de084: [],
    de085: [],
    de086: [],
    de087: [],
    de088: [],
    de089: [],
    de090: [],
    de091: [],
    de092: [],
    de093: [],
    de094: [],
    de095: [],
    de096: [],
    de097: [],
    de098: [],
    de099: [],
    de100: [],
    de101: [],
    de102: [],
    de103: [],
    de104: [],
    de105: [],
    de106: [],
    de107: [],
    de108: [],
    de109: [],
    de110: [],
    de111: [],
    de112: [],
    de113: [],
    de114: [],
    de115: [],
    de116: [],
    de117: [],
    de118: [],
    de119: [],
    de120: [],
    de121: [],
    de122: [],
    de123: [],
    de124: [],
    de125: [],
    de126: [],
    de127: [],
    de128: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected testCaseService: TestCaseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ testCase }) => {
      this.updateForm(testCase);
    });
  }

  updateForm(testCase: ITestCase): void {
    this.editForm.patchValue({
      id: testCase.id,
      caseDescription: testCase.caseDescription,
      psdnType: testCase.psdnType,
      reqResType: testCase.reqResType,
      mti: testCase.mti,
      de001: testCase.de001,
      de002: testCase.de002,
      de003: testCase.de003,
      de004: testCase.de004,
      de005: testCase.de005,
      de006: testCase.de006,
      de007: testCase.de007,
      de008: testCase.de008,
      de009: testCase.de009,
      de010: testCase.de010,
      de011: testCase.de011,
      de012: testCase.de012,
      de013: testCase.de013,
      de014: testCase.de014,
      de015: testCase.de015,
      de016: testCase.de016,
      de017: testCase.de017,
      de018: testCase.de018,
      de019: testCase.de019,
      de020: testCase.de020,
      de021: testCase.de021,
      de022: testCase.de022,
      de023: testCase.de023,
      de024: testCase.de024,
      de025: testCase.de025,
      de026: testCase.de026,
      de027: testCase.de027,
      de028: testCase.de028,
      de029: testCase.de029,
      de030: testCase.de030,
      de031: testCase.de031,
      de032: testCase.de032,
      de033: testCase.de033,
      de034: testCase.de034,
      de035: testCase.de035,
      de036: testCase.de036,
      de037: testCase.de037,
      de038: testCase.de038,
      de039: testCase.de039,
      de040: testCase.de040,
      de041: testCase.de041,
      de042: testCase.de042,
      de043: testCase.de043,
      de044: testCase.de044,
      de045: testCase.de045,
      de046: testCase.de046,
      de047: testCase.de047,
      de048: testCase.de048,
      de049: testCase.de049,
      de050: testCase.de050,
      de051: testCase.de051,
      de052: testCase.de052,
      de053: testCase.de053,
      de054: testCase.de054,
      de055: testCase.de055,
      de056: testCase.de056,
      de057: testCase.de057,
      de058: testCase.de058,
      de059: testCase.de059,
      de060: testCase.de060,
      de061: testCase.de061,
      de062: testCase.de062,
      de063: testCase.de063,
      de064: testCase.de064,
      de065: testCase.de065,
      de066: testCase.de066,
      de067: testCase.de067,
      de068: testCase.de068,
      de069: testCase.de069,
      de070: testCase.de070,
      de071: testCase.de071,
      de072: testCase.de072,
      de073: testCase.de073,
      de074: testCase.de074,
      de075: testCase.de075,
      de076: testCase.de076,
      de077: testCase.de077,
      de078: testCase.de078,
      de079: testCase.de079,
      de080: testCase.de080,
      de081: testCase.de081,
      de082: testCase.de082,
      de083: testCase.de083,
      de084: testCase.de084,
      de085: testCase.de085,
      de086: testCase.de086,
      de087: testCase.de087,
      de088: testCase.de088,
      de089: testCase.de089,
      de090: testCase.de090,
      de091: testCase.de091,
      de092: testCase.de092,
      de093: testCase.de093,
      de094: testCase.de094,
      de095: testCase.de095,
      de096: testCase.de096,
      de097: testCase.de097,
      de098: testCase.de098,
      de099: testCase.de099,
      de100: testCase.de100,
      de101: testCase.de101,
      de102: testCase.de102,
      de103: testCase.de103,
      de104: testCase.de104,
      de105: testCase.de105,
      de106: testCase.de106,
      de107: testCase.de107,
      de108: testCase.de108,
      de109: testCase.de109,
      de110: testCase.de110,
      de111: testCase.de111,
      de112: testCase.de112,
      de113: testCase.de113,
      de114: testCase.de114,
      de115: testCase.de115,
      de116: testCase.de116,
      de117: testCase.de117,
      de118: testCase.de118,
      de119: testCase.de119,
      de120: testCase.de120,
      de121: testCase.de121,
      de122: testCase.de122,
      de123: testCase.de123,
      de124: testCase.de124,
      de125: testCase.de125,
      de126: testCase.de126,
      de127: testCase.de127,
      de128: testCase.de128
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('simulatorApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const testCase = this.createFromForm();
    if (testCase.id !== undefined) {
      this.subscribeToSaveResponse(this.testCaseService.update(testCase));
    } else {
      this.subscribeToSaveResponse(this.testCaseService.create(testCase));
    }
  }

  private createFromForm(): ITestCase {
    return {
      ...new TestCase(),
      id: this.editForm.get(['id'])!.value,
      caseDescription: this.editForm.get(['caseDescription'])!.value,
      psdnType: this.editForm.get(['psdnType'])!.value,
      reqResType: this.editForm.get(['reqResType'])!.value,
      mti: this.editForm.get(['mti'])!.value,
      de001: this.editForm.get(['de001'])!.value,
      de002: this.editForm.get(['de002'])!.value,
      de003: this.editForm.get(['de003'])!.value,
      de004: this.editForm.get(['de004'])!.value,
      de005: this.editForm.get(['de005'])!.value,
      de006: this.editForm.get(['de006'])!.value,
      de007: this.editForm.get(['de007'])!.value,
      de008: this.editForm.get(['de008'])!.value,
      de009: this.editForm.get(['de009'])!.value,
      de010: this.editForm.get(['de010'])!.value,
      de011: this.editForm.get(['de011'])!.value,
      de012: this.editForm.get(['de012'])!.value,
      de013: this.editForm.get(['de013'])!.value,
      de014: this.editForm.get(['de014'])!.value,
      de015: this.editForm.get(['de015'])!.value,
      de016: this.editForm.get(['de016'])!.value,
      de017: this.editForm.get(['de017'])!.value,
      de018: this.editForm.get(['de018'])!.value,
      de019: this.editForm.get(['de019'])!.value,
      de020: this.editForm.get(['de020'])!.value,
      de021: this.editForm.get(['de021'])!.value,
      de022: this.editForm.get(['de022'])!.value,
      de023: this.editForm.get(['de023'])!.value,
      de024: this.editForm.get(['de024'])!.value,
      de025: this.editForm.get(['de025'])!.value,
      de026: this.editForm.get(['de026'])!.value,
      de027: this.editForm.get(['de027'])!.value,
      de028: this.editForm.get(['de028'])!.value,
      de029: this.editForm.get(['de029'])!.value,
      de030: this.editForm.get(['de030'])!.value,
      de031: this.editForm.get(['de031'])!.value,
      de032: this.editForm.get(['de032'])!.value,
      de033: this.editForm.get(['de033'])!.value,
      de034: this.editForm.get(['de034'])!.value,
      de035: this.editForm.get(['de035'])!.value,
      de036: this.editForm.get(['de036'])!.value,
      de037: this.editForm.get(['de037'])!.value,
      de038: this.editForm.get(['de038'])!.value,
      de039: this.editForm.get(['de039'])!.value,
      de040: this.editForm.get(['de040'])!.value,
      de041: this.editForm.get(['de041'])!.value,
      de042: this.editForm.get(['de042'])!.value,
      de043: this.editForm.get(['de043'])!.value,
      de044: this.editForm.get(['de044'])!.value,
      de045: this.editForm.get(['de045'])!.value,
      de046: this.editForm.get(['de046'])!.value,
      de047: this.editForm.get(['de047'])!.value,
      de048: this.editForm.get(['de048'])!.value,
      de049: this.editForm.get(['de049'])!.value,
      de050: this.editForm.get(['de050'])!.value,
      de051: this.editForm.get(['de051'])!.value,
      de052: this.editForm.get(['de052'])!.value,
      de053: this.editForm.get(['de053'])!.value,
      de054: this.editForm.get(['de054'])!.value,
      de055: this.editForm.get(['de055'])!.value,
      de056: this.editForm.get(['de056'])!.value,
      de057: this.editForm.get(['de057'])!.value,
      de058: this.editForm.get(['de058'])!.value,
      de059: this.editForm.get(['de059'])!.value,
      de060: this.editForm.get(['de060'])!.value,
      de061: this.editForm.get(['de061'])!.value,
      de062: this.editForm.get(['de062'])!.value,
      de063: this.editForm.get(['de063'])!.value,
      de064: this.editForm.get(['de064'])!.value,
      de065: this.editForm.get(['de065'])!.value,
      de066: this.editForm.get(['de066'])!.value,
      de067: this.editForm.get(['de067'])!.value,
      de068: this.editForm.get(['de068'])!.value,
      de069: this.editForm.get(['de069'])!.value,
      de070: this.editForm.get(['de070'])!.value,
      de071: this.editForm.get(['de071'])!.value,
      de072: this.editForm.get(['de072'])!.value,
      de073: this.editForm.get(['de073'])!.value,
      de074: this.editForm.get(['de074'])!.value,
      de075: this.editForm.get(['de075'])!.value,
      de076: this.editForm.get(['de076'])!.value,
      de077: this.editForm.get(['de077'])!.value,
      de078: this.editForm.get(['de078'])!.value,
      de079: this.editForm.get(['de079'])!.value,
      de080: this.editForm.get(['de080'])!.value,
      de081: this.editForm.get(['de081'])!.value,
      de082: this.editForm.get(['de082'])!.value,
      de083: this.editForm.get(['de083'])!.value,
      de084: this.editForm.get(['de084'])!.value,
      de085: this.editForm.get(['de085'])!.value,
      de086: this.editForm.get(['de086'])!.value,
      de087: this.editForm.get(['de087'])!.value,
      de088: this.editForm.get(['de088'])!.value,
      de089: this.editForm.get(['de089'])!.value,
      de090: this.editForm.get(['de090'])!.value,
      de091: this.editForm.get(['de091'])!.value,
      de092: this.editForm.get(['de092'])!.value,
      de093: this.editForm.get(['de093'])!.value,
      de094: this.editForm.get(['de094'])!.value,
      de095: this.editForm.get(['de095'])!.value,
      de096: this.editForm.get(['de096'])!.value,
      de097: this.editForm.get(['de097'])!.value,
      de098: this.editForm.get(['de098'])!.value,
      de099: this.editForm.get(['de099'])!.value,
      de100: this.editForm.get(['de100'])!.value,
      de101: this.editForm.get(['de101'])!.value,
      de102: this.editForm.get(['de102'])!.value,
      de103: this.editForm.get(['de103'])!.value,
      de104: this.editForm.get(['de104'])!.value,
      de105: this.editForm.get(['de105'])!.value,
      de106: this.editForm.get(['de106'])!.value,
      de107: this.editForm.get(['de107'])!.value,
      de108: this.editForm.get(['de108'])!.value,
      de109: this.editForm.get(['de109'])!.value,
      de110: this.editForm.get(['de110'])!.value,
      de111: this.editForm.get(['de111'])!.value,
      de112: this.editForm.get(['de112'])!.value,
      de113: this.editForm.get(['de113'])!.value,
      de114: this.editForm.get(['de114'])!.value,
      de115: this.editForm.get(['de115'])!.value,
      de116: this.editForm.get(['de116'])!.value,
      de117: this.editForm.get(['de117'])!.value,
      de118: this.editForm.get(['de118'])!.value,
      de119: this.editForm.get(['de119'])!.value,
      de120: this.editForm.get(['de120'])!.value,
      de121: this.editForm.get(['de121'])!.value,
      de122: this.editForm.get(['de122'])!.value,
      de123: this.editForm.get(['de123'])!.value,
      de124: this.editForm.get(['de124'])!.value,
      de125: this.editForm.get(['de125'])!.value,
      de126: this.editForm.get(['de126'])!.value,
      de127: this.editForm.get(['de127'])!.value,
      de128: this.editForm.get(['de128'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITestCase>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
