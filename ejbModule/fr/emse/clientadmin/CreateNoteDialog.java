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
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class CreateNoteDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jLabel1;
	private JLabel jLabelComments;
	private JButton jButtonCancel;
	private JTextField jTextFieldHeight;
	private JLabel jLabelHeight;
	private JTextField jTextFieldCoordinate2;
	private JLabel jLabelCoordinate2;
	private JTextField jTextFieldCoordinate1;
	private JLabel jLabelCoordinate1;
	private JTextArea jTextAreaComments;
	private JTextField jTextFieldCategory;
	private JLabel jLabel2;
	private JButton jButtonCreateNote;

	private MainSwingApp mainFrame;

	/**
	 * Auto-generated main method to display this JFrame
	 */

	public CreateNoteDialog(SCoordinate coor, int height, MainSwingApp mainFrame) {
		super(mainFrame, Dialog.ModalityType.APPLICATION_MODAL);
		System.out.println(coor.getLat());
		initGUI();
		jTextFieldCoordinate1.setText(String.valueOf(coor.getLat()));
		jTextFieldCoordinate2.setText(String.valueOf(coor.getLon()));
		jTextFieldHeight.setText(String.valueOf(height));
		Context.setState(State.CREATE_NOTE);
		this.mainFrame = mainFrame;

		setVisible(true);
		setModal(true);
	}

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
		if (ae.getSource() == jButtonCreateNote) {
			try {

				double latitude = Double.parseDouble(jTextFieldCoordinate1
						.getText());
				double longitude = Double.parseDouble(jTextFieldCoordinate2
						.getText());
				int height = Integer.parseInt(jTextFieldHeight.getText());
				String comment = jTextAreaComments.getText();
				String category = jTextFieldCategory.getText();

				Note note = new Note(new SCoordinate(latitude, longitude),
						height, comment, category);
				System.out.println("Note : " + note.getCoordinate().getLat()
						+ " " + note.getCoordinate().getLon());

				System.out.println(Context.getState().toString());
				if (Context.getState() == State.CREATE_NOTE) {
					ClientAdmin.dataModel.addNote(note);
					System.out.println("Note ajoutée !");
					mainFrame.createNotefinished();
				}

				if (Context.getState() == State.EDIT_NOTE) {
					SCoordinate coor = new SCoordinate(Context
							.getCurrentMapMarker().getLat(), Context
							.getCurrentMapMarker().getLon());
					ClientAdmin.dataModel.updateNote(coor, note);
					Context.setCurrentMapMarker(new MapMarkerDot(note
							.getCoordinate().getLat(), note.getCoordinate()
							.getLon()));
					System.out.println("Note mise à jour");
					mainFrame.updateNotefinished();
				}

				this.dispose();
			} catch (NumberFormatException e2) {
				e2.printStackTrace();
			} catch (NullPointerException e3) {
				e3.printStackTrace();
			}
		} else if (ae.getSource() == jButtonCancel) {
			if (Context.getState() == State.CREATE_NOTE)
				mainFrame.cancelCreateNote();
			if (Context.getState() == State.EDIT_NOTE) {
				mainFrame.updateNotefinished();
			}

			this.dispose();
		}
	}

}