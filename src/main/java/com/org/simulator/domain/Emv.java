package com.org.simulator.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Emv.
 */
@Entity
@Table(name = "emv")
public class Emv implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    @Pattern(regexp = "[a-zA-Z0-9 _.]*")
    @Column(name = "emv_description", length = 255, nullable = false)
    private String emvDescription;

    @Lob
    @Column(name = "de_5_f_2_a")
    private String de5F2A;

    @Lob
    @Column(name = "de_82")
    private String de82;

    @Lob
    @Column(name = "de_84")
    private String de84;

    @Lob
    @Column(name = "de_95")
    private String de95;

    @Lob
    @Column(name = "de_9_a")
    private String de9A;

    @Lob
    @Column(name = "de_9_c")
    private String de9C;

    @Lob
    @Column(name = "de_9_f_02")
    private String de9F02;

    @Lob
    @Column(name = "de_9_f_03")
    private String de9F03;

    @Lob
    @Column(name = "de_9_f_09")
    private String de9F09;

    @Lob
    @Column(name = "de_9_f_10")
    private String de9F10;

    @Lob
    @Column(name = "de_9_f_1_a")
    private String de9F1A;

    @Lob
    @Column(name = "de_9_f_1_e")
    private String de9F1E;

    @Lob
    @Column(name = "de_9_f_26")
    private String de9F26;

    @Lob
    @Column(name = "de_9_f_27")
    private String de9F27;

    @Lob
    @Column(name = "de_9_f_33")
    private String de9F33;

    @Lob
    @Column(name = "de_9_f_34")
    private String de9F34;

    @Lob
    @Column(name = "de_9_f_35")
    private String de9F35;

    @Lob
    @Column(name = "de_9_f_36")
    private String de9F36;

    @Lob
    @Column(name = "de_9_f_37")
    private String de9F37;

    @Lob
    @Column(name = "de_9_f_41")
    private String de9F41;

    @Lob
    @Column(name = "de_9_f_53")
    private String de9F53;

    @Lob
    @Column(name = "de_8_a")
    private String de8A;

    @Lob
    @Column(name = "de_71")
    private String de71;

    @Lob
    @Column(name = "de_72")
    private String de72;

    @Lob
    @Column(name = "de_91")
    private String de91;

    @Lob
    @Column(name = "others")
    private String others;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmvDescription() {
        return emvDescription;
    }

    public Emv emvDescription(String emvDescription) {
        this.emvDescription = emvDescription;
        return this;
    }

    public void setEmvDescription(String emvDescription) {
        this.emvDescription = emvDescription;
    }

    public String getDe5F2A() {
        return de5F2A;
    }

    public Emv de5F2A(String de5F2A) {
        this.de5F2A = de5F2A;
        return this;
    }

    public void setDe5F2A(String de5F2A) {
        this.de5F2A = de5F2A;
    }

    public String getDe82() {
        return de82;
    }

    public Emv de82(String de82) {
        this.de82 = de82;
        return this;
    }

    public void setDe82(String de82) {
        this.de82 = de82;
    }

    public String getDe84() {
        return de84;
    }

    public Emv de84(String de84) {
        this.de84 = de84;
        return this;
    }

    public void setDe84(String de84) {
        this.de84 = de84;
    }

    public String getDe95() {
        return de95;
    }

    public Emv de95(String de95) {
        this.de95 = de95;
        return this;
    }

    public void setDe95(String de95) {
        this.de95 = de95;
    }

    public String getDe9A() {
        return de9A;
    }

    public Emv de9A(String de9A) {
        this.de9A = de9A;
        return this;
    }

    public void setDe9A(String de9A) {
        this.de9A = de9A;
    }

    public String getDe9C() {
        return de9C;
    }

    public Emv de9C(String de9C) {
        this.de9C = de9C;
        return this;
    }

    public void setDe9C(String de9C) {
        this.de9C = de9C;
    }

    public String getDe9F02() {
        return de9F02;
    }

    public Emv de9F02(String de9F02) {
        this.de9F02 = de9F02;
        return this;
    }

    public void setDe9F02(String de9F02) {
        this.de9F02 = de9F02;
    }

    public String getDe9F03() {
        return de9F03;
    }

    public Emv de9F03(String de9F03) {
        this.de9F03 = de9F03;
        return this;
    }

    public void setDe9F03(String de9F03) {
        this.de9F03 = de9F03;
    }

    public String getDe9F09() {
        return de9F09;
    }

    public Emv de9F09(String de9F09) {
        this.de9F09 = de9F09;
        return this;
    }

    public void setDe9F09(String de9F09) {
        this.de9F09 = de9F09;
    }

    public String getDe9F10() {
        return de9F10;
    }

    public Emv de9F10(String de9F10) {
        this.de9F10 = de9F10;
        return this;
    }

    public void setDe9F10(String de9F10) {
        this.de9F10 = de9F10;
    }

    public String getDe9F1A() {
        return de9F1A;
    }

    public Emv de9F1A(String de9F1A) {
        this.de9F1A = de9F1A;
        return this;
    }

    public void setDe9F1A(String de9F1A) {
        this.de9F1A = de9F1A;
    }

    public String getDe9F1E() {
        return de9F1E;
    }

    public Emv de9F1E(String de9F1E) {
        this.de9F1E = de9F1E;
        return this;
    }

    public void setDe9F1E(String de9F1E) {
        this.de9F1E = de9F1E;
    }

    public String getDe9F26() {
        return de9F26;
    }

    public Emv de9F26(String de9F26) {
        this.de9F26 = de9F26;
        return this;
    }

    public void setDe9F26(String de9F26) {
        this.de9F26 = de9F26;
    }

    public String getDe9F27() {
        return de9F27;
    }

    public Emv de9F27(String de9F27) {
        this.de9F27 = de9F27;
        return this;
    }

    public void setDe9F27(String de9F27) {
        this.de9F27 = de9F27;
    }

    public String getDe9F33() {
        return de9F33;
    }

    public Emv de9F33(String de9F33) {
        this.de9F33 = de9F33;
        return this;
    }

    public void setDe9F33(String de9F33) {
        this.de9F33 = de9F33;
    }

    public String getDe9F34() {
        return de9F34;
    }

    public Emv de9F34(String de9F34) {
        this.de9F34 = de9F34;
        return this;
    }

    public void setDe9F34(String de9F34) {
        this.de9F34 = de9F34;
    }

    public String getDe9F35() {
        return de9F35;
    }

    public Emv de9F35(String de9F35) {
        this.de9F35 = de9F35;
        return this;
    }

    public void setDe9F35(String de9F35) {
        this.de9F35 = de9F35;
    }

    public String getDe9F36() {
        return de9F36;
    }

    public Emv de9F36(String de9F36) {
        this.de9F36 = de9F36;
        return this;
    }

    public void setDe9F36(String de9F36) {
        this.de9F36 = de9F36;
    }

    public String getDe9F37() {
        return de9F37;
    }

    public Emv de9F37(String de9F37) {
        this.de9F37 = de9F37;
        return this;
    }

    public void setDe9F37(String de9F37) {
        this.de9F37 = de9F37;
    }

    public String getDe9F41() {
        return de9F41;
    }

    public Emv de9F41(String de9F41) {
        this.de9F41 = de9F41;
        return this;
    }

    public void setDe9F41(String de9F41) {
        this.de9F41 = de9F41;
    }

    public String getDe9F53() {
        return de9F53;
    }

    public Emv de9F53(String de9F53) {
        this.de9F53 = de9F53;
        return this;
    }

    public void setDe9F53(String de9F53) {
        this.de9F53 = de9F53;
    }

    public String getDe8A() {
        return de8A;
    }

    public Emv de8A(String de8A) {
        this.de8A = de8A;
        return this;
    }

    public void setDe8A(String de8A) {
        this.de8A = de8A;
    }

    public String getDe71() {
        return de71;
    }

    public Emv de71(String de71) {
        this.de71 = de71;
        return this;
    }

    public void setDe71(String de71) {
        this.de71 = de71;
    }

    public String getDe72() {
        return de72;
    }

    public Emv de72(String de72) {
        this.de72 = de72;
        return this;
    }

    public void setDe72(String de72) {
        this.de72 = de72;
    }

    public String getDe91() {
        return de91;
    }

    public Emv de91(String de91) {
        this.de91 = de91;
        return this;
    }

    public void setDe91(String de91) {
        this.de91 = de91;
    }

    public String getOthers() {
        return others;
    }

    public Emv others(String others) {
        this.others = others;
        return this;
    }

    public void setOthers(String others) {
        this.others = others;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Emv)) {
            return false;
        }
        return id != null && id.equals(((Emv) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Emv{" +
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
