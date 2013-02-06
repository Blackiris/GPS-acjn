/**
 * Classe SCoordinate
 */

package fr.emse.server;

import java.io.Serializable;
import java.text.DecimalFormat;

public class SCoordinate implements Serializable {

	/**
	 * 
	 */
	private double lat;
	private double lon;

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * 
	 * @param lat
	 *            Latitude
	 * @param lon
	 *            Longitude
	 */
	public SCoordinate(double lat, double lon) {
		super();
		this.lat = lat;
		this.lon = lon;
	}

	/**
	 * Renvoie la latitude
	 * 
	 * @return Latitude
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * Modifie la latitude
	 * 
	 * @param lat
	 *            Nouvelle latitude
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * Renvoie la longitude
	 * 
	 * @return Longitude
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * Modifie la longitude
	 * 
	 * @param lon
	 *            Nouvelle longitude
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		DecimalFormat dFormat = new DecimalFormat("0.000");
		return dFormat.format(lat) + ":" + dFormat.format(lon);
	}
}
