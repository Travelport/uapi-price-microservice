
package com.travelport.refimpl.air.price.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "offerRef",
    "Identifier",
    "Product",
    "Price",
    "TermsAndConditionsFull"
})
public class OfferSummary {

    @JsonProperty("id")
    private String id;
    @JsonProperty("offerRef")
    private String offerRef;
    @JsonProperty("Identifier")
    private Identifier identifier;
    @JsonProperty("Product")
    private List<Product> product = null;
    @JsonProperty("Price")
    private Price price;
    @JsonProperty("TermsAndConditionsFull")
    private List<TermsAndConditionsFull> termsAndConditionsFull = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OfferSummary() {
    }

    /**
     * 
     * @param product
     * @param id
     * @param price
     * @param offerRef
     * @param termsAndConditionsFull
     * @param identifier
     */
    public OfferSummary(String id, String offerRef, Identifier identifier, List<Product> product, Price price, List<TermsAndConditionsFull> termsAndConditionsFull) {
        super();
        this.id = id;
        this.offerRef = offerRef;
        this.identifier = identifier;
        this.product = product;
        this.price = price;
        this.termsAndConditionsFull = termsAndConditionsFull;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("offerRef")
    public String getOfferRef() {
        return offerRef;
    }

    @JsonProperty("offerRef")
    public void setOfferRef(String offerRef) {
        this.offerRef = offerRef;
    }

    @JsonProperty("Identifier")
    public Identifier getIdentifier() {
        return identifier;
    }

    @JsonProperty("Identifier")
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    @JsonProperty("Product")
    public List<Product> getProduct() {
        return product;
    }

    @JsonProperty("Product")
    public void setProduct(List<Product> product) {
        this.product = product;
    }

    @JsonProperty("Price")
    public Price getPrice() {
        return price;
    }

    @JsonProperty("Price")
    public void setPrice(Price price) {
        this.price = price;
    }

    @JsonProperty("TermsAndConditionsFull")
    public List<TermsAndConditionsFull> getTermsAndConditionsFull() {
        return termsAndConditionsFull;
    }

    @JsonProperty("TermsAndConditionsFull")
    public void setTermsAndConditionsFull(List<TermsAndConditionsFull> termsAndConditionsFull) {
        this.termsAndConditionsFull = termsAndConditionsFull;
    }

}
