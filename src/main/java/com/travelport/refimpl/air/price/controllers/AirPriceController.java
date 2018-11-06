package com.travelport.refimpl.air.price.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelport.refimpl.air.price.models.OfferQueryBuildFromProducts;
import com.travelport.refimpl.air.price.models.Request;
import com.travelport.refimpl.air.price.models.Response;
import com.travelport.refimpl.air.price.services.AirPriceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="AirPrice Microservice")
public class AirPriceController {
	@Autowired
	private AirPriceService AirPriceService;
	
	@Autowired
	AirPriceController(AirPriceService uAPIAirPriceService)
	{
		this.AirPriceService = uAPIAirPriceService;
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(AirPriceController.class);
	
	@ApiOperation(value = "Maps the model to an AirPriceReq and executes a UAPI request", response=Response.class)
	@RequestMapping(value = "/buildfromproducts", method = RequestMethod.POST, produces = "application/json")
	public Response index(@RequestBody Request request,
			@RequestParam(value = "priceAsBooked", required = false) Boolean priceAsBooked) {

		LOG.debug("Enter price controller, map to /buildfromproducts");
		OfferQueryBuildFromProducts offerQueryBuildFromProducts = request.getOfferQueryBuildFromProducts();
		return AirPriceService.getAirPrice(offerQueryBuildFromProducts, priceAsBooked);
	}
}