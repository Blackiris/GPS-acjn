package fr.emse.clientadmin;

import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

/**
 * Classe qui fait le lien entre les interfaces graphiques et la classe DataModel
 * Les attributs définis sont statiques pour que les modifications soient synchronisées entre les classes qui les utilisent
 * @author Antoine, Julien
 *
 */
public class Context {

	private static State state;
	private static int currentIndex;
	private static MapMarker currentMapMarker;

	public static State getState() {
		return state;
	}

	public static void setState(State state) {
		Context.state = state;
	}

	public static int getCurrentIndex() {
		return currentIndex;
	}

	public static void setCurrentIndex(int currentIndex) {
		Context.currentIndex = currentIndex;
	}

	public static MapMarker getCurrentMapMarker() {
		return currentMapMarker;
	}

	public static void setCurrentMapMarker(MapMarker currentMapMaker) {
		Context.currentMapMarker = currentMapMaker;
	}

}
