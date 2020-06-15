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
	
	
	@Before
	public void beforeEach() {
		timetableService = mock(TimetableService.class);
		service = new StandardIntineraryService(timetableService, Period.minutes(15));
	}
	
	@Test
	public void findTheNextProperStationShouldReturnListWithOneArrivalTime() {
		LocalTime startTime = LocalTime.now();
		LocalTime arrivalTime = startTime.plusMinutes(5);
		
		when(timetableService.findLinesThrough(any(String.class), any(String.class)))
				.thenReturn(Collections.singletonList(new Line.LineBuilder("Line").departingFrom("Station 0").withStations("Station 1")));
		when(timetableService.findArrivalTimes(any(Line.class), any(String.class)))
				.thenReturn(Collections.singletonList(arrivalTime));
		
		List<LocalTime> times = service.findNextDepartures("Station 0", "Station 1", startTime);
		assertThat(times, is(List.of(arrivalTime)));
	}
	
}
