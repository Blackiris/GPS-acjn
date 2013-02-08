package fr.emse.server;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ClientBeanRemote {
	/**
	 * Récupère les itinéraires de la base de données
	 * 
	 * @return Itinéraires
	 */
	List<Itinerary> getItineraries();

	/**
	 * Permets de dire au serveur que nous utilisons l'itinéraire indiqué par id
	 * 
	 * @param id
	 *            Id de l'itinéraire
	 */
	void usingItinerary(int id);

	/**
	 * Renvoie l'itinéraire indiqué par l'id
	 * 
	 * @param id
	 *            Id de l'itinéraire à récupérer
	 * @return Itinéraire souhaité
	 */
	Itinerary getItinerary(int id);

	Note getNote(SCoordinate coord);
}
