package fr.emse.clientuser;

import javax.naming.InitialContext;

import fr.emse.beans.AdminBeanRemote;
import fr.emse.objects.Coordinate;
import fr.emse.objects.Note;

public class ClientUser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InitialContext ctx = new InitialContext();
			System.out.println("Recherche du bean...");
			AdminBeanRemote bean = (AdminBeanRemote) ctx.lookup("java:global/GPS-acjn/AdminEJB!fr.emse.server.AdminBeanRemote");
			
			Note note = new Note(1, new Coordinate(10,20), 522, "Sommet de la montagne", "Nature");
			System.out.println("Envoi...");
			bean.addNote(note);
			System.out.println("Lecture...");
			System.out.println(bean.getNote(0).getComments());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
