package fr.emse.server;

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
 * Session Bean implementation class ClientBean
 */
@Stateless
@LocalBean
@WebService(serviceName = "ClientService")
public class ClientBean implements ClientBeanRemote {

	@PersistenceContext(unitName = "admin-unit")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public ClientBean() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	@WebMethod(operationName = "itineraries")
	public List<Itinerary> getItineraries() {
		Query query = em.createQuery("SELECT m from Itinerary as m");
		return (List<Itinerary>) query.getResultList();
	}

	@Override
	@WebMethod(operationName = "usingItinerary")
	public void usingItinerary(@WebParam(name = "itineraryId") int id) {
		Itinerary itinerary = (Itinerary) em.find(Itinerary.class, id);
		itinerary.isUsed();
		em.merge(itinerary);
	}

	@Override
	@WebMethod(operationName = "getNote")
	public Note getNote(@WebParam(name = "coordinate") SCoordinate coor) {
		Query query = em.createQuery("SELECT m from Note as m");
		List<Note> noteList = (List<Note>) query.getResultList();

		double min = 1000;
		Note actualNote = null;
		// on parcours l'ensemble des notes de la Map
		for (Note note : noteList) {
			// on calcule la distance entre la note courante et les coordonnées
			double dist = Math.abs(coor.getLat()
					- note.getCoordinate().getLat())
					+ Math.abs(coor.getLon() - note.getCoordinate().getLon());
			// on cherche le min
			if (dist < min) {
				min = dist;
				actualNote = note;
			}
		}

		return actualNote;
	}

	@Override
	@WebMethod(operationName = "getItinerary")
	public Itinerary getItinerary(@WebParam(name = "id") int id) {
		return em.find(Itinerary.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@WebMethod(operationName = "getNotes")
	public List<Note> getNotes() {
		Query query = em.createQuery("SELECT m from Note as m");
		return (List<Note>) query.getResultList();
	}

}
