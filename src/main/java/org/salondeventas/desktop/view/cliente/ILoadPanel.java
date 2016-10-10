package org.salondeventas.desktop.view.cliente;

import javafx.fxml.FXMLLoader;

public interface ILoadPanel<T> {
	 public FXMLLoader getFxmlLoader();
	 public T getObjectModel();
}