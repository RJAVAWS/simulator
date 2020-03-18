package com.org.simulator.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.org.simulator.domain.enumeration.AcqIssType;

/**
 * A DTO for the {@link com.org.simulator.domain.Transaction} entity.
 */
public class TransactionDTO implements Serializable {

    private Long id;

    private AcqIssType tranType;

    private Integer status;

    private String mti;

    @Lob
    private String de001;

    @Lob
    private String de002;

    @Lob
    private String de003;

    @Lob
    private String de004;

    @Lob
    private String de005;

    @Lob
    private String de006;

    @Lob
    private String de007;

    @Lob
    private String de008;

    @Lob
    private String de009;

    @Lob
    private String de010;

    @Lob
    private String de011;

    @Lob
    private String de012;

    @Lob
    private String de013;

    @Lob
    private String de014;

    @Lob
    private String de015;

    @Lob
    private String de016;

    @Lob
    private String de017;

    @Lob
    private String de018;

    @Lob
    private String de019;

    @Lob
    private String de020;

    @Lob
    private String de021;

    @Lob
    private String de022;

    @Lob
    private String de023;

    @Lob
    private String de024;

    @Lob
    private String de025;

    @Lob
    private String de026;

    @Lob
    private String de027;

    @Lob
    private String de028;

    @Lob
    private String de029;

    @Lob
    private String de030;

    @Lob
    private String de031;

    @Lob
    private String de032;

    @Lob
    private String de033;

    @Lob
    private String de034;

    @Lob
    private String de035;

    @Lob
    private String de036;

    @Lob
    private String de037;

    @Lob
    private String de038;

    @Lob
    private String de039;

    @Lob
    private String de040;

    @Lob
    private String de041;

    @Lob
    private String de042;

    @Lob
    private String de043;

    @Lob
    private String de044;

    @Lob
    private String de045;

    @Lob
    private String de046;

    @Lob
    private String de047;

    @Lob
    private String de048;

    @Lob
    private String de049;

    @Lob
    private String de050;

    @Lob
    private String de051;

    @Lob
    private String de052;

    @Lob
    private String de053;

    @Lob
    private String de054;

    @Lob
    private String de055;

    @Lob
    private String de056;

    @Lob
    private String de057;

    @Lob
    private String de058;

    @Lob
    private String de059;

    @Lob
    private String de060;

    @Lob
    private String de061;

    @Lob
    private String de062;

    @Lob
    private String de063;

    @Lob
    private String de064;

    @Lob
    private String de065;

    @Lob
    private String de066;

    @Lob
    private String de067;

    @Lob
    private String de068;

    @Lob
    private String de069;

    @Lob
    private String de070;

    @Lob
    private String de071;

    @Lob
    private String de072;

    @Lob
    private String de073;

    @Lob
    private String de074;

    @Lob
    private String de075;

    @Lob
    private String de076;

    @Lob
    private String de077;

    @Lob
    private String de078;

    @Lob
    private String de079;

    @Lob
    private String de080;

    @Lob
    private String de081;

    @Lob
    private String de082;

    @Lob
    private String de083;

    @Lob
    private String de084;

    @Lob
    private String de085;

    @Lob
    private String de086;

    @Lob
    private String de087;

    @Lob
    private String de088;

    @Lob
    private String de089;

    @Lob
    private String de090;

    @Lob
    private String de091;

    @Lob
    private String de092;

    @Lob
    private String de093;

    @Lob
    private String de094;

    @Lob
    private String de095;

    @Lob
    private String de096;

    @Lob
    private String de097;

    @Lob
    private String de098;

    @Lob
    private String de099;

    @Lob
    private String de100;

    @Lob
    private String de101;

    @Lob
    private String de102;

    @Lob
    private String de103;

    @Lob
    private String de104;

    @Lob
    private String de105;

    @Lob
    private String de106;

    @Lob
    private String de107;

    @Lob
    private String de108;

    @Lob
    private String de109;

    @Lob
    private String de110;

    @Lob
    private String de111;

    @Lob
    private String de112;

    @Lob
    private String de113;

    @Lob
    private String de114;

    @Lob
    private String de115;

    @Lob
    private String de116;

    @Lob
    private String de117;

    @Lob
    private String de118;

    @Lob
    private String de119;

    @Lob
    private String de120;

    @Lob
    private String de121;

    @Lob
    private String de122;

    @Lob
    private String de123;

    @Lob
    private String de124;

