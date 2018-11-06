package com.travelport.refimpl.air.price.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "@type",

})
public class BuildFromProductsRequest {

    @JsonProperty("@type")
    private String type;
    @JsonProperty("PricingModifiersAir")
    private PricingModifiersAir pricingModifiersAir;
    @JsonProperty("PassengerCriteria")
    private List<PassengerCriterium> passengerCriteria = null;
    @JsonProperty("ProductCriteriaAir")
    private List<ProductCriteriaAir> productCriteriaAir = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BuildFromProductsRequest() {
    }

    /**
     * 
     * @param productCriteriaAir
     * @param type
     * @param passengerCriteria
     * @param pricingModifiersAir
     */
    public BuildFromProductsRequest(String type, PricingModifiersAir pricingModifiersAir, List<PassengerCriterium> passengerCriteria, List<ProductCriteriaAir> productCriteriaAir) {
        super();
        this.type = type;
        this.pricingModifiersAir = pricingModifiersAir;
        this.passengerCriteria = passengerCriteria;
        this.productCriteriaAir = productCriteriaAir;
    }

    @JsonProperty("@type")
    public String getType() {
        return type;
    }

    @JsonProperty("@type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("PricingModifiersAir")
    public PricingModifiersAir getPricingModifiersAir() {
        return pricingModifiersAir;
    }

    @JsonProperty("PricingModifiersAir")
    public void setPricingModifiersAir(PricingModifiersAir pricingModifiersAir) {
        this.pricingModifiersAir = pricingModifiersAir;
    }

    @JsonProperty("PassengerCriteria")
    public List<PassengerCriterium> getPassengerCriteria() {
        return passengerCriteria;
    }

    @JsonProperty("PassengerCriteria")
    public void setPassengerCriteria(List<PassengerCriterium> passengerCriteria) {
        this.passengerCriteria = passengerCriteria;
    }

    @JsonProperty("ProductCriteriaAir")
    public List<ProductCriteriaAir> getProductCriteriaAir() {
        return productCriteriaAir;
    }

    @JsonProperty("ProductCriteriaAir")
    public void setProductCriteriaAir(List<ProductCriteriaAir> productCriteriaAir) {
        this.productCriteriaAir = productCriteriaAir;
    }

}
