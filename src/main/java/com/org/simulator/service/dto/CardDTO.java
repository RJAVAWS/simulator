package com.org.simulator.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.org.simulator.domain.Card} entity.
 */
public class CardDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    @Pattern(regexp = "[a-zA-Z0-9 _.]*")
    private String cardDescription;

    @NotNull
    @Size(min = 16, max = 19)
    @Pattern(regexp = "[0-9]*")
    private String cardNumber;

    @NotNull
    @Size(min = 3, max = 3)
    @Pattern(regexp = "[0-9]*")
    private String cvv;

    @NotNull
    @Size(min = 4, max = 4)
    @Pattern(regexp = "[0-9]*")
    private String expiry;

    @NotNull
    @Size(min = 4, max = 4)
    @Pattern(regexp = "[0-9]*")
    private String pin;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9=]*")
    private String track2data;


    private Long emvId;

    private Set<TestCaseDTO> testCases = new HashSet<>();

    private Long bankId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTrack2data() {
        return track2data;
    }

    public void setTrack2data(String track2data) {
        this.track2data = track2data;
    }

    public Long getEmvId() {
        return emvId;
    }

    public void setEmvId(Long emvId) {
        this.emvId = emvId;
    }

    public Set<TestCaseDTO> getTestCases() {
        return testCases;
    }

    public void setTestCases(Set<TestCaseDTO> testCases) {
        this.testCases = testCases;
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

        CardDTO cardDTO = (CardDTO) o;
        if (cardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CardDTO{" +
            "id=" + getId() +
            ", cardDescription='" + getCardDescription() + "'" +
            ", cardNumber='" + getCardNumber() + "'" +
            ", cvv='" + getCvv() + "'" +
            ", expiry='" + getExpiry() + "'" +
            ", pin='" + getPin() + "'" +
            ", track2data='" + getTrack2data() + "'" +
            ", emvId=" + getEmvId() +
            ", bankId=" + getBankId() +
            "}";
    }
}
