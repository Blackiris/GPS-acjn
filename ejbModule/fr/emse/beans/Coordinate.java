package fr.emse.beans;

import java.io.Serializable;

public class Coordinate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double latitude;
	double longitude;
	
	public Coordinate(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
