package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.IntineraryService;
import edu.iis.mto.bdd.trains.services.StandardIntineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.hamcrest.MatcherAssert;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.jruby.embed.Extension;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WhenCalculatingArrivalTimes {
	
	private TimetableService timetableService;
	private IntineraryService service;
	private String firstStation;
	private String middleStation;
	private String lastStation;
	private String[] stations;
	private Line exampleLine;
	
	
	@Before
	public void beforeEach() {
		timetableService = mock(TimetableService.class);
		service = new StandardIntineraryService(timetableService, Period.minutes(15));
		
		firstStation = "Station 0";
		middleStation = "Station 1";
		lastStation = "Station 3";
		stations = new String[]{firstStation, middleStation, lastStation};
		
		
		exampleLine = new Line.LineBuilder("Line").departingFrom(firstStation).withStations(stations);
	}
	
	@Test
	public void findTheNextProperStationShouldReturnListWithOneArrivalTime() {
		LocalTime startTime = LocalTime.now();
		LocalTime arrivalTime = startTime.plusMinutes(5);
		
		prepareStandardTimetableService(Collections.singletonList(arrivalTime));
		
		List<LocalTime> times = service.findNextDepartures(firstStation, middleStation, startTime);
		assertThat(times, is(List.of(arrivalTime)));
	}
	
	
	@Test
	public void findLastProperStationShouldReturnListWithProperArrivalTime() {
		LocalTime startTime = LocalTime.now();
		LocalTime midlleArrivalTime = startTime.plusMinutes(5);
		LocalTime lastArrivalTime = midlleArrivalTime.plusMinutes(5);
		List<LocalTime> arrivalTimes = List.of(midlleArrivalTime, lastArrivalTime);
		
		prepareStandardTimetableService(arrivalTimes);
		
		List<LocalTime> times = service.findNextDepartures(firstStation, middleStation, startTime);
		assertThat(times, is(arrivalTimes));
	}
	
	private void prepareStandardTimetableService(List<LocalTime> localTimes) {
		when(timetableService.findLinesThrough(any(String.class), any(String.class)))
				.thenReturn(Collections.singletonList(exampleLine));
		when(timetableService.findArrivalTimes(any(Line.class), any(String.class)))
				.thenReturn(localTimes);
	}
}
