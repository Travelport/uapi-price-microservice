package com.travelport.refimpl.air.price.responseMapper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.travelport.refimpl.air.price.models.Price;
import com.travelport.refimpl.air.price.models.Product;
import com.travelport.refimpl.air.price.models.Response;
import com.travelport.refimpl.air.price.models.TermsAndConditionsFull;
import com.travelport.schema.air_v45_0.AirItinerary;
import com.travelport.schema.air_v45_0.AirPriceResult;
import com.travelport.schema.air_v45_0.AirPriceRsp;
import com.travelport.schema.air_v45_0.AirPricingInfo;
import com.travelport.schema.air_v45_0.AirPricingSolution;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirPriceResponseMapperTest {
  
  @Autowired
  AirPriceResponseMapper airPriceResponseMapper;
  
  @MockBean
  PriceObjectMapper priceMapper;
  
  @MockBean
  TermsAndConditionsObjectMapper tcMapper;
  
  @MockBean
  ProductObjectMapper productMapper;
  
  AirPriceRsp airPriceRsp = new AirPriceRsp();

  @Test
  public void testAirPriceResponseMapper() {
    airPriceRsp.setTransactionId("ID");
    airPriceRsp.getAirPriceResult().add(new AirPriceResult());
    airPriceRsp.setAirItinerary(new AirItinerary());
    
    AirPricingSolution airPricingSolution = new AirPricingSolution();
    airPricingSolution.getAirPricingInfo().add(new AirPricingInfo());
    airPriceRsp.getAirPriceResult().get(0).getAirPricingSolution().add(airPricingSolution);
    
    when(priceMapper.mapPrice(airPriceRsp.getAirPriceResult())).thenReturn(new Price());
    when(productMapper.mapProducts(airPriceRsp.getAirItinerary(), airPriceRsp.getAirPriceResult()))
        .thenReturn(new ArrayList<Product>());
    when(tcMapper.mapTermsAndConditions(airPricingSolution.getAirPricingInfo().get(0)))
        .thenReturn(new ArrayList<TermsAndConditionsFull>());
    
    Response response = airPriceResponseMapper.getResponse(airPriceRsp);
    assertNotNull(response);
    assertEquals(response.getOfferSummary().getId(),"ID");
  }

}
