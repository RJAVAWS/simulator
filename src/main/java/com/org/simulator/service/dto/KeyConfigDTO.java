package com.org.simulator.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.org.simulator.domain.enumeration.PinMacType;

/**
 * A DTO for the {@link com.org.simulator.domain.KeyConfig} entity.
 */
public class KeyConfigDTO implements Serializable {

    private Long id;

    private PinMacType pmType;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String key;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String kcv;

    private Boolean status;

    private String de01;

    private String de02;

    private String de03;

    private String de04;

    private String de05;


    private Long bankId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PinMacType getPmType() {
        return pmType;
    }

    public void setPmType(PinMacType pmType) {
        this.pmType = pmType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKcv() {
        return kcv;
    }

    public void setKcv(String kcv) {
        this.kcv = kcv;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDe01() {
        return de01;
    }

    public void setDe01(String de01) {
        this.de01 = de01;
    }

    public String getDe02() {
        return de02;
    }

    public void setDe02(String de02) {
        this.de02 = de02;
    }

    public String getDe03() {
        return de03;
    }

    public void setDe03(String de03) {
        this.de03 = de03;
    }

    public String getDe04() {
        return de04;
    }

    public void setDe04(String de04) {
        this.de04 = de04;
    }

    public String getDe05() {
        return de05;
    }

    public void setDe05(String de05) {
        this.de05 = de05;
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

        KeyConfigDTO keyConfigDTO = (KeyConfigDTO) o;
        if (keyConfigDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), keyConfigDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KeyConfigDTO{" +
            "id=" + getId() +
            ", pmType='" + getPmType() + "'" +
            ", key='" + getKey() + "'" +
            ", kcv='" + getKcv() + "'" +
            ", status='" + isStatus() + "'" +
            ", de01='" + getDe01() + "'" +
            ", de02='" + getDe02() + "'" +
            ", de03='" + getDe03() + "'" +
            ", de04='" + getDe04() + "'" +
            ", de05='" + getDe05() + "'" +
            ", bankId=" + getBankId() +
            "}";
    }
}
