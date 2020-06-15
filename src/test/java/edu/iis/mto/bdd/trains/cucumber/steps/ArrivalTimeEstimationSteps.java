package edu.iis.mto.bdd.trains.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.pl.Gdy;
import cucumber.api.java.pl.I;
import cucumber.api.java.pl.Wtedy;
import cucumber.api.java.pl.Zakładając;
import org.joda.time.LocalTime;

public class ArrivalTimeEstimationSteps {
	@Zakładając("^chcę się dostać z (.*) do (.*)")
	public void givenStartAndDestination(String lineStart, String destination) {
		throw new PendingException();
	}
	
	@I("^następny pociąg odjeżdża o (.*) na linii (.*)")
	public void givenTimeAndLine(@Transform(JodaLocalTimeConverter.class) LocalTime startTime,
	                                  String line) {
		throw new PendingException();
	}
	
	@Gdy("^zapytam o godzinę przyjazdu")
	public void whenIAskForArrivalTime() {
		throw new PendingException();
	}
	
	@Wtedy("^powinienem uzyskać następujący szacowany czas przyjazdu: (.*)")
	public void thanShouldBeInformed(@Transform(JodaLocalTimeConverter.class) LocalTime startTime) {
		throw new PendingException();
	}
}