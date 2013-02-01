package fr.emse.clientadmin;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.emse.server.AdminBeanRemote;
import fr.emse.server.Itinerary;
import fr.emse.server.Note;

public class DataModel {
	private AdminBeanRemote adminBeanRemote;
	
	private List<Note> notes;
	private List<Itinerary> itineraries;
	
	public DataModel() throws NamingException {
		InitialContext ctx;
		ctx = new InitialContext();
		System.out.println("Recherche du bean...");
		adminBeanRemote = (AdminBeanRemote) ctx.lookup("java:global/GPS-acjn/AdminEJB!fr.emse.server.AdminBeanRemote");
		
		notes = adminBeanRemote.getNotes();
		itineraries = adminBeanRemote.getItineraries();
	}

	public void addNote(Note newNote) {
		notes.add(newNote);
		adminBeanRemote.addNote(newNote);
	}
	
	public List<Note> getNotes() {
		return notes;
	}
	
	public void addItinerary(Itinerary newItinerary) {
		itineraries.add(newItinerary);
		adminBeanRemote.addItinerary(newItinerary);
	}
	
	public List<Itinerary> getItineraries() {
		return itineraries;
	}
}
