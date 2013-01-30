package fr.emse.clientadmin;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


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
public class CreateNoteJFrame extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabelComments;
	private JTextArea jTextAreaComments;
	private JTextField jTextFieldCategory;
	private JLabel jLabel2;
	private JButton jButtonCreateNote;

	/**
	* Auto-generated main method to display this JFrame
	*/
		
	public CreateNoteJFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jTextAreaComments = new JTextArea();
				getContentPane().add(jTextAreaComments, new AnchorConstraint(494, 865, 650, 298, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextAreaComments.setPreferredSize(new java.awt.Dimension(218, 41));
			}
			{
				jTextFieldCategory = new JTextField();
				getContentPane().add(jTextFieldCategory, new AnchorConstraint(675, 865, 757, 298, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextFieldCategory.setPreferredSize(new java.awt.Dimension(221, 22));
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2, new AnchorConstraint(687, 206, 742, 50, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel2.setText("Catégorie");
			}
			{
				jButtonCreateNote = new JButton();
				getContentPane().add(jButtonCreateNote, new AnchorConstraint(820, 529, 901, 406, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButtonCreateNote.setText("Créer");
				jButtonCreateNote.setPreferredSize(new java.awt.Dimension(48, 22));
			}
			{
				jLabelComments = new JLabel();
				getContentPane().add(jLabelComments, new AnchorConstraint(505, 280, 561, 50, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabelComments.setText("Commentaires");
				jLabelComments.setPreferredSize(new java.awt.Dimension(90, 15));
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new AnchorConstraint(64, 970, 146, 50, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Créer une nouvelle note");
				jLabel1.setPreferredSize(new java.awt.Dimension(359, 22));
				jLabel1.setFont(new java.awt.Font("FreeSans",0,16));
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
