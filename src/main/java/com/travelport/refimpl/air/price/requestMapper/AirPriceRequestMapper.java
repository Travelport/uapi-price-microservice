package com.travelport.refimpl.air.price.requestMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.travelport.refimpl.air.price.models.BuildFromProductsRequest;
import com.travelport.refimpl.air.price.models.OfferQueryBuildFromProducts;
import com.travelport.schema.air_v45_0.AirPriceReq;
import com.travelport.schema.air_v45_0.AirPricingCommand;
import com.travelport.schema.air_v45_0.Connection;
import com.travelport.schema.common_v45_0.BillingPointOfSaleInfo;

@Component
@EnableConfigurationProperties
@ConfigurationProperties("air")

public class AirPriceRequestMapper {
	
	private static final Logger LOG = LoggerFactory.getLogger(AirPriceRequestMapper.class);
	
	@Value("${air.branch}")
	private String branch;
	
	@Autowired
	PriceModifiersMapper priceModMapper;
	
	@Autowired
	SearchPassengerMapper passengerMapper;
	
	@Autowired
	AirItineraryMapper itineraryMapper;
	
	public AirPriceRequestMapper() {
		
	}

	public AirPriceReq getAirPrice(OfferQueryBuildFromProducts offerQueryBuildFromProducts, Boolean priceAsBooked) 
	{
		AirPriceReq airPriceRequest = new AirPriceReq();
		airPriceRequest.setTargetBranch(branch);
		airPriceRequest.setBillingPointOfSaleInfo(mapBillingPointOfSale());
		airPriceRequest.setAuthorizedBy("user");
		BuildFromProductsRequest buildFromProductsRequest = offerQueryBuildFromProducts.getBuildFromProductsRequest();
		airPriceRequest.setAirPricingModifiers(
				priceModMapper.mapAirPricingModifiers(
				    buildFromProductsRequest.getPricingModifiersAir(),
				    buildFromProductsRequest.getProductCriteriaAir()));
		
		airPriceRequest.getSearchPassenger().addAll(
				passengerMapper.mapSearchPassengers(
				    buildFromProductsRequest.getPassengerCriteria()));
		
		airPriceRequest.setAirItinerary(
				itineraryMapper.mapAirItinerary(
				    buildFromProductsRequest.getProductCriteriaAir()));
		
		airPriceRequest.getAirPricingCommand().add(mapAirPricingCommand());
		return airPriceRequest;
	}
	
	private BillingPointOfSaleInfo mapBillingPointOfSale()
	{
		LOG.debug("Entered mapBillingPointOfSale");
		BillingPointOfSaleInfo bpos = new BillingPointOfSaleInfo();
		bpos.setOriginApplication("UAPI");
		return bpos;
	}
	
	
	/**
	 * Required for minimum air price request
	 * Empty object needed in order to get a price response.
	 * UAPI documentation does not reference this object for a air price request.
	 */
	private AirPricingCommand mapAirPricingCommand()
	{
		LOG.debug("Entered mapAirPricingCommand");
		AirPricingCommand airPricingCommand = new AirPricingCommand();
		return airPricingCommand;
	}
	
	/**
	 * Connection indicator for multiple connected legs.
	 * Not necessary for the minimum fields for air price request. 	
	 */
	private Connection mapConnection()
	{
		LOG.debug("Entered mapConnection");
		Connection connectionList = new Connection();
		return connectionList;
	}
	
}
