package com.travelport.refimpl.air.price.requestMapper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.travelport.refimpl.air.price.models.BuildFromProductsRequest;
import com.travelport.refimpl.air.price.models.OfferQueryBuildFromProducts;
import com.travelport.refimpl.air.price.models.PassengerCriterium;
import com.travelport.refimpl.air.price.models.PricingModifiersAir;
import com.travelport.refimpl.air.price.models.ProductCriteriaAir;
import com.travelport.schema.air_v45_0.AirItinerary;
import com.travelport.schema.air_v45_0.AirPriceReq;
import com.travelport.schema.air_v45_0.AirPricingModifiers;
import com.travelport.schema.common_v45_0.SearchPassenger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirPriceRequestMapperTest {
  
  @MockBean
  PriceModifiersMapper priceModMapper;
  
  @MockBean
  SearchPassengerMapper passengerMapper;
  
  @MockBean
  AirItineraryMapper itineraryMapper;
  
  @Autowired
  AirPriceRequestMapper priceRequestMapper;
  
  PricingModifiersAir pricingModifiersAir = new PricingModifiersAir();
  List<PassengerCriterium> passengerCriteria = new ArrayList<PassengerCriterium>();
  List<ProductCriteriaAir> productCriteriaAir = new ArrayList<ProductCriteriaAir>();
  BuildFromProductsRequest buildFromProductsRequest = 
      new BuildFromProductsRequest("BuildFromProductsRequestAir",pricingModifiersAir,passengerCriteria,productCriteriaAir);
  OfferQueryBuildFromProducts offerQueryBuildFromProducts = 
      new OfferQueryBuildFromProducts(null,null,null,buildFromProductsRequest);
  
  @Test
  public void testAirPriceRequestMapper() {
   when(priceModMapper.mapAirPricingModifiers(pricingModifiersAir, productCriteriaAir))
       .thenReturn(new AirPricingModifiers());
   when(passengerMapper.mapSearchPassengers(passengerCriteria))
       .thenReturn(new ArrayList<SearchPassenger>());
   when(itineraryMapper.mapAirItinerary(productCriteriaAir))
       .thenReturn(new AirItinerary());
   
   AirPriceReq priceReq = priceRequestMapper.getAirPrice(offerQueryBuildFromProducts, true);
   
   assertEquals(priceReq.getAuthorizedBy(), "user");
   assertNotNull(priceReq.getAirItinerary());
   assertNotNull(priceReq.getAirPricingCommand());
   assertNotNull(priceReq.getAirPricingModifiers());
   assertNotNull(priceReq.getSearchPassenger());
   assertNotNull(priceReq.getTargetBranch());
   assertNotNull(priceReq.getBillingPointOfSaleInfo().getOriginApplication());
  }

}
