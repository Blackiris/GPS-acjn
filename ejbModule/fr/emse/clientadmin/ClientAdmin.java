package fr.emse.clientadmin;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.emse.server.AdminBeanRemote;

public class ClientAdmin {

	static public AdminBeanRemote adminBeanRemote;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			System.out.println("Recherche du bean...");
			adminBeanRemote = (AdminBeanRemote) ctx.lookup("java:global/GPS-acjn/AdminEJB!fr.emse.server.AdminBeanRemote");
			
			MainSwingApp mainSwingApp = new MainSwingApp();
			mainSwingApp.setVisible(true);
		} catch (NamingException e) {
			System.out.println("Connexion échouée");
		}
	}
}
