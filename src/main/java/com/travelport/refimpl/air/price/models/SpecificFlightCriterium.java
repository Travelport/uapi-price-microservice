package com.travelport.refimpl.air.price.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "@type",
    "carrier",
    "flightNumber",
    "departureDate",
    "departureTime",
    "arrivalDate",
    "arrivalTime",
    "from",
    "to",
    "cabin",
    "classOfService",
    "brandTier",
    "segmentSequence"
})
public class SpecificFlightCriterium {

    @JsonProperty("@type")
    private String type;
    @JsonProperty("carrier")
    private String carrier;
    @JsonProperty("flightNumber")
    private String flightNumber;
    @JsonProperty("departureDate")
    private String departureDate;
    @JsonProperty("departureTime")
    private String departureTime;
    @JsonProperty("arrivalDate")
    private String arrivalDate;
    @JsonProperty("arrivalTime")
    private String arrivalTime;
    @JsonProperty("from")
    private String from;
    @JsonProperty("to")
    private String to;
    @JsonProperty("cabin")
    private String cabin;
    @JsonProperty("classOfService")
    private String classOfService;
    @JsonProperty("brandTier")
    private Integer brandTier;
    @JsonProperty("segmentSequence")
    private Integer segmentSequence;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SpecificFlightCriterium() {
    }

    /**
     * 
     * @param to
     * @param brandTier
     * @param cabin
     * @param departureDate
     * @param departureTime
     * @param flightNumber
     * @param from
     * @param type
     * @param arrivalTime
     * @param segmentSequence
     * @param carrier
     * @param arrivalDate
     * @param classOfService
     */
    public SpecificFlightCriterium(String type, String carrier, String flightNumber, String departureDate, String departureTime, String arrivalDate, String arrivalTime, String from, String to, String cabin, String classOfService, Integer brandTier, Integer segmentSequence) {
        super();
        this.type = type;
        this.carrier = carrier;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.from = from;
        this.to = to;
        this.cabin = cabin;
        this.classOfService = classOfService;
        this.brandTier = brandTier;
        this.segmentSequence = segmentSequence;
    }

    @JsonProperty("@type")
    public String getType() {
        return type;
    }

    @JsonProperty("@type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("carrier")
    public String getCarrier() {
        return carrier;
    }

    @JsonProperty("carrier")
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @JsonProperty("flightNumber")
    public String getFlightNumber() {
        return flightNumber;
    }

    @JsonProperty("flightNumber")
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @JsonProperty("departureDate")
    public String getDepartureDate() {
        return departureDate;
    }

    @JsonProperty("departureDate")
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @JsonProperty("departureTime")
    public String getDepartureTime() {
        return departureTime;
    }

    @JsonProperty("departureTime")
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @JsonProperty("arrivalDate")
    public String getArrivalDate() {
        return arrivalDate;
    }

    @JsonProperty("arrivalDate")
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @JsonProperty("arrivalTime")
    public String getArrivalTime() {
        return arrivalTime;
    }

    @JsonProperty("arrivalTime")
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @JsonProperty("from")
    public String getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(String from) {
        this.from = from;
    }

    @JsonProperty("to")
    public String getTo() {
        return to;
    }

    @JsonProperty("to")
    public void setTo(String to) {
        this.to = to;
    }

    @JsonProperty("cabin")
    public String getCabin() {
        return cabin;
    }

    @JsonProperty("cabin")
    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    @JsonProperty("classOfService")
    public String getClassOfService() {
        return classOfService;
    }

    @JsonProperty("classOfService")
    public void setClassOfService(String classOfService) {
        this.classOfService = classOfService;
    }

    @JsonProperty("brandTier")
    public Integer getBrandTier() {
        return brandTier;
    }

    @JsonProperty("brandTier")
    public void setBrandTier(Integer brandTier) {
        this.brandTier = brandTier;
    }

    @JsonProperty("segmentSequence")
    public Integer getSegmentSequence() {
        return segmentSequence;
    }

    @JsonProperty("segmentSequence")
    public void setSegmentSequence(Integer segmentSequence) {
        this.segmentSequence = segmentSequence;
    }

}
