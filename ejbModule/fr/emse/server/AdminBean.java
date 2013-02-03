package fr.emse.server;

import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class AdminBean
 */
@Stateless(name = "AdminEJB", mappedName = "AdminBean")
@LocalBean
public class AdminBean implements AdminBeanRemote {

	@PersistenceContext(unitName = "admin-unit")
	EntityManager em;
	boolean isLogged = false;

	/**
	 * Default constructor.
	 */
	public AdminBean() {
	}

	@Override
	public boolean signIn(String password) {
		if (password.equals("monbean")) {
			isLogged = true;
		}
		return false;
	}

	@Override
	public void signOut() {
		isLogged = false;
	}

	@Override
	public void addNote(Note note) {
		em.persist(note);
	}

	@Override
	public Note getNote(SCoordinate coor) {
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

	@Override
	public List<Note> getNotes() {
		Query query = em.createQuery("SELECT m from Note as m");
		return (List<Note>) query.getResultList();
	}

	@Override
	public void updateNote(SCoordinate coor, Note note) {
		Note previousNote = getNote(coor);
		Note newNote = em.merge(previousNote);
		newNote.setCategory(note.getCategory());
		newNote.setComments(note.getComments());
		newNote.setCoordinate(note.getCoordinate());
		newNote.setHeight(note.getHeight());
	}

	@Override
	public void removeNote(SCoordinate coor) {
		Note note = getNote(coor);
		em.remove(note);
	}

	@Override
	public List<Itinerary> getItineraries() {
		Query query = em.createQuery("SELECT m from Itinerary as m");
		return (List<Itinerary>) query.getResultList();
	}

	@Override
	public void addItinerary(Itinerary newItinerary) {
		em.persist(newItinerary);
	}

	@Override
	public String hello() {
		return "Hello!";
	}
}
