package com.org.simulator.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.org.simulator.domain.enumeration.TranType;

import com.org.simulator.domain.enumeration.IsoVersions;

/**
 * A MtiConfig.
 */
@Entity
@Table(name = "mti_config")
public class MtiConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    @Pattern(regexp = "[a-zA-Z0-9 _.]*")
    @Column(name = "mti_description", length = 255, nullable = false)
    private String mtiDescription;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tn_type", nullable = false)
    private TranType tnType;

    @Enumerated(EnumType.STRING)
    @Column(name = "iso_type")
    private IsoVersions isoType;

    @NotNull
    @Pattern(regexp = "[0-9]*")
    @Column(name = "mti", nullable = false)
    private String mti;

    @NotNull
    @Pattern(regexp = "[0-9]*")
    @Column(name = "repeat_mti", nullable = false)
    private String repeatMti;

    @NotNull
    @Pattern(regexp = "[0-9]*")
    @Column(name = "response_mti", nullable = false)
    private String responseMti;

    @NotNull
    @Pattern(regexp = "[0-9]*")
    @Column(name = "response_repeat_mti", nullable = false)
    private String responseRepeatMti;

    @Pattern(regexp = "[0-9]*")
    @Column(name = "nmm_identifier_de")
    private String nmmIdentifierDe;

    @Pattern(regexp = "[0-9]*")
    @Column(name = "nmm_identifier_de_val")
    private String nmmIdentifierDeVal;

    @NotNull
    @Pattern(regexp = "[0-9]*")
    @Column(name = "response_identifier_de", nullable = false)
    private String responseIdentifierDe;

    @NotNull
    @Pattern(regexp = "[0-9]*")
    @Column(name = "response_identifier_de_val", nullable = false)
    private String responseIdentifierDeVal;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMtiDescription() {
        return mtiDescription;
    }

    public MtiConfig mtiDescription(String mtiDescription) {
        this.mtiDescription = mtiDescription;
        return this;
    }

    public void setMtiDescription(String mtiDescription) {
        this.mtiDescription = mtiDescription;
    }

    public TranType getTnType() {
        return tnType;
    }

    public MtiConfig tnType(TranType tnType) {
        this.tnType = tnType;
        return this;
    }

    public void setTnType(TranType tnType) {
        this.tnType = tnType;
    }

    public IsoVersions getIsoType() {
        return isoType;
    }

    public MtiConfig isoType(IsoVersions isoType) {
        this.isoType = isoType;
        return this;
    }

    public void setIsoType(IsoVersions isoType) {
        this.isoType = isoType;
    }

    public String getMti() {
        return mti;
    }

    public MtiConfig mti(String mti) {
        this.mti = mti;
        return this;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public String getRepeatMti() {
        return repeatMti;
    }

    public MtiConfig repeatMti(String repeatMti) {
        this.repeatMti = repeatMti;
        return this;
    }

    public void setRepeatMti(String repeatMti) {
        this.repeatMti = repeatMti;
    }

    public String getResponseMti() {
        return responseMti;
    }

    public MtiConfig responseMti(String responseMti) {
        this.responseMti = responseMti;
        return this;
    }

    public void setResponseMti(String responseMti) {
        this.responseMti = responseMti;
    }

    public String getResponseRepeatMti() {
        return responseRepeatMti;
    }

    public MtiConfig responseRepeatMti(String responseRepeatMti) {
        this.responseRepeatMti = responseRepeatMti;
        return this;
    }

    public void setResponseRepeatMti(String responseRepeatMti) {
        this.responseRepeatMti = responseRepeatMti;
    }

    public String getNmmIdentifierDe() {
        return nmmIdentifierDe;
    }

    public MtiConfig nmmIdentifierDe(String nmmIdentifierDe) {
        this.nmmIdentifierDe = nmmIdentifierDe;
        return this;
    }

    public void setNmmIdentifierDe(String nmmIdentifierDe) {
        this.nmmIdentifierDe = nmmIdentifierDe;
    }

    public String getNmmIdentifierDeVal() {
        return nmmIdentifierDeVal;
    }

    public MtiConfig nmmIdentifierDeVal(String nmmIdentifierDeVal) {
        this.nmmIdentifierDeVal = nmmIdentifierDeVal;
        return this;
    }

    public void setNmmIdentifierDeVal(String nmmIdentifierDeVal) {
        this.nmmIdentifierDeVal = nmmIdentifierDeVal;
    }

    public String getResponseIdentifierDe() {
        return responseIdentifierDe;
    }

    public MtiConfig responseIdentifierDe(String responseIdentifierDe) {
        this.responseIdentifierDe = responseIdentifierDe;
        return this;
    }

    public void setResponseIdentifierDe(String responseIdentifierDe) {
        this.responseIdentifierDe = responseIdentifierDe;
    }

    public String getResponseIdentifierDeVal() {
        return responseIdentifierDeVal;
    }

    public MtiConfig responseIdentifierDeVal(String responseIdentifierDeVal) {
        this.responseIdentifierDeVal = responseIdentifierDeVal;
        return this;
    }

    public void setResponseIdentifierDeVal(String responseIdentifierDeVal) {
        this.responseIdentifierDeVal = responseIdentifierDeVal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MtiConfig)) {
            return false;
        }
        return id != null && id.equals(((MtiConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MtiConfig{" +
            "id=" + getId() +
            ", mtiDescription='" + getMtiDescription() + "'" +
            ", tnType='" + getTnType() + "'" +
            ", isoType='" + getIsoType() + "'" +
            ", mti='" + getMti() + "'" +
            ", repeatMti='" + getRepeatMti() + "'" +
            ", responseMti='" + getResponseMti() + "'" +
            ", responseRepeatMti='" + getResponseRepeatMti() + "'" +
            ", nmmIdentifierDe='" + getNmmIdentifierDe() + "'" +
            ", nmmIdentifierDeVal='" + getNmmIdentifierDeVal() + "'" +
            ", responseIdentifierDe='" + getResponseIdentifierDe() + "'" +
            ", responseIdentifierDeVal='" + getResponseIdentifierDeVal() + "'" +
            "}";
    }
}
