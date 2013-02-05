package fr.emse.clientadmin;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import fr.emse.server.Itinerary;

/**
 * Classe qui affiche une fenêtre et demande les paramètres à entrer pour la création d'un itinéraire
 * @author Antoine, Julien
 */
public class CreateItineraryDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2424945033857892960L;
	//bouton pour valider l'itinéraire
	private JButton jButtonFinishItinerary;
	//texte 'commentaires' à côté de la zone d'insertion de commentaires
	private JLabel jLabelComments;
	//texte 'titre' à côté de la zone de texte du champ titre
	private JLabel jLabelTitre;
	//zone de texte à remplir pour le titre
	private JTextField jTextFieldTitle;
	//zone de texte à remplir pour les commentaires
	private JTextArea jTextAreaComments;
	
	//itinéaire qui sera modifié selon les informations passées par l'utilisateur
	Itinerary itinerary;
	//fenêtre principale de l'interface graphique
	private MainSwingApp mainFrame;

	/**
	 * Auto-generated main method to display this JFrame
	 */

	public CreateItineraryDialog(Itinerary itinerary, MainSwingApp mainFrame) {
		super(mainFrame, Dialog.ModalityType.APPLICATION_MODAL);
		this.itinerary = itinerary;
		this.mainFrame = mainFrame;
		initGUI();

		setVisible(true);
		setModal(true);
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
				jLabelTitre = new JLabel();
				getContentPane().add(jLabelTitre, new AnchorConstraint(104, 258, 237, 34, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabelTitre.setText("Titre");
				jLabelTitre.setPreferredSize(new java.awt.Dimension(82, 16));
			}
			{
				jTextAreaComments = new JTextArea();
				getContentPane().add(jTextAreaComments, new AnchorConstraint(329, 946, 587, 363, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextAreaComments.setPreferredSize(new java.awt.Dimension(213, 31));
			}
			{
				jLabelComments = new JLabel();
				getContentPane().add(jLabelComments, new AnchorConstraint(329, 330, 462, 34, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabelComments.setText("Commentaires");
				jLabelComments.setPreferredSize(new java.awt.Dimension(108, 16));
			}
			{
				jButtonFinishItinerary = new JButton();
				getContentPane().add(
						jButtonFinishItinerary,
						new AnchorConstraint(685, 598, 859, 331,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jButtonFinishItinerary.setText("Terminer");
				jButtonFinishItinerary.setPreferredSize(new java.awt.Dimension(
						99, 23));
				jButtonFinishItinerary.addActionListener(this);
			}
			pack();
			this.setSize(381, 158);

		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	/**
	 * méthode qui gère les actions sur le 'Finish itinéraire'
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == this.jButtonFinishItinerary) {
			//on enregistre le nom de l'itinéraire dans l'attribut title
			itinerary.setTitle(this.jTextFieldTitle.getText());
			//on enregistre les commentaires
			itinerary.setComments(this.jTextAreaComments.getText());
			//appelle la méthode de MainSwingApp qui s'occupe de repasser en mode d'affichage normal
			mainFrame.createItineraryFinished();
			this.dispose();
		}
	}

}
