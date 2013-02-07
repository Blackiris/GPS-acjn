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

import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import fr.emse.server.Note;
import fr.emse.server.SCoordinate;

/**
 * Classe qui se charge de générer la fenêtre pour entrer les paramètres de
 * création ou d'édition d'une note
 * 
 * @author Antoine, Julien
 */
public class CreateNoteDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// label 'Créer une nouvelle note'
	private JLabel jLabel1;
	// label 'commentaires'
	private JLabel jLabelComments;
	// bouton annuler la création d'une note
	private JButton jButtonCancel;
	// bouton pour entrer la hauteur du point
	private JTextField jTextFieldHeight;
	// label 'hauteur'
	private JLabel jLabelHeight;
	// champ pour entrer la longitude des coordonnées de la note
	private JTextField jTextFieldCoordinate2;
	// label 'longitude'
	private JLabel jLabelCoordinate2;
	// /champ pour entrer la latitude des coordonnées de la note
	private JTextField jTextFieldCoordinate1;
	// label 'latitude'
	private JLabel jLabelCoordinate1;
	// champ pour entre les commentaires
	private JTextArea jTextAreaComments;
	// champ pour entrer la catégorie
	private JTextField jTextFieldCategory;
	// label 'catégorie'
	private JLabel jLabel2;
	private JButton jButtonCreateNote;

	private MainSwingApp mainFrame;

	/**
	 * Auto-generated main method to display this JFrame
	 */

	/**
	 * Constructeur qui affiche la fenêtre avec les coordonnées et la hauteur
	 * déjà initialisées
	 * 
	 * @param coor
	 * @param height
	 * @param mainFrame
	 */
	public CreateNoteDialog(SCoordinate coor, int height, MainSwingApp mainFrame) {
		super(mainFrame, Dialog.ModalityType.APPLICATION_MODAL);
		initGUI();
		jTextFieldCoordinate1.setText(String.valueOf(coor.getLat()));
		jTextFieldCoordinate2.setText(String.valueOf(coor.getLon()));
		jTextFieldHeight.setText(String.valueOf(height));
		Context.setState(State.CREATE_NOTE);
		this.mainFrame = mainFrame;

		setVisible(true);
		setModal(true);
	}

	/**
	 * Constructeur qui affiche la fenêtre de création de la note avec tous les
	 * champs potentiellement remplis utilisé lors de l'édition d'une note
	 * 
	 * @param note
	 * @param mainFrame
	 */
	public CreateNoteDialog(Note note, MainSwingApp mainFrame) {
		super(mainFrame, Dialog.ModalityType.APPLICATION_MODAL);
		initGUI();
		jTextFieldCoordinate1.setText(String.valueOf(note.getCoordinate()
				.getLat()));
		jTextFieldCoordinate2.setText(String.valueOf(note.getCoordinate()
				.getLon()));
		jTextFieldCategory.setText(note.getCategory());
		jTextAreaComments.setText(note.getComments());
		jTextFieldHeight.setText(String.valueOf(note.getHeight()));
		jButtonCreateNote.setText("Éditer");
		Context.setState(State.EDIT_NOTE);
		this.mainFrame = mainFrame;

		setVisible(true);
		setModal(true);
	}

	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Nouvelle note");
			{
				jTextFieldHeight = new JTextField();
				getContentPane().add(
						jTextFieldHeight,
						new AnchorConstraint(446, 480, 527, 234,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jTextFieldHeight
						.setPreferredSize(new java.awt.Dimension(96, 22));
			}
			{
				jLabelHeight = new JLabel();
				getContentPane().add(
						jLabelHeight,
						new AnchorConstraint(457, 203, 520, 57,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jLabelHeight.setText("Altitude");
				jLabelHeight.setFont(new java.awt.Font("Andale Mono", 0, 10));
				jLabelHeight.setPreferredSize(new java.awt.Dimension(57, 17));
			}
			{
				jLabelCoordinate2 = new JLabel();
				getContentPane().add(
						jLabelCoordinate2,
						new AnchorConstraint(280, 657, 341, 516,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jLabelCoordinate2.setText("Longitude");
				jLabelCoordinate2.setPreferredSize(new java.awt.Dimension(54,
						16));
				jLabelCoordinate2.setFont(new java.awt.Font("Andale Mono", 0,
						10));
			}
			{
				jTextFieldCoordinate1 = new JTextField();
				getContentPane().add(
						jTextFieldCoordinate1,
						new AnchorConstraint(268, 485, 353, 234,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jTextFieldCoordinate1.setPreferredSize(new java.awt.Dimension(
						98, 23));
			}
			{
				jLabelCoordinate1 = new JLabel();
				getContentPane().add(
						jLabelCoordinate1,
						new AnchorConstraint(290, 203, 353, 55,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jLabelCoordinate1.setText("Latitude");
				jLabelCoordinate1.setPreferredSize(new java.awt.Dimension(58,
						17));
				jLabelCoordinate1.setFont(new java.awt.Font("Andale Mono", 0,
						10));
			}
			{
				jTextAreaComments = new JTextArea();
				getContentPane().add(
						jTextAreaComments,
						new AnchorConstraint(566, 865, 723, 298,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jTextAreaComments.setPreferredSize(new java.awt.Dimension(218,
						41));
			}
			{
				jTextFieldCategory = new JTextField();
				getContentPane().add(
						jTextFieldCategory,
						new AnchorConstraint(746, 865, 830, 298,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jTextFieldCategory.setPreferredSize(new java.awt.Dimension(218,
						22));
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(
						jLabel2,
						new AnchorConstraint(746, 267, 801, 50,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jLabel2.setText("Catégorie");
				jLabel2.setPreferredSize(new java.awt.Dimension(85, 15));
				jLabel2.setFont(new java.awt.Font("Andale Mono", 0, 11));
			}
			{
				jButtonCreateNote = new JButton();
				getContentPane().add(
						jButtonCreateNote,
						new AnchorConstraint(879, 775, 961, 547,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jButtonCreateNote.setText("Créer");
				jButtonCreateNote.setPreferredSize(new java.awt.Dimension(89,
						22));
				jButtonCreateNote.setFont(new java.awt.Font("Andale Mono", 0,
						11));
				jButtonCreateNote.addActionListener(this);
			}
			{
				jLabelComments = new JLabel();
				getContentPane().add(
						jLabelComments,
						new AnchorConstraint(616, 279, 669, 50,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jLabelComments.setText("Commentaires");
				jLabelComments.setPreferredSize(new java.awt.Dimension(88, 14));
				jLabelComments.setFont(new java.awt.Font("Andale Mono", 0, 11));
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(
						jLabel1,
						new AnchorConstraint(64, 970, 146, 50,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("Créer une nouvelle note");
				jLabel1.setPreferredSize(new java.awt.Dimension(359, 22));
				jLabel1.setFont(new java.awt.Font("FreeSans", 0, 16));
			}
			{
				jTextFieldCoordinate2 = new JTextField();
				getContentPane().add(
						jTextFieldCoordinate2,
						new AnchorConstraint(269, 928, 353, 675,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jTextFieldCoordinate2.setPreferredSize(new java.awt.Dimension(
						97, 22));
			}
			{
				jButtonCancel = new JButton();
				getContentPane().add(
						jButtonCancel,
						new AnchorConstraint(875, 424, 957, 185,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jButtonCancel.setText("Annuler");
				jButtonCancel.setFont(new java.awt.Font("Andale Mono", 0, 11));
				jButtonCancel.setPreferredSize(new java.awt.Dimension(93, 22));
				jButtonCancel.addActionListener(this);
			}
			pack();
			setSize(400, 300);

		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// si on clique sur le bouton 'Create Note'
		if (ae.getSource() == jButtonCreateNote) {
			try {
				// on récupère toutes les informations contenues dans les divers
				// champs
				double latitude = Double.parseDouble(jTextFieldCoordinate1
						.getText());
				double longitude = Double.parseDouble(jTextFieldCoordinate2
						.getText());
				int height = Integer.parseInt(jTextFieldHeight.getText());
				String comment = jTextAreaComments.getText();
				String category = jTextFieldCategory.getText();

				// on créé un objet de type note avec ces informations
				Note note = new Note(new SCoordinate(latitude, longitude),
						height, comment, category);
				System.out.println("Note : " + note.getCoordinate().getLat()
						+ " " + note.getCoordinate().getLon());

				System.out.println(Context.getState().toString());
				// si on est dans le contexte de création d'une note
				if (Context.getState() == State.CREATE_NOTE) {
					// on appelle la méthode de la classe DataModel qui est en
					// charge de la création de la note
					ClientAdmin.dataModel.addNote(note);
					System.out.println("Note ajoutée !");
					// on appelle la méthode de MainSwingApp qui s'occupe du
					// retour au contexte normal
					mainFrame.createNotefinished();
				}

				// si on est dans le cadre de l'édition d'une note
				if (Context.getState() == State.EDIT_NOTE) {
					// on créé un objet de type coordinate qui contient les
					// coordonnées de la note à modifier chargées depuis le
					// contexte
					SCoordinate coor = new SCoordinate(Context
							.getCurrentMapMarker().getLat(), Context
							.getCurrentMapMarker().getLon());
					// on appelle la méthode de la classe DataModel qui est en
					// charge de la création de la note
					ClientAdmin.dataModel.updateNote(coor, note);
					// on actualise le contexte en mettant à jour la note
					// courante
					Context.setCurrentMapMarker(new MapMarkerDot(note
							.getCoordinate().getLat(), note.getCoordinate()
							.getLon()));
					System.out.println("Note mise à jour");
					// on appelle la méthode de MainSwingApp qui se charge du
					// retour au contexte normal
					mainFrame.updateNotefinished();
				}

				this.dispose();
			} catch (NumberFormatException e2) {
				e2.printStackTrace();
			} catch (NullPointerException e3) {
				e3.printStackTrace();
			}
			// si on appuie sur le bouton 'Annuler'
		} else if (ae.getSource() == jButtonCancel) {
			if (Context.getState() == State.CREATE_NOTE)
				// on repasse en mode normal
				mainFrame.cancelCreateNote();
			if (Context.getState() == State.EDIT_NOTE) {
				// on quitte le mode update
				mainFrame.updateNotefinished();
			}

			this.dispose();
		}
	}

}