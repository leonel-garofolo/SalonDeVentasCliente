package org.salondeventas.cliente.desktop.view;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

public class PanelVentaTest extends ApplicationTest{

	@Test
	public void testPanel(){
		rightClickOn("#btnAgregar");
		/*
		Tab tab = new Tab();
		PanelGrillaVenta panelGrilla = new PanelGrillaVenta(tab);
		PanelVenta panel = new PanelVenta(panelGrilla);
		*/
	}	


	@Override
	public void start(Stage stage) throws Exception {
		Tab tab = new Tab();
		PanelGrillaVenta panelGrilla = new PanelGrillaVenta(tab);
		Scene scene = new Scene(panelGrilla, 800, 600);
        stage.setScene(scene);
        stage.show();
		
	}
}
