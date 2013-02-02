package fr.emse.server;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface AdminBeanRemote {
	boolean signIn(String password);
	void signOut();
	void addNote(Note note);
	Note getNote(int id);
	void updateNote(int id, Note note);
	List<Note> getNotes();
	List<Itinerary> getItineraries();
	void addItinerary(Itinerary newItinerary);
	String hello();
}
