package fr.emse.clientadmin;

import java.util.ArrayList;
import java.util.HashMap;
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
		
		mapNotes = new HashMap<Coordinate, Note>();
		
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
		System.out.println(mapNotes.size());
		
		return new ArrayList<Note>();
	}
	
	public Note getNearestNodeFrom(double latitude, double longitude) {
		double min = 1000;
		Note actualNote = null;
		for (Coordinate coord : mapNotes.keySet()) {
			double dist = Math.abs(latitude-coord.getLatitude())+Math.abs(longitude-coord.getLongitude());
			if (dist < min) {
				min = dist;
				actualNote = mapNotes.get(coord);
			}
		}
		
		return actualNote;
	}
	
	public void addItinerary(Itinerary newItinerary) {
		itineraries.add(newItinerary);
		adminBeanRemote.addItinerary(newItinerary);
	}
	
	public List<Itinerary> getItineraries() {
		return itineraries;
	}
}