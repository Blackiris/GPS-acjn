package fr.emse.beans;

import java.util.List;

import javax.ejb.Remote;

import fr.emse.objects.Itinerary;

@Remote
public interface ClientBeanRemote {
	List<Itinerary> getItineraries();
	void usingItinerary(int id);
}
