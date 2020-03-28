package com.org.simulator.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.org.simulator.domain.enumeration.IsoVersions;

/**
 * A Bank.
 */
@Entity
@Table(name = "bank")
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 150)
    @Pattern(regexp = "[a-zA-Z ]*")
    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @NotNull
    @Size(min = 9, max = 150)
    @Pattern(regexp = "[0-9]*")
    @Column(name = "code", length = 150, nullable = false)
    private String code;

    @Column(name = "logo")
    private String logo;

    @NotNull
    @Pattern(regexp = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))")
    @Column(name = "ip", nullable = false)
    private String ip;

    @NotNull
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[0-9]*")
    @Column(name = "port", length = 10, nullable = false)
    private String port;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "iso_type", nullable = false)
    private IsoVersions isoType;

    @NotNull
    @Column(name = "sign_on_off_flag", nullable = false)
    private Boolean signOnOffFlag;

    @NotNull
    @Column(name = "pin_exchange_flag", nullable = false)
    private Boolean pinExchangeFlag;

    @NotNull
    @Column(name = "mac_exchange_flag", nullable = false)
    private Boolean macExchangeFlag;

    @NotNull
    @Column(name = "echo_flag", nullable = false)
    private Boolean echoFlag;

    @NotNull
    @Column(name = "cutover_flag", nullable = false)
    private Boolean cutoverFlag;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    @Column(name = "master_key", nullable = false)
    private String masterKey;

    @OneToMany(mappedBy = "bank")
    private Set<Card> cards = new HashSet<>();

    @OneToMany(mappedBy = "bank")
    private Set<KeyConfig> keyConfigs = new HashSet<>();

    @OneToMany(mappedBy = "bank")
    private Set<Transaction> transactions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Bank name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public Bank code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogo() {
        return logo;
    }

    public Bank logo(String logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIp() {
        return ip;
    }

    public Bank ip(String ip) {
        this.ip = ip;
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public Bank port(String port) {
        this.port = port;
        return this;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public IsoVersions getIsoType() {
        return isoType;
    }

    public Bank isoType(IsoVersions isoType) {
        this.isoType = isoType;
        return this;
    }

    public void setIsoType(IsoVersions isoType) {
        this.isoType = isoType;
    }

    public Boolean isSignOnOffFlag() {
        return signOnOffFlag;
    }

    public Bank signOnOffFlag(Boolean signOnOffFlag) {
        this.signOnOffFlag = signOnOffFlag;
        return this;
    }

    public void setSignOnOffFlag(Boolean signOnOffFlag) {
        this.signOnOffFlag = signOnOffFlag;
    }

    public Boolean isPinExchangeFlag() {
        return pinExchangeFlag;
    }

    public Bank pinExchangeFlag(Boolean pinExchangeFlag) {
        this.pinExchangeFlag = pinExchangeFlag;
        return this;
    }

    public void setPinExchangeFlag(Boolean pinExchangeFlag) {
        this.pinExchangeFlag = pinExchangeFlag;
    }

    public Boolean isMacExchangeFlag() {
        return macExchangeFlag;
    }

    public Bank macExchangeFlag(Boolean macExchangeFlag) {
        this.macExchangeFlag = macExchangeFlag;
        return this;
    }

    public void setMacExchangeFlag(Boolean macExchangeFlag) {
        this.macExchangeFlag = macExchangeFlag;
    }

    public Boolean isEchoFlag() {
        return echoFlag;
    }

    public Bank echoFlag(Boolean echoFlag) {
        this.echoFlag = echoFlag;
        return this;
    }

    public void setEchoFlag(Boolean echoFlag) {
        this.echoFlag = echoFlag;
    }

    public Boolean isCutoverFlag() {
        return cutoverFlag;
    }

    public Bank cutoverFlag(Boolean cutoverFlag) {
        this.cutoverFlag = cutoverFlag;
        return this;
    }

    public void setCutoverFlag(Boolean cutoverFlag) {
        this.cutoverFlag = cutoverFlag;
    }

    public String getMasterKey() {
        return masterKey;
    }

    public Bank masterKey(String masterKey) {
        this.masterKey = masterKey;
        return this;
    }

    public void setMasterKey(String masterKey) {
        this.masterKey = masterKey;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public Bank cards(Set<Card> cards) {
        this.cards = cards;
        return this;
    }

    public Bank addCard(Card card) {
        this.cards.add(card);
        card.setBank(this);
        return this;
    }

    public Bank removeCard(Card card) {
        this.cards.remove(card);
        card.setBank(null);
        return this;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public Set<KeyConfig> getKeyConfigs() {
        return keyConfigs;
    }

    public Bank keyConfigs(Set<KeyConfig> keyConfigs) {
        this.keyConfigs = keyConfigs;
        return this;
    }

    public Bank addKeyConfig(KeyConfig keyConfig) {
        this.keyConfigs.add(keyConfig);
        keyConfig.setBank(this);
        return this;
    }

    public Bank removeKeyConfig(KeyConfig keyConfig) {
        this.keyConfigs.remove(keyConfig);
        keyConfig.setBank(null);
        return this;
    }

    public void setKeyConfigs(Set<KeyConfig> keyConfigs) {
        this.keyConfigs = keyConfigs;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Bank transactions(Set<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public Bank addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setBank(this);
        return this;
    }

    public Bank removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setBank(null);
        return this;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bank)) {
            return false;
        }
        return id != null && id.equals(((Bank) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Bank{" +
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
