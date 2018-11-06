package com.travelport.refimpl.air.price.connector;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.travelport.schema.air_v45_0.AirPriceReq;
import com.travelport.schema.air_v45_0.AirPriceRsp;
import com.travelport.service.air_v45_0.AirFaultMessage;
import com.travelport.service.air_v45_0.AirPricePortType;
import com.travelport.service.air_v45_0.AirService;

@Component
public class AirPriceConnector {
	
	private static final Logger LOG = LoggerFactory.getLogger(AirPriceConnector.class);
	
	@Value("${air.username}")
	private String username;
	
	@Value("${air.password}")
	private String password;
	
	@Value("${air.endpoint}")
	private String endpoint;
	
	@Value("${air.branch}")
	private String branch;
	
	public AirPricePortType airPrice;
	public AirService air;
	
	@Autowired
	public AirPriceConnector() {
		this.air = new com.travelport.service.air_v45_0.AirService();
        this.airPrice = air.getAirPricePort();
	}
	
	public AirPriceRsp callAirPrice(AirPriceReq airPriceMicro) throws AirFaultMessage
	{
		LOG.debug("Username: " + username);
		LOG.debug("Endpoint: " + endpoint);
		LOG.debug("Branch: " + branch);
		addParametersToProvider((BindingProvider)airPrice);
		AirPriceRsp airPriceResponse = new AirPriceRsp();
		try {
			airPriceResponse = airPrice.service(airPriceMicro);
		}
		catch (AirFaultMessage e) {
			e.printStackTrace();
			throw e;
		}
		LOG.info("Request complete");
		return airPriceResponse;
	}
	
	protected void addParametersToProvider(BindingProvider provider) {
        provider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, 
                endpoint);        
        provider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,
                username);
        provider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,
                password);       
        provider.getRequestContext().put("schema-validation-enabled", "false");       
    }
}