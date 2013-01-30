package fr.emse.server;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Itinerary {
	@Id
	Integer id;
	List<Note> notes;
	double distance;
	int deniveleTotal;
	String comments;
	int nbUsed;
	String dateCreation;
	
	public Itinerary() {
		this.id = -1;
		this.notes = null;
		this.comments = "";
		this.nbUsed = 0;
		this.deniveleTotal = 0;
		this.distance = 0;
		
		// Date
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter=  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.dateCreation = formatter.format(currentDate.getTime());
	}
	
	public Itinerary(Integer id, List<Note> notes, String comments) {
		super();
		this.id = id;
		this.notes = notes;
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
		}
		
		// Date
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter=  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.dateCreation = formatter.format(currentDate.getTime());
	}
	
	private double distance(Coordinate A, Coordinate B) {
		double R = 6371;
		
		// Convert to radiant
		double latA = A.getLatitude()*Math.PI/180;
		double longA = A.getLongitude()*Math.PI/180;
		double latB = B.getLatitude()*Math.PI/180;
		double longB = B.getLongitude()*Math.PI/180;
		
		return R*Math.acos(Math.sin(latA)*Math.sin(latB)+Math.cos(latA)*Math.cos(latB)*Math.cos(longA-longB));
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
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
	
	public void isUsed() {
		nbUsed ++;
	}
}
