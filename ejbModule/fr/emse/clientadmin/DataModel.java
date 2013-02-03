package fr.emse.clientadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

import fr.emse.server.Itinerary;
import fr.emse.server.Note;
import fr.emse.server.SCoordinate;

public class DataModel {

	@ManyToMany(cascade = CascadeType.ALL)
	Map<SCoordinate, Note> mapNotes;
	private List<Itinerary> itineraries;

	public DataModel() throws NamingException {

		mapNotes = new HashMap<SCoordinate, Note>();

		List<Note> notes = ClientAdmin.adminBeanRemote.getNotes();
		if (notes != null) {
			for (Note note : notes) {
				mapNotes.put(note.getCoordinate(), note);
			}
		}

		itineraries = ClientAdmin.adminBeanRemote.getItineraries();
	}

	public void addNote(Note newNote) {
		mapNotes.put(newNote.getCoordinate(), newNote);
		ClientAdmin.adminBeanRemote.addNote(newNote);
	}

	public List<Note> getNotes() {
		List<Note> res = new ArrayList<Note>();

		for (SCoordinate sCoordinate : mapNotes.keySet()) {
			res.add(mapNotes.get(sCoordinate));
		}

		return res;
	}

	public Note getNote(SCoordinate coor) {
		return ClientAdmin.adminBeanRemote.getNote(coor);
	}

	public void updateNote(SCoordinate coor, Note note) {
		ClientAdmin.adminBeanRemote.updateNote(coor, note);
	}

	public void removeNote(SCoordinate coor) {
		ClientAdmin.adminBeanRemote.removeNote(coor);
	}

	public Note getNearestNodeFrom(double latitude, double longitude) {
		double min = 1000;
		Note actualNote = null;
		for (SCoordinate coord : mapNotes.keySet()) {
			double dist = Math.abs(latitude - coord.getLat())
					+ Math.abs(longitude - coord.getLon());
			if (dist < min) {
				min = dist;
				actualNote = mapNotes.get(coord);
			}
		}

		return actualNote;
	}

	public void addItinerary(Itinerary newItinerary) {
		itineraries.add(newItinerary);
		ClientAdmin.adminBeanRemote.addItinerary(newItinerary);
	}

	public List<Itinerary> getItineraries() {
		return itineraries;
	}
}
