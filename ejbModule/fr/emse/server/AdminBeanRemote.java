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

	/**
	 * Supprime la note située aux coordonnées coor
	 * 
	 * @param coor
	 *            Coordonnées de la note à supprimer
	 */
	void removeNote(SCoordinate coor);

	/**
	 * Récupère toutes les notes enregistrées dans la base de données
	 * 
	 * @return Notes du serveur
	 */
	List<Note> getNotes();

	/**
	 * Récupère toutes les itinéraires de la base de données
	 * 
	 * @return Itinéraires du serveur
	 */
	List<Itinerary> getItineraries();

	/**
	 * Ajoute un nouvel itinéraire à la base de données
	 * 
	 * @param newItinerary
	 *            Nouvel itinéraire à ajouter
	 */
	void addItinerary(Itinerary newItinerary);

	/**
	 * Renvoie l'itinéraire indiqué par l'id
	 * 
	 * @param id
	 *            Id de l'itinéraire à récupérer
	 * @return Itinéraire souhaité
	 */
	Itinerary getItinerary(int id);

	/**
	 * Remplace l'itinéraire indiqué par l'id par un nouvel itinéraire
	 * 
	 * @param id
	 *            Id de l'itinéraire à remplacer
	 * @param itinerary
	 *            Nouvel itinéraire
	 */
	void updateItinerary(int id, Itinerary itinerary);
}
