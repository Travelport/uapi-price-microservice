package com.travelport.refimpl.air.price.responseMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.travelport.refimpl.air.price.models.Brand;
import com.travelport.refimpl.air.price.models.FlightProduct;
import com.travelport.refimpl.air.price.models.PassengerFlight;
import com.travelport.schema.air_v45_0.AirPricingInfo;
import com.travelport.schema.air_v45_0.BookingInfo;
import com.travelport.schema.air_v45_0.FareInfo;

@Component
public class PassengerFlightMapper {
	public List<PassengerFlight> mapPassengerFlight(List<AirPricingInfo> airPricingInfoList)
	{
		List<PassengerFlight> passengerFlights = new ArrayList<PassengerFlight>();
		for(AirPricingInfo airPricingInfo:airPricingInfoList)
		{
			PassengerFlight passengerFlight = new PassengerFlight();
			passengerFlight.setType("PassengerFlight");
			passengerFlight.setPassengerQuantity(airPricingInfo.getPassengerType().size());
			passengerFlight.setPassengerTypeCode(airPricingInfo.getPassengerType().get(0).getCode());
			passengerFlight.setFlightProduct(mapFlightProduct(airPricingInfo));
			passengerFlights.add(passengerFlight);
		}
		return passengerFlights;
	}
	
	private List<FlightProduct> mapFlightProduct(AirPricingInfo airPricingInfo)
	{
		List<FlightProduct> flightProducts = new ArrayList<FlightProduct>();
		Integer sequenceIterator = 1;
		for(BookingInfo bookingInfo:airPricingInfo.getBookingInfo())
		{
			FareInfo fareInfo = airPricingInfo.getFareInfo().stream()
					  .filter(fare -> bookingInfo.getFareInfoRef().equals(fare.getKey()))
					  .findAny()
					  .orElse(null);
			
			FlightProduct flightProduct = new FlightProduct();
			flightProduct.setType("FlightProduct");
			flightProduct.setClassOfService(bookingInfo.getBookingCode());
			flightProduct.setCabin(bookingInfo.getCabinClass());
			flightProduct.setFareBasisCode(fareInfo.getFareBasis());
			flightProduct.setPrivateFareInd(fareInfo.getPrivateFare()!=null);
			flightProduct.setSegmentSequence(Arrays.asList(sequenceIterator++));
			flightProduct.setBrand(mapBrand(fareInfo));
			flightProducts.add(flightProduct);
		}
		return flightProducts;
	}
	
	private Brand mapBrand(FareInfo fareInfo)
	{
		com.travelport.schema.air_v45_0.Brand uapiBrand = fareInfo.getBrand();
		Brand brand = null;
		
		if(uapiBrand!=null)
		{
			brand = new Brand();
			brand.setType("Brand");
			if(uapiBrand.getBrandTier()!=null)
			{
				brand.setTier(Integer.valueOf(uapiBrand.getBrandTier()));
			}
			brand.setId(uapiBrand.getBrandID());
			brand.setBrandRef(uapiBrand.getKey());
			brand.setName(uapiBrand.getName());
		}
		return brand;
	}
}
