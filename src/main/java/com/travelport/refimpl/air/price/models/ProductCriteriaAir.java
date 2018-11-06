
package com.travelport.refimpl.air.price.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "@type",
    "sequence",
    "SpecificFlightCriteria"
})
public class ProductCriteriaAir {

    @JsonProperty("@type")
    private String type;
    @JsonProperty("sequence")
    private Integer sequence;
    @JsonProperty("SpecificFlightCriteria")
    private List<SpecificFlightCriterium> specificFlightCriteria = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProductCriteriaAir() {
    }

    /**
     * 
     * @param sequence
     * @param specificFlightCriteria
     * @param type
     */
    public ProductCriteriaAir(String type, Integer sequence, List<SpecificFlightCriterium> specificFlightCriteria) {
        super();
        this.type = type;
        this.sequence = sequence;
        this.specificFlightCriteria = specificFlightCriteria;
    }

    @JsonProperty("@type")
    public String getType() {
        return type;
    }

    @JsonProperty("@type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("sequence")
    public Integer getSequence() {
        return sequence;
    }

    @JsonProperty("sequence")
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @JsonProperty("SpecificFlightCriteria")
    public List<SpecificFlightCriterium> getSpecificFlightCriteria() {
        return specificFlightCriteria;
    }

    @JsonProperty("SpecificFlightCriteria")
    public void setSpecificFlightCriteria(List<SpecificFlightCriterium> specificFlightCriteria) {
        this.specificFlightCriteria = specificFlightCriteria;
    }

}
