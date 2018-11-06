package com.travelport.refimpl.air.price.responseMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.travelport.refimpl.air.price.models.Amount;
import com.travelport.refimpl.air.price.models.Price;
import com.travelport.refimpl.air.price.models.PriceBreakdown;
import com.travelport.refimpl.air.price.models.Tax;
import com.travelport.refimpl.air.price.models.Taxes;
import com.travelport.schema.air_v45_0.AirPriceResult;
import com.travelport.schema.air_v45_0.AirPricingInfo;
import com.travelport.schema.air_v45_0.AirPricingSolution;
import com.travelport.schema.common_v45_0.TypeTaxInfo;

@Component
public class PriceObjectMapper {
	public Price mapPrice(List<AirPriceResult> priceResults)
	{
		Price price = new Price();
		price.setType("PriceDetail");
		AirPricingSolution pricingSolution = priceResults.get(0).getAirPricingSolution().get(0);
		price.setCurrencyCode(pricingSolution.getTotalPrice().substring(0, 3));
		price.setTotalPrice(Double.valueOf(pricingSolution.getTotalPrice().substring(3)));
		price.setBase(Double.valueOf(pricingSolution.getApproximateBasePrice().substring(3)));
		price.setTotalTaxes(Double.valueOf(pricingSolution.getApproximateTaxes().substring(3)));
		price.setPriceBreakdown(mapPriceBreakdowns(pricingSolution.getAirPricingInfo()));
		return price;
	}
	
	private List<PriceBreakdown> mapPriceBreakdowns(List<AirPricingInfo> pricingInfoList)
	{
		List<PriceBreakdown> priceBreakdowns = new ArrayList<PriceBreakdown>();
		for(AirPricingInfo pricingInfo:pricingInfoList)
		{
			PriceBreakdown priceBreakdown = new PriceBreakdown();
			priceBreakdown.setType("PriceBreakdownAir");
			priceBreakdown.setRequestedPassengerType(pricingInfo.getFareInfo().get(0).getPassengerTypeCode());
			priceBreakdown.setAmount(mapAmount(pricingInfo));
			priceBreakdowns.add(priceBreakdown);
		}
		return priceBreakdowns;
	}
	
	private Amount mapAmount(AirPricingInfo pricingInfo)
	{
		Amount amount = new Amount();
		amount.setBase(Double.valueOf(pricingInfo.getApproximateBasePrice().substring(3)));
		amount.setTotal(Double.valueOf(pricingInfo.getTotalPrice().substring(3)));
		amount.setTaxes(mapTaxes(pricingInfo.getTaxInfo()));
		return amount;
		
	}
	
	private Taxes mapTaxes(List<TypeTaxInfo> taxInfoList)
	{
		Taxes taxes = new Taxes();
		List<Tax> taxList = new ArrayList<Tax>();
		for(TypeTaxInfo taxInfo:taxInfoList)
		{
			Tax tax = new Tax();
			tax.setTaxCode(taxInfo.getCategory());
			tax.setValue(Double.valueOf(taxInfo.getAmount().substring(3)));
			taxList.add(tax);
		}
		taxes.setTax(taxList);
		return taxes;
	}
}
