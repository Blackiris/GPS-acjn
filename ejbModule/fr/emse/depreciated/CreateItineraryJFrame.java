package fr.emse.depreciated;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import fr.emse.clientadmin.MainSwingApp;
import fr.emse.server.Itinerary;


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
public class CreateItineraryJFrame extends javax.swing.JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2424945033857892960L;
	private JButton jButtonFinishItinerary;
	private JLabel jLabelComments;
	private JTextField jTextFieldTitle;
	private JLabel jLabelTitle;
	private JTextArea jTextAreaComments;

	Itinerary itinerary;
	private MainSwingApp mainFrame;
	
	/**
	* Auto-generated main method to display this JFrame
	*/
		
	public CreateItineraryJFrame(Itinerary itinerary, MainSwingApp mainFrame) {
		super();
		this.itinerary = itinerary;
		this.mainFrame = mainFrame;
		initGUI();
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Nouvel itineraire");
			{
				jTextFieldTitle = new JTextField();
				getContentPane().add(jTextFieldTitle, new AnchorConstraint(79, 946, 270, 363, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextFieldTitle.setPreferredSize(new java.awt.Dimension(213, 23));
			}
			{
				jLabelTitle = new JLabel();
				getContentPane().add(jLabelTitle, new AnchorConstraint(104, 97, 237, 34, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabelTitle.setText("Title");
			}
			{
				jTextAreaComments = new JTextArea();
				getContentPane().add(jTextAreaComments, new AnchorConstraint(370, 946, 612, 363, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextAreaComments.setPreferredSize(new java.awt.Dimension(213, 29));
			}
			{
				jLabelComments = new JLabel();
				getContentPane().add(jLabelComments, new AnchorConstraint(370, 330, 504, 34, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabelComments.setText("Commentaires");
				jLabelComments.setPreferredSize(new java.awt.Dimension(108, 16));
			}
			{
				jButtonFinishItinerary = new JButton();
				getContentPane().add(jButtonFinishItinerary, new AnchorConstraint(737, 617, 912, 349, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButtonFinishItinerary.setText("Create");
				jButtonFinishItinerary.setPreferredSize(new java.awt.Dimension(98, 21));
				jButtonFinishItinerary.addActionListener(this);
			}
			pack();
			this.setSize(381, 158);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == this.jButtonFinishItinerary) {
			itinerary.setTitle(jTextFieldTitle.getText());
			itinerary.setComments(this.jTextAreaComments.getText());
			mainFrame.createItineraryFinished();
			this.dispose();
		}
	}

}
