package com.travelport.refimpl.air.price.responseMapper;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.travelport.refimpl.air.price.models.FlightSegment;
import com.travelport.schema.air_v45_0.AirSegmentRef;
import com.travelport.schema.air_v45_0.TypeBaseAirSegment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightSegmentsMapperTest {

  @Autowired
  FlightSegmentsMapper flightSegmentsMapper;
  
  List<TypeBaseAirSegment> segments = new ArrayList<TypeBaseAirSegment>();
  TypeBaseAirSegment seg1 = new TypeBaseAirSegment();
  TypeBaseAirSegment seg2 = new TypeBaseAirSegment();
  
  List<AirSegmentRef> segRefs = new ArrayList<AirSegmentRef>();
  AirSegmentRef segRef1 = new AirSegmentRef();
  AirSegmentRef segRef2 = new AirSegmentRef();
  
  @Before
  public void populateFlightSegmentsMapperTestArgs() {
    seg1.setKey("key1");
    seg1.setArrivalTime("2018-05-14T12:00:00.000+11:00");
    seg1.setDepartureTime("2018-05-14T11:00:00.000+11:00");
    seg1.setCarrier("F9");
    seg1.setEquipment("777");
    seg1.setFlightNumber("144");
    seg1.setDistance(BigInteger.valueOf(100));
    seg1.setFlightTime(BigInteger.valueOf(60));
    seg1.setOrigin("DEN");
    seg1.setDestination("DAL");

    seg2.setKey("key2");
    seg2.setArrivalTime("2018-05-14T14:00:00.000+11:00");
    seg2.setDepartureTime("2018-05-14T13:00:00.000+11:00");
    seg2.setCarrier("UA");
    seg2.setEquipment("777");
    seg2.setFlightNumber("164");
    seg2.setDistance(BigInteger.valueOf(200));
    seg2.setFlightTime(BigInteger.valueOf(120));
    seg2.setOrigin("DAL");
    seg2.setDestination("ATL");
    
    segments.add(seg1);
    segments.add(seg2);
    
    segRef1.setKey("key1");
    segRef2.setKey("key2");
    
    segRefs.add(segRef1);
    segRefs.add(segRef2);
  }
  
  @Test
  public void testFlightSegmentsMapper() {
    List<FlightSegment> flightSegments = flightSegmentsMapper.mapFlightSegments(segRefs, segments);
    assertEquals(flightSegments.size(),2);
    assertEquals(flightSegments.get(0).getSequence(),Integer.valueOf(1));
    assertEquals(flightSegments.get(0).getId(),"key1");
    assertEquals(flightSegments.get(0).getFlight().getDeparture().getDate(),"2018-05-14");
    assertEquals(flightSegments.get(0).getFlight().getDeparture().getLocation(),"DEN");
    assertEquals(flightSegments.get(0).getFlight().getDeparture().getTime(),"11:00:00.000+11:00");
    assertEquals(flightSegments.get(0).getFlight().getArrival().getDate(),"2018-05-14");
    assertEquals(flightSegments.get(0).getFlight().getArrival().getLocation(), "DAL");
    assertEquals(flightSegments.get(0).getFlight().getArrival().getTime(), "12:00:00.000+11:00");
    assertEquals(flightSegments.get(0).getFlight().getCarrier(),"F9");
    assertEquals(flightSegments.get(0).getFlight().getDistance(),Integer.valueOf(100));
    assertEquals(flightSegments.get(0).getFlight().getDuration(),"PT1H");
    
    assertEquals(flightSegments.get(1).getSequence(),Integer.valueOf(2));
    assertEquals(flightSegments.get(1).getId(),"key2");
    assertEquals(flightSegments.get(1).getFlight().getDeparture().getDate(),"2018-05-14");
    assertEquals(flightSegments.get(1).getFlight().getDeparture().getLocation(),"DAL");
    assertEquals(flightSegments.get(1).getFlight().getDeparture().getTime(),"13:00:00.000+11:00");
    assertEquals(flightSegments.get(1).getFlight().getArrival().getDate(),"2018-05-14");
    assertEquals(flightSegments.get(1).getFlight().getArrival().getLocation(), "ATL");
    assertEquals(flightSegments.get(1).getFlight().getArrival().getTime(), "14:00:00.000+11:00");
    assertEquals(flightSegments.get(1).getFlight().getCarrier(),"UA");
    assertEquals(flightSegments.get(1).getFlight().getDistance(),Integer.valueOf(200));
    assertEquals(flightSegments.get(1).getFlight().getDuration(),"PT2H");
  }

}
