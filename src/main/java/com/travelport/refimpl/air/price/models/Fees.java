
package com.travelport.refimpl.air.price.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "@type",
    "TotalFees",
    "Fee"
})
public class Fees {

    @JsonProperty("@type")
    private String type;
    @JsonProperty("TotalFees")
    private Double totalFees;
    @JsonProperty("Fee")
    private List<Fee> fee = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Fees() {
    }

    /**
     * 
     * @param fee
     * @param totalFees
     * @param type
     */
    public Fees(String type, Double totalFees, List<Fee> fee) {
        super();
        this.type = type;
        this.totalFees = totalFees;
        this.fee = fee;
    }

    @JsonProperty("@type")
    public String getType() {
        return type;
    }

    @JsonProperty("@type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("TotalFees")
    public Double getTotalFees() {
        return totalFees;
    }

    @JsonProperty("TotalFees")
    public void setTotalFees(Double totalFees) {
        this.totalFees = totalFees;
    }

    @JsonProperty("Fee")
    public List<Fee> getFee() {
        return fee;
    }

    @JsonProperty("Fee")
    public void setFee(List<Fee> fee) {
        this.fee = fee;
    }

}
