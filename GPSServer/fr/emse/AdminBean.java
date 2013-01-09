package fr.emse;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class AdminBean
 */
@Stateless
@LocalBean
public class AdminBean implements AdminBeanRemote {

	EntityManager em;
	boolean isLogged = false;
	
    /**
     * Default constructor. 
     */
    public AdminBean() {
        // TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Itinerary> getItineraries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addItinerary(Itinerary newItinerary) {
		// TODO Auto-generated method stub
		
	}

}
