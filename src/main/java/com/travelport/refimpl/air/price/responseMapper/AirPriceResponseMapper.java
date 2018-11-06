package com.travelport.refimpl.air.price.responseMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.travelport.refimpl.air.price.models.OfferSummary;
import com.travelport.refimpl.air.price.models.Response;
import com.travelport.schema.air_v45_0.AirPriceRsp;


@Component
public class AirPriceResponseMapper {
	
	@Autowired 
	PriceObjectMapper priceMapper;
	@Autowired
	TermsAndConditionsObjectMapper tcMapper;
	@Autowired
	ProductObjectMapper productMapper;
	
	public AirPriceResponseMapper() {
		
	}
	
	public Response getResponse(AirPriceRsp airPriceRsp)
	{
		Response response = new Response();
		response.setOfferSummary(mapOfferSummary(airPriceRsp));
		return response;
	}
	
	private OfferSummary mapOfferSummary(AirPriceRsp airPriceRsp)
	{
		OfferSummary offerSummary = new OfferSummary();
		offerSummary.setId(airPriceRsp.getTransactionId());
		offerSummary.setPrice(priceMapper.mapPrice(airPriceRsp.getAirPriceResult()));
		offerSummary.setProduct(productMapper.mapProducts(airPriceRsp.getAirItinerary(),airPriceRsp.getAirPriceResult()));
		offerSummary.setTermsAndConditionsFull(
				tcMapper.mapTermsAndConditions(airPriceRsp.getAirPriceResult().get(0).getAirPricingSolution().get(0).getAirPricingInfo().get(0)));
		return offerSummary;
	}
	
	
}
