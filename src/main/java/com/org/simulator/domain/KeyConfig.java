package com.org.simulator.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.org.simulator.domain.enumeration.PinMacType;

/**
 * A KeyConfig.
 */
@Entity
@Table(name = "key_config")
public class KeyConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "pm_type")
    private PinMacType pmType;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]*")
    @Column(name = "jhi_key", nullable = false)
    private String key;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]*")
    @Column(name = "kcv", nullable = false)
    private String kcv;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "de_01")
    private String de01;

    @Column(name = "de_02")
    private String de02;

    @Column(name = "de_03")
    private String de03;

    @Column(name = "de_04")
    private String de04;

    @Column(name = "de_05")
    private String de05;

    @ManyToOne
    @JsonIgnoreProperties("keyConfigs")
    private Bank bank;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PinMacType getPmType() {
        return pmType;
    }

    public KeyConfig pmType(PinMacType pmType) {
        this.pmType = pmType;
        return this;
    }

    public void setPmType(PinMacType pmType) {
        this.pmType = pmType;
    }

    public String getKey() {
        return key;
    }

    public KeyConfig key(String key) {
        this.key = key;
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKcv() {
        return kcv;
    }

    public KeyConfig kcv(String kcv) {
        this.kcv = kcv;
        return this;
    }

    public void setKcv(String kcv) {
        this.kcv = kcv;
    }

    public Boolean isStatus() {
        return status;
    }

    public KeyConfig status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDe01() {
        return de01;
    }

    public KeyConfig de01(String de01) {
        this.de01 = de01;
        return this;
    }

    public void setDe01(String de01) {
        this.de01 = de01;
    }

    public String getDe02() {
        return de02;
    }

    public KeyConfig de02(String de02) {
        this.de02 = de02;
        return this;
    }

    public void setDe02(String de02) {
        this.de02 = de02;
    }

    public String getDe03() {
        return de03;
    }

    public KeyConfig de03(String de03) {
        this.de03 = de03;
        return this;
    }

    public void setDe03(String de03) {
        this.de03 = de03;
    }

    public String getDe04() {
        return de04;
    }

    public KeyConfig de04(String de04) {
        this.de04 = de04;
        return this;
    }

    public void setDe04(String de04) {
        this.de04 = de04;
    }

    public String getDe05() {
        return de05;
    }

    public KeyConfig de05(String de05) {
        this.de05 = de05;
        return this;
    }

    public void setDe05(String de05) {
        this.de05 = de05;
    }

    public Bank getBank() {
        return bank;
    }

    public KeyConfig bank(Bank bank) {
        this.bank = bank;
        return this;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KeyConfig)) {
            return false;
        }
        return id != null && id.equals(((KeyConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "KeyConfig{" +
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
            "}";
    }
}
