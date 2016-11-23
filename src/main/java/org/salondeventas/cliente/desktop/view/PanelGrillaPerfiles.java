package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.Perfiles;
import org.salondeventas.cliente.desktop.servicios.IPerfilesServicio;
import org.salondeventas.cliente.desktop.servicios.impl.PerfilesServicio;
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

public class PanelGrillaPerfiles extends BorderPane implements Initializable, IPanelControllerGrilla<IPerfilesServicio>, EventHandler<ActionEvent> {
	private IPerfilesServicio perfilesServicio;
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

	private ObservableList<Perfiles> data;
	
	@FXML	
	private TableView<Perfiles> tblPerfiles;

	@FXML
	private NumberField txtidperfiles;
	@FXML
	private StringField txtnombre;
	
	public PanelGrillaPerfiles(Tab tab) {
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
		perfilesServicio = new PerfilesServicio();
		tblPerfiles.setOnMousePressed(new EventHandler<MouseEvent>() {
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
			data = FXCollections.observableArrayList(perfilesServicio.loadAll());
			tblPerfiles.setItems(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public IPerfilesServicio getPerfilesServicio() {
		return perfilesServicio;
	}

	public void setPerfilesServicio(IPerfilesServicio perfilesServicio) {
		this.perfilesServicio = perfilesServicio;
	}	

	@Override
	public IPerfilesServicio getServicio() {
		return this.perfilesServicio;
	}

	public PanelGrillaPerfiles getController() {
		return this;
	}	

	public TableView<Perfiles> getTblPerfiles() {
		return tblPerfiles;
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
			
			ObservableList<Perfiles> filter= data;

			if(!txtidperfiles.getText().trim().equals("")){
				filter=filter.filtered(p -> p.getIdperfiles() != null && p.getIdperfiles() == Integer.valueOf(txtidperfiles.getText()));
			}
			if(!txtnombre.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getNombre() != null && p.getNombre().contains(txtnombre.getText()));
			}
			tblPerfiles.setItems(new SortedList<>(filter));							
		}
		
		if (event.getSource().equals(btnLimpiar)) {			
			for(Node node: hButtonFilter.getChildren()){
				if(node instanceof TextField){
					((TextField)node).setText("");
				}								
			}		
			tblPerfiles.setItems(data);							
		}

		if (event.getSource().equals(panelControlesABM.getBtnAgregar())) {
			new PanelPerfiles(PanelGrillaPerfiles.this);
		}
		if (event.getSource().equals(panelControlesABM.getBtnEditar())) {
			btnEditarAction();					
		}
		if (event.getSource().equals(panelControlesABM.getBtnEliminar())) {
			
			Perfiles itemSelected = tblPerfiles.getSelectionModel().getSelectedItem();
			if(itemSelected != null){
				try {
					perfilesServicio.delete(itemSelected);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			loadGrilla();
		}
	}
	
	private void btnEditarAction(){
		int itemSelected = tblPerfiles.getSelectionModel().getSelectedIndex();
		if(itemSelected >= 0){
			new PanelPerfiles(PanelGrillaPerfiles.this, tblPerfiles.getSelectionModel().getSelectedItem().getIdperfiles());
		}
	}
}