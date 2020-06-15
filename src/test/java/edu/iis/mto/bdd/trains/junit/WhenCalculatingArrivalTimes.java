package edu.iis.mto.bdd.trains.junit;

import edu.iis.mto.bdd.trains.services.IntineraryService;
import edu.iis.mto.bdd.trains.services.StandardIntineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.hamcrest.MatcherAssert;
import org.joda.time.LocalTime;
import org.jruby.embed.Extension;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WhenCalculatingArrivalTimes {
	
	@Mock
	private TimetableService timetableService;
	private IntineraryService service;
	
	
	@Before
	public void beforeEach() {
		service = new StandardIntineraryService(timetableService);
	}
	
	@Test
	public void findTheNextProperStationShouldReturnListWithOneArrivalTime() {
		List<LocalTime> times = service.findNextDepartures("Station 0", "Station 1", LocalTime.parse("12:00"));
		assertThat(times, is(List.of(LocalTime.parse("12:05"))));
	}
	
}
