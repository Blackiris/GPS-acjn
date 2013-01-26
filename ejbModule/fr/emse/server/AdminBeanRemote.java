package fr.emse.server;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface AdminBeanRemote {
	boolean signIn(String password);
	void signOut();
	void addNote(Note note);
	List<Itinerary> getItineraries();
	void addItinerary(Itinerary newItinerary);
	String hello();
}
