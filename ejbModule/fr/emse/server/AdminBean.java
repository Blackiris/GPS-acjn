package fr.emse.server;

import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class AdminBean
 */
@Stateless
@LocalBean
@WebService(serviceName = "AdminService")
public class AdminBean implements AdminBeanRemote {

	@PersistenceContext(unitName = "admin-unit")
	EntityManager em;

	// boolean isLogged = false;

	/**
	 * Default constructor.
	 */
	public AdminBean() {
	}

	// @Override
	// @WebMethod(operationName = "signIn")
	// public boolean signIn(@WebParam(name = "password") String password) {
	// if (password.equals("monbean")) {
	// isLogged = true;
	// }
	// return isLogged;
	// }
	//
	// @Override
	// public void signOut() {
	// isLogged = false;
	// }

	@Override
	@WebMethod(operationName = "addNote")
	public void addNote(@WebParam(name = "note") Note note) {
		em.persist(note);
	}

	@Override
	@WebMethod(operationName = "getNote")
	public Note getNote(@WebParam(name = "coordinate") SCoordinate coor) {
		List<Note> noteList = getNotes();
		Iterator<Note> iter = noteList.listIterator();
		Note note = null;
		while (iter.hasNext()) {
			note = iter.next();
			if (note.getCoordinate().getLat() == coor.getLat()
					&& note.getCoordinate().getLon() == coor.getLon()) {
				break;
			} else {
				note = null;
			}

		}

		return note;
	}

	@SuppressWarnings("unchecked")
	@Override
	@WebMethod(operationName = "getNotes")
	public List<Note> getNotes() {
		Query query = em.createQuery("SELECT m from Note as m");
		return (List<Note>) query.getResultList();
	}

	@Override
	@WebMethod(operationName = "updateNote")
	public void updateNote(@WebParam(name = "coordinate") SCoordinate coor,
			@WebParam(name = "note") Note note) {
		Note previousNote = getNote(coor);
		Note newNote = em.merge(previousNote);
		newNote.setCategory(note.getCategory());
		newNote.setComments(note.getComments());
		newNote.setCoordinate(note.getCoordinate());
		newNote.setHeight(note.getHeight());
	}

	@Override
	@WebMethod(operationName = "removeNote")
	public void removeNote(@WebParam(name = "coordinate") SCoordinate coor) {
		Note note = getNote(coor);
		em.remove(note);
	}

	@SuppressWarnings("unchecked")
	@Override
	@WebMethod(operationName = "itineraries")
	public List<Itinerary> getItineraries() {
		Query query = em.createQuery("SELECT m from Itinerary as m");
		return (List<Itinerary>) query.getResultList();
	}

	@Override
	@WebMethod(operationName = "addItinerary")
	public void addItinerary(
			@WebParam(name = "newItinerary") Itinerary newItinerary) {
		em.persist(newItinerary);
	}
	
	@Override
	@WebMethod(operationName = "getItinerary")
	public Itinerary getItinerary(@WebParam(name = "id") int id) {
		return em.find(Itinerary.class, id);
	}
	
	@Override
	@WebMethod(operationName = "updateItinerary")
	public void updateItinerary(
			@WebParam(name = "id") int id, @WebParam(name = "itinerary") Itinerary itinerary) {
		Itinerary previousItinerary = getItinerary(id);
		Itinerary newItinerary = em.merge(previousItinerary);
		newItinerary.setTitle(itinerary.getTitle());
		newItinerary.setNotes(itinerary.getNotes());
		newItinerary.setComments(itinerary.getComments());
	}
}
