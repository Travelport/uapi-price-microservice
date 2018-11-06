package com.travelport.refimpl.air.price.requestMapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.travelport.refimpl.air.price.models.PassengerCriterium;
import com.travelport.schema.common_v45_0.PersonalGeography;
import com.travelport.schema.common_v45_0.SearchPassenger;

@Component
public class SearchPassengerMapper {
	
	private static final Logger LOG = LoggerFactory.getLogger(AirPriceRequestMapper.class);
	/**
	 * Required for minimum air price request.
	 * Sets the passenger type code (PTC).
	 * Sets a booking traveler reference for each passenger type code (PTC).
	 * Calls a helper method to generate a random booking traveler ref.
	 * Each booking traveler ref must be unique for each passenger type code (PTC).
	 */
	public List<SearchPassenger> mapSearchPassengers(List<PassengerCriterium> passengers)
	{
		LOG.debug("***Set Passengers");
		List<SearchPassenger> passengerList = new ArrayList<SearchPassenger>();
		for(PassengerCriterium passenger:passengers)
		{
			SearchPassenger searchPassenger = new SearchPassenger();
			UUID uuid = UUID.randomUUID();
			searchPassenger.setBookingTravelerRef(uuid.toString());
			searchPassenger.setCode(passenger.getValue());
			searchPassenger.setAge(mapAge(passenger.getAge()));
			searchPassenger.setPersonalGeography(mapTravelerGeography(passenger));
			
			for(int passengerCopy = 0; passengerCopy < passenger.getNumber(); passengerCopy++)
			{
				passengerList.add(searchPassenger);
			}
		}
		return passengerList;
	}
	
	private BigInteger mapAge(Integer age)
	{
		BigInteger bigAge = null;
		
		if(age != null)
		{
			bigAge = BigInteger.valueOf(age.intValue());
		}
		
		return bigAge;
	}
	
	private PersonalGeography mapTravelerGeography(PassengerCriterium passenger)
	{
		PersonalGeography geography = null;
		
		if(passenger.getTravelerGeographicLocationType()!=null)
		{
			geography = new PersonalGeography();
			switch(passenger.getTravelerGeographicLocationType())
			{
				case "StateOrProvince":
					geography.setStateProvinceCode(passenger.getTravelerGeographicLocation());
					break;
				case "City":
					geography.setCityCode(passenger.getTravelerGeographicLocation());
					break;
				case "Country":
					geography.setCountryCode(passenger.getTravelerGeographicLocation());
					break;
			}
		}

		return geography;
	}
}
