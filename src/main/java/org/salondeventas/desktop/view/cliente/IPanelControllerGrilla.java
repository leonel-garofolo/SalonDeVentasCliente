package org.salondeventas.desktop.view.cliente;

import javafx.scene.layout.BorderPane;

public interface IPanelControllerGrilla<T> {

	public BorderPane getPnlBorder();
	public void reLoad();
	public T getServicio();
}
