package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.IntineraryService;
import edu.iis.mto.bdd.trains.services.StandardIntineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
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
	private LocalTime startTime;
	
	
	@Before
	public void beforeEach() {
		timetableService = mock(TimetableService.class);
		firstStation = "Station 0";
		middleStation = "Station 1";
		lastStation = "Station 3";
		stations = new String[]{firstStation, middleStation, lastStation};
		
		
		exampleLine = new Line.LineBuilder("Line").departingFrom(firstStation).withStations(stations);
		startTime = LocalTime.now();
		
		service = new StandardIntineraryService(timetableService, Period.minutes(5));
	}
	
	@Test
	public void findTheNextProperStationShouldReturnListWithAtLeastOneArrivalTime() {
		LocalTime arrivalTime = startTime.plusMinutes(4);
		
		prepareStandardTimetableService(Collections.singletonList(arrivalTime));
		
		List<LocalTime> times = service.findNextDepartures(firstStation, middleStation, startTime);
		assertThat(times, is(List.of(arrivalTime)));
	}
	
	
	@Test
	public void findDepartureToLastProperStationShouldReturnListWithProperArrivalTime() {
		LocalTime midlleArrivalTime = startTime.plusMinutes(10);
		LocalTime lastArrivalTime = midlleArrivalTime.plusMinutes(20);
		List<LocalTime> arrivalTimes = List.of(midlleArrivalTime, lastArrivalTime);
		
		prepareStandardTimetableService(arrivalTimes);
		
		LocalTime afterFirstDeparture = this.startTime.plusMinutes(20);
		List<LocalTime> times = service.findNextDepartures(firstStation, lastStation, afterFirstDeparture);
		assertThat(times, is(List.of(lastArrivalTime)));
	}
	
	@Test
	public void whenLineWithGivenDestinationNotExistShouldReturnEmptyList() {
		prepareStandardTimetableService(Collections.EMPTY_LIST);
		
		List<LocalTime> times = service.findNextDepartures(firstStation, middleStation, startTime);
		
		assertThat(times, hasSize(0));
	}
	
	private void prepareStandardTimetableService(List<LocalTime> localTimes) {
		when(timetableService.findLinesThrough(any(String.class), any(String.class)))
				.thenReturn(Collections.singletonList(exampleLine));
		when(timetableService.findArrivalTimes(any(Line.class), any(String.class)))
				.thenReturn(localTimes);
	}
}
