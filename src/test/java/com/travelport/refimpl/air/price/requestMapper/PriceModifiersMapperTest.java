package com.travelport.refimpl.air.price.requestMapper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.travelport.refimpl.air.price.models.FareSelection;
import com.travelport.refimpl.air.price.models.PricingModifiersAir;
import com.travelport.refimpl.air.price.models.ProductCriteriaAir;
import com.travelport.refimpl.air.price.models.SpecificFlightCriterium;
import com.travelport.schema.air_v45_0.AirPricingModifiers;
import com.travelport.schema.air_v45_0.TypeFaresIndicator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceModifiersMapperTest {
  
  @Autowired
  PriceModifiersMapper priceModifiersMapper;

  SpecificFlightCriterium flightCriteria = 
      new SpecificFlightCriterium("SpecificFlightCriteria","F9","420","2018-12-01"
          ,"17:31:00.000-07:00","2018-12-01","22:25:00.000-05:00","DEN","ATL","Economy","S",0,1);
  
  List<SpecificFlightCriterium> flightCriteriaList = 
      new ArrayList<SpecificFlightCriterium>(Arrays.asList(flightCriteria));
  
  ProductCriteriaAir productCriteriaAir = 
      new ProductCriteriaAir("ProductCriteriaAir",1,flightCriteriaList);
  
  List<ProductCriteriaAir> productCriteriaAirList = 
      new ArrayList<ProductCriteriaAir>(Arrays.asList(productCriteriaAir));
  
  FareSelection fareSelection = new FareSelection("FareSelectionDetail","PublicFaresOnly",null,null,true,true,true,true);
  
  PricingModifiersAir pricingModifiersAir = new PricingModifiersAir("PricingModifiersAir","USD",null,fareSelection);
  
  @Test
  public void testPriceModifiersMapper() {
    AirPricingModifiers airPricingModifiers = priceModifiersMapper.mapAirPricingModifiers(pricingModifiersAir, productCriteriaAirList);
    assertEquals(airPricingModifiers.getPlatingCarrier(),"F9");
    assertEquals(airPricingModifiers.getCurrencyType(),"USD");
    assertTrue(airPricingModifiers.isProhibitAdvancePurchaseFares());
    assertTrue(airPricingModifiers.isProhibitMaxStayFares());
    assertTrue(airPricingModifiers.isProhibitMinStayFares());
    assertTrue(airPricingModifiers.isProhibitNonRefundableFares());
    assertEquals(airPricingModifiers.getFaresIndicator(), TypeFaresIndicator.PUBLIC_FARES_ONLY);
  }

}
