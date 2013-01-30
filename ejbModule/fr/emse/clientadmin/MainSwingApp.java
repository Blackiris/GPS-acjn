package fr.emse.clientadmin;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import fr.emse.server.AdminBeanRemote;
import fr.emse.server.Itinerary;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;

import org.openstreetmap.gui.jmapviewer.JMapViewer;


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
public class MainSwingApp extends javax.swing.JFrame implements ActionListener {
	private JButton jButtonCreateNote;
	private JButton jButtonItinery;
	private JList jListItineraries;
	
	AdminBeanRemote bean;
	private JLabel jLabel1;

	/**
	* Auto-generated main method to display this JFrame
	*/
		
	public MainSwingApp() {
		super();
		
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			System.out.println("Recherche du bean...");
			bean = (AdminBeanRemote) ctx.lookup("java:global/GPS-acjn/AdminEJB!fr.emse.server.AdminBeanRemote");
			
			initGUI();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new AnchorConstraint(317, 225, 358, 49, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Liste des itinéraires");
			}
			{
				
				List<Itinerary> itineraries = bean.getItineraries();
				ArrayList<String> itinerariesName = new ArrayList<String>();
				for (Itinerary itinerary : itineraries) {
					itinerariesName.add("Itinéraire "+itinerary.getId());
				}
				
				ListModel jListItinerariesModel = 
						new DefaultComboBoxModel(
								itinerariesName.toArray());
				
				jListItineraries = new JList();
				getContentPane().add(jListItineraries, new AnchorConstraint(388, 215, 767, 49, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jListItineraries.setModel(jListItinerariesModel);
				jListItineraries.setPreferredSize(new java.awt.Dimension(116, 138));
			}
			{
				jButtonItinery = new JButton();
				getContentPane().add(jButtonItinery, new AnchorConstraint(831, 489, 913, 49, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButtonItinery.setText("Créer un itinéraire");
				jButtonItinery.setPreferredSize(new java.awt.Dimension(308, 30));
			}
			{
				JMapViewer map = new JMapViewer();
				getContentPane().add(map, new AnchorConstraint(42, 970, 767, 339, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				map.setPreferredSize(new java.awt.Dimension(441, 264));
			}
			{
				jButtonCreateNote = new JButton();
				getContentPane().add(jButtonCreateNote, new AnchorConstraint(831, 899, 913, 544, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButtonCreateNote.setText("Créer une note");
				jButtonCreateNote.setPreferredSize(new java.awt.Dimension(248, 30));
				jButtonCreateNote.addActionListener(this);
			}
			
			this.setSize(709, 394);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new CreateNoteJFrame();
	}

}
