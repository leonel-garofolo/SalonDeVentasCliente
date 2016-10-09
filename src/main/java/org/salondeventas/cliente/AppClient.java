package org.salondeventas.cliente;

import org.salondeventas.cliente.desktop.view.Principal;

public class AppClient extends Thread {

	public void run() {		
		Principal.iniciar();
	}
}