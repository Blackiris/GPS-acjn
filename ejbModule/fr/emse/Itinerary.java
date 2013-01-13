package fr.emse;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Itinerary {
	@Id
	int id;
	List<Note> notes;
	double distance;
	int denivele;
	String comments;
	int nbUsed;
	Date dateCreation;
	
	public Itinerary(int id, List<Note> notes, String comments) {
		super();
		this.id = id;
		this.notes = notes;
		this.comments = comments;
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

	public int getDenivele() {
		return denivele;
	}

	public int getNbUsed() {
		return nbUsed;
	}

	public Date getDateCreation() {
		return dateCreation;
	}
	
}
