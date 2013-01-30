package fr.emse.objects;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import fr.emse.objects.Coordinate;

@Entity
public class Note implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue
	@Id
	Integer id;
	Coordinate coordinate;
	int height;
	String comments;
	String dateCreation;
	String category;
	
	public Note() {
		this.id = -1;
		this.coordinate = null;
		this.height = 0;
		this.comments = "";
		this.category = "";
		
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter=  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		dateCreation = formatter.format(currentDate.getTime());
	}
	
	public Note(Integer id, Coordinate coordinate, int height, String comments,
			String category) {
		super();
		this.id = id;
		this.coordinate = coordinate;
		this.height = height;
		this.comments = comments;
		this.category = category;
		
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter=  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		dateCreation = formatter.format(currentDate.getTime());
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

	public String getDateCreation() {
		return dateCreation;
	}
	
	
}
