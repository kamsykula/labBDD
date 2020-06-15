package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.model.Line;
import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.Period;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StandardIntineraryService implements IntineraryService {
	private final Period timeWindow;
	private final TimetableService timetableService;
	
	public StandardIntineraryService(TimetableService timetableService, Period timeWindow) {
		this.timetableService = timetableService;
		this.timeWindow = timeWindow;
	}
	
	@Override
	public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {
		List<LocalTime> rV = new LinkedList<>();
		
		for (Line line : timetableService.findLinesThrough(departure, destination)) {
			rV.addAll(
					timetableService.findArrivalTimes(line, departure)
							.stream()
//							.filter(time -> startTime.plus(timeWindow).isAfter(time))
							.filter(time -> startTime.plusMinutes(15).compareTo(time) >= 0 && startTime.compareTo(time) < 0)
							.collect(Collectors.toList()));
		}
		
		return rV;
	}
	
}
