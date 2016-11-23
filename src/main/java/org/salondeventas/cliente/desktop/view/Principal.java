package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;		


public class Principal extends AnchorPane {
	private ResourceBundle resource;
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private MenuBar menuBar;
	
	@FXML
	private static Scene scene;
	@FXML
	private BorderPane borderPane;
	@FXML
	private AnchorPane anchorPane;
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
	
	private ControllLogin controllLogin;
	
	public Principal() {		
		initComponentes();
    }

	public Principal(ControllLogin controllLogin) {
		this.controllLogin = controllLogin;
		initComponentes();
	}

	private void initComponentes(){	
		this.resource = ResourceBundle.getBundle("i18n.ValidationMessages");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.getClass().getSimpleName() + ".fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.setResources(resource);		
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }         
        
        Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();	
		this.setWidth(bounds.getWidth());
		this.setHeight(bounds.getHeight());
		
		HBox hButtons = new HBox();
		hButtons.setPadding(new Insets(15, 15, 0, 15));
		
		Image imageVenta = new Image(getClass().getResourceAsStream("/image/anadir-al-carrito.png"));		
		Button btnVenta = new Button();		
		btnVenta.setGraphic(new ImageView(imageVenta));	
		btnVenta.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				handleItemVentaAction(event);				
			}
		});
		hButtons.getChildren().addAll(btnVenta);
		
		Tab homeTab = new Tab();
		homeTab.setContent(hButtons);
		homeTab.closableProperty().set(false);
		Image imageHome = new Image(getClass().getResourceAsStream("/image/casa.png"));
		homeTab.setGraphic(new ImageView(imageHome));
		tabPane.getTabs().add(homeTab);				
	}

	@FXML
	private void handleItemConfiguracionAction(ActionEvent event) {
		try {
			panelConfiguracion = (Pane) FXMLLoader.load(getClass().getResource(
					"PanelConfiguracion.fxml"));
		} catch (IOException e) {			
			e.printStackTrace();
		}
		borderPane.setCenter(panelConfiguracion);		
	}
	
	@FXML
	private void handleItemCerrarSessionAction(ActionEvent event) throws IOException {
		this.setVisible(false);
		controllLogin.showLogin();				
	}	

	@FXML
	private void handleItemEmpresaAction(ActionEvent event) {		
		Tab tab = new Tab(resource.getString("pantalla.name.empresa"));
		PanelGrillaEmpresa grilla = new PanelGrillaEmpresa(tab);
		tab.setContent(grilla);
		tabPane.getTabs().add(tab);	
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(tab);	
	}

	@FXML
	private void handleItemProductoAction(ActionEvent event) {
		Tab tab = new Tab(resource.getString("pantalla.name.producto"));		
		PanelGrillaProducto grilla = new PanelGrillaProducto(tab);
		tab.setContent(grilla);
		tabPane.getTabs().add(tab);		
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(tab);
	}

	@FXML
	private void handleItemUsuarioAction(ActionEvent event) {
		Tab tab = new Tab(resource.getString("pantalla.name.usuario"));		
		PanelGrillaUsuario grilla = new PanelGrillaUsuario(tab);
		tab.setContent(grilla);
		tabPane.getTabs().add(tab);	
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(tab);			
	}

	@FXML
	private void handleItemVentaAction(ActionEvent event) {
		Tab tab = new Tab(resource.getString("pantalla.name.venta"));
		PanelGrillaVenta grilla = new PanelGrillaVenta(tab);
		tab.setContent(grilla);
		tabPane.getTabs().add(tab);	
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(tab);	
	}
	
	@FXML
	private void handleItemCantidadProductosAction(ActionEvent event) {		
		Tab tab = new Tab(resource.getString("pantalla.name.vCantProdVendidos"));
		PanelGrillaVCantProdVendidos grilla = new PanelGrillaVCantProdVendidos(tab);
		tab.setContent(grilla);
		tabPane.getTabs().add(tab);	
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(tab);	
	}

	@FXML
	private void closeButtonAction(){
		System.exit(0);
	}		
	
	@FXML
	private void handleItemSalirAction(ActionEvent event) {
		System.exit(0);
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
		return anchorPane;
	}

	public void setMyPane(AnchorPane myPane) {
		this.anchorPane = myPane;
	}	
}