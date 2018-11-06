package com.travelport.refimpl.air.price.requestMapper;

import java.util.List;

import com.travelport.refimpl.air.price.models.FareSelection;
import com.travelport.refimpl.air.price.models.PricingModifiersAir;
import com.travelport.refimpl.air.price.models.ProductCriteriaAir;
import com.travelport.schema.air_v45_0.AirPricingModifiers;
import com.travelport.schema.air_v45_0.TypeFaresIndicator;

import org.springframework.stereotype.Component;

@Component
public class PriceModifiersMapper {
	public AirPricingModifiers mapAirPricingModifiers(PricingModifiersAir pricingModifiersAir, List<ProductCriteriaAir> productCriteriaAir)
	{
		AirPricingModifiers airPricingModifiers = new AirPricingModifiers();
		
		airPricingModifiers.setPlatingCarrier(productCriteriaAir.get(0).getSpecificFlightCriteria().get(0).getCarrier());
		
		if(pricingModifiersAir != null)
		{
			airPricingModifiers = mapFareSelectionModifiers(airPricingModifiers,pricingModifiersAir);
			airPricingModifiers.setCurrencyType(pricingModifiersAir.getCurrencyCode());
		}
		return airPricingModifiers;
	}
	
	private AirPricingModifiers mapFareSelectionModifiers(AirPricingModifiers airPricingModifiers, PricingModifiersAir pricingModifiersAir)
	
	{
		FareSelection fareSelection = pricingModifiersAir.getFareSelection();
		if(fareSelection!=null)
		{
			airPricingModifiers.setProhibitAdvancePurchaseFares(fareSelection.getProhibitAdvancePurchaseFaresInd());
			airPricingModifiers.setProhibitMaxStayFares(fareSelection.getProhibitMaxStayFaresInd());
			airPricingModifiers.setProhibitMinStayFares(fareSelection.getProhibitMinStayFaresInd());
			airPricingModifiers.setProhibitNonRefundableFares(fareSelection.getRefundableOnlyInd());
			airPricingModifiers.setFaresIndicator(mapFaresIndicator(fareSelection.getFareType()));
		}
		return airPricingModifiers;
	}

	private TypeFaresIndicator mapFaresIndicator(String fareType) {
		TypeFaresIndicator faresIndicator = null;
		if(fareType != null)
		{
			faresIndicator= TypeFaresIndicator.fromValue(fareType);
		}
		return faresIndicator;
	}
}
