package fr.emse.server;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Itinerary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8148007240281677876L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	Integer id;
	String title;
	// @ManyToMany(mappedBy = "itineraries", s, fetch =
	// FetchType.EAGER)

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@MapKey(name = "pos")
	Map<Integer, Position> positions;
	double distance;
	int deniveleTotal;
	String comments;
	int nbUsed;
	String dateCreation;

	/**
	 * Constructeur vides
	 */
	public Itinerary() {
		this.positions = new TreeMap<Integer, Position>();
		this.title = "";
		this.comments = "";
		this.nbUsed = 0;
		this.deniveleTotal = 0;
		this.distance = 0;

		// Date
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.dateCreation = formatter.format(currentDate.getTime());
	}

	/**
	 * Constructeur
	 * 
	 * @param notes
	 *            Liste de notes représentant le parcours
	 * @param title
	 *            Titre du parcours
	 * @param comments
	 *            Commentaires sur le parcours
	 */
	public Itinerary(Map<Integer, Position> positions, String title,
			String comments) {
		super();
		this.positions = positions;
		this.title = title;
		this.comments = comments;
		this.nbUsed = 0;

		// Computes denivele and distance
		this.deniveleTotal = 0;
		this.distance = 0;
		Note previousNote = positions.get(0).getNote();

		boolean isFirst = true;

		for (Integer pos : positions.keySet()) {
			if (isFirst) {
				isFirst = false;
			} else {
				this.deniveleTotal += Math.abs(positions.get(pos).getNote()
						.getHeight()
						- previousNote.getHeight());
				this.distance += distance(positions.get(pos).getNote()
						.getCoordinate(), previousNote.getCoordinate());
			}

			previousNote = positions.get(pos).getNote();
			// notes.get(pos).addItinerary(this);
		}

		// Date
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.dateCreation = formatter.format(currentDate.getTime());
	}

	/**
	 * Calcule la distance entre deux coordonnées
	 * 
	 * @param A
	 *            Coordonnées du premier point
	 * @param B
	 *            Coordonnées du deuxième point
	 * @return Distance entre les deux coordonnées
	 */
	private double distance(SCoordinate A, SCoordinate B) {
		double R = 6371;

		// Convert to radiant
		double latA = A.getLat() * Math.PI / 180;
		double longA = A.getLon() * Math.PI / 180;
		double latB = B.getLat() * Math.PI / 180;
		double longB = B.getLon() * Math.PI / 180;

		return R
				* Math.acos(Math.sin(latA) * Math.sin(latB) + Math.cos(latA)
						* Math.cos(latB) * Math.cos(longA - longB));
	}

	/**
	 * Renvoie la liste des notes représentant le parcours
	 * 
	 * @return
	 */
	public Map<Integer, Position> getPositions() {
		return positions;
	}

	/**
	 * Modifie la liste des notes représentant le parcours
	 * 
	 * @param notes
	 *            Nouvelle liste de notes
	 */
	public void setPositions(Map<Integer, Position> positions) {
		this.positions = positions;
	}

	/**
	 * Renvoie le titre du parcours
	 * 
	 * @return titre du parcours
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Renvoie la distance du parcours
	 * 
	 * @return Distance du parcours
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Renvoie l'information de la distance sous forme de chaîne de caractères
	 * 
	 * @return
	 */
	public String getDistanceString() {
		DecimalFormat dFormat = new DecimalFormat("0.00");
		return dFormat.format(distance) + " km";
	}

	/**
	 * Renvoie le dénivelé total du parcours
	 * 
	 * @return Dénivelé du parcours
	 */
	public int getDeniveleTotal() {
		return deniveleTotal;
	}

	/**
	 * Renvoie l'information du dénivelé sous forme de chaîne de caractères
	 * 
	 * @return
	 */
	public String getDeniveleString() {
		return deniveleTotal + " m";
	}

	/**
	 * Renvoie le nombre de fois que le parcours a été utilisé
	 * 
	 * @return
	 */
	public int getNbUsed() {
		return nbUsed;
	}

	/**
	 * Renvoie la date de création du parcours
	 * 
	 * @return
	 */
	public String getDateCreation() {
		return dateCreation;
	}

	/**
	 * Ajout d'une note en fin de parcours
	 * 
	 * @param newNote
	 *            Nouvelle note à ajouter
	 */
	public void appendNote(Note newNote) {
		if (positions.size() > 0) {
			Note lastNote = positions.get(positions.size()).getNote();

			distance += distance(newNote.getCoordinate(),
					lastNote.getCoordinate());
			deniveleTotal += Math.abs(newNote.getHeight()
					- lastNote.getHeight());
		}

		newNote.addItinerary(this);
		positions.put(positions.size() + 1, new Position(positions.size() + 1,
				newNote));
	}

	/**
	 * Mets à jour
	 * 
	 * @param coor
	 * @param newNote
	 */
	public void updateNote(SCoordinate coor, Note newNote) {
		for (Integer posInt : positions.keySet()) {
			Position position = positions.get(posInt);

			if (position.getNote().getCoordinate().getLat() == coor.getLat()
					&& position.getNote().getCoordinate().getLon() == coor
							.getLon()) {
				position.setNote(newNote);
			}
		}
	}

	/**
	 * Supprime la note de l'itinéraire
	 * 
	 * @param coor
	 *            Coordonnées de la note à supprimer
	 */
	public void removeNote(SCoordinate coor) {
		for (Integer pos : positions.keySet()) {
			if (positions.get(pos).getNote().getCoordinate().getLat() == coor
					.getLat()
					&& positions.get(pos).getNote().getCoordinate().getLon() == coor
							.getLon()) {
				positions.remove(pos);
			}
		}

		Map<Integer, Position> newPositions = new TreeMap<Integer, Position>();

		int i = 1;
		for (Integer pos : positions.keySet()) {
			newPositions.put(i, positions.get(pos));
			i++;
		}
	}

	/**
	 * Mets à jour les valeurs de distance et de dénivelé
	 */
	public void updateGeometry() {
		Note previousNote = positions.get(0).getNote();
		this.distance = 0;
		this.deniveleTotal = 0;

		boolean isFirst = true;
		for (Integer pos : positions.keySet()) {
			if (isFirst) {
				isFirst = false;
			} else {
				this.deniveleTotal += Math.abs(positions.get(pos).getNote()
						.getHeight()
						- previousNote.getHeight());
				this.distance += distance(positions.get(pos).getNote()
						.getCoordinate(), previousNote.getCoordinate());
			}
		}
	}

	/**
	 * Augmente le nombre de fois que le parcours a été sélectionné
	 */
	public void isUsed() {
		nbUsed++;
	}
}
