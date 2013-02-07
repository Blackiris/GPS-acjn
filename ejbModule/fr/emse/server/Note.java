package fr.emse.server;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Note implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	Integer id;
	SCoordinate coordinate;
	int height;
	String comments;
	String dateCreation;
	String category;

	@ManyToMany(fetch = FetchType.EAGER)
	List<Itinerary> itineraries;

	/**
	 * Constructeur de note vide
	 */
	public Note() {
		this.coordinate = null;
		this.height = 0;
		this.comments = "";
		this.category = "";
		itineraries = new ArrayList<Itinerary>();

		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		dateCreation = formatter.format(currentDate.getTime());
	}

	/**
	 * Constructeur
	 * 
	 * @param coordinate
	 *            Coordonnées
	 * @param height
	 *            Altitude du lieu
	 * @param comments
	 *            Commentaires sur le lieu
	 * @param category
	 *            Catégorie de lieu
	 */
	public Note(SCoordinate coordinate, int height, String comments,
			String category) {
		super();
		this.coordinate = coordinate;
		this.height = height;
		this.comments = comments;
		this.category = category;

		itineraries = new ArrayList<Itinerary>();

		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		dateCreation = formatter.format(currentDate.getTime());
	}

	/**
	 * Renvoie les coordonnées de la position de la note
	 * 
	 * @return Coordonnées
	 */
	public SCoordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * Déplace la position de la note
	 * 
	 * @param coordinate
	 *            Nouvelle position
	 */
	public void setCoordinate(SCoordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Renvoie l'altitude de la note
	 * 
	 * @return altitude
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Change l'altitude
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Renvoie les commentaires sur la note
	 * 
	 * @return Commentaires
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Change les commentaires sur le note
	 * 
	 * @param comments
	 *            Nouveaux commentaires
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Renvoie la catégorie de la note
	 * 
	 * @return Catégorie
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Change la catégorie de la note
	 * 
	 * @param category
	 *            Nouvelle catégorie
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Renvoie la date de création de la note
	 * 
	 * @return date de céation
	 */
	public String getDateCreation() {
		return dateCreation;
	}

	/**
	 * Ajoute une itinéraire auquelle la note appartient
	 * 
	 * @param newItinerary
	 *            Nouvel itinéraire contenant la note
	 */
	public void addItinerary(Itinerary newItinerary) {
		if (!itineraries.contains(newItinerary))
			itineraries.add(newItinerary);
	}
}
