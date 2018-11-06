package com.travelport.refimpl.air.price.requestMapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.travelport.refimpl.air.price.models.ProductCriteriaAir;
import com.travelport.refimpl.air.price.models.SpecificFlightCriterium;
import com.travelport.schema.air_v45_0.AirItinerary;
import com.travelport.schema.air_v45_0.TypeBaseAirSegment;

@Component
@EnableConfigurationProperties
@ConfigurationProperties("air")

public class AirItineraryMapper {
	
	private static final Logger LOG = LoggerFactory.getLogger(AirPriceRequestMapper.class);
	/**
	 * Method to build each segment in a journey.
	 * @param key UAPI Base 64-encoded Universally Unique Identifier (UUID)
	 * @param carrier Air carrier code Ex. UA
	 * @param flightNumber Air carrier flight number Ex. 1411
	 * @param origin Location of the origin, Ex. DEN 
	 * @param destination Location of the destination Ex. LAX
	 * @param departTime Date and time of departure Ex. 2018-05-27T08:20:00.000+10:00
	 * @param arrivalTime Date and time of arrival Ex. 2018-05-27T08:20:00.000+10:00
	 * @param providerCode The provider code from Carrier, GDS Ex. 1G 
	 */	
	 @Value("${air.core}")
	  private String core;

	public AirItinerary mapAirItinerary(List<ProductCriteriaAir> productCriteriaAir)	
	{
		LOG.debug("Entered mapAirItinerary");
		AirItinerary airItinerary = new AirItinerary();
		airItinerary.getAirSegment().addAll(mapAirSegments(productCriteriaAir));
		return airItinerary;
	}
	
	private List<TypeBaseAirSegment> mapAirSegments(List<ProductCriteriaAir> productCriteriaAir)
	{
		List<TypeBaseAirSegment> segments = new ArrayList<TypeBaseAirSegment>();
		for(int i = 0; i < productCriteriaAir.size(); i++)
		{                                       
			List<SpecificFlightCriterium> flightCriteriums = productCriteriaAir.get(i).getSpecificFlightCriteria();
			for(SpecificFlightCriterium flightCriterium:flightCriteriums)
			{
				TypeBaseAirSegment segment = new TypeBaseAirSegment();
				segment.setCarrier(flightCriterium.getCarrier());
				segment.setFlightNumber(flightCriterium.getFlightNumber());
				segment.setOrigin(flightCriterium.getFrom());
				segment.setDestination(flightCriterium.getTo());
				segment.setDepartureTime(flightCriterium.getDepartureDate()+"T"+flightCriterium.getDepartureTime());
				segment.setArrivalTime(flightCriterium.getArrivalDate()+"T"+flightCriterium.getArrivalTime());
				segment.setKey(flightCriterium.getSegmentSequence().toString());
				segment.setProviderCode(core);
				segments.add(segment);
			}
		}	
		LOG.debug("List size: " + segments.size());
		return segments;
	}
}
