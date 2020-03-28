import { ICard } from 'app/shared/model/card.model';
import { PsdnType } from 'app/shared/model/enumerations/psdn-type.model';
import { ReqResType } from 'app/shared/model/enumerations/req-res-type.model';

export interface ITestCase {
  id?: number;
  caseDescription?: string;
  psdnType?: PsdnType;
  reqResType?: ReqResType;
  mti?: string;
  de001?: any;
  de002?: any;
  de003?: any;
  de004?: any;
  de005?: any;
  de006?: any;
  de007?: any;
  de008?: any;
  de009?: any;
  de010?: any;
  de011?: any;
  de012?: any;
  de013?: any;
  de014?: any;
  de015?: any;
  de016?: any;
  de017?: any;
  de018?: any;
  de019?: any;
  de020?: any;
  de021?: any;
  de022?: any;
  de023?: any;
  de024?: any;
  de025?: any;
  de026?: any;
  de027?: any;
  de028?: any;
  de029?: any;
  de030?: any;
  de031?: any;
  de032?: any;
  de033?: any;
  de034?: any;
  de035?: any;
  de036?: any;
  de037?: any;
  de038?: any;
  de039?: any;
  de040?: any;
  de041?: any;
  de042?: any;
  de043?: any;
  de044?: any;
  de045?: any;
  de046?: any;
  de047?: any;
  de048?: any;
  de049?: any;
  de050?: any;
  de051?: any;
  de052?: any;
  de053?: any;
  de054?: any;
  de055?: any;
  de056?: any;
  de057?: any;
  de058?: any;
  de059?: any;
  de060?: any;
  de061?: any;
  de062?: any;
  de063?: any;
  de064?: any;
  de065?: any;
  de066?: any;
  de067?: any;
  de068?: any;
  de069?: any;
  de070?: any;
  de071?: any;
  de072?: any;
  de073?: any;
  de074?: any;
  de075?: any;
  de076?: any;
  de077?: any;
  de078?: any;
  de079?: any;
  de080?: any;
  de081?: any;
  de082?: any;
  de083?: any;
  de084?: any;
  de085?: any;
  de086?: any;
  de087?: any;
  de088?: any;
  de089?: any;
  de090?: any;
  de091?: any;
  de092?: any;
  de093?: any;
  de094?: any;
  de095?: any;
  de096?: any;
  de097?: any;
  de098?: any;
  de099?: any;
  de100?: any;
  de101?: any;
  de102?: any;
  de103?: any;
  de104?: any;
  de105?: any;
  de106?: any;
  de107?: any;
  de108?: any;
  de109?: any;
  de110?: any;
  de111?: any;
  de112?: any;
  de113?: any;
  de114?: any;
  de115?: any;
  de116?: any;
  de117?: any;
  de118?: any;
  de119?: any;
  de120?: any;
  de121?: any;
  de122?: any;
  de123?: any;
  de124?: any;
  de125?: any;
  de126?: any;
  de127?: any;
  de128?: any;
  cards?: ICard[];
}

export class TestCase implements ITestCase {
  constructor(
    public id?: number,
    public caseDescription?: string,
    public psdnType?: PsdnType,
    public reqResType?: ReqResType,
    public mti?: string,
    public de001?: any,
    public de002?: any,
    public de003?: any,
    public de004?: any,
    public de005?: any,
    public de006?: any,
    public de007?: any,
    public de008?: any,
    public de009?: any,
    public de010?: any,
    public de011?: any,
    public de012?: any,
    public de013?: any,
    public de014?: any,
    public de015?: any,
    public de016?: any,
    public de017?: any,
    public de018?: any,
    public de019?: any,
    public de020?: any,
    public de021?: any,
    public de022?: any,
    public de023?: any,
    public de024?: any,
    public de025?: any,
    public de026?: any,
    public de027?: any,
    public de028?: any,
    public de029?: any,
    public de030?: any,
    public de031?: any,
    public de032?: any,
    public de033?: any,
    public de034?: any,
    public de035?: any,
    public de036?: any,
    public de037?: any,
    public de038?: any,
    public de039?: any,
    public de040?: any,
    public de041?: any,
    public de042?: any,
    public de043?: any,
    public de044?: any,
    public de045?: any,
    public de046?: any,
    public de047?: any,
    public de048?: any,
    public de049?: any,
    public de050?: any,
    public de051?: any,
    public de052?: any,
    public de053?: any,
    public de054?: any,
    public de055?: any,
    public de056?: any,
    public de057?: any,
    public de058?: any,
    public de059?: any,
    public de060?: any,
    public de061?: any,
    public de062?: any,
    public de063?: any,
    public de064?: any,
    public de065?: any,
    public de066?: any,
    public de067?: any,
    public de068?: any,
    public de069?: any,
    public de070?: any,
    public de071?: any,
    public de072?: any,
    public de073?: any,
    public de074?: any,
    public de075?: any,
    public de076?: any,
    public de077?: any,
    public de078?: any,
    public de079?: any,
    public de080?: any,
    public de081?: any,
    public de082?: any,
    public de083?: any,
    public de084?: any,
    public de085?: any,
    public de086?: any,
    public de087?: any,
    public de088?: any,
    public de089?: any,
    public de090?: any,
    public de091?: any,
    public de092?: any,
    public de093?: any,
    public de094?: any,
    public de095?: any,
    public de096?: any,
    public de097?: any,
    public de098?: any,
    public de099?: any,
    public de100?: any,
    public de101?: any,
    public de102?: any,
    public de103?: any,
    public de104?: any,
    public de105?: any,
    public de106?: any,
    public de107?: any,
    public de108?: any,
    public de109?: any,
    public de110?: any,
    public de111?: any,
    public de112?: any,
    public de113?: any,
    public de114?: any,
    public de115?: any,
    public de116?: any,
    public de117?: any,
    public de118?: any,
    public de119?: any,
    public de120?: any,
    public de121?: any,
    public de122?: any,
    public de123?: any,
    public de124?: any,
    public de125?: any,
    public de126?: any,
    public de127?: any,
    public de128?: any,
    public cards?: ICard[]
  ) {}
}
