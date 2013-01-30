package fr.emse.clientadmin;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import java.awt.BorderLayout;

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
public class MainSwingApp extends javax.swing.JFrame {
	private JButton jButtonCreateNote;
	private JButton jButtonItinery;

	/**
	* Auto-generated main method to display this JFrame
	*/
		
	public MainSwingApp() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			{
				jButtonItinery = new JButton();
				getContentPane().add(jButtonItinery, new AnchorConstraint(161, 473, 243, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButtonItinery.setText("Créer un itinéraire");
				jButtonItinery.setPreferredSize(new java.awt.Dimension(269, 24));
			}
			{
				jButtonCreateNote = new JButton();
				getContentPane().add(jButtonCreateNote, new AnchorConstraint(42, 473, 124, 32, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButtonCreateNote.setText("Créer une note");
				jButtonCreateNote.setPreferredSize(new java.awt.Dimension(269, 24));
			}
			{
				JMapViewer jMapViewer = new JMapViewer();
			}
			
			this.setSize(619, 324);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
