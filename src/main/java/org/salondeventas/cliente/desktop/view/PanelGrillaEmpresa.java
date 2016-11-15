package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.Empresa;
import org.salondeventas.cliente.desktop.servicios.IEmpresaServicio;
import org.salondeventas.cliente.desktop.servicios.impl.EmpresaServicio;
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

public class PanelGrillaEmpresa extends BorderPane implements Initializable, IPanelControllerGrilla<IEmpresaServicio>, EventHandler<ActionEvent> {
	private IEmpresaServicio empresaServicio;
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

	private ObservableList<Empresa> data;
	
	@FXML	
	private TableView<Empresa> tblEmpresa;

	@FXML
	private TextField txtidempresa;
	@FXML
	private TextField txtdescripcion;
	@FXML
	private TextField txtdireccion;
	@FXML
	private TextField txtnombre;
	@FXML
	private TextField txttelefono;
	
	public PanelGrillaEmpresa(Tab tab) {
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
		empresaServicio = new EmpresaServicio();
		tblEmpresa.setOnMousePressed(new EventHandler<MouseEvent>() {
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
			data = FXCollections.observableArrayList(empresaServicio.loadAll());
			tblEmpresa.setItems(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public IEmpresaServicio getEmpresaServicio() {
		return empresaServicio;
	}

	public void setEmpresaServicio(IEmpresaServicio empresaServicio) {
		this.empresaServicio = empresaServicio;
	}	

	@Override
	public IEmpresaServicio getServicio() {
		return this.empresaServicio;
	}

	public PanelGrillaEmpresa getController() {
		return this;
	}	

	public TableView<Empresa> getTblEmpresa() {
		return tblEmpresa;
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
			
			ObservableList<Empresa> filter= data;

			if(!txtidempresa.getText().trim().equals("")){
				filter=filter.filtered(p -> p.getIdempresa() != null && p.getIdempresa() == Integer.valueOf(txtidempresa.getText()));
			}
			if(!txtdescripcion.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getDescripcion() != null && p.getDescripcion().contains(txtdescripcion.getText()));
			}
			if(!txtdireccion.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getDireccion() != null && p.getDireccion().contains(txtdireccion.getText()));
			}
			if(!txtnombre.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getNombre() != null && p.getNombre().contains(txtnombre.getText()));
			}
			if(!txttelefono.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getTelefono() != null && p.getTelefono().contains(txttelefono.getText()));
			}
			tblEmpresa.setItems(new SortedList<>(filter));							
		}
		
		if (event.getSource().equals(btnLimpiar)) {			
			for(Node node: hButtonFilter.getChildren()){
				if(node instanceof TextField){
					((TextField)node).setText("");
				}								
			}		
			tblEmpresa.setItems(data);							
		}

		if (event.getSource().equals(panelControlesABM.getBtnAgregar())) {
			new PanelEmpresa(PanelGrillaEmpresa.this);
		}
		if (event.getSource().equals(panelControlesABM.getBtnEditar())) {
			btnEditarAction();					
		}
		if (event.getSource().equals(panelControlesABM.getBtnEliminar())) {
			
			Empresa itemSelected = tblEmpresa.getSelectionModel().getSelectedItem();
			if(itemSelected != null){
				try {
					empresaServicio.delete(itemSelected);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			loadGrilla();
		}
	}
	
	private void btnEditarAction(){
		int itemSelected = tblEmpresa.getSelectionModel().getSelectedItem().getIdempresa();
		if(itemSelected > 0){
			new PanelEmpresa(PanelGrillaEmpresa.this, itemSelected);
		}
	}
}