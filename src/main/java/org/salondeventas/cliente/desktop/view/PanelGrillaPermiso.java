package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.Permiso;
import org.salondeventas.cliente.desktop.servicios.IPermisoServicio;
import org.salondeventas.cliente.desktop.servicios.impl.PermisoServicio;
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

public class PanelGrillaPermiso extends BorderPane implements Initializable, IPanelControllerGrilla<IPermisoServicio>, EventHandler<ActionEvent> {
	private IPermisoServicio permisoServicio;
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

	private ObservableList<Permiso> data;
	
	@FXML	
	private TableView<Permiso> tblPermiso;

	@FXML
	private StringField txtidpermiso;
	@FXML
	private StringField txtnombre;
	@FXML
	private StringField txtdescripcion;
	
	public PanelGrillaPermiso(Tab tab) {
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
		permisoServicio = new PermisoServicio();
		tblPermiso.setOnMousePressed(new EventHandler<MouseEvent>() {
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
			data = FXCollections.observableArrayList(permisoServicio.loadAll());
			tblPermiso.setItems(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public IPermisoServicio getPermisoServicio() {
		return permisoServicio;
	}

	public void setPermisoServicio(IPermisoServicio permisoServicio) {
		this.permisoServicio = permisoServicio;
	}	

	@Override
	public IPermisoServicio getServicio() {
		return this.permisoServicio;
	}

	public PanelGrillaPermiso getController() {
		return this;
	}	

	public TableView<Permiso> getTblPermiso() {
		return tblPermiso;
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
			
			ObservableList<Permiso> filter= data;

			if(!txtidpermiso.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getIdpermiso() != null && p.getIdpermiso().contains(txtidpermiso.getText()));
			}
			if(!txtnombre.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getNombre() != null && p.getNombre().contains(txtnombre.getText()));
			}
			if(!txtdescripcion.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getDescripcion() != null && p.getDescripcion().contains(txtdescripcion.getText()));
			}
			tblPermiso.setItems(new SortedList<>(filter));							
		}
		
		if (event.getSource().equals(btnLimpiar)) {			
			for(Node node: hButtonFilter.getChildren()){
				if(node instanceof TextField){
					((TextField)node).setText("");
				}								
			}		
			tblPermiso.setItems(data);							
		}

		if (event.getSource().equals(panelControlesABM.getBtnAgregar())) {
			new PanelPermiso(PanelGrillaPermiso.this);
		}
		if (event.getSource().equals(panelControlesABM.getBtnEditar())) {
			btnEditarAction();					
		}
		if (event.getSource().equals(panelControlesABM.getBtnEliminar())) {
			
			Permiso itemSelected = tblPermiso.getSelectionModel().getSelectedItem();
			if(itemSelected != null){
				try {
					permisoServicio.delete(itemSelected);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			loadGrilla();
		}
	}
	
	private void btnEditarAction(){
		int itemSelected = tblPermiso.getSelectionModel().getSelectedIndex();
		if(itemSelected >= 0){
			//new PanelPermiso(PanelGrillaPermiso.this, tblPermiso.getSelectionModel().getSelectedItem().getIdpermiso());
		}
	}
}