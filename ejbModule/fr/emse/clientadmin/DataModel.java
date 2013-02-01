package fr.emse.clientadmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.emse.server.AdminBeanRemote;
import fr.emse.server.Coordinate;
import fr.emse.server.Itinerary;
import fr.emse.server.Note;

public class DataModel {
	private AdminBeanRemote adminBeanRemote;
	
	Map<Coordinate, Note> mapNotes;
	private List<Itinerary> itineraries;
	
	public DataModel() throws NamingException {
		InitialContext ctx;
		ctx = new InitialContext();
		System.out.println("Recherche du bean...");
		adminBeanRemote = (AdminBeanRemote) ctx.lookup("java:global/GPS-acjn/AdminEJB!fr.emse.server.AdminBeanRemote");
		
		List<Note> notes = adminBeanRemote.getNotes();
		for (Note note : notes) {
			mapNotes.put(note.getCoordinate(), note);
		}
		
		itineraries = adminBeanRemote.getItineraries();
	}

	public void addNote(Note newNote) {
		mapNotes.put(newNote.getCoordinate(), newNote);
		adminBeanRemote.addNote(newNote);
	}
	
	public List<Note> getNotes() {
		return new ArrayList<Note>(mapNotes.values());
	}
	
	public Note getNodeAt(double latitude, double longitude) {
		return mapNotes.get(new Coordinate(latitude, longitude));
	}
	
	public void addItinerary(Itinerary newItinerary) {
		itineraries.add(newItinerary);
		adminBeanRemote.addItinerary(newItinerary);
	}
	
	public List<Itinerary> getItineraries() {
		return itineraries;
	}
}
