package com.travelport.refimpl.air.price.responseMapper;

import java.math.BigInteger;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.travelport.refimpl.air.price.models.Arrival;
import com.travelport.refimpl.air.price.models.Departure;
import com.travelport.refimpl.air.price.models.Flight;
import com.travelport.refimpl.air.price.models.FlightSegment;
import com.travelport.refimpl.air.price.models.IntermediateStop;
import com.travelport.schema.air_v45_0.AirSegmentRef;
import com.travelport.schema.air_v45_0.FlightDetails;
import com.travelport.schema.air_v45_0.TypeBaseAirSegment;

@Component
public class FlightSegmentsMapper {
	public List<FlightSegment> mapFlightSegments(List<AirSegmentRef> segmentRefs, List<TypeBaseAirSegment> segments)
	{
		List<FlightSegment> flightSegments = new ArrayList<FlightSegment>();
		int i = 1;
		for(AirSegmentRef segmentRef:segmentRefs)
		{
			TypeBaseAirSegment segment = segments.stream()
					  .filter(seg -> segmentRef.getKey().equals(seg.getKey()))
					  .findAny()
					  .orElse(null);
			FlightSegment flightSegment = new FlightSegment();
			flightSegment.setType("FlightSegment");
			flightSegment.setSequence(i++);
			flightSegment.setId(segment.getKey());
			flightSegment.setFlight(mapFlight(segment));
			flightSegments.add(flightSegment);
		}
		return flightSegments;
	}
	
	private Flight mapFlight(TypeBaseAirSegment seg)
	{
		Flight flight = new Flight();
		flight.setId(seg.getKey());
		flight.setType("Flight");
		
		if(seg.getCodeshareInfo()!=null)
		{
			flight.setOperatingCarrier(seg.getCodeshareInfo().getOperatingCarrier());
			flight.setOperatingCarrierName(seg.getCodeshareInfo().getValue());
		}
		
		flight.setDeparture(mapDeparture(seg.getDepartureTime(), seg.getOrigin()));
		flight.setArrival(mapArrival(seg.getArrivalTime(),seg.getDestination()));
		flight.setCarrier(seg.getCarrier());
		flight.setEquipment(mapEquipment(seg.getEquipment()));
		flight.setNumber(seg.getFlightNumber());
		flight.setDistance(mapDistance(seg.getDistance()));
		flight.setDuration(mapDuration(seg.getFlightTime()));
		flight.setIntermediateStop(mapIntermediateStops(seg));
		return flight;
	}
	
	private Arrival mapArrival(String arrivalTime, String destination)
	{
		String arrivalTimeAndDate[] = arrivalTime.split("T");
		Arrival arrival = new Arrival();
		arrival.setType("Arrival");
		arrival.setTime(arrivalTimeAndDate[1]);
		arrival.setDate(arrivalTimeAndDate[0]);
		arrival.setLocation(destination);
		return arrival;
	}
	
	private Departure mapDeparture(String departureTime, String origin)
	{
		String timeAndDate[]= departureTime.split("T");
		Departure departure = new Departure();
		departure.setType("Departure");
		departure.setLocation(origin);
		departure.setTime(timeAndDate[1]);
		departure.setDate(timeAndDate[0]);
		return departure;
	}
	
	private Integer mapDistance(BigInteger distance)
	{
		Integer intDistance = null;
		if(distance != null)
			intDistance = distance.intValue();
		return intDistance;
	}
	
	private List<String> mapEquipment(String equipment)
	{
		List<String> equipList = null;
		if(equipment != null)
			equipList = Arrays.asList(equipment);
		return equipList;
	}
	
	private String mapDuration(BigInteger flightTime)
	{
		String durationStr = null;
		
		if(flightTime != null)
		{
			Duration duration = Duration.ofMinutes(flightTime.longValue());
			durationStr = duration.toString();
		}

		return durationStr;
	}
	
	private List<IntermediateStop> mapIntermediateStops(TypeBaseAirSegment seg)
	{
		List<IntermediateStop> intermediateStops = null;
		
		if(seg.getNumberOfStops()!=null && seg.getFlightDetails()!=null)
		{
			intermediateStops = new ArrayList<IntermediateStop>();
			for(FlightDetails flightDetail:seg.getFlightDetails())
			{
				IntermediateStop intermediateStop = new IntermediateStop();
				intermediateStop.setDuration(Duration.ofMinutes(flightDetail.getGroundTime().longValue()).toString());
				intermediateStop.setValue(flightDetail.getDestination());
				intermediateStops.add(intermediateStop);
			}
		}
		
		return intermediateStops;
	}
}
