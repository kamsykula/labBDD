package edu.iis.mto.bdd.trains.cucumber.steps;

import java.util.List;

import edu.iis.mto.bdd.trains.model.Line;
import edu.iis.mto.bdd.trains.services.InMemoryTimetableService;
import edu.iis.mto.bdd.trains.services.TimetableService;
import org.hamcrest.Matchers;
import org.joda.time.LocalTime;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import org.junit.Assert;

import static org.hamcrest.MatcherAssert.assertThat;

public class OptimalItinerarySteps {
	
	private String destination;
	private Line line;
	
	@Zakładając("^pociągi linii \"(.*)\" z \"(.*)\" odjeżdżają ze stacji \"(.*)\" do \"(.*)\" o$")
	public void givenArrivingTrains(String line, String lineStart, String departure, String destination,
	                                @Transform(JodaLocalTimeConverter.class) List<LocalTime> departureTimes) {
		throw new PendingException();
		
	}
	
	@Gdy("^chcę podróżować z \"([^\"]*)\" do \"([^\"]*)\" o (.*)$")
	public void whenIWantToTravel(String departure, String destination,
	                              @Transform(JodaLocalTimeConverter.class) LocalTime startTime) {
		throw new PendingException();
	}
	
	@Wtedy("^powinienem uzyskać informację o pociągach o:$")
	public void shouldBeInformedAbout(@Transform(JodaLocalTimeConverter.class) List<LocalTime> expectedTrainTimes) {
		TimetableService timetableService = new InMemoryTimetableService();
		List<LocalTime> rV = timetableService.findArrivalTimes(line, destination);
        
        assertThat(rV, Matchers.is(expectedTrainTimes));
	}
}
