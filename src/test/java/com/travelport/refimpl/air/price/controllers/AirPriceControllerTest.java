package com.travelport.refimpl.air.price.controllers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelport.refimpl.air.price.models.OfferQueryBuildFromProducts;
import com.travelport.refimpl.air.price.models.OfferSummary;
import com.travelport.refimpl.air.price.models.Request;
import com.travelport.refimpl.air.price.models.Response;
import com.travelport.refimpl.air.price.services.AirPriceService;

@RunWith(SpringRunner.class)
@WebMvcTest(AirPriceController.class)
public class AirPriceControllerTest {

  @Autowired
  private MockMvc mvc;
  
  @MockBean
  AirPriceService mockAirPriceService;

  @Test
  public void testDefaultControllerResponse() throws Exception {
    // Setup Mock vars and behavior
    OfferSummary offerSummary = new OfferSummary("1","txn12345",null,null,null,null);
    Response mockResponse = new Response(offerSummary,null);
    Request request = new Request();
    Mockito.when(mockAirPriceService
        .getAirPrice(Mockito.any(OfferQueryBuildFromProducts.class), Mockito.anyBoolean()))
        .thenReturn(mockResponse);
    
    // Call MockMvc and Assert the response has the appropriate values
    mvc.perform(MockMvcRequestBuilders.post("/buildfromproducts").accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(request)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.OfferSummary.id")
            .value("1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.OfferSummary.offerRef")
            .value("txn12345"));    
  }
  
  public static String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final String jsonContent = mapper.writeValueAsString(obj);
      return jsonContent;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
