package com.travelport.refimpl.air.price.responseMapper;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.travelport.refimpl.air.price.models.TermsAndConditionsFull;
import com.travelport.schema.air_v45_0.AirPricingInfo;
import com.travelport.schema.air_v45_0.BaggageAllowance;
import com.travelport.schema.air_v45_0.BookingInfo;
import com.travelport.schema.air_v45_0.FareInfo;
import com.travelport.schema.air_v45_0.FlightOption;
import com.travelport.schema.air_v45_0.FlightOptionsList;
import com.travelport.schema.air_v45_0.Option;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TermsAndConditionsObjectMapperTest {

  @Autowired
  TermsAndConditionsObjectMapper tcMapper;
  
  AirPricingInfo airPricingInfo = new AirPricingInfo();
  FareInfo fareInfo1 = new FareInfo();
  FareInfo fareInfo2 = new FareInfo();
  
  @Before
  public void populateTermsAndConditionsArgs() {
    BookingInfo bookingInfo1 = new BookingInfo();
    bookingInfo1.setFareInfoRef("ref_1");
    BookingInfo bookingInfo2 = new BookingInfo();
    bookingInfo2.setFareInfoRef("ref_2");

    Option option = new Option();
    option.getBookingInfo().add(bookingInfo1);
    option.getBookingInfo().add(bookingInfo2);

    FlightOption flightOption = new FlightOption();
    flightOption.getOption().add(option);

    FlightOptionsList flightOptionsList = new FlightOptionsList();
    flightOptionsList.getFlightOption().add(flightOption);

    airPricingInfo.setFlightOptionsList(flightOptionsList);
    
    BaggageAllowance bag1 = new BaggageAllowance();
    BaggageAllowance bag2 = new BaggageAllowance();
    bag1.setNumberOfPieces(BigInteger.valueOf(2));
    fareInfo1.setBaggageAllowance(bag1);
    fareInfo1.setKey("ref_1");
    bag2.setNumberOfPieces(BigInteger.valueOf(4));
    fareInfo2.setBaggageAllowance(bag2);
    fareInfo2.setKey("ref_2");
    airPricingInfo.getFareInfo().add(fareInfo1);
    airPricingInfo.getFareInfo().add(fareInfo2);
  }
  
  @Test
  public void testTermsAndConditionsObjectMapper() {
    airPricingInfo.setPlatingCarrier("UA");
    List<TermsAndConditionsFull> termsAndConditions = tcMapper.mapTermsAndConditions(airPricingInfo);
    assertNotNull(termsAndConditions);
    assertEquals("UA", termsAndConditions.get(0).getValidatingCarrier());
    assertNull(termsAndConditions.get(0).getExpiryDate());
  }
  
  @Test
  public void termsAndConditionsObjectMapperExpiryDateTest() {
    airPricingInfo.setLatestTicketingTime("2018-10-04");
    List<TermsAndConditionsFull> termsAndConditions = tcMapper.mapTermsAndConditions(airPricingInfo);
    assertNotNull(termsAndConditions);
    assertEquals("2018-10-04", termsAndConditions.get(0).getExpiryDate());
    assertNull(termsAndConditions.get(0).getValidatingCarrier());
  }

  @Test
  public void termsAndConditionsObjectMapperBaggageQuantityTest() {
    List<TermsAndConditionsFull> termsAndConditions = tcMapper.mapTermsAndConditions(airPricingInfo);
    assertNotNull(termsAndConditions);
    assertNull(termsAndConditions.get(0).getExpiryDate());
    assertNull(termsAndConditions.get(0).getValidatingCarrier());
    assertNotNull(termsAndConditions.get(0).getBaggageAllowance());
    assertEquals(2, termsAndConditions.get(0).getBaggageAllowance().get(0).getBaggageItem().get(0)
        .getQuantity().intValue());
  }

}
