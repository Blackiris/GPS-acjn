package fr.emse.clientadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

import fr.emse.server.Itinerary;
import fr.emse.server.Note;
import fr.emse.server.SCoordinate;

/**
 * Classe qui fait le lien au niveau des données entre le bean et l'interface
 * graphique
 * 
 * @author Antoine, Julien
 * 
 */
public class DataModel {

	// définit l'option de persistance pour JPA
	@ManyToMany(cascade = CascadeType.ALL)
	// map qui contient les notes ainsi que leurs coordonnées
	Map<SCoordinate, Note> mapNotes;
	// liste des itinéraires générés
	private List<Itinerary> itineraries;

	public DataModel() throws NamingException {
		// on instancie la Map
		mapNotes = new HashMap<SCoordinate, Note>();

		// on charge les notes présentes dans la base de données
		List<Note> notes = ClientAdmin.adminBeanRemote.getNotes();
		if (notes != null) {
			for (Note note : notes) {
				// on ajoute les notes de la base de données dans la Map
				mapNotes.put(note.getCoordinate(), note);
			}
		}

		// on charge les itinéraires de la base de données
		itineraries = ClientAdmin.adminBeanRemote.getItineraries();
	}

	/**
	 * méthode qui ajoute une note à la Map et à la base de données
	 * 
	 * @param newNote
	 */
	public void addNote(Note newNote) {
		// ajout dans la map
		mapNotes.put(newNote.getCoordinate(), newNote);
		// appel de la fonction addNote de AdminBeanRemote qui effectue l'ajout
		// dans la base de données
		ClientAdmin.adminBeanRemote.addNote(newNote);
	}

	/**
	 * méthode qui récupère la liste des notes depuis la Map
	 * 
	 * @return List<Note>
	 */
	public List<Note> getNotes() {
		List<Note> res = new ArrayList<Note>();

		for (SCoordinate sCoordinate : mapNotes.keySet()) {
			res.add(mapNotes.get(sCoordinate));
		}

		return res;
	}

	/**
	 * Méthode qui récupère une note depuis la base de donnée en se basant sur
	 * ses coordonées
	 * 
	 * @param coor
	 *            coordonées de la note à récupérer
	 * @return Note
	 */
	public Note getNote(SCoordinate coor) {
		return ClientAdmin.adminBeanRemote.getNote(coor);
	}

	/**
	 * Méthode qui met à jour la note ayant les coordonées passées en paramètre
	 * 
	 * @param coor
	 *            coordonnées de la note à mettre à jour
	 * @param note
	 *            mise à jour pour l'ancienne note
	 */
	public void updateNote(SCoordinate coor, Note note) {
		// on ajoute la nouvelle note à la Map
		mapNotes.put(note.getCoordinate(), note);
		// on retire l'ancienne
		mapNotes.remove(coor);
		// on appelle la méthode de AdminBeanRemote qui se charge de mettre à
		// jour la note concernée dans la base de données
		ClientAdmin.adminBeanRemote.updateNote(coor, note);
		// on met à jour l'itinéraire de la liste des itinéaires dont la note a
		// changé
		updateRelatedItinerary(coor, note);
	}

	/**
	 * méthode qui supprime une note de la Map et de la base de données
	 * 
	 * @param coor
	 *            coordonées de la note en question
	 */
	public void removeNote(SCoordinate coor) {
		// retire la note de la Map
		mapNotes.remove(coor);
		// appelle la méthode de AdminBeanRemote qui se charge de supprimer la
		// note de la base de données
		ClientAdmin.adminBeanRemote.removeNote(coor);
		// modifie l'itinéraire associé à cette note dans la liste des
		// itinéraires
		removeRelatedNote(coor);
	}

	/**
	 * méthode qui trouve la note la plus proche des coordonées spécifiées en
	 * paramètres
	 * 
	 * @param latitude
	 * @param longitude
	 * @return Note
	 */
	public Note getNearestNoteFrom(double latitude, double longitude) {
		double min = 1000;
		Note actualNote = null;
		// on parcours l'ensemble des notes de la Map
		for (SCoordinate coord : mapNotes.keySet()) {
			// on calcule la distance entre la note courante et les coordonnées
			double dist = Math.abs(latitude - coord.getLat())
					+ Math.abs(longitude - coord.getLon());
			// on cherche le min
			if (dist < min) {
				min = dist;
				actualNote = mapNotes.get(coord);
			}
		}

		return actualNote;
	}

	/**
	 * méthode qui ajoute un itinéraire à la liste et à la bse de données
	 * 
	 * @param newItinerary
	 *            itinéraire en question
	 */
	public void addItinerary(Itinerary newItinerary) {
		// ajout de l'itinéraire à la liste
		itineraries.add(newItinerary);
		// appel de la méthode de AdminBeanRemote qui ajout l'itinéraire à la
		// base de données
		ClientAdmin.adminBeanRemote.addItinerary(newItinerary);
	}

	public List<Itinerary> getItineraries() {
		return itineraries;
	}

	/**
	 * méthode qui met à jour un itinéraire de la liste lorsqu'une note est
	 * modifiée
	 * 
	 * @param coor
	 *            coordonées de l'ancienne note
	 * @param newNote
	 *            nouvelle note pour la mise à jour
	 */
	public void updateRelatedItinerary(SCoordinate coor, Note newNote) {
		// on parcourt l'ensemble des itinéraires de la liste
		for (Itinerary itinerary : itineraries) {
			// on appelle la méthode d'un itinéraire qui se charge de mettre à
			// jour la note spécifiée par les coordonées
			if (itinerary.updateNote(coor, newNote))
				// on met à jour la distance et le dénivelé de l'itinéraire
				itinerary.updateGeometry();
			System.out.println("Itinéraire " + itinerary.getTitle()
					+ " modifié");
		}
	}

	/**
	 * méthode qui retire la note dont les coordonnées sont spécifiées en
	 * paramètres
	 * 
	 * @param coor
	 *            coordonnées de la note
	 */
	public void removeRelatedNote(SCoordinate coor) {
		// on parcourt l'ensemble des itinéraires de la liste
		for (Itinerary itinerary : itineraries) {
			// on appelle la méthode d'un itinéraire qui se supprimer la note
			// spécifiée par les coordonées
			itinerary.removeNote(coor);
			// on met à jour la distance et le dénivelé de l'itinéraire
			itinerary.updateGeometry();
			System.out.println("Itinéraire " + itinerary.getTitle()
					+ " modifié");
		}
	}
}
