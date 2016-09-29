package org.salondeventas.desktop.view.cliente;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PanelControlesEdit extends HBox {
	
	private ILoadPanel panelAbm;
	private IPanelControllerGrilla grilla;
	private Button btnGuardar;
	private Button btnCancelar;
	
	
	public PanelControlesEdit(IPanelControllerGrilla grilla, ILoadPanel panelAbm){
		this.panelAbm = panelAbm;
		this.grilla = grilla;
		initComponents();
	}
	
	private void initComponents(){
		 this.btnGuardar = new Button();
		this.btnCancelar = new Button();
		
		this.getChildren().addAll(btnGuardar, btnCancelar);

		Image imageDecline = new Image(getClass().getResourceAsStream("/image/save.png"));
		btnGuardar.setGraphic(new ImageView(imageDecline));
		btnGuardar.setTooltip(new Tooltip("Guardar"));
		btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	grilla.reLoad();
		    }
		});
		imageDecline = new Image(getClass().getResourceAsStream("/image/edit.png"));
		btnCancelar.setGraphic(new ImageView(imageDecline));
		btnCancelar.setTooltip(new Tooltip("Cancelar"));	
		btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	grilla.reLoad();
		    }
		});
	}
}
