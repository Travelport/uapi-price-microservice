package com.travelport.refimpl.air.price.responseMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.travelport.refimpl.air.price.models.Product;
import com.travelport.schema.air_v45_0.AirItinerary;
import com.travelport.schema.air_v45_0.AirPriceResult;
import com.travelport.schema.air_v45_0.AirPricingSolution;

@Component
public class ProductObjectMapper {
	
	@Autowired
	FlightSegmentsMapper flightSegmentsMapper;
	
	@Autowired
	PassengerFlightMapper passengerFlightMapper;
	
	public List<Product> mapProducts(AirItinerary itinerary, List<AirPriceResult> priceResults )
	{
		List<Product> products = new ArrayList<Product>();
		AirPricingSolution priceSolution = priceResults.get(0).getAirPricingSolution().get(0);
		Product product = new Product();
		product.setFlightSegment(flightSegmentsMapper.mapFlightSegments(
				priceSolution.getAirSegmentRef(), itinerary.getAirSegment()));
		product.setPassengerFlight(
				passengerFlightMapper.mapPassengerFlight(priceSolution.getAirPricingInfo()));
		products.add(product);
		return products;
	}
	
}
