package fr.emse.clientadmin;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.emse.server.AdminBeanRemote;

public class ClientAdmin {
	static public DataModel dataModel;
	static public AdminBeanRemote adminBeanRemote;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InitialContext ctx;
			ctx = new InitialContext();
			System.out.println("Recherche du bean...");
			ClientAdmin.adminBeanRemote = (AdminBeanRemote) ctx
					.lookup("java:global/GPS-acjn/AdminBean!fr.emse.server.AdminBeanRemote");

			dataModel = new DataModel();

			MainSwingApp mainSwingApp = new MainSwingApp();
			mainSwingApp.setVisible(true);
		} catch (NamingException e) {
			System.out.println("Connexion échouée");
		}
	}
}
