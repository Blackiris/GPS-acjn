package fr.emse.clientadmin;

import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

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
<<<<<<< HEAD
	
=======

>>>>>>> branch 'master' of ssh://git@github.com/Blackiris/GPS-acjn.git


}
