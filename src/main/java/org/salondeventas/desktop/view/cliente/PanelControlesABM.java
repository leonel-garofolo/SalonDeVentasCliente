package org.salondeventas.desktop.view.cliente;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PanelControlesABM extends HBox implements Initializable {
	
	private ILoadPanel panelAbm;
	private IPanelControllerGrilla grilla;
	private Button btnAgregar;
	private Button btnEditar;
	private Button btnEliminar;		
	private Button btnInforme;
	
	public PanelControlesABM(IPanelControllerGrilla grilla, ILoadPanel panelAbm){
		this.panelAbm = panelAbm;
		this.grilla = grilla;
		initComponents();
	}
	
	private void initComponents(){
		 this.btnAgregar = new Button();
		this.btnEditar = new Button();
		this.btnEliminar = new Button();
		this.btnInforme = new Button();
		this.getChildren().addAll(btnAgregar, btnEditar, btnEliminar, btnInforme);

		Image imageDecline = new Image(getClass().getResourceAsStream("/image/agregar.png"));
		btnAgregar.setGraphic(new ImageView(imageDecline));
		btnAgregar.setTooltip(new Tooltip("Agregar Elemento"));
		btnAgregar.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	try {
					grilla.getPnlBorder().setCenter((Parent)panelAbm.getFxmlLoader().load());
					PanelControlesEdit panel = new PanelControlesEdit(grilla, panelAbm);
					grilla.getPnlBorder().setTop(panel);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //;
		    }
		});
		imageDecline = new Image(getClass().getResourceAsStream("/image/edit.png"));
		btnEditar.setGraphic(new ImageView(imageDecline));
		btnEditar.setTooltip(new Tooltip("Editar Elemento"));

		imageDecline = new Image(getClass().getResourceAsStream("/image/rubbish-bin.png"));
		btnEliminar.setGraphic(new ImageView(imageDecline));
		btnEliminar.setTooltip(new Tooltip("Eliminar Elemento"));

		imageDecline = new Image(getClass().getResourceAsStream("/image/printer.png"));
		btnInforme.setGraphic(new ImageView(imageDecline));
		btnInforme.setTooltip(new Tooltip("Imprimir Grilla"));
	}
		
	@Override
	public void initialize(URL url, ResourceBundle res) {		
		
	}
	
	
	@FXML
	public void guardar(ActionEvent event) {		
	}
	
	@FXML
	public void cancelar(ActionEvent event) {
		
	}
}
