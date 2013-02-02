package fr.emse.server;

import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 * Session Bean implementation class AdminBean
 */
@Stateless (name="AdminEJB", mappedName="AdminBean")
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
	public Note getNote(int id) {
		return (Note)em.find(Note.class, id);
	}
	
	@Override
	public List<Note> getNotes() {
		Query query = em.createQuery("SELECT m from Note as m");
        return (List<Note>)query.getResultList();
	}
	
	@Override
	public void updateNote(int id, Note note) {
		Note previousNote = (Note) em.find(Note.class, id);
		previousNote.setCategory(note.getCategory());
		previousNote.setComments(note.getComments());
		previousNote.setCoordinate(note.getCoordinate());
		previousNote.setHeight(note.getHeight());
		
		em.merge(previousNote);
		em.getTransaction().commit(); 
	}

	@Override
	public List<Itinerary> getItineraries() {
		Query query = em.createQuery("SELECT m from Itinerary as m");
        return (List<Itinerary>)query.getResultList();
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
