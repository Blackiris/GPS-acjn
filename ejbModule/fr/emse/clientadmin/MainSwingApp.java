package fr.emse.clientadmin;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JTextPane;
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
import fr.emse.server.SCoordinate;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainSwingApp extends JFrame implements ActionListener,
MouseInputListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton jButtonCreateNote;
	private JButton jButtonItinerary;
	private JButton jButtonRemove;
	private JList<String> jListItineraries;
	private JMapViewer map;
	private MapMarker tmpMapmarker;
	private JTextPane jTextPaneInfo;

	private Itinerary currentItinerary;
	private Note currentNote;

	private JLabel jLabel1;

	/**
	 * Auto-generated main method to display this JFrame
	 */

	public MainSwingApp() {
		super();
		Context.setState(State.NORMAL);

		this.map = new JMapViewer();
		map.addMouseListener(this);
		initiateMainView();
		initGUI();
		if (map.getMapMarkerList().isEmpty()) {
			map.setDisplayPositionByLatLon(45.430262484682125, 4.3890380859375,
					11);
		} else {
			map.setDisplayToFitMapMarkers();
			map.setZoom(9);
		}

		// Sign in
		// new SignInDialog(this);
	}

	@SuppressWarnings("unchecked")
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			this.setTitle("GeoNotes");
			{
				jTextPaneInfo = new JTextPane();
				getContentPane().add(jTextPaneInfo, new AnchorConstraint(42, 302, 283, 49, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextPaneInfo.setText("");
				jTextPaneInfo.setPreferredSize(new java.awt.Dimension(199, 87));
			}
			{
				jButtonRemove = new JButton();
				getContentPane().add(jButtonRemove, new AnchorConstraint(810, 961, 929, 902, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButtonRemove.setText("X");
				jButtonRemove.setPreferredSize(new java.awt.Dimension(46, 43));
				jButtonRemove.addActionListener(this);
				jButtonRemove.setVisible(false);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1,
						new AnchorConstraint(317, 225, 358, 49,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Liste des itinéraires");
			}
			{

				List<Itinerary> itineraries = ClientAdmin.dataModel
						.getItineraries();
				ArrayList<String> itinerariesName = new ArrayList<String>();
				if (itineraries != null){
					for (Itinerary itinerary : itineraries) {
						itinerariesName.add("Itinéraire " + itinerary.getId());
					}
				}

				@SuppressWarnings({ "rawtypes" })
				ListModel jListItinerariesModel = new DefaultComboBoxModel(
						itinerariesName.toArray());

				jListItineraries = new JList<String>();
				getContentPane().add(
						jListItineraries,
						new AnchorConstraint(388, 215, 767, 49,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jListItineraries.setModel(jListItinerariesModel);
				jListItineraries.setPreferredSize(new java.awt.Dimension(116,
						138));
				MouseListener mouseListener = new MouseAdapter() {
					public void mousePressed(MouseEvent mouseEvent) {
						int index = jListItineraries.locationToIndex(mouseEvent.getPoint());
						if (index >= 0) {
							Itinerary itinerary = ClientAdmin.adminBeanRemote.getItineraries().get(index);
							jTextPaneInfo.setText("Itinéraire: "+itinerary.getTitle()+"\n"
									+"Commentaires: "+itinerary.getComments()+"\n"
									+"Distance: "+itinerary.getDistanceString()+"\n"
									+"Dénivelé: "+itinerary.getDeniveleString()+"\n"
									+"Date: "+itinerary.getDateCreation());

							String element = jListItineraries.getModel().getElementAt(index);
							System.out.println("clicked on: " + element.toString());
						}
					}
				};
				jListItineraries.addMouseListener(mouseListener);

			}
			{
				jButtonItinerary = new JButton();
				getContentPane().add(
						jButtonItinerary,
						new AnchorConstraint(831, 489, 913, 49,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jButtonItinerary.setText("Créer un itinéraire");
				jButtonItinerary.setPreferredSize(new java.awt.Dimension(308,
						30));
				jButtonItinerary.addActionListener(this);
			}
			{

				final JCheckBox scrollWrapEnabled = new JCheckBox(
						"Scrollwrap enabled");
				scrollWrapEnabled.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						map.setScrollWrapEnabled(scrollWrapEnabled.isSelected());
					}
				});

				final JCheckBox showMapMarker = new JCheckBox(
						"Map markers visible");
				showMapMarker.setSelected(map.getMapMarkersVisible());
				showMapMarker.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						map.setMapMarkerVisible(showMapMarker.isSelected());
					}
				});

				getContentPane().add(map,
						new AnchorConstraint(42, 970, 767, 339,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				map.setPreferredSize(new java.awt.Dimension(440, 300));

			}
			{
				jButtonCreateNote = new JButton();
				getContentPane().add(
						jButtonCreateNote,
						new AnchorConstraint(830, 870, 912, 515,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jButtonCreateNote.setText("Create note");
				jButtonCreateNote.setPreferredSize(new java.awt.Dimension(278,
						30));
				jButtonCreateNote.addActionListener(this);
			}

			this.setSize(800, 400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initiateMainView() {
		for (Note note : ClientAdmin.dataModel.getNotes()) {
			System.out.println(note.getCoordinate().getLat() + " "
					+ note.getCoordinate().getLon());
			map.addMapMarker(new MapMarkerDot(note.getCoordinate().getLat(),
					note.getCoordinate().getLon()));
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jButtonItinerary) {
			if (Context.getState() == State.NORMAL) {
				currentItinerary = new Itinerary();
				Context.setState(State.CREATE_ITINERARY);
				jButtonItinerary.setText("Finish itinerary");
			} else if (Context.getState() == State.EDIT_NOTE) {
				System.out.println("On ne peut pas crér un itineraire lors de l'édition d'un point");
			} else {
				new CreateItineraryDialog(currentItinerary, this);
			}
		} else if (ae.getSource() == jButtonCreateNote) {
			if (Context.getState() == State.NORMAL) {
				Context.setState(State.CREATE_NOTE);
				jButtonCreateNote.setText("Put note on map");
			}

			else if (Context.getState() == State.EDIT_NOTE) {

				SCoordinate coor = new SCoordinate(Context
						.getCurrentMapMarker().getLat(), Context
						.getCurrentMapMarker().getLon());
				System.out.println(coor.toString());
				Note note = ClientAdmin.dataModel.getNote(coor);
				new CreateNoteDialog(note, this);
			}

			else if (Context.getState() == State.CREATE_NOTE) {
				jButtonCreateNote.setText("Create note");
				Context.setState(State.NORMAL);
			}
		} else if (ae.getSource() == jButtonRemove) {
			if (Context.getState() == State.EDIT_NOTE) {
				map.removeMapMarker(tmpMapmarker);
				SCoordinate coor = new SCoordinate(Context
						.getCurrentMapMarker().getLat(), Context
						.getCurrentMapMarker().getLon());
				ClientAdmin.dataModel.removeNote(coor);
				Context.setCurrentMapMarker(null);
				deselectNote();
				System.out.println("Note supprimée");
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point mousePoint = e.getPoint();

		if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {

			MapMarker mapMarker = getMapMarker(mousePoint);
			if (Context.getState() == State.NORMAL && mapMarker != null) {
				Context.setCurrentIndex(map.getMapMarkerList().indexOf(
						mapMarker));
				Context.setCurrentMapMarker(mapMarker);
				tmpMapmarker = new MapMarkerDot(Color.RED, mapMarker.getLat(),
						mapMarker.getLon());
				map.getMapMarkerList().set(Context.getCurrentIndex(),
						tmpMapmarker);
				System.out.println(mapMarker.toString() + " is clicked");

				SCoordinate coor = new SCoordinate(Context.getCurrentMapMarker().getLat(), Context.getCurrentMapMarker().getLon());
				currentNote = ClientAdmin.dataModel.getNote(coor);

				jButtonCreateNote.setText("Edit note");
				jButtonRemove.setVisible(true);


				jTextPaneInfo.setText("Note: "+currentNote.getId()+"\n"
						+currentNote.getCoordinate().toString()+"\n"
						+"Category: "+currentNote.getCategory()+"\n"
						+"Comments: "+currentNote.getComments()+"\n"
						+"Created: "+currentNote.getDateCreation());

				Context.setState(State.EDIT_NOTE);
			}

			else if (Context.getState() == State.EDIT_NOTE) {
				SCoordinate oldCoor = new SCoordinate(Context.getCurrentMapMarker().getLat(), Context.getCurrentMapMarker().getLon());
				SCoordinate newCoor = new SCoordinate(map.getPosition(mousePoint).getLat(),map.getPosition(mousePoint).getLon());
				MapMarker tmpMapmarker = new MapMarkerDot(newCoor.getLat(), newCoor.getLon());
				map.getMapMarkerList().set(Context.getCurrentIndex(), tmpMapmarker);

				Note note = ClientAdmin.dataModel.getNote(oldCoor);
				note.setCoordinate(newCoor);
				ClientAdmin.dataModel.updateNote(oldCoor, note);
				deselectNote();
			}

			else if (Context.getState() == State.CREATE_NOTE) {
				if (mapMarker == null) {
					SCoordinate coor = new SCoordinate(map.getPosition(
							mousePoint).getLat(), map.getPosition(mousePoint)
							.getLon());
					Context.setCurrentMapMarker(new MapMarkerDot(coor.getLat(),
							coor.getLon()));
					map.addMapMarker(Context.getCurrentMapMarker());
					new CreateNoteDialog(coor, 0, this);
				} else {
					System.out.println("Note existante à cet emplacement");
					Context.setState(State.NORMAL);
					jButtonCreateNote.setText("Create note");
				}
			}

			else if (Context.getState() == State.CREATE_ITINERARY) {
				if (mapMarker != null) {
					System.out.println("Note : "+mapMarker.toString());
					Note noteToAdd = ClientAdmin.dataModel.getNearestNodeFrom(
							mapMarker.getLat(), mapMarker.getLon());
					currentItinerary.appendNote(noteToAdd);
					updateMap();
				}
			}
			// System.out.println(Context.getState().toString());
			map.repaint();
		}
	}

	private MapMarker getMapMarker(Point mousePoint) {
		int X = mousePoint.x + 3;
		int Y = mousePoint.y + 3;

		List<MapMarker> ar = map.getMapMarkerList();
		Iterator<MapMarker> i = ar.iterator();

		double radCircle = 10;
		MapMarker mapMarker = null;
		while (i.hasNext() && radCircle >= 8) {

			mapMarker = (MapMarker) i.next();

			Point MarkerPosition = map.getMapPosition(mapMarker.getLat(),
					mapMarker.getLon());
			if (MarkerPosition != null) {

				int centerX = MarkerPosition.x;
				int centerY = MarkerPosition.y;

				// System.out.println(map.getPosition(p).getLat()+":"+map.getPosition(p).getLon());
				// calculate the radius from the touch to the center of the dot
				radCircle = Math
						.sqrt((((centerX - X) * (centerX - X)) + (centerY - Y)
								* (centerY - Y)));

			}
		}
		// if the radius is smaller then 23 (radius of a ball is 5), then it
		// must be on the dot
		if (radCircle < 8) {
			return mapMarker;
		}
		return null;
	}

	public void deselectNote(){
		jButtonCreateNote.setText("Create note");
		jButtonRemove.setVisible(false);
		jTextPaneInfo.setText("");
		Context.setState(State.NORMAL);
		updateMap();
	}

	public void cancelCreateNote() {
		if (Context.getCurrentMapMarker() != null) {
			map.removeMapMarker(Context.getCurrentMapMarker());
			Context.setCurrentMapMarker(null);
		}
		deselectNote();
	}

	public void cancelUpdate(){
		map.getMapMarkerList().set(Context.getCurrentIndex(), new MapMarkerDot(Context.getCurrentMapMarker().getLat(),
				Context.getCurrentMapMarker().getLon()));
		deselectNote();
	}

	public void updateNotefinished() {
		map.getMapMarkerList().set(Context.getCurrentIndex(), Context.getCurrentMapMarker());
		deselectNote();
		map.getMapMarkerList().set(Context.getCurrentIndex(),
				Context.getCurrentMapMarker());
		jButtonCreateNote.setText("Create note");
		jButtonRemove.setVisible(false);
		Context.setState(State.NORMAL);
		map.repaint();
	}

	public void createNotefinished() {
		jButtonCreateNote.setText("Create note");
		Context.setState(State.NORMAL);
	}

	public void createItineraryFinished() {
		ClientAdmin.dataModel.addItinerary(currentItinerary);
		System.out.println("Itinéraire ajouté !");
		updateListItineraries();
		currentItinerary = null;
		Context.setState(State.NORMAL);
		jButtonItinerary.setText("Create itinerary");
	}

	private void updateMap() {
		map.removeAllMapPolygons();

		if (currentItinerary != null){
			updateMapItinerary(currentItinerary);
		}
		List<Itinerary> itineraries = ClientAdmin.dataModel.getItineraries();
		for (Itinerary itinerary:itineraries){
			updateMapItinerary(itinerary);
		}
	}

	private void updateMapItinerary(Itinerary itinerary){
		Coordinate coord1 = null;
		Coordinate coord2 = null;

		for (Note note : itinerary.getNotes()) {
			coord1 = coord2;
			coord2 = new Coordinate(note.getCoordinate().getLat(), note.getCoordinate().getLon());

			if (coord1 != null) {
				List<Coordinate> route = new ArrayList<Coordinate>(
						Arrays.asList(coord1, coord2, coord2));
				map.addMapPolygon(new MapPolygonImpl(route));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void updateListItineraries() {
		List<Itinerary> itineraries = ClientAdmin.dataModel.getItineraries();
		ArrayList<String> itinerariesName = new ArrayList<String>();
		for (Itinerary itinerary : itineraries) {
			itinerariesName.add("Itinéraire "+itinerary.getTitle());
		}

		@SuppressWarnings({ "rawtypes" })
		ListModel jListItinerariesModel = new DefaultComboBoxModel(
				itinerariesName.toArray());
		jListItineraries.setModel(jListItinerariesModel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
		System.out.println(p.x + ":" + p.y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
