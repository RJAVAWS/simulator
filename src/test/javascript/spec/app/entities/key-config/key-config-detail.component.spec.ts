import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SimulatorTestModule } from '../../../test.module';
import { KeyConfigDetailComponent } from 'app/entities/key-config/key-config-detail.component';
import { KeyConfig } from 'app/shared/model/key-config.model';

describe('Component Tests', () => {
  describe('KeyConfig Management Detail Component', () => {
    let comp: KeyConfigDetailComponent;
    let fixture: ComponentFixture<KeyConfigDetailComponent>;
    const route = ({ data: of({ keyConfig: new KeyConfig(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SimulatorTestModule],
        declarations: [KeyConfigDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(KeyConfigDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(KeyConfigDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load keyConfig on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.keyConfig).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
