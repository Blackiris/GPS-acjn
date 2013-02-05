package fr.emse.clientadmin;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

/**
 * Classe qui se charge d'ouvrir le panneau pour l'identification de l'administrateur
 * @author Julien
 */
public class SignInDialog extends javax.swing.JDialog implements ActionListener {
	private JLabel jLabelPassword;
	private JTextField jTextFieldPassword;
	private JButton jButtonSingIn;

	/**
	 * Auto-generated main method to display this JDialog
	 */

	public SignInDialog(JFrame frame) {
		super(frame, Dialog.ModalityType.APPLICATION_MODAL);
		initGUI();

		setVisible(true);
		setModal(true);
	}

	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			this.setTitle("Connexion Admin");
			{
				jButtonSingIn = new JButton();
				getContentPane().add(
						jButtonSingIn,
						new AnchorConstraint(619, 635, 770, 372,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jButtonSingIn.setText("Se connecter");
				jButtonSingIn.setPreferredSize(new java.awt.Dimension(98, 22));
				jButtonSingIn.addActionListener(this);
			}
			{
				jTextFieldPassword = new JTextField();
				getContentPane().add(
						jTextFieldPassword,
						new AnchorConstraint(270, 560, 421, 33,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jTextFieldPassword.setPreferredSize(new java.awt.Dimension(196,
						22));
			}
			{
				jLabelPassword = new JLabel();
				getContentPane().add(
						jLabelPassword,
						new AnchorConstraint(85, 420, 188, 33,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				jLabelPassword.setText("Mot de passe");
				jLabelPassword
						.setPreferredSize(new java.awt.Dimension(144, 15));
			}
			this.setSize(382, 176);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// String pass = jTextFieldPassword.getText();
		// if (!ClientAdmin.adminBeanRemote.signIn(pass)) {
		// jTextFieldPassword.setText("");
		// } else {
		// System.out.println("Bienvenue maître");
		// this.dispose();
		// }
	}

}
