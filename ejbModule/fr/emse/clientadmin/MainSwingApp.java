package fr.emse.clientadmin;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.MouseInputListener;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import fr.emse.server.Itinerary;
import fr.emse.server.Note;


/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainSwingApp extends JFrame implements ActionListener, MouseInputListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton jButtonCreateNote;
	private JButton jButtonItinerary;
	private JList<String> jListItineraries;
	private JMapViewer map;

	private MapMarker currentMapmarker;
	private Itinerary currentItinerary;
	private boolean isSelected;

	private JLabel jLabel1;

	private State state;

	/**
	 * Auto-generated main method to display this JFrame
	 */

	public MainSwingApp() {
		super();
		state = State.NORMAL;
		isSelected = false;

		this.map = new JMapViewer();
		map.addMouseListener(this);
		initiateMainView();
		initGUI();
		if (map.getMapMarkerList().isEmpty()){
			map.setDisplayPositionByLatLon(45.430262484682125,4.3890380859375, 11);
		} else {
			map.setDisplayToFitMapMarkers();
			map.setZoom(9);
		}
	}

	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			this.setTitle("GeoNotes");
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new AnchorConstraint(317, 225, 358, 49, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Liste des itinéraires");
			}
			{

				List<Itinerary> itineraries = ClientAdmin.dataModel.getItineraries();
				ArrayList<String> itinerariesName = new ArrayList<String>();
				for (Itinerary itinerary : itineraries) {
					itinerariesName.add("Itinéraire "+itinerary.getId());
				}

				ListModel jListItinerariesModel = new DefaultComboBoxModel(itinerariesName.toArray());

				jListItineraries = new JList<String>();
				getContentPane().add(jListItineraries, new AnchorConstraint(388, 215, 767, 49, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jListItineraries.setModel(jListItinerariesModel);
				jListItineraries.setPreferredSize(new java.awt.Dimension(116, 138));
			}
			{
				jButtonItinerary = new JButton();
				getContentPane().add(jButtonItinerary, new AnchorConstraint(831, 489, 913, 49, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButtonItinerary.setText("Créer un itinéraire");
				jButtonItinerary.setPreferredSize(new java.awt.Dimension(308, 30));
				jButtonItinerary.addActionListener(this);
			}
			{	

				final JCheckBox scrollWrapEnabled = new JCheckBox("Scrollwrap enabled");
				scrollWrapEnabled.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						map.setScrollWrapEnabled(scrollWrapEnabled.isSelected());
					}
				});

				final JCheckBox showMapMarker = new JCheckBox("Map markers visible");
				showMapMarker.setSelected(map.getMapMarkersVisible());
				showMapMarker.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						map.setMapMarkerVisible(showMapMarker.isSelected());
					}
				});

				getContentPane().add(map, new AnchorConstraint(42, 970, 767, 339, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				map.setPreferredSize(new java.awt.Dimension(440, 300));

			}
			{
				jButtonCreateNote = new JButton();
				getContentPane().add(jButtonCreateNote, new AnchorConstraint(831, 899, 913, 544, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButtonCreateNote.setText("Créer une note");
				jButtonCreateNote.setPreferredSize(new java.awt.Dimension(248, 30));
				jButtonCreateNote.addActionListener(this);
			}

			this.setSize(800, 400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initiateMainView(){
		for (Note note : ClientAdmin.dataModel.getNotes()) {
			map.addMapMarker(new MapMarkerDot(note.getCoordinate().getLatitude(),note.getCoordinate().getLongitude()));
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jButtonItinerary) {
			if (state == State.NORMAL) {
				currentItinerary = new Itinerary();
				state = State.CREATE_ITINERARY;
				jButtonItinerary.setText("Finish itinerary");
			} else {
				CreateItineraryJFrame frame = new CreateItineraryJFrame(currentItinerary, this);
				frame.setVisible(true);
			}
		}
		else if (ae.getSource() == jButtonCreateNote) {
			if (state == State.NORMAL) {
				state = State.CREATE_NOTE;
				jButtonCreateNote.setText("Put note on map");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point mousePoint = e.getPoint();

		if(e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1){

			MapMarker mapMarker = getMapMarker(mousePoint);
			if (state == State.NORMAL){
				if (isSelected){
					map.removeMapMarker(currentMapmarker);
					map.addMapMarker(new MapMarkerDot(map.getPosition(mousePoint).getLat(),map.getPosition(mousePoint).getLon()));
					isSelected = false;
				} else {
					if (mapMarker != null) {
						map.removeMapMarker(mapMarker);
						currentMapmarker = new MapMarkerDot(Color.RED, mapMarker.getLat(), mapMarker.getLon());
						map.addMapMarker(currentMapmarker);
						System.out.println(mapMarker.toString() + " is clicked");
						jButtonCreateNote.setText("Edit note");
						isSelected = true;
					}
				}
			}

			if (state == State.CREATE_NOTE) {
				if (mapMarker == null){
					Coordinate coor = map.getPosition(mousePoint);
					currentMapmarker = new MapMarkerDot(coor.getLat(), coor.getLon());
					map.addMapMarker(currentMapmarker);
					JFrame createNoteFrame = new CreateNoteJFrame(coor.getLat(), coor.getLon(), 0, this);
					createNoteFrame.setVisible(true);
					state = State.NORMAL;
				} else {
					System.out.println("Note existante à cet emplacement");
				}
			}

			else if (state == State.CREATE_ITINERARY) {
				if (mapMarker != null) {
					Note noteToAdd = ClientAdmin.dataModel.getNearestNodeFrom(mapMarker.getLat(), mapMarker.getLon());
					currentItinerary.appendNote(noteToAdd);
					updateMap();
				}
			}
		}

	}

	private MapMarker getMapMarker(Point mousePoint) {
		int X = mousePoint.x+3;
		int Y = mousePoint.y+3;

		List<MapMarker> ar = map.getMapMarkerList();
		Iterator<MapMarker> i = ar.iterator();

		double radCircle = 10;
		MapMarker mapMarker = null;
		while (i.hasNext() && radCircle>=8) {

			mapMarker = (MapMarker) i.next();

			Point MarkerPosition = map.getMapPosition(mapMarker.getLat(), mapMarker.getLon());
			if( MarkerPosition != null){

				int centerX = MarkerPosition.x;
				int centerY = MarkerPosition.y;

				//System.out.println(map.getPosition(p).getLat()+":"+map.getPosition(p).getLon());
				// calculate the radius from the touch to the center of the dot
				radCircle  = Math.sqrt( (((centerX-X)*(centerX-X)) + (centerY-Y)*(centerY-Y)));

			}
		}
		// if the radius is smaller then 23 (radius of a ball is 5), then it must be on the dot
		if (radCircle < 8){
			return mapMarker;
		}
		return null;
	}

	public void cancelCreateNote() {
		if (currentMapmarker != null) {
			map.removeMapMarker(currentMapmarker);
			currentMapmarker = null;
		}
		jButtonCreateNote.setText("Create note");
	}

	public void createNotefinished() {
		jButtonCreateNote.setText("Create note");
	}
	
	public void createItineraryFinished() {
		ClientAdmin.dataModel.addItinerary(currentItinerary);
		System.out.println("Itinéraire ajouté !");
		updateListItineraries();
		state = State.NORMAL;
		jButtonItinerary.setText("Create itinerary");
	}
	
	private void updateMap() {
		map.removeAllMapPolygons();

		List<Note> notes = currentItinerary.getNotes();
		org.openstreetmap.gui.jmapviewer.Coordinate coord1 = null;
		org.openstreetmap.gui.jmapviewer.Coordinate coord2 = null;

		for (Note note : notes) {
			coord1 = coord2;
			coord2 = new org.openstreetmap.gui.jmapviewer.Coordinate(note.getCoordinate().getLatitude(), note.getCoordinate().getLongitude());

			if (coord1 != null) {
				List<org.openstreetmap.gui.jmapviewer.Coordinate> route = 
						new ArrayList<org.openstreetmap.gui.jmapviewer.Coordinate>(Arrays.asList(coord1, coord2, coord2));
				map.addMapPolygon(new MapPolygonImpl(route));
			}	
		}
	}

	private void updateListItineraries() {
		List<Itinerary> itineraries = ClientAdmin.dataModel.getItineraries();
		ArrayList<String> itinerariesName = new ArrayList<String>();
		for (Itinerary itinerary : itineraries) {
			itinerariesName.add("Itinéraire "+itinerary.getId());
		}

		ListModel jListItinerariesModel = new DefaultComboBoxModel(itinerariesName.toArray());
		jListItineraries.setModel(jListItinerariesModel);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = e.getPoint();
		System.out.println(p.x+":"+p.y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
