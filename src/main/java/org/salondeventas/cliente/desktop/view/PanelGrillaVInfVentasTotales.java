package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.VInfVentasTotales;
import org.salondeventas.cliente.desktop.servicios.IVInfVentasTotalesServicio;
import org.salondeventas.cliente.desktop.servicios.impl.VInfVentasTotalesServicio;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PanelGrillaVInfVentasTotales extends BorderPane implements Initializable, IPanelControllerGrilla<IVInfVentasTotalesServicio>, EventHandler<ActionEvent> {
	private IVInfVentasTotalesServicio vInfVentasTotalesServicio;
	private Tab tab;

	@FXML
	VBox vTop;

	@FXML
	HBox hButtonFilter;

	@FXML
	private Button btnBuscar;
	
	@FXML
	private Button btnLimpiar;

	private ObservableList<VInfVentasTotales> data;
	
	@FXML	
	private TableView<VInfVentasTotales> tblVInfVentasTotales;

	
	public PanelGrillaVInfVentasTotales(Tab tab) {
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
		vInfVentasTotalesServicio = new VInfVentasTotalesServicio();		
		loadGrilla();
		this.autosize();
		
		this.btnBuscar.setOnAction(this);
		this.btnLimpiar.setOnAction(this);
	}
	
	public void loadGrilla(){
		try {
			data = FXCollections.observableArrayList(vInfVentasTotalesServicio.loadAll());
			tblVInfVentasTotales.setItems(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public IVInfVentasTotalesServicio getVInfVentasTotalesServicio() {
		return vInfVentasTotalesServicio;
	}

	public void setVInfVentasTotalesServicio(IVInfVentasTotalesServicio vInfVentasTotalesServicio) {
		this.vInfVentasTotalesServicio = vInfVentasTotalesServicio;
	}	

	@Override
	public IVInfVentasTotalesServicio getServicio() {
		return this.vInfVentasTotalesServicio;
	}

	public PanelGrillaVInfVentasTotales getController() {
		return this;
	}	

	public TableView<VInfVentasTotales> getTblVInfVentasTotales() {
		return tblVInfVentasTotales;
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
			
			ObservableList<VInfVentasTotales> filter= data;

			tblVInfVentasTotales.setItems(new SortedList<>(filter));							
		}
		
		if (event.getSource().equals(btnLimpiar)) {			
			for(Node node: hButtonFilter.getChildren()){
				if(node instanceof TextField){
					((TextField)node).setText("");
				}								
			}		
			tblVInfVentasTotales.setItems(data);							
		}		
	}		
}