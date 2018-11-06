package com.travelport.refimpl.air.price.responseMapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.travelport.refimpl.air.price.models.PassengerFlight;
import com.travelport.schema.air_v45_0.AirPricingInfo;
import com.travelport.schema.air_v45_0.BookingInfo;
import com.travelport.schema.air_v45_0.Brand;
import com.travelport.schema.air_v45_0.FareInfo;
import com.travelport.schema.air_v45_0.PassengerType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PassengerFlightMapperTest {
  
  @Autowired
  PassengerFlightMapper passengerFlightMapper;
  
  List<AirPricingInfo> airPricingInfoList = new ArrayList<AirPricingInfo>();
  FareInfo fareInfo = new FareInfo();
  
  @Before
  public void populatePassengerFlightMapperArgs() {
    Brand brand = new Brand();
    brand.setBrandID("ID");
    brand.setKey("key");
    brand.setName("brandName");
    brand.setBrandTier("1");
    BookingInfo bookingInfo1 = new BookingInfo();
    bookingInfo1.setFareInfoRef("ref_1");
    bookingInfo1.setCabinClass("Economy");
    bookingInfo1.setBookingCode("G");
    fareInfo.setFareBasis("FOO_CODE");
    fareInfo.setBrand(brand);
    fareInfo.setKey("ref_1");
    PassengerType adult = new PassengerType();
    adult.setCode("ADT");
    AirPricingInfo airPricingInfo = new AirPricingInfo();
    airPricingInfo.getPassengerType().add(adult);
    airPricingInfo.getBookingInfo().add(bookingInfo1);
    airPricingInfo.getFareInfo().add(fareInfo);
    airPricingInfoList.add(airPricingInfo);
    
  }

  @Test
  public void testPassengerFlightMapper() {
    List<PassengerFlight> passengerFlights = passengerFlightMapper.mapPassengerFlight(airPricingInfoList);
    assertEquals(passengerFlights.get(0).getPassengerQuantity(),Integer.valueOf(1));
    assertEquals(passengerFlights.get(0).getPassengerTypeCode(),"ADT");
    assertEquals(passengerFlights.get(0).getFlightProduct().get(0).getCabin(),"Economy");
    assertEquals(passengerFlights.get(0).getFlightProduct().get(0).getClassOfService(), "G");
    assertEquals(passengerFlights.get(0).getFlightProduct().get(0).getFareBasisCode(),"FOO_CODE");
    assertEquals(passengerFlights.get(0).getFlightProduct().get(0).getBrand().getName(),"brandName");
    assertEquals(passengerFlights.get(0).getFlightProduct().get(0).getBrand().getBrandRef(),"key");
    assertEquals(passengerFlights.get(0).getFlightProduct().get(0).getBrand().getId(),"ID");
    assertEquals(passengerFlights.get(0).getFlightProduct().get(0).getBrand().getTier(),Integer.valueOf(1));
  }

}
