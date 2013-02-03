package fr.emse.server;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface AdminBeanRemote {
	// boolean signIn(String password);
	//
	// void signOut();

	void addNote(Note note);

	Note getNote(SCoordinate coor);

	void updateNote(SCoordinate coor, Note note);

	void removeNote(SCoordinate coor);

	List<Note> getNotes();

	List<Itinerary> getItineraries();

	void addItinerary(Itinerary newItinerary);
}
