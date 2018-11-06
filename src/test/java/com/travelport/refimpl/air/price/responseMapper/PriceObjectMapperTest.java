package com.travelport.refimpl.air.price.responseMapper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.travelport.refimpl.air.price.models.Price;
import com.travelport.refimpl.air.price.models.PriceBreakdown;
import com.travelport.schema.air_v45_0.AirPriceResult;
import com.travelport.schema.air_v45_0.AirPricingInfo;
import com.travelport.schema.air_v45_0.AirPricingSolution;
import com.travelport.schema.air_v45_0.FareInfo;
import com.travelport.schema.common_v45_0.TypeTaxInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceObjectMapperTest {
  
  @Autowired
  PriceObjectMapper priceObjectMapper;
  
  List<AirPriceResult> priceResults = new ArrayList<AirPriceResult>();
  
  @Before
  public void populatePriceObjectMapperArgs() {
    AirPricingSolution pricingSolution = new AirPricingSolution();
    pricingSolution.setTotalPrice("USD80.00");
    pricingSolution.setApproximateBasePrice("USD60.00");
    pricingSolution.setApproximateTaxes("USD20.00");
    
    TypeTaxInfo taxInfo = new TypeTaxInfo();
    taxInfo.setCategory("TEST");
    taxInfo.setAmount("USD10.00");
    
    FareInfo adultFare = new FareInfo();
    adultFare.setPassengerTypeCode("ADT");
    AirPricingInfo adultInfo = new AirPricingInfo();
    adultInfo.getFareInfo().add(adultFare);
    adultInfo.setApproximateBasePrice("USD30.00");
    adultInfo.setTotalPrice("USD40.00");
    adultInfo.setApproximateTaxes("USD10.00");
    adultInfo.getTaxInfo().add(taxInfo);

    FareInfo childFare = new FareInfo();
    childFare.setPassengerTypeCode("CHD");
    AirPricingInfo childInfo = new AirPricingInfo();
    childInfo.getFareInfo().add(childFare);
    childInfo.setApproximateBasePrice("USD30.00");
    childInfo.setTotalPrice("USD40.00");
    childInfo.setApproximateTaxes("USD10.00");
    childInfo.getTaxInfo().add(taxInfo);
    
    pricingSolution.getAirPricingInfo().add(adultInfo);
    pricingSolution.getAirPricingInfo().add(childInfo);
    
    AirPriceResult airPriceResult = new AirPriceResult();
    airPriceResult.getAirPricingSolution().add(pricingSolution);
    
    priceResults.add(airPriceResult);
  }

  @Test
  public void testPriceObjectMapper() {
    Price price = priceObjectMapper.mapPrice(priceResults);
    assertNotNull(price);
    assertEquals("USD", price.getCurrencyCode());
    assertEquals("PriceDetail", price.getType());
    assertEquals(Double.valueOf(60.00), price.getBase());
    assertEquals(Double.valueOf(80.00), price.getTotalPrice());
    assertEquals(Double.valueOf(20.00), price.getTotalTaxes());
  }
  
  @Test
  public void priceMapperDetailsTest() {
    Price price = priceObjectMapper.mapPrice(priceResults);
    List<PriceBreakdown> priceBreakdowns = price.getPriceBreakdown();
    assertNotNull(price);
    assertNotNull(priceBreakdowns);
    assertEquals("USD", price.getCurrencyCode());
    assertEquals("PriceDetail", price.getType());
    assertEquals(Double.valueOf(80.00), price.getTotalPrice());
    assertEquals(Double.valueOf(20.00), price.getTotalTaxes());
    assertEquals(2, priceBreakdowns.size());

    assertEquals("ADT", priceBreakdowns.get(0).getRequestedPassengerType());
    assertEquals(Double.valueOf(30.00), priceBreakdowns.get(0).getAmount().getBase());
    assertEquals(Double.valueOf(40.00), priceBreakdowns.get(0).getAmount().getTotal());
    assertEquals(Double.valueOf(10.00),
        priceBreakdowns.get(0).getAmount().getTaxes().getTax().get(0).getValue());

    assertEquals("CHD", priceBreakdowns.get(1).getRequestedPassengerType());
    assertEquals(Double.valueOf(30.00), priceBreakdowns.get(1).getAmount().getBase());
    assertEquals(Double.valueOf(40.00), priceBreakdowns.get(1).getAmount().getTotal());
    assertEquals(Double.valueOf(10.00),
        priceBreakdowns.get(1).getAmount().getTaxes().getTax().get(0).getValue());
  }

}
