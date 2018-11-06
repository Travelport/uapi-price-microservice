package com.travelport.refimpl.air.price.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "OfferQueryBuildFromProducts"
})
public class Request {

    @JsonProperty("OfferQueryBuildFromProducts")
    private OfferQueryBuildFromProducts offerQueryBuildFromProducts;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Request() {
    }

    /**
     * 
     * @param offerQueryBuildFromProducts
     */
    public Request(OfferQueryBuildFromProducts offerQueryBuildFromProducts) {
        super();
        this.offerQueryBuildFromProducts = offerQueryBuildFromProducts;
    }

    @JsonProperty("OfferQueryBuildFromProducts")
    public OfferQueryBuildFromProducts getOfferQueryBuildFromProducts() {
        return offerQueryBuildFromProducts;
    }

    @JsonProperty("OfferQueryBuildFromProducts")
    public void setOfferQueryBuildFromProducts(OfferQueryBuildFromProducts offerQueryBuildFromProducts) {
        this.offerQueryBuildFromProducts = offerQueryBuildFromProducts;
    }

}
