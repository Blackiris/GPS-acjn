package fr.emse.server;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 * Session Bean implementation class ClientBean
 */
@Stateless
@LocalBean
public class ClientBean implements ClientBeanRemote {

	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ClientBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Itinerary> getItineraries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void usingItinerary(int id) {
		// TODO Auto-generated method stub
		
	}

}
