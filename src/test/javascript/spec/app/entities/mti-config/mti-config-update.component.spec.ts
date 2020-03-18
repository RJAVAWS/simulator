import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SimulatorTestModule } from '../../../test.module';
import { MtiConfigUpdateComponent } from 'app/entities/mti-config/mti-config-update.component';
import { MtiConfigService } from 'app/entities/mti-config/mti-config.service';
import { MtiConfig } from 'app/shared/model/mti-config.model';

describe('Component Tests', () => {
  describe('MtiConfig Management Update Component', () => {
    let comp: MtiConfigUpdateComponent;
    let fixture: ComponentFixture<MtiConfigUpdateComponent>;
    let service: MtiConfigService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SimulatorTestModule],
        declarations: [MtiConfigUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MtiConfigUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MtiConfigUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MtiConfigService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MtiConfig(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MtiConfig();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
