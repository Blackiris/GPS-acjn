package fr.emse.server;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ClientBeanRemote {
	List<Itinerary> getItineraries();

	void usingItinerary(int id);
}
