
package com.travelport.refimpl.air.price.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "value",
    "currencyCode",
    "feeCode",
    "reportingAuthority",
    "purpose",
    "description"
})
public class Fee {

    @JsonProperty("value")
    private Integer value;
    @JsonProperty("currencyCode")
    private String currencyCode;
    @JsonProperty("feeCode")
    private String feeCode;
    @JsonProperty("reportingAuthority")
    private String reportingAuthority;
    @JsonProperty("purpose")
    private String purpose;
    @JsonProperty("description")
    private String description;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Fee() {
    }

    /**
     * 
     * @param currencyCode
     * @param feeCode
     * @param description
     * @param value
     * @param reportingAuthority
     * @param purpose
     */
    public Fee(Integer value, String currencyCode, String feeCode, String reportingAuthority, String purpose, String description) {
        super();
        this.value = value;
        this.currencyCode = currencyCode;
        this.feeCode = feeCode;
        this.reportingAuthority = reportingAuthority;
        this.purpose = purpose;
        this.description = description;
    }

    @JsonProperty("value")
    public Integer getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Integer value) {
        this.value = value;
    }

    @JsonProperty("currencyCode")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @JsonProperty("currencyCode")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @JsonProperty("feeCode")
    public String getFeeCode() {
        return feeCode;
    }

    @JsonProperty("feeCode")
    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    @JsonProperty("reportingAuthority")
    public String getReportingAuthority() {
        return reportingAuthority;
    }

    @JsonProperty("reportingAuthority")
    public void setReportingAuthority(String reportingAuthority) {
        this.reportingAuthority = reportingAuthority;
    }

    @JsonProperty("purpose")
    public String getPurpose() {
        return purpose;
    }

    @JsonProperty("purpose")
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

}
