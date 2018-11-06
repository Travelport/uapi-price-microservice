package com.travelport.refimpl.air.price.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelport.refimpl.air.price.connector.AirPriceConnector;
import com.travelport.refimpl.air.price.models.OfferQueryBuildFromProducts;
import com.travelport.refimpl.air.price.models.Response;
import com.travelport.refimpl.air.price.requestMapper.AirPriceRequestMapper;
import com.travelport.refimpl.air.price.responseMapper.AirPriceResponseMapper;
import com.travelport.schema.air_v45_0.AirPriceReq;
import com.travelport.schema.air_v45_0.AirPriceRsp;
import com.travelport.service.air_v45_0.AirFaultMessage;

@Service
public class AirPriceService {
	
	private static final Logger LOG = LoggerFactory.getLogger(AirPriceService.class);
	
	@Autowired
	AirPriceRequestMapper airPriceRequestMapper;
	@Autowired
	AirPriceResponseMapper airPriceResponseMapper;
	@Autowired
	AirPriceConnector airPriceConnector;
	
	public Response getAirPrice(OfferQueryBuildFromProducts offerQueryBuildFromProducts, Boolean priceAsBooked)
	{
		LOG.debug("Model sent to mapper to get a response");
		AirPriceReq airPriceReq = airPriceRequestMapper.getAirPrice(offerQueryBuildFromProducts, priceAsBooked);
		Response response = new Response();
		try {
			AirPriceRsp airPriceRsp = airPriceConnector.callAirPrice(airPriceReq);
			response = airPriceResponseMapper.getResponse(airPriceRsp);
		} catch (AirFaultMessage e) {
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}
}