package com.org.simulator.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.org.simulator.domain.enumeration.TranType;
import com.org.simulator.domain.enumeration.IsoVersions;

/**
 * A DTO for the {@link com.org.simulator.domain.MtiConfig} entity.
 */
public class MtiConfigDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    @Pattern(regexp = "[a-zA-Z0-9 _.]*")
    private String mtiDescription;

    @NotNull
    private TranType tnType;

    private IsoVersions isoType;

    @NotNull
    @Pattern(regexp = "[0-9]*")
    private String mti;

    @NotNull
    @Pattern(regexp = "[0-9]*")
    private String repeatMti;

    @NotNull
    @Pattern(regexp = "[0-9]*")
    private String responseMti;

    @NotNull
    @Pattern(regexp = "[0-9]*")
    private String responseRepeatMti;

    @Pattern(regexp = "[0-9]*")
    private String nmmIdentifierDe;

    @Pattern(regexp = "[0-9]*")
    private String nmmIdentifierDeVal;

    @NotNull
    @Pattern(regexp = "[0-9]*")
    private String responseIdentifierDe;

    @NotNull
    @Pattern(regexp = "[0-9]*")
    private String responseIdentifierDeVal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMtiDescription() {
        return mtiDescription;
    }

    public void setMtiDescription(String mtiDescription) {
        this.mtiDescription = mtiDescription;
    }

    public TranType getTnType() {
        return tnType;
    }

    public void setTnType(TranType tnType) {
        this.tnType = tnType;
    }

    public IsoVersions getIsoType() {
        return isoType;
    }

    public void setIsoType(IsoVersions isoType) {
        this.isoType = isoType;
    }

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public String getRepeatMti() {
        return repeatMti;
    }

    public void setRepeatMti(String repeatMti) {
        this.repeatMti = repeatMti;
    }

    public String getResponseMti() {
        return responseMti;
    }

    public void setResponseMti(String responseMti) {
        this.responseMti = responseMti;
    }

    public String getResponseRepeatMti() {
        return responseRepeatMti;
    }

    public void setResponseRepeatMti(String responseRepeatMti) {
        this.responseRepeatMti = responseRepeatMti;
    }

    public String getNmmIdentifierDe() {
        return nmmIdentifierDe;
    }

    public void setNmmIdentifierDe(String nmmIdentifierDe) {
        this.nmmIdentifierDe = nmmIdentifierDe;
    }

    public String getNmmIdentifierDeVal() {
        return nmmIdentifierDeVal;
    }

    public void setNmmIdentifierDeVal(String nmmIdentifierDeVal) {
        this.nmmIdentifierDeVal = nmmIdentifierDeVal;
    }

    public String getResponseIdentifierDe() {
        return responseIdentifierDe;
    }

    public void setResponseIdentifierDe(String responseIdentifierDe) {
        this.responseIdentifierDe = responseIdentifierDe;
    }

    public String getResponseIdentifierDeVal() {
        return responseIdentifierDeVal;
    }

    public void setResponseIdentifierDeVal(String responseIdentifierDeVal) {
        this.responseIdentifierDeVal = responseIdentifierDeVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MtiConfigDTO mtiConfigDTO = (MtiConfigDTO) o;
        if (mtiConfigDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mtiConfigDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MtiConfigDTO{" +
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
