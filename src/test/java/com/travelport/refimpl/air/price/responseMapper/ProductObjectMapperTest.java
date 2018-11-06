package com.travelport.refimpl.air.price.responseMapper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.travelport.refimpl.air.price.models.FlightSegment;
import com.travelport.refimpl.air.price.models.PassengerFlight;
import com.travelport.refimpl.air.price.models.Product;
import com.travelport.schema.air_v45_0.AirItinerary;
import com.travelport.schema.air_v45_0.AirPriceResult;
import com.travelport.schema.air_v45_0.AirPricingInfo;
import com.travelport.schema.air_v45_0.AirPricingSolution;
import com.travelport.schema.air_v45_0.AirSegmentRef;
import com.travelport.schema.air_v45_0.TypeBaseAirSegment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductObjectMapperTest {
  
  @Autowired
  ProductObjectMapper productObjectMapper;
  
  @MockBean
  FlightSegmentsMapper flightSegmentsMapper;
  
  @MockBean
  PassengerFlightMapper passengerFlightMapper;

  List<TypeBaseAirSegment> segments = new ArrayList<TypeBaseAirSegment>();
  AirItinerary itinerary = new AirItinerary();
  List<AirPriceResult> priceResults = new ArrayList<AirPriceResult>();
  
  @Before
  public void populateProductObjectMapperTestArgs() {
    itinerary.getAirSegment().add(new TypeBaseAirSegment());
    priceResults.add(new AirPriceResult());
    priceResults.get(0).getAirPricingSolution().add(new AirPricingSolution());
    priceResults.get(0).getAirPricingSolution().get(0).getAirSegmentRef().add(new AirSegmentRef());
    priceResults.get(0).getAirPricingSolution().get(0).getAirPricingInfo().add(new AirPricingInfo());
  }
  
  @Test
  public void testProductObjectMapper() {
    List<AirSegmentRef> segRefs = priceResults.get(0).getAirPricingSolution().get(0).getAirSegmentRef();
    List<AirPricingInfo> airPricingInfoList = priceResults.get(0).getAirPricingSolution().get(0).getAirPricingInfo();
    when(flightSegmentsMapper.mapFlightSegments(segRefs, itinerary.getAirSegment()))
        .thenReturn(new ArrayList<FlightSegment>());
    when(passengerFlightMapper.mapPassengerFlight(airPricingInfoList))
        .thenReturn(new ArrayList<PassengerFlight>());
    
    List<Product> products = productObjectMapper.mapProducts(itinerary, priceResults);
    
    assertEquals(products.size(),1);
    assertNotNull(products.get(0).getPassengerFlight());
    assertNotNull(products.get(0).getFlightSegment());
  }

}
