package org.salondeventas.cliente.desktop;

import org.salondeventas.cliente.desktop.view.Principal;

public class AppClient extends Thread {

	public void run() {		
		Principal.iniciar();
	}
}