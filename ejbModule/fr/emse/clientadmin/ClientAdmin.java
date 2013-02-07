package fr.emse.clientadmin;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.emse.server.AdminBeanRemote;

/**
 * Classe qui contient la méthode main, et qui initialise les classes DataModel
 * ainsi que AdminBeanRemote
 * 
 * @author Antoine, Julien
 * 
 */
public class ClientAdmin {
	static public DataModel dataModel;
	static public AdminBeanRemote adminBeanRemote;

	/**
	 * Méthode main qui permet de lancer le serveur
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InitialContext ctx;
			ctx = new InitialContext();
			System.out.println("Recherche du bean...");
			// on charge le contexte
			ClientAdmin.adminBeanRemote = (AdminBeanRemote) ctx
					.lookup("java:global/GPS-acjn/AdminBean!fr.emse.server.AdminBeanRemote");

			// on instancie la classe datamodel
			dataModel = new DataModel();

			// on ouvre une fenêtre de l'interface graphique
			MainSwingApp mainSwingApp = new MainSwingApp();
			mainSwingApp.setVisible(true);
		} catch (NamingException e) {
			System.out.println("Connexion échouée");
		}
	}
}
