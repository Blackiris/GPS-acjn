package fr.emse;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Note implements Serializable {
	@Id
	int id;
	Coordinate coordinate;
	int height;
	String comments;
	Date dateCreation;
	String category;
	
	public Note(int id, Coordinate coordinate, int height, String comments,
			String category) {
		super();
		this.id = id;
		this.coordinate = coordinate;
		this.height = height;
		this.comments = comments;
		this.category = category;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public Date getDateCreation() {
		return dateCreation;
	}
	
	
}
