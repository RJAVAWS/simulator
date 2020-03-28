package com.org.simulator.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Card.
 */
@Entity
@Table(name = "card")
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    @Pattern(regexp = "[a-zA-Z0-9 _.]*")
    @Column(name = "card_description", length = 255, nullable = false)
    private String cardDescription;

    @NotNull
    @Size(min = 16, max = 19)
    @Pattern(regexp = "[0-9]*")
    @Column(name = "card_number", length = 19, nullable = false)
    private String cardNumber;

    @NotNull
    @Size(min = 3, max = 3)
    @Pattern(regexp = "[0-9]*")
    @Column(name = "cvv", length = 3, nullable = false)
    private String cvv;

    @NotNull
    @Size(min = 4, max = 4)
    @Pattern(regexp = "[0-9]*")
    @Column(name = "expiry", length = 4, nullable = false)
    private String expiry;

    @NotNull
    @Size(min = 4, max = 4)
    @Pattern(regexp = "[0-9]*")
    @Column(name = "pin", length = 4, nullable = false)
    private String pin;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9=]*")
    @Column(name = "track_2_data", nullable = false)
    private String track2data;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Emv emv;

    @ManyToMany
    @JoinTable(name = "card_test_case",
               joinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "test_case_id", referencedColumnName = "id"))
    private Set<TestCase> testCases = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("cards")
    private Bank bank;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public Card cardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
        return this;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Card cardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public Card cvv(String cvv) {
        this.cvv = cvv;
        return this;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiry() {
        return expiry;
    }

    public Card expiry(String expiry) {
        this.expiry = expiry;
        return this;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getPin() {
        return pin;
    }

    public Card pin(String pin) {
        this.pin = pin;
        return this;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTrack2data() {
        return track2data;
    }

    public Card track2data(String track2data) {
        this.track2data = track2data;
        return this;
    }

    public void setTrack2data(String track2data) {
        this.track2data = track2data;
    }

    public Emv getEmv() {
        return emv;
    }

    public Card emv(Emv emv) {
        this.emv = emv;
        return this;
    }

    public void setEmv(Emv emv) {
        this.emv = emv;
    }

    public Set<TestCase> getTestCases() {
        return testCases;
    }

    public Card testCases(Set<TestCase> testCases) {
        this.testCases = testCases;
        return this;
    }

    public Card addTestCase(TestCase testCase) {
        this.testCases.add(testCase);
        testCase.getCards().add(this);
        return this;
    }

    public Card removeTestCase(TestCase testCase) {
        this.testCases.remove(testCase);
        testCase.getCards().remove(this);
        return this;
    }

    public void setTestCases(Set<TestCase> testCases) {
        this.testCases = testCases;
    }

    public Bank getBank() {
        return bank;
    }

    public Card bank(Bank bank) {
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
        if (!(o instanceof Card)) {
            return false;
        }
        return id != null && id.equals(((Card) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Card{" +
            "id=" + getId() +
            ", cardDescription='" + getCardDescription() + "'" +
            ", cardNumber='" + getCardNumber() + "'" +
            ", cvv='" + getCvv() + "'" +
            ", expiry='" + getExpiry() + "'" +
            ", pin='" + getPin() + "'" +
            ", track2data='" + getTrack2data() + "'" +
            "}";
    }
}
