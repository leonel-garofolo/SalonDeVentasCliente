package org.salondeventas.cliente.desktop;

import org.salondeventas.cliente.desktop.utils.MiPrinterJob;

public class App {
	private void iniciar(){
		AppClient client = new AppClient();
		client.start();
		
		MiPrinterJob.preparedPrinter();
	}
	
	public static void main(String[] args) throws Exception {
		App app = new App();
		app.iniciar();		
	}
}
