package com.org.simulator.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.org.simulator.domain.enumeration.IsoVersions;

/**
 * A DTO for the {@link com.org.simulator.domain.Bank} entity.
 */
public class BankDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3, max = 150)
    @Pattern(regexp = "[a-zA-Z ]*")
    private String name;

    @NotNull
    @Size(min = 9, max = 150)
    @Pattern(regexp = "[0-9]*")
    private String code;

    private String logo;

    @NotNull
    @Pattern(regexp = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))")
    private String ip;

    @NotNull
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[0-9]*")
    private String port;

    @NotNull
    private IsoVersions isoType;

    @NotNull
    private Boolean signOnOffFlag;

    @NotNull
    private Boolean pinExchangeFlag;

    @NotNull
    private Boolean macExchangeFlag;

    @NotNull
    private Boolean echoFlag;

    @NotNull
    private Boolean cutoverFlag;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String masterKey;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public IsoVersions getIsoType() {
        return isoType;
    }

    public void setIsoType(IsoVersions isoType) {
        this.isoType = isoType;
    }

    public Boolean isSignOnOffFlag() {
        return signOnOffFlag;
    }

    public void setSignOnOffFlag(Boolean signOnOffFlag) {
        this.signOnOffFlag = signOnOffFlag;
    }

    public Boolean isPinExchangeFlag() {
        return pinExchangeFlag;
    }

    public void setPinExchangeFlag(Boolean pinExchangeFlag) {
        this.pinExchangeFlag = pinExchangeFlag;
    }

    public Boolean isMacExchangeFlag() {
        return macExchangeFlag;
    }

    public void setMacExchangeFlag(Boolean macExchangeFlag) {
        this.macExchangeFlag = macExchangeFlag;
    }

    public Boolean isEchoFlag() {
        return echoFlag;
    }

    public void setEchoFlag(Boolean echoFlag) {
        this.echoFlag = echoFlag;
    }

    public Boolean isCutoverFlag() {
        return cutoverFlag;
    }

    public void setCutoverFlag(Boolean cutoverFlag) {
        this.cutoverFlag = cutoverFlag;
    }

    public String getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(String masterKey) {
        this.masterKey = masterKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BankDTO bankDTO = (BankDTO) o;
        if (bankDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bankDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BankDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", logo='" + getLogo() + "'" +
            ", ip='" + getIp() + "'" +
            ", port='" + getPort() + "'" +
            ", isoType='" + getIsoType() + "'" +
            ", signOnOffFlag='" + isSignOnOffFlag() + "'" +
            ", pinExchangeFlag='" + isPinExchangeFlag() + "'" +
            ", macExchangeFlag='" + isMacExchangeFlag() + "'" +
            ", echoFlag='" + isEchoFlag() + "'" +
            ", cutoverFlag='" + isCutoverFlag() + "'" +
            ", masterKey='" + getMasterKey() + "'" +
            "}";
    }
}
