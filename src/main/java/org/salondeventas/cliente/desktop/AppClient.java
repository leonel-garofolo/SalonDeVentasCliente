package org.salondeventas.cliente.desktop;

import org.salondeventas.cliente.desktop.view.ControllLogin;

public class AppClient extends Thread {

	public void run() {		
		ControllLogin.iniciar();
	}
}