package fr.emse.server;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface AdminBeanRemote {
	// boolean signIn(String password);
	//
	// void signOut();

	/**
	 * Ajoute une note
	 * 
	 * @param note
	 */
	void addNote(Note note);

	/**
	 * Récupère la note située aux coordonnées coor
	 * 
	 * @param coor
	 *            Coordonnées de la note à récupérer
	 * @return
	 */
	Note getNote(SCoordinate coor);

	/**
	 * Met à jour une note
	 * 
	 * @param coor
	 *            Coordonnées de l'ancienne note
	 * @param note
	 *            Nouvelle note
	 */
	void updateNote(SCoordinate coor, Note note);

	void removeNote(SCoordinate coor);

	List<Note> getNotes();

	List<Itinerary> getItineraries();

	void addItinerary(Itinerary newItinerary);

	Itinerary getItinerary(int id);

	void updateItinerary(int id, Itinerary itinerary);
}
