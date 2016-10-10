package org.salondeventas.desktop.view;

import java.io.IOException;

import org.salondeventas.desktop.view.cliente.PanelGrillaUsuario;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Principal extends Application {
	
	
	@FXML
	private TabPane tabPane;

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
	private Pane panelClientes;
	
	@FXML
	private Pane panelUsuarios;

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
		Tab tab = new Tab("Configuracion",panelConfiguracion);
		tabPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
		tabPane.getTabs().add(tab);			
	}
	
	@FXML
	private void handleItemCerrarSessionAction(ActionEvent event) {		
	}	

	@FXML
	private void handleItemClientesAction(ActionEvent event) {
		borderPane = (BorderPane) scene.lookup("#borderPane");
		try {
			panelClientes = (Pane) FXMLLoader.load(getClass().getResource(
					"PanelClientes.fxml"));
		} catch (IOException e) {			
			e.printStackTrace();
		}
		Tab tab = new Tab("Clientes",panelClientes);
		tabPane.getTabs().add(tab);		
	}
	
	@FXML
	private void handleItemUsuariosAction(ActionEvent event) {
		borderPane = (BorderPane) scene.lookup("#borderPane");
		Tab tab = new Tab("Usuarios");
		PanelGrillaUsuario grilla = new PanelGrillaUsuario(tab);
		tab.setContent(grilla);
		tabPane.getTabs().add(tab);			
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

	public Pane getPanelClientes() {
		return panelClientes;
	}

	public void setPanelClientes(Pane panelClientes) {
		this.panelClientes = panelClientes;
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