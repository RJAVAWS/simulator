package com.org.simulator.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.org.simulator.domain.Emv} entity.
 */
public class EmvDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    @Pattern(regexp = "[a-zA-Z0-9 _.]*")
    private String emvDescription;

    @Lob
    private String de5F2A;

    @Lob
    private String de82;

    @Lob
    private String de84;

    @Lob
    private String de95;

    @Lob
    private String de9A;

    @Lob
    private String de9C;

    @Lob
    private String de9F02;

    @Lob
    private String de9F03;

    @Lob
    private String de9F09;

    @Lob
    private String de9F10;

    @Lob
    private String de9F1A;

    @Lob
    private String de9F1E;

    @Lob
    private String de9F26;

    @Lob
    private String de9F27;

    @Lob
    private String de9F33;

    @Lob
    private String de9F34;

    @Lob
    private String de9F35;

    @Lob
    private String de9F36;

    @Lob
    private String de9F37;

    @Lob
    private String de9F41;

    @Lob
    private String de9F53;

    @Lob
    private String de8A;

    @Lob
    private String de71;

    @Lob
    private String de72;

    @Lob
    private String de91;

    @Lob
    private String others;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmvDescription() {
        return emvDescription;
    }

    public void setEmvDescription(String emvDescription) {
        this.emvDescription = emvDescription;
    }

    public String getDe5F2A() {
        return de5F2A;
    }

    public void setDe5F2A(String de5F2A) {
        this.de5F2A = de5F2A;
    }

    public String getDe82() {
        return de82;
    }

    public void setDe82(String de82) {
        this.de82 = de82;
    }

    public String getDe84() {
        return de84;
    }

    public void setDe84(String de84) {
        this.de84 = de84;
    }

    public String getDe95() {
        return de95;
    }

    public void setDe95(String de95) {
        this.de95 = de95;
    }

    public String getDe9A() {
        return de9A;
    }

    public void setDe9A(String de9A) {
        this.de9A = de9A;
    }

    public String getDe9C() {
        return de9C;
    }

    public void setDe9C(String de9C) {
        this.de9C = de9C;
    }

    public String getDe9F02() {
        return de9F02;
    }

    public void setDe9F02(String de9F02) {
        this.de9F02 = de9F02;
    }

    public String getDe9F03() {
        return de9F03;
    }

    public void setDe9F03(String de9F03) {
        this.de9F03 = de9F03;
    }

    public String getDe9F09() {
        return de9F09;
    }

    public void setDe9F09(String de9F09) {
        this.de9F09 = de9F09;
    }

    public String getDe9F10() {
        return de9F10;
    }

    public void setDe9F10(String de9F10) {
        this.de9F10 = de9F10;
    }

    public String getDe9F1A() {
        return de9F1A;
    }

    public void setDe9F1A(String de9F1A) {
        this.de9F1A = de9F1A;
    }

    public String getDe9F1E() {
        return de9F1E;
    }

    public void setDe9F1E(String de9F1E) {
        this.de9F1E = de9F1E;
    }

    public String getDe9F26() {
        return de9F26;
    }

    public void setDe9F26(String de9F26) {
        this.de9F26 = de9F26;
    }

    public String getDe9F27() {
        return de9F27;
    }

    public void setDe9F27(String de9F27) {
        this.de9F27 = de9F27;
    }

    public String getDe9F33() {
        return de9F33;
    }

    public void setDe9F33(String de9F33) {
        this.de9F33 = de9F33;
    }

    public String getDe9F34() {
        return de9F34;
    }

    public void setDe9F34(String de9F34) {
        this.de9F34 = de9F34;
    }

    public String getDe9F35() {
        return de9F35;
    }

    public void setDe9F35(String de9F35) {
        this.de9F35 = de9F35;
    }

    public String getDe9F36() {
        return de9F36;
    }

    public void setDe9F36(String de9F36) {
        this.de9F36 = de9F36;
    }

    public String getDe9F37() {
        return de9F37;
    }

    public void setDe9F37(String de9F37) {
        this.de9F37 = de9F37;
    }

    public String getDe9F41() {
        return de9F41;
    }

    public void setDe9F41(String de9F41) {
        this.de9F41 = de9F41;
    }

    public String getDe9F53() {
        return de9F53;
    }

    public void setDe9F53(String de9F53) {
        this.de9F53 = de9F53;
    }

    public String getDe8A() {
        return de8A;
    }

    public void setDe8A(String de8A) {
        this.de8A = de8A;
    }

    public String getDe71() {
        return de71;
    }

    public void setDe71(String de71) {
        this.de71 = de71;
    }

    public String getDe72() {
        return de72;
    }

    public void setDe72(String de72) {
        this.de72 = de72;
    }

    public String getDe91() {
        return de91;
    }

    public void setDe91(String de91) {
        this.de91 = de91;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmvDTO emvDTO = (EmvDTO) o;
        if (emvDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emvDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmvDTO{" +
            "id=" + getId() +
            ", emvDescription='" + getEmvDescription() + "'" +
            ", de5F2A='" + getDe5F2A() + "'" +
            ", de82='" + getDe82() + "'" +
            ", de84='" + getDe84() + "'" +
            ", de95='" + getDe95() + "'" +
            ", de9A='" + getDe9A() + "'" +
            ", de9C='" + getDe9C() + "'" +
            ", de9F02='" + getDe9F02() + "'" +
            ", de9F03='" + getDe9F03() + "'" +
            ", de9F09='" + getDe9F09() + "'" +
            ", de9F10='" + getDe9F10() + "'" +
            ", de9F1A='" + getDe9F1A() + "'" +
            ", de9F1E='" + getDe9F1E() + "'" +
            ", de9F26='" + getDe9F26() + "'" +
            ", de9F27='" + getDe9F27() + "'" +
            ", de9F33='" + getDe9F33() + "'" +
            ", de9F34='" + getDe9F34() + "'" +
            ", de9F35='" + getDe9F35() + "'" +
            ", de9F36='" + getDe9F36() + "'" +
            ", de9F37='" + getDe9F37() + "'" +
            ", de9F41='" + getDe9F41() + "'" +
            ", de9F53='" + getDe9F53() + "'" +
            ", de8A='" + getDe8A() + "'" +
            ", de71='" + getDe71() + "'" +
            ", de72='" + getDe72() + "'" +
            ", de91='" + getDe91() + "'" +
            ", others='" + getOthers() + "'" +
            "}";
    }
}
