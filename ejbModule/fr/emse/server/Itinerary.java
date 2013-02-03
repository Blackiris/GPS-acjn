package fr.emse.server;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Itinerary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8148007240281677876L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String title;
	@ManyToMany(mappedBy="itineraries", cascade = CascadeType.PERSIST)
	List<Note> notes;
	double distance;
	int deniveleTotal;
	String comments;
	int nbUsed;
	String dateCreation;
	
	public Itinerary() {
		this.id = -1;
		this.notes = new ArrayList<Note>();
		this.title = "";
		this.comments = "";
		this.nbUsed = 0;
		this.deniveleTotal = 0;
		this.distance = 0;
		
		// Date
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter=  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.dateCreation = formatter.format(currentDate.getTime());
	}
	
	public Itinerary(List<Note> notes, String title, String comments) {
		super();
		this.notes = notes;
		this.title = title;
		this.comments = comments;
		this.nbUsed = 0;
		
		// Computes denivele and distance
		this.deniveleTotal = 0;
		this.distance = 0;
		Note previousNote = notes.get(0);
		
		
		boolean isFirst = true;
		for (Note note : notes) {
			if (isFirst) {
				isFirst = false;
			} else {
				this.deniveleTotal += Math.abs(note.getHeight() - previousNote.getHeight());
				this.distance += distance(note.getCoordinate(), previousNote.getCoordinate());
			}
			
			previousNote = note;
			note.addItinerary(this);
		}
		
		// Date
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter=  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.dateCreation = formatter.format(currentDate.getTime());
	}
	
	private double distance(SCoordinate A, SCoordinate B) {
		double R = 6371;
		
		// Convert to radiant
		double latA = A.getLat()*Math.PI/180;
		double longA = A.getLon()*Math.PI/180;
		double latB = B.getLat()*Math.PI/180;
		double longB = B.getLon()*Math.PI/180;
		
		return R*Math.acos(Math.sin(latA)*Math.sin(latB)+Math.cos(latA)*Math.cos(latB)*Math.cos(longA-longB));
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	

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

	public int getId() {
		return id;
	}

	public double getDistance() {
		return distance;
	}

	public int getDeniveleTotal() {
		return deniveleTotal;
	}

	public int getNbUsed() {
		return nbUsed;
	}

	public String getDateCreation() {
		return dateCreation;
	}
	
	public void appendNote(Note newNote) {
		if (notes.size()>0) {
			Note lastNote = notes.get(notes.size()-1);
			
			distance+=distance(newNote.getCoordinate(), lastNote.getCoordinate());
			deniveleTotal += Math.abs(newNote.getHeight() - lastNote.getHeight());
		}
		
		newNote.addItinerary(this);
		notes.add(newNote);
	}
	
	public void updateNote(SCoordinate coor, Note newNote) {
		int i=0;
		for (Note note:notes){
			if (note.getCoordinate().getLat() == coor.getLat() && note.getCoordinate().getLon() == coor.getLon()){
				notes.set(i, newNote);
			}
			i++;
		}
	}
	
	public void removeNote(SCoordinate coor) {
		int i=0;
		for (Note note:notes){
			if (note.getCoordinate().getLat() == coor.getLat() && note.getCoordinate().getLon() == coor.getLon()){
				notes.remove(i);
			}
			i++;
		}
	}
	
	public void isUsed() {
		nbUsed ++;
	}
}
