package fr.emse.clientuser;

import javax.naming.InitialContext;

import fr.emse.server.AdminBeanRemote;
import fr.emse.server.Note;
import fr.emse.server.SCoordinate;

public class ClientUser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InitialContext ctx = new InitialContext();
			System.out.println("Recherche du bean...");
			AdminBeanRemote bean = (AdminBeanRemote) ctx.lookup("java:global/GPS-acjn/AdminEJB!fr.emse.server.AdminBeanRemote");
			
			Note note = new Note(new SCoordinate(10,20), 522, "Sommet de la montagne", "Nature");
			Note note2 = new Note(new SCoordinate(30,40), 54, "Bas de la montagne", "Nature");
			System.out.println("Envoi...");
			bean.addNote(note);
			bean.addNote(note2);
			System.out.println("Lecture...");
			System.out.println(bean.getNote(new SCoordinate(10,20)).getComments());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
