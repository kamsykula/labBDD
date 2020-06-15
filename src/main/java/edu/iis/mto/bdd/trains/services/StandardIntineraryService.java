package edu.iis.mto.bdd.trains.services;

import org.joda.time.LocalTime;

import java.util.List;

public class StandardIntineraryService implements IntineraryService{
	
	@Override
	public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {
		return null;
	}
}
