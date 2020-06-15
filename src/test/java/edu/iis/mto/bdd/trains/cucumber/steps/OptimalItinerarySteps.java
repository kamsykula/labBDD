package edu.iis.mto.bdd.trains.cucumber.steps;

import java.util.List;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.InMemoryTimetableService;
import edu.iis.mto.bdd.trains.services.IntineraryService;
import edu.iis.mto.bdd.trains.services.StandardIntineraryService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.hamcrest.Matchers;
import org.joda.time.LocalTime;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import org.joda.time.Period;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class OptimalItinerarySteps {
	
    private List<LocalTime> timesOfArrival;
    private IntineraryService service;
	
	@Zakładając("^pociągi linii \"(.*)\" z \"(.*)\" odjeżdżają ze stacji \"(.*)\" do \"(.*)\" o$")
	public void givenArrivingTrains(String line, String lineStart, String departure, String destination,
	                                @Transform(JodaLocalTimeConverter.class) List<LocalTime> departureTimes) {
		service = new StandardIntineraryService(new InMemoryTimetableService(), Period.minutes(15));
		
	}
	
	@Gdy("^chcę podróżować z \"([^\"]*)\" do \"([^\"]*)\" o (.*)$")
	public void whenIWantToTravel(String departure, String destination,
	                              @Transform(JodaLocalTimeConverter.class) LocalTime startTime) {
		this.timesOfArrival = service.findNextDepartures(departure, destination, startTime);
	}
	
	@Wtedy("^powinienem uzyskać informację o pociągach o:$")
	public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes) {
        assertThat(timesOfArrival, Matchers.is(expectedTrainTimes));
	}
}
