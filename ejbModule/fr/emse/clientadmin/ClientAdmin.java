package fr.emse.clientadmin;

import javax.naming.NamingException;

public class ClientAdmin {
	static public DataModel dataModel;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			dataModel = new DataModel();
			
			MainSwingApp mainSwingApp = new MainSwingApp();
			mainSwingApp.setVisible(true);
		} catch (NamingException e) {
			System.out.println("Connexion échouée");
		}
	}
}