    @Lob
    private String de125;

    @Lob
    private String de126;

    @Lob
    private String de127;

    @Lob
    private String de128;

    @Lob
    private String isoMsg;


    private Long bankId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcqIssType getTranType() {
        return tranType;
    }

    public void setTranType(AcqIssType tranType) {
        this.tranType = tranType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public String getDe001() {
        return de001;
    }

    public void setDe001(String de001) {
        this.de001 = de001;
    }

    public String getDe002() {
        return de002;
    }

    public void setDe002(String de002) {
        this.de002 = de002;
    }

    public String getDe003() {
        return de003;
    }

    public void setDe003(String de003) {
        this.de003 = de003;
    }

    public String getDe004() {
        return de004;
    }

    public void setDe004(String de004) {
        this.de004 = de004;
    }

    public String getDe005() {
        return de005;
    }

    public void setDe005(String de005) {
        this.de005 = de005;
    }

    public String getDe006() {
        return de006;
    }

    public void setDe006(String de006) {
        this.de006 = de006;
    }

    public String getDe007() {
        return de007;
    }

    public void setDe007(String de007) {
        this.de007 = de007;
    }

    public String getDe008() {
        return de008;
    }

    public void setDe008(String de008) {
        this.de008 = de008;
    }

    public String getDe009() {
        return de009;
    }

    public void setDe009(String de009) {
        this.de009 = de009;
    }

    public String getDe010() {
        return de010;
    }

    public void setDe010(String de010) {
        this.de010 = de010;
    }

    public String getDe011() {
        return de011;
    }

    public void setDe011(String de011) {
        this.de011 = de011;
    }

    public String getDe012() {
        return de012;
    }

    public void setDe012(String de012) {
        this.de012 = de012;
    }

    public String getDe013() {
        return de013;
    }

    public void setDe013(String de013) {
        this.de013 = de013;
    }

    public String getDe014() {
        return de014;
    }

    public void setDe014(String de014) {
        this.de014 = de014;
    }

    public String getDe015() {
        return de015;
    }

    public void setDe015(String de015) {
        this.de015 = de015;
    }

    public String getDe016() {
        return de016;
    }

    public void setDe016(String de016) {
        this.de016 = de016;
    }

    public String getDe017() {
        return de017;
    }

    public void setDe017(String de017) {
        this.de017 = de017;
    }

    public String getDe018() {
        return de018;
    }

    public void setDe018(String de018) {
        this.de018 = de018;
    }

    public String getDe019() {
        return de019;
    }

    public void setDe019(String de019) {
        this.de019 = de019;
    }

    public String getDe020() {
        return de020;
    }

    public void setDe020(String de020) {
        this.de020 = de020;
    }

    public String getDe021() {
        return de021;
    }

    public void setDe021(String de021) {
        this.de021 = de021;
    }

    public String getDe022() {
        return de022;
    }

    public void setDe022(String de022) {
        this.de022 = de022;
    }

    public String getDe023() {
        return de023;
    }

    public void setDe023(String de023) {
        this.de023 = de023;
    }

    public String getDe024() {
        return de024;
    }

    public void setDe024(String de024) {
        this.de024 = de024;
    }

    public String getDe025() {
        return de025;
    }

    public void setDe025(String de025) {
        this.de025 = de025;
    }

    public String getDe026() {
        return de026;
    }

    public void setDe026(String de026) {
        this.de026 = de026;
    }

    public String getDe027() {
        return de027;
    }

    public void setDe027(String de027) {
        this.de027 = de027;
    }

    public String getDe028() {
        return de028;
    }

    public void setDe028(String de028) {
        this.de028 = de028;
    }

    public String getDe029() {
        return de029;
    }

    public void setDe029(String de029) {
        this.de029 = de029;
    }

    public String getDe030() {
        return de030;
    }

    public void setDe030(String de030) {
        this.de030 = de030;
    }

    public String getDe031() {
        return de031;
    }

    public void setDe031(String de031) {
        this.de031 = de031;
    }

    public String getDe032() {
        return de032;
    }

    public void setDe032(String de032) {
        this.de032 = de032;
    }

    public String getDe033() {
        return de033;
    }

    public void setDe033(String de033) {
        this.de033 = de033;
    }

    public String getDe034() {
        return de034;
    }

    public void setDe034(String de034) {
        this.de034 = de034;
    }

    public String getDe035() {
        return de035;
    }

    public void setDe035(String de035) {
        this.de035 = de035;
    }

    public String getDe036() {
        return de036;
    }

    public void setDe036(String de036) {
        this.de036 = de036;
    }

    public String getDe037() {
        return de037;
    }

    public void setDe037(String de037) {
        this.de037 = de037;
    }

    public String getDe038() {
        return de038;
    }

    public void setDe038(String de038) {
        this.de038 = de038;
    }

    public String getDe039() {
        return de039;
    }

    public void setDe039(String de039) {
        this.de039 = de039;
    }

    public String getDe040() {
        return de040;
    }

    public void setDe040(String de040) {
        this.de040 = de040;
    }

    public String getDe041() {
        return de041;
    }

    public void setDe041(String de041) {
        this.de041 = de041;
    }

    public String getDe042() {
        return de042;
    }

    public void setDe042(String de042) {
        this.de042 = de042;
    }

    public String getDe043() {
        return de043;
    }

    public void setDe043(String de043) {
        this.de043 = de043;
    }

    public String getDe044() {
        return de044;
    }

    public void setDe044(String de044) {
        this.de044 = de044;
    }

    public String getDe045() {
        return de045;
    }

    public void setDe045(String de045) {
        this.de045 = de045;
    }

    public String getDe046() {
        return de046;
    }

    public void setDe046(String de046) {
        this.de046 = de046;
    }

    public String getDe047() {
        return de047;
    }

    public void setDe047(String de047) {
        this.de047 = de047;
    }

    public String getDe048() {
        return de048;
    }

    public void setDe048(String de048) {
        this.de048 = de048;
    }

    public String getDe049() {
        return de049;
    }

    public void setDe049(String de049) {
        this.de049 = de049;
    }

    public String getDe050() {
        return de050;
    }

    public void setDe050(String de050) {
        this.de050 = de050;
    }

    public String getDe051() {
        return de051;
    }

    public void setDe051(String de051) {
        this.de051 = de051;
    }

    public String getDe052() {
        return de052;
    }

    public void setDe052(String de052) {
        this.de052 = de052;
    }

    public String getDe053() {
        return de053;
    }

    public void setDe053(String de053) {
        this.de053 = de053;
    }

    public String getDe054() {
        return de054;
    }

    public void setDe054(String de054) {
        this.de054 = de054;
    }

    public String getDe055() {
        return de055;
    }

    public void setDe055(String de055) {
        this.de055 = de055;
    }

    public String getDe056() {
        return de056;
    }

    public void setDe056(String de056) {
        this.de056 = de056;
    }

    public String getDe057() {
        return de057;
    }

    public void setDe057(String de057) {
        this.de057 = de057;
    }

    public String getDe058() {
        return de058;
    }

    public void setDe058(String de058) {
        this.de058 = de058;
    }

    public String getDe059() {
        return de059;
    }

    public void setDe059(String de059) {
        this.de059 = de059;
    }

    public String getDe060() {
        return de060;
    }

    public void setDe060(String de060) {
        this.de060 = de060;
    }

    public String getDe061() {
        return de061;
    }

    public void setDe061(String de061) {
        this.de061 = de061;
    }

    public String getDe062() {
        return de062;
    }

    public void setDe062(String de062) {
        this.de062 = de062;
    }

    public String getDe063() {
        return de063;
    }

    public void setDe063(String de063) {
        this.de063 = de063;
    }

    public String getDe064() {
        return de064;
    }

    public void setDe064(String de064) {
        this.de064 = de064;
    }

    public String getDe065() {
        return de065;
    }

    public void setDe065(String de065) {
        this.de065 = de065;
    }

    public String getDe066() {
        return de066;
    }

    public void setDe066(String de066) {
        this.de066 = de066;
    }

    public String getDe067() {
        return de067;
    }

    public void setDe067(String de067) {
        this.de067 = de067;
    }

    public String getDe068() {
        return de068;
    }

    public void setDe068(String de068) {
        this.de068 = de068;
    }

    public String getDe069() {
        return de069;
    }

    public void setDe069(String de069) {
        this.de069 = de069;
    }

    public String getDe070() {
        return de070;
    }

    public void setDe070(String de070) {
        this.de070 = de070;
    }

    public String getDe071() {
        return de071;
    }

    public void setDe071(String de071) {
        this.de071 = de071;
    }

    public String getDe072() {
        return de072;
    }

    public void setDe072(String de072) {
        this.de072 = de072;
    }

    public String getDe073() {
        return de073;
    }

    public void setDe073(String de073) {
        this.de073 = de073;
    }

    public String getDe074() {
        return de074;
    }

    public void setDe074(String de074) {
        this.de074 = de074;
    }

    public String getDe075() {
        return de075;
    }

    public void setDe075(String de075) {
        this.de075 = de075;
    }

    public String getDe076() {
        return de076;
    }

    public void setDe076(String de076) {
        this.de076 = de076;
    }

    public String getDe077() {
        return de077;
    }

    public void setDe077(String de077) {
        this.de077 = de077;
    }

    public String getDe078() {
        return de078;
    }

    public void setDe078(String de078) {
        this.de078 = de078;
    }

    public String getDe079() {
        return de079;
    }

    public void setDe079(String de079) {
        this.de079 = de079;
    }

    public String getDe080() {
        return de080;
    }

    public void setDe080(String de080) {
        this.de080 = de080;
    }

    public String getDe081() {
        return de081;
    }

    public void setDe081(String de081) {
        this.de081 = de081;
    }

    public String getDe082() {
        return de082;
    }

    public void setDe082(String de082) {
        this.de082 = de082;
    }

    public String getDe083() {
        return de083;
    }

    public void setDe083(String de083) {
        this.de083 = de083;
    }

    public String getDe084() {
        return de084;
    }

    public void setDe084(String de084) {
        this.de084 = de084;
    }

    public String getDe085() {
        return de085;
    }

    public void setDe085(String de085) {
        this.de085 = de085;
    }

    public String getDe086() {
        return de086;
    }

    public void setDe086(String de086) {
        this.de086 = de086;
    }

    public String getDe087() {
        return de087;
    }

    public void setDe087(String de087) {
        this.de087 = de087;
    }

    public String getDe088() {
        return de088;
    }

    public void setDe088(String de088) {
        this.de088 = de088;
    }

    public String getDe089() {
        return de089;
    }

    public void setDe089(String de089) {
        this.de089 = de089;
    }

    public String getDe090() {
        return de090;
    }

    public void setDe090(String de090) {
        this.de090 = de090;
    }

    public String getDe091() {
        return de091;
    }

    public void setDe091(String de091) {
        this.de091 = de091;
    }

    public String getDe092() {
        return de092;
    }

    public void setDe092(String de092) {
        this.de092 = de092;
    }

    public String getDe093() {
        return de093;
    }

    public void setDe093(String de093) {
        this.de093 = de093;
    }

    public String getDe094() {
        return de094;
    }

    public void setDe094(String de094) {
        this.de094 = de094;
    }

    public String getDe095() {
        return de095;
    }

    public void setDe095(String de095) {
        this.de095 = de095;
    }

    public String getDe096() {
        return de096;
    }

    public void setDe096(String de096) {
        this.de096 = de096;
    }

    public String getDe097() {
        return de097;
    }

    public void setDe097(String de097) {
        this.de097 = de097;
    }

    public String getDe098() {
        return de098;
    }

    public void setDe098(String de098) {
        this.de098 = de098;
    }

    public String getDe099() {
        return de099;
    }

    public void setDe099(String de099) {
        this.de099 = de099;
    }

    public String getDe100() {
        return de100;
    }

    public void setDe100(String de100) {
        this.de100 = de100;
    }

    public String getDe101() {
        return de101;
    }

    public void setDe101(String de101) {
        this.de101 = de101;
    }

    public String getDe102() {
        return de102;
    }

    public void setDe102(String de102) {
        this.de102 = de102;
    }

    public String getDe103() {
        return de103;
    }

    public void setDe103(String de103) {
        this.de103 = de103;
    }

    public String getDe104() {
        return de104;
    }

    public void setDe104(String de104) {
        this.de104 = de104;
    }

    public String getDe105() {
        return de105;
    }

    public void setDe105(String de105) {
        this.de105 = de105;
    }

    public String getDe106() {
        return de106;
    }

    public void setDe106(String de106) {
        this.de106 = de106;
    }

    public String getDe107() {
        return de107;
    }

    public void setDe107(String de107) {
        this.de107 = de107;
    }

    public String getDe108() {
        return de108;
    }

    public void setDe108(String de108) {
        this.de108 = de108;
    }

    public String getDe109() {
        return de109;
    }

    public void setDe109(String de109) {
        this.de109 = de109;
    }

    public String getDe110() {
        return de110;
    }

    public void setDe110(String de110) {
        this.de110 = de110;
    }

    public String getDe111() {
        return de111;
    }

    public void setDe111(String de111) {
        this.de111 = de111;
    }

    public String getDe112() {
        return de112;
    }

    public void setDe112(String de112) {
        this.de112 = de112;
    }

    public String getDe113() {
        return de113;
    }

    public void setDe113(String de113) {
        this.de113 = de113;
    }

    public String getDe114() {
        return de114;
    }

    public void setDe114(String de114) {
        this.de114 = de114;
    }

    public String getDe115() {
        return de115;
    }

    public void setDe115(String de115) {
        this.de115 = de115;
    }

    public String getDe116() {
        return de116;
    }

    public void setDe116(String de116) {
        this.de116 = de116;
    }

    public String getDe117() {
        return de117;
    }

    public void setDe117(String de117) {
        this.de117 = de117;
    }

    public String getDe118() {
        return de118;
    }

    public void setDe118(String de118) {
        this.de118 = de118;
    }

    public String getDe119() {
        return de119;
    }

    public void setDe119(String de119) {
        this.de119 = de119;
    }

    public String getDe120() {
        return de120;
    }

    public void setDe120(String de120) {
        this.de120 = de120;
    }

    public String getDe121() {
        return de121;
    }

    public void setDe121(String de121) {
        this.de121 = de121;
    }

    public String getDe122() {
        return de122;
    }

    public void setDe122(String de122) {
        this.de122 = de122;
    }

    public String getDe123() {
        return de123;
    }

    public void setDe123(String de123) {
        this.de123 = de123;
    }

    public String getDe124() {
        return de124;
    }

    public void setDe124(String de124) {
        this.de124 = de124;
    }

    public String getDe125() {
        return de125;
    }

    public void setDe125(String de125) {
        this.de125 = de125;
    }

    public String getDe126() {
        return de126;
    }

    public void setDe126(String de126) {
        this.de126 = de126;
    }

    public String getDe127() {
        return de127;
    }

    public void setDe127(String de127) {
        this.de127 = de127;
    }

    public String getDe128() {
        return de128;
    }

    public void setDe128(String de128) {
        this.de128 = de128;
    }

    public String getIsoMsg() {
        return isoMsg;
    }

    public void setIsoMsg(String isoMsg) {
        this.isoMsg = isoMsg;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TransactionDTO transactionDTO = (TransactionDTO) o;
        if (transactionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transactionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
            "id=" + getId() +
            ", tranType='" + getTranType() + "'" +
            ", status=" + getStatus() +
            ", mti='" + getMti() + "'" +
            ", de001='" + getDe001() + "'" +
            ", de002='" + getDe002() + "'" +
            ", de003='" + getDe003() + "'" +
            ", de004='" + getDe004() + "'" +
            ", de005='" + getDe005() + "'" +
            ", de006='" + getDe006() + "'" +
            ", de007='" + getDe007() + "'" +
            ", de008='" + getDe008() + "'" +
            ", de009='" + getDe009() + "'" +
            ", de010='" + getDe010() + "'" +
            ", de011='" + getDe011() + "'" +
            ", de012='" + getDe012() + "'" +
            ", de013='" + getDe013() + "'" +
            ", de014='" + getDe014() + "'" +
            ", de015='" + getDe015() + "'" +
            ", de016='" + getDe016() + "'" +
            ", de017='" + getDe017() + "'" +
            ", de018='" + getDe018() + "'" +
            ", de019='" + getDe019() + "'" +
            ", de020='" + getDe020() + "'" +
            ", de021='" + getDe021() + "'" +
            ", de022='" + getDe022() + "'" +
            ", de023='" + getDe023() + "'" +
            ", de024='" + getDe024() + "'" +
            ", de025='" + getDe025() + "'" +
            ", de026='" + getDe026() + "'" +
            ", de027='" + getDe027() + "'" +
            ", de028='" + getDe028() + "'" +
            ", de029='" + getDe029() + "'" +
            ", de030='" + getDe030() + "'" +
            ", de031='" + getDe031() + "'" +
            ", de032='" + getDe032() + "'" +
            ", de033='" + getDe033() + "'" +
            ", de034='" + getDe034() + "'" +
            ", de035='" + getDe035() + "'" +
            ", de036='" + getDe036() + "'" +
            ", de037='" + getDe037() + "'" +
            ", de038='" + getDe038() + "'" +
            ", de039='" + getDe039() + "'" +
            ", de040='" + getDe040() + "'" +
            ", de041='" + getDe041() + "'" +
            ", de042='" + getDe042() + "'" +
            ", de043='" + getDe043() + "'" +
            ", de044='" + getDe044() + "'" +
            ", de045='" + getDe045() + "'" +
            ", de046='" + getDe046() + "'" +
            ", de047='" + getDe047() + "'" +
            ", de048='" + getDe048() + "'" +
            ", de049='" + getDe049() + "'" +
            ", de050='" + getDe050() + "'" +
            ", de051='" + getDe051() + "'" +
            ", de052='" + getDe052() + "'" +
            ", de053='" + getDe053() + "'" +
            ", de054='" + getDe054() + "'" +
            ", de055='" + getDe055() + "'" +
            ", de056='" + getDe056() + "'" +
            ", de057='" + getDe057() + "'" +
            ", de058='" + getDe058() + "'" +
            ", de059='" + getDe059() + "'" +
            ", de060='" + getDe060() + "'" +
            ", de061='" + getDe061() + "'" +
            ", de062='" + getDe062() + "'" +
            ", de063='" + getDe063() + "'" +
            ", de064='" + getDe064() + "'" +
            ", de065='" + getDe065() + "'" +
            ", de066='" + getDe066() + "'" +
            ", de067='" + getDe067() + "'" +
            ", de068='" + getDe068() + "'" +
            ", de069='" + getDe069() + "'" +
            ", de070='" + getDe070() + "'" +
            ", de071='" + getDe071() + "'" +
            ", de072='" + getDe072() + "'" +
            ", de073='" + getDe073() + "'" +
            ", de074='" + getDe074() + "'" +
            ", de075='" + getDe075() + "'" +
            ", de076='" + getDe076() + "'" +
            ", de077='" + getDe077() + "'" +
            ", de078='" + getDe078() + "'" +
            ", de079='" + getDe079() + "'" +
            ", de080='" + getDe080() + "'" +
            ", de081='" + getDe081() + "'" +
            ", de082='" + getDe082() + "'" +
            ", de083='" + getDe083() + "'" +
            ", de084='" + getDe084() + "'" +
            ", de085='" + getDe085() + "'" +
            ", de086='" + getDe086() + "'" +
            ", de087='" + getDe087() + "'" +
            ", de088='" + getDe088() + "'" +
            ", de089='" + getDe089() + "'" +
            ", de090='" + getDe090() + "'" +
            ", de091='" + getDe091() + "'" +
            ", de092='" + getDe092() + "'" +
            ", de093='" + getDe093() + "'" +
            ", de094='" + getDe094() + "'" +
            ", de095='" + getDe095() + "'" +
            ", de096='" + getDe096() + "'" +
            ", de097='" + getDe097() + "'" +
            ", de098='" + getDe098() + "'" +
            ", de099='" + getDe099() + "'" +
            ", de100='" + getDe100() + "'" +
            ", de101='" + getDe101() + "'" +
            ", de102='" + getDe102() + "'" +
            ", de103='" + getDe103() + "'" +
            ", de104='" + getDe104() + "'" +
            ", de105='" + getDe105() + "'" +
            ", de106='" + getDe106() + "'" +
            ", de107='" + getDe107() + "'" +
            ", de108='" + getDe108() + "'" +
            ", de109='" + getDe109() + "'" +
            ", de110='" + getDe110() + "'" +
            ", de111='" + getDe111() + "'" +
            ", de112='" + getDe112() + "'" +
            ", de113='" + getDe113() + "'" +
            ", de114='" + getDe114() + "'" +
            ", de115='" + getDe115() + "'" +
            ", de116='" + getDe116() + "'" +
            ", de117='" + getDe117() + "'" +
            ", de118='" + getDe118() + "'" +
            ", de119='" + getDe119() + "'" +
            ", de120='" + getDe120() + "'" +
            ", de121='" + getDe121() + "'" +
            ", de122='" + getDe122() + "'" +
            ", de123='" + getDe123() + "'" +
            ", de124='" + getDe124() + "'" +
            ", de125='" + getDe125() + "'" +
            ", de126='" + getDe126() + "'" +
            ", de127='" + getDe127() + "'" +
            ", de128='" + getDe128() + "'" +
            ", isoMsg='" + getIsoMsg() + "'" +
            ", bankId=" + getBankId() +
            "}";
    }
}
