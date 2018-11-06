package com.travelport.refimpl.air.price.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"fareRuleType",
"lowFareFinderInd",
"returnBrandedFaresInd",
"BuildFromProductsRequest"
})
public class OfferQueryBuildFromProducts {

@JsonProperty("fareRuleType")
private String fareRuleType;
@JsonProperty("lowFareFinderInd")
private String lowFareFinderInd;
@JsonProperty("returnBrandedFaresInd")
private String returnBrandedFaresInd;
@JsonProperty("BuildFromProductsRequest")
private BuildFromProductsRequest buildFromProductsRequest;

/**
* No args constructor for use in serialization
*
*/
public OfferQueryBuildFromProducts() {
}

/**
*
* @param buildFromProductsRequest
* @param returnBrandedFaresInd
* @param fareRuleType
* @param lowFareFinderInd
*/
public OfferQueryBuildFromProducts(String fareRuleType, String lowFareFinderInd, String returnBrandedFaresInd, BuildFromProductsRequest buildFromProductsRequest) {
super();
this.fareRuleType = fareRuleType;
this.lowFareFinderInd = lowFareFinderInd;
this.returnBrandedFaresInd = returnBrandedFaresInd;
this.buildFromProductsRequest = buildFromProductsRequest;
}

@JsonProperty("fareRuleType")
public String getFareRuleType() {
return fareRuleType;
}

@JsonProperty("fareRuleType")
public void setFareRuleType(String fareRuleType) {
this.fareRuleType = fareRuleType;
}

@JsonProperty("lowFareFinderInd")
public String getLowFareFinderInd() {
return lowFareFinderInd;
}

@JsonProperty("lowFareFinderInd")
public void setLowFareFinderInd(String lowFareFinderInd) {
this.lowFareFinderInd = lowFareFinderInd;
}

@JsonProperty("returnBrandedFaresInd")
public String getReturnBrandedFaresInd() {
return returnBrandedFaresInd;
}

@JsonProperty("returnBrandedFaresInd")
public void setReturnBrandedFaresInd(String returnBrandedFaresInd) {
this.returnBrandedFaresInd = returnBrandedFaresInd;
}

@JsonProperty("BuildFromProductsRequest")
public BuildFromProductsRequest getBuildFromProductsRequest() {
return buildFromProductsRequest;
}

@JsonProperty("BuildFromProductsRequest")
public void setBuildFromProductsRequest(BuildFromProductsRequest buildFromProductsRequest) {
this.buildFromProductsRequest = buildFromProductsRequest;
}
}