package org.salondeventas.desktop.view.cliente;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Principal extends Application {

	@FXML
	private static Scene scene;
	@FXML
	private BorderPane borderPane;
	@FXML
	private AnchorPane myPane;
	@FXML 
	private Button closeButton;
	@FXML
	private Pane panelConfiguracion;

	@FXML
	private Pane panelEmpresa;
	@FXML
	private Pane panelProducto;
	@FXML
	private Pane panelUsuario;
	@FXML
	private Pane panelVenta;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Principal");
		
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		primaryStage.setX(bounds.getMinX());
		primaryStage.setY(bounds.getMinY());
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());

		loadPage();			
		primaryStage.setScene(scene);
		primaryStage.resizableProperty().set(false);
		primaryStage.show();
		
		primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

            public void handle(WindowEvent event) {
            	System.exit(0);
            }
        });
	}

	@FXML
	private void handleItemConfiguracionAction(ActionEvent event) {
		borderPane = (BorderPane) scene.lookup("#borderPane");
		try {
			panelConfiguracion = (Pane) FXMLLoader.load(getClass().getResource(
					"PanelConfiguracion.fxml"));
		} catch (IOException e) {			
			e.printStackTrace();
		}
		borderPane.setCenter(panelConfiguracion);		
	}
	
	@FXML
	private void handleItemCerrarSessionAction(ActionEvent event) {		
	}	

	@FXML
	private void handleItemEmpresaAction(ActionEvent event) {
		borderPane = (BorderPane) scene.lookup("#borderPane");
		try {
			panelEmpresa = (Pane) FXMLLoader.load(getClass().getResource(
					"PanelEmpresa.fxml"));
		} catch (IOException e) {			
			e.printStackTrace();
		}
		borderPane.setCenter(panelEmpresa);		
	}

	@FXML
	private void handleItemProductoAction(ActionEvent event) {
		borderPane = (BorderPane) scene.lookup("#borderPane");
		try {
			panelProducto = (Pane) FXMLLoader.load(getClass().getResource(
					"PanelProducto.fxml"));
		} catch (IOException e) {			
			e.printStackTrace();
		}
		borderPane.setCenter(panelProducto);		
	}

	@FXML
	private void handleItemUsuarioAction(ActionEvent event) {
		borderPane = (BorderPane) scene.lookup("#borderPane");
		try {
			panelUsuario = (Pane) FXMLLoader.load(getClass().getResource(
					"PanelUsuario.fxml"));
		} catch (IOException e) {			
			e.printStackTrace();
		}
		borderPane.setCenter(panelUsuario);		
	}

	@FXML
	private void handleItemVentaAction(ActionEvent event) {
		borderPane = (BorderPane) scene.lookup("#borderPane");
		try {
			panelVenta = (Pane) FXMLLoader.load(getClass().getResource(
					"PanelVenta.fxml"));
		} catch (IOException e) {			
			e.printStackTrace();
		}
		borderPane.setCenter(panelVenta);		
	}


	@FXML
	private void closeButtonAction(){
		System.exit(0);
	}		
	
	@FXML
	private void handleItemSalirAction(ActionEvent event) {
		System.exit(0);
	}

	private void loadPage() throws IOException {
		this.myPane = (AnchorPane) FXMLLoader.load(getClass().getResource(
				"Principal.fxml"));				
		this.scene = new Scene(myPane);
	}

	public Pane getPanelConfiguracion() {
		return panelConfiguracion;
	}

	public void setPanelConfiguracion(Pane panelConfiguracion) {
		this.panelConfiguracion = panelConfiguracion;
	}

	public Pane getPanelEmpresa() {
		return panelEmpresa;
	}

	public void setPanelEmpresa(Pane panelEmpresa) {
		this.panelEmpresa = panelEmpresa;
	}
	public Pane getPanelProducto() {
		return panelProducto;
	}

	public void setPanelProducto(Pane panelProducto) {
		this.panelProducto = panelProducto;
	}
	public Pane getPanelUsuario() {
		return panelUsuario;
	}

	public void setPanelUsuario(Pane panelUsuario) {
		this.panelUsuario = panelUsuario;
	}
	public Pane getPanelVenta() {
		return panelVenta;
	}

	public void setPanelVenta(Pane panelVenta) {
		this.panelVenta = panelVenta;
	}
	
	

	public AnchorPane getMyPane() {
		return myPane;
	}

	public void setMyPane(AnchorPane myPane) {
		this.myPane = myPane;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public static void iniciar(){
		String[] args = {};
		launch(args);
	}

	public static void main(String[] args) {
		Principal.iniciar();
	}
}