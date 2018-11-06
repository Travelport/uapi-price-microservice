
package com.travelport.refimpl.air.price.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "OfferSummary",
    "ErrorMessage"
})
public class Response {

    @JsonProperty("OfferSummary")
    private OfferSummary offerSummary;
    @JsonProperty("ErrorMessage")
    private String errorMessage;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Response() {
    }

    /**
     * 
     * @param offerSummary
     */
    public Response(OfferSummary offerSummary, String errorMessage) {
        super();
        this.offerSummary = offerSummary;
        this.errorMessage = errorMessage;
    }

    @JsonProperty("OfferSummary")
    public OfferSummary getOfferSummary() {
        return offerSummary;
    }

    @JsonProperty("OfferSummary")
    public void setOfferSummary(OfferSummary offerSummary) {
        this.offerSummary = offerSummary;
    }
    
    @JsonProperty("ErrorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty("ErrorMessage")
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
