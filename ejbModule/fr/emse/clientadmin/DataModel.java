package fr.emse.clientadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

import fr.emse.server.AdminBeanRemote;
import fr.emse.server.Itinerary;
import fr.emse.server.Note;
import fr.emse.server.SCoordinate;

public class DataModel {
	private AdminBeanRemote adminBeanRemote;

	@ManyToMany(cascade = CascadeType.ALL)
	Map<SCoordinate, Note> mapNotes;
	private List<Itinerary> itineraries;

	public DataModel() throws NamingException {
		InitialContext ctx;
		ctx = new InitialContext();
		System.out.println("Recherche du bean...");
		adminBeanRemote = (AdminBeanRemote) ctx.lookup("java:global/GPS-acjn/AdminEJB!fr.emse.server.AdminBeanRemote");

		mapNotes = new HashMap<SCoordinate, Note>();
		
		List<Note> notes = adminBeanRemote.getNotes();
		if (notes != null){
			for (Note note : notes) {
				mapNotes.put(note.getCoordinate(), note);
			}
		}

		itineraries = adminBeanRemote.getItineraries();
	}

	public void addNote(Note newNote) {
		mapNotes.put(newNote.getCoordinate(), newNote);
		adminBeanRemote.addNote(newNote);
	}

	public List<Note> getNotes() {
		return new ArrayList<Note>();
	}

	public Note getNote(SCoordinate coor){
		return adminBeanRemote.getNote(coor);
	}
	
	public void updateNote(SCoordinate coor, Note note) {
		mapNotes.put(note.getCoordinate(), note);
		mapNotes.remove(coor);
		adminBeanRemote.updateNote(coor, note);
		updateRelatedItinerary(coor, note);
	}
	
	public void removeNote(SCoordinate coor) {
		mapNotes.remove(coor);
		adminBeanRemote.removeNote(coor);
	}

	public Note getNearestNodeFrom(double latitude, double longitude) {
		double min = 1000;
		Note actualNote = null;
		for (SCoordinate coord : mapNotes.keySet()) {
			double dist = Math.abs(latitude-coord.getLat())+Math.abs(longitude-coord.getLon());
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
	
	public void updateRelatedItinerary(SCoordinate coor, Note newNote){
		for (Itinerary itinerary:itineraries){
			itinerary.updateNote(coor, newNote);
			System.out.println("Itinéraire "+itinerary.getTitle()+" modifié");
		}
	}
}
