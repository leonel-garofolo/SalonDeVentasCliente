package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.javafx.controls.panels.PanelControlesABM;
import org.salondeventas.cliente.desktop.modelo.Configuracion;
import org.salondeventas.cliente.desktop.servicios.IConfiguracionServicio;
import org.salondeventas.cliente.desktop.servicios.impl.ConfiguracionServicio;

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

public class PanelGrillaConfiguracion extends BorderPane implements Initializable, IPanelControllerGrilla<IConfiguracionServicio>, EventHandler<ActionEvent> {
	private IConfiguracionServicio configuracionServicio;
	private Tab tab;

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

	private ObservableList<Configuracion> data;
	
	@FXML	
	private TableView<Configuracion> tblConfiguracion;

	@FXML
	private TextField txtidconfiguracion;
	@FXML
	private TextField txtcodigo;
	@FXML
	private TextField txtnombre;
	@FXML
	private TextField txtdescripcion;
	@FXML
	private TextField txtvalor;
	
	public PanelGrillaConfiguracion(Tab tab) {
		this.tab = tab;
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.getClass().getSimpleName() + ".fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.setResources(ResourceBundle.getBundle("i18n.ValidationMessages"));
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

	public void initialize(URL location, ResourceBundle resources) {
		configuracionServicio = new ConfiguracionServicio();
		tblConfiguracion.setOnMousePressed(new EventHandler<MouseEvent>() {
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
			data = FXCollections.observableArrayList(configuracionServicio.loadAll());
			tblConfiguracion.setItems(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public IConfiguracionServicio getConfiguracionServicio() {
		return configuracionServicio;
	}

	public void setConfiguracionServicio(IConfiguracionServicio configuracionServicio) {
		this.configuracionServicio = configuracionServicio;
	}	

	@Override
	public IConfiguracionServicio getServicio() {
		return this.configuracionServicio;
	}

	public PanelGrillaConfiguracion getController() {
		return this;
	}	

	public TableView<Configuracion> getTblConfiguracion() {
		return tblConfiguracion;
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
			
			ObservableList<Configuracion> filter= data;

			if(!txtidconfiguracion.getText().trim().equals("")){
				filter=filter.filtered(p -> p.getIdconfiguracion() != null && p.getIdconfiguracion() == Integer.valueOf(txtidconfiguracion.getText()));
			}
			if(!txtcodigo.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getCodigo() != null && p.getCodigo().contains(txtcodigo.getText()));
			}
			if(!txtnombre.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getNombre() != null && p.getNombre().contains(txtnombre.getText()));
			}
			if(!txtdescripcion.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getDescripcion() != null && p.getDescripcion().contains(txtdescripcion.getText()));
			}
			if(!txtvalor.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getValor() != null && p.getValor().contains(txtvalor.getText()));
			}
			tblConfiguracion.setItems(new SortedList<>(filter));							
		}
		
		if (event.getSource().equals(btnLimpiar)) {			
			for(Node node: hButtonFilter.getChildren()){
				if(node instanceof TextField){
					((TextField)node).setText("");
				}								
			}		
			tblConfiguracion.setItems(data);							
		}

		if (event.getSource().equals(panelControlesABM.getBtnAgregar())) {
			new PanelConfiguracion(PanelGrillaConfiguracion.this);
		}
		if (event.getSource().equals(panelControlesABM.getBtnEditar())) {
			btnEditarAction();					
		}
		if (event.getSource().equals(panelControlesABM.getBtnEliminar())) {
			
			Configuracion itemSelected = tblConfiguracion.getSelectionModel().getSelectedItem();
			if(itemSelected != null){
				try {
					configuracionServicio.delete(itemSelected);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			loadGrilla();
		}
	}
	
	private void btnEditarAction(){
		int itemSelected = tblConfiguracion.getSelectionModel().getSelectedItem().getIdconfiguracion();
		if(itemSelected > 0){
			new PanelConfiguracion(PanelGrillaConfiguracion.this, itemSelected);
		}
	}
}