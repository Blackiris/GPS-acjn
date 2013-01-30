package fr.emse.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.emse.objects.Itinerary;

/**
 * Session Bean implementation class ClientBean
 */
@Stateless
@LocalBean
public class ClientBean implements ClientBeanRemote {

	@PersistenceContext(unitName = "admin-unit")
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ClientBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Itinerary> getItineraries() {
		Query query = em.createQuery("SELECT m from Itinerary as m");
        return (List<Itinerary>)query.getResultList();
	}

	@Override
	public void usingItinerary(int id) {
		Itinerary itinerary = (Itinerary)em.find(Itinerary.class, id);
		itinerary.isUsed();
		em.persist(itinerary);
	}

}
