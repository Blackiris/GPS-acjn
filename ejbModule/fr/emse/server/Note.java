package fr.emse.server;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Note implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	SCoordinate coordinate;
	int height;
	String comments;
	String dateCreation;
	String category;
	
	List<Itinerary> itineraries;
	
	public Note() {
		this.id = -1;
		this.coordinate = null;
		this.height = 0;
		this.comments = "";
		this.category = "";
		itineraries = new ArrayList<Itinerary>();
		
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter=  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		dateCreation = formatter.format(currentDate.getTime());
	}
	
	public Note(SCoordinate coordinate, int height, String comments,
			String category) {
		super();
		this.coordinate = coordinate;
		this.height = height;
		this.comments = comments;
		this.category = category;
		
		itineraries = new ArrayList<Itinerary>();
		
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter=  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		dateCreation = formatter.format(currentDate.getTime());
	}

	public SCoordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(SCoordinate coordinate) {
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
	
	public void addItinerary(Itinerary newItinerary) {
		itineraries.add(newItinerary);
	}
}
