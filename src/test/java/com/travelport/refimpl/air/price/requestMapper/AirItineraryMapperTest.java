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

import com.travelport.refimpl.air.price.models.ProductCriteriaAir;
import com.travelport.refimpl.air.price.models.SpecificFlightCriterium;
import com.travelport.schema.air_v45_0.AirItinerary;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirItineraryMapperTest {

  @Autowired
  AirItineraryMapper airItineraryMapper;
  
  SpecificFlightCriterium flightCriteria = 
      new SpecificFlightCriterium("SpecificFlightCriteria","F9","420","2018-12-01"
          ,"17:31:00.000-07:00","2018-12-01","22:25:00.000-05:00","DEN","ATL","Economy","S",0,1);
  
  List<SpecificFlightCriterium> flightCriteriaList = 
      new ArrayList<SpecificFlightCriterium>(Arrays.asList(flightCriteria));
  
  ProductCriteriaAir productCriteriaAir = 
      new ProductCriteriaAir("ProductCriteriaAir",1,flightCriteriaList);
  
  List<ProductCriteriaAir> productCriteriaAirList = 
      new ArrayList<ProductCriteriaAir>(Arrays.asList(productCriteriaAir));
  
  @Test
  public void testAirItineraryMapper() {
    AirItinerary itinerary = airItineraryMapper.mapAirItinerary(productCriteriaAirList);
    assertEquals(itinerary.getAirSegment().get(0).getCarrier(),"F9");
    assertEquals(itinerary.getAirSegment().get(0).getFlightNumber(),"420");
    assertEquals(itinerary.getAirSegment().get(0).getDepartureTime(),"2018-12-01T17:31:00.000-07:00");
    assertEquals(itinerary.getAirSegment().get(0).getArrivalTime(),"2018-12-01T22:25:00.000-05:00");
    assertEquals(itinerary.getAirSegment().get(0).getOrigin(),"DEN");
    assertEquals(itinerary.getAirSegment().get(0).getDestination(),"ATL");
    assertEquals(itinerary.getAirSegment().get(0).getKey(),"1");
  }

}
