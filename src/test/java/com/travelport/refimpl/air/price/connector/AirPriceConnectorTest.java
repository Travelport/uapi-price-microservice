package com.travelport.refimpl.air.price.connector;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.travelport.service.air_v45_0.AirPricePortType;
import com.travelport.service.air_v45_0.AirService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirPriceConnectorTest {
  
  @Autowired
  AirPriceConnector airPriceConnector;

  @Test
  public void airPriceConnectorConstructorTest() {
    assertTrue(airPriceConnector.airPrice instanceof AirPricePortType);
    assertTrue(airPriceConnector.air instanceof AirService);
  }

}
