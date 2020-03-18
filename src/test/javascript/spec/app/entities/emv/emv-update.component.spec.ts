import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SimulatorTestModule } from '../../../test.module';
import { EmvUpdateComponent } from 'app/entities/emv/emv-update.component';
import { EmvService } from 'app/entities/emv/emv.service';
import { Emv } from 'app/shared/model/emv.model';

describe('Component Tests', () => {
  describe('Emv Management Update Component', () => {
    let comp: EmvUpdateComponent;
    let fixture: ComponentFixture<EmvUpdateComponent>;
    let service: EmvService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SimulatorTestModule],
        declarations: [EmvUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EmvUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EmvUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EmvService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Emv(123);
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
        const entity = new Emv();
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
