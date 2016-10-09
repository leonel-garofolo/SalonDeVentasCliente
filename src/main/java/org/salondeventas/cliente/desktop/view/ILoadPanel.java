package org.salondeventas.cliente.desktop.view;

import javafx.fxml.FXMLLoader;

public interface ILoadPanel<T> {
	 public FXMLLoader getFxmlLoader();
	 public T getObjectModel();
}