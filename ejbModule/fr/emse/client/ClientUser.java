package fr.emse.client;

import javax.naming.InitialContext;

import fr.emse.server.AdminBeanRemote;

public class ClientUser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InitialContext ctx = new InitialContext();
			AdminBeanRemote bean = (AdminBeanRemote) ctx.lookup("java:global/GPS-acjn/AdminEJB!fr.emse.server.AdminBeanRemote");
			System.out.println(bean.hello());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
