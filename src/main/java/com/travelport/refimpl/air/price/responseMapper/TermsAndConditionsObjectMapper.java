package com.travelport.refimpl.air.price.responseMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.travelport.refimpl.air.price.models.BaggageAllowance;
import com.travelport.refimpl.air.price.models.BaggageItem;
import com.travelport.refimpl.air.price.models.TermsAndConditionsFull;
import com.travelport.schema.air_v45_0.AirPricePoint;
import com.travelport.schema.air_v45_0.AirPricingInfo;
import com.travelport.schema.air_v45_0.BookingInfo;
import com.travelport.schema.air_v45_0.FareInfo;
import com.travelport.schema.air_v45_0.FlightOption;
import com.travelport.schema.air_v45_0.Option;

@Component
public class TermsAndConditionsObjectMapper {
	
	public List<TermsAndConditionsFull> mapTermsAndConditions(AirPricingInfo pricingInfo)
	{
		List<TermsAndConditionsFull> termsAndConditionsList = new ArrayList<TermsAndConditionsFull>();
		TermsAndConditionsFull termsAndConditions = new TermsAndConditionsFull();
		
		termsAndConditions.setType("TermsAndConditionsFull");
		termsAndConditions.setExpiryDate(pricingInfo.getLatestTicketingTime());
		termsAndConditions.setValidatingCarrier(pricingInfo.getPlatingCarrier());
		termsAndConditions.setBaggageAllowance(mapBaggageAllowances(pricingInfo));
		termsAndConditionsList.add(termsAndConditions);
		
		return termsAndConditionsList;
	}
	
	private List<BaggageAllowance> mapBaggageAllowances(AirPricingInfo pricingInfo)
	{
		List<BaggageAllowance> baggageAllowances = new ArrayList<BaggageAllowance>();
		BaggageAllowance baggage = new BaggageAllowance();
		baggage.setType("BaggageAllowance");
		baggage.setBaggageItem(mapBaggageItems(pricingInfo));
		
		if(baggage.getBaggageItem()!=null)
		{
			baggage.setProductRef(mapProductRef(pricingInfo.getFlightOptionsList().getFlightOption()));
			baggageAllowances.add(baggage);
		}
		
		return baggage.getBaggageItem() != null ? baggageAllowances : null;
	}
	
	private List<BaggageItem> mapBaggageItems(AirPricingInfo pricingInfo)
	{
		List<BaggageItem> baggageItems = null;
		BaggageItem baggageItem = new BaggageItem();
		
		int baggageQuantity = mapBaggageQuantity(pricingInfo);
		
		if(baggageQuantity != -1)
		{
			baggageItems = new ArrayList<BaggageItem>();
			baggageItem.setType("BaggageItem");
			baggageItem.setQuantity(baggageQuantity);
			baggageItems.add(baggageItem);
		}
		
		return baggageItems;
	}
	
	private int mapBaggageQuantity(AirPricingInfo pricingInfo)
	{
		int baggageQuantity = -1;
		
		if(pricingInfo.getFlightOptionsList()!=null)
		{
			FlightOptionLoop:
			for(FlightOption flightOption:pricingInfo.getFlightOptionsList().getFlightOption())
			{
				Option option = flightOption.getOption().get(0);
				for(BookingInfo bookingInfo:option.getBookingInfo())
				{
					FareInfo fareInfo = pricingInfo.getFareInfo().stream()
							  .filter(fare -> bookingInfo.getFareInfoRef().equals(fare.getKey()))
							  .findAny()
							  .orElse(null);
					
					baggageQuantity = getLesserBaggageQuantity(
							fareInfo,baggageQuantity);
					
					if(baggageQuantity==-1)
						break FlightOptionLoop;
				}
			}
		}
		return baggageQuantity;
	}
	
	private int getLesserBaggageQuantity(FareInfo fareInfo, int baggageQuantity)
	{
		//If there is no baggageAllowance in the fareInfo, exit with -1
		if(fareInfo.getBaggageAllowance() == null || 
				fareInfo.getBaggageAllowance().getNumberOfPieces() == null)
		{
			baggageQuantity = -1;
		}
		else if(fareInfo.getBaggageAllowance().getNumberOfPieces().intValue() < baggageQuantity ||
				baggageQuantity == -1)
		{
			baggageQuantity = fareInfo.getBaggageAllowance().getNumberOfPieces().intValue();
		}
		return baggageQuantity;
	}
	
	private List<String> mapProductRef(List<FlightOption> flightOptions)
	{
		List<String> productRef = new ArrayList<String>();
		int nProducts = flightOptions.size();
		int iterator = 0;
		
		while(iterator < nProducts)
		{
			productRef.add("p"+iterator++);
		}
		
		return productRef;
	}
}
