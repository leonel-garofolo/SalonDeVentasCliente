package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.Pantalla;
import org.salondeventas.cliente.desktop.servicios.IPantallaServicio;
import org.salondeventas.cliente.desktop.servicios.impl.PantallaServicio;
import org.javafx.controls.panels.PanelControlesABM;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.javafx.controls.customs.DecimalField;
import org.javafx.controls.customs.NumberField;
import org.javafx.controls.customs.StringField;

public class PanelGrillaPantalla extends BorderPane implements Initializable, IPanelControllerGrilla<IPantallaServicio>, EventHandler<ActionEvent> {
	private IPantallaServicio pantallaServicio;
	private Tab tab;
	private ResourceBundle resource;

	@FXML
	private PanelControlesABM panelControlesABM;

	@FXML
	VBox vTop;

	@FXML
	HBox hButtonFilter;

	@FXML
	private Button btnBuscar;
	
	@FXML
	private Button btnLimpiar;

	private ObservableList<Pantalla> data;
	
	@FXML	
	private TableView<Pantalla> tblPantalla;

	@FXML
	private NumberField txtidpantalla;
	@FXML
	private StringField txtnombre;
	
	public PanelGrillaPantalla(Tab tab) {
		this.tab = tab;
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
    }

	public void initialize(URL location, ResourceBundle resources) {
		pantallaServicio = new PantallaServicio();
		tblPantalla.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	btnEditarAction();          
		        }
		    }
		});
		loadGrilla();
		this.autosize();

		panelControlesABM.getBtnAgregar().setOnAction(this);
		panelControlesABM.getBtnEditar().setOnAction(this);
		panelControlesABM.getBtnEliminar().setOnAction(this);
		this.btnBuscar.setOnAction(this);
		this.btnLimpiar.setOnAction(this);
	}
	
	public void loadGrilla(){
		try {
			data = FXCollections.observableArrayList(pantallaServicio.loadAll());
			tblPantalla.setItems(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public IPantallaServicio getPantallaServicio() {
		return pantallaServicio;
	}

	public void setPantallaServicio(IPantallaServicio pantallaServicio) {
		this.pantallaServicio = pantallaServicio;
	}	

	@Override
	public IPantallaServicio getServicio() {
		return this.pantallaServicio;
	}

	public PanelGrillaPantalla getController() {
		return this;
	}	

	public TableView<Pantalla> getTblPantalla() {
		return tblPantalla;
	}

	public Tab getTab() {
		return tab;
	}

	@Override
	public void reLoad() {
		tab.setContent(this);
		loadGrilla();
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource().equals(btnBuscar)) {	
			loadGrilla();
			
			ObservableList<Pantalla> filter= data;

			if(!txtidpantalla.getText().trim().equals("")){
				filter=filter.filtered(p -> p.getIdpantalla() != null && p.getIdpantalla() == Integer.valueOf(txtidpantalla.getText()));
			}
			if(!txtnombre.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getNombre() != null && p.getNombre().contains(txtnombre.getText()));
			}
			tblPantalla.setItems(new SortedList<>(filter));							
		}
		
		if (event.getSource().equals(btnLimpiar)) {			
			for(Node node: hButtonFilter.getChildren()){
				if(node instanceof TextField){
					((TextField)node).setText("");
				}								
			}		
			tblPantalla.setItems(data);							
		}

		if (event.getSource().equals(panelControlesABM.getBtnAgregar())) {
			new PanelPantalla(PanelGrillaPantalla.this);
		}
		if (event.getSource().equals(panelControlesABM.getBtnEditar())) {
			btnEditarAction();					
		}
		if (event.getSource().equals(panelControlesABM.getBtnEliminar())) {
			
			Pantalla itemSelected = tblPantalla.getSelectionModel().getSelectedItem();
			if(itemSelected != null){
				try {
					pantallaServicio.delete(itemSelected);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			loadGrilla();
		}
	}
	
	private void btnEditarAction(){
		int itemSelected = tblPantalla.getSelectionModel().getSelectedIndex();
		if(itemSelected >= 0){
			new PanelPantalla(PanelGrillaPantalla.this, tblPantalla.getSelectionModel().getSelectedItem().getIdpantalla());
		}
	}
}