package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.Venta;
import org.salondeventas.cliente.desktop.servicios.IVentaServicio;
import org.salondeventas.cliente.desktop.servicios.impl.VentaServicio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class PanelGrillaVenta extends PanelControlesABM implements Initializable, IPanelControllerGrilla<IVentaServicio>, EventHandler<ActionEvent> {
	private IVentaServicio ventaServicio;
	private Node top;
	private Node center;
	private Node bottom;
	private Tab tab;
	@FXML
	private BorderPane pnlBorder;
	
	@FXML
	private TextField txtBuscar;
	
	@FXML	
	private TableView<Venta> tblVenta;
	
	public PanelGrillaVenta(Tab tab) {
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
		ventaServicio = new VentaServicio();
		tblVenta.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	btnEditarAction();          
		        }
		    }
		});
		loadGrilla();

		pnlBorder.setTop(generarPanel());		
		this.btnAgregar.setOnAction(this);
		this.btnEditar.setOnAction(this);
		this.btnEliminar.setOnAction(this);
	}
	
	public void loadGrilla(){
		try {
			final ObservableList<Venta> data =
			        FXCollections.observableArrayList(ventaServicio.loadAll());
			tblVenta.setItems(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public IVentaServicio getVentaServicio() {
		return ventaServicio;
	}

	public void setVentaServicio(IVentaServicio ventaServicio) {
		this.ventaServicio = ventaServicio;
	}

	@Override
	public BorderPane getPnlBorder() {
		return pnlBorder;
	}

	public void setPnlBorder(BorderPane pnlBorder) {
		this.pnlBorder = pnlBorder;
	}

	@Override
	public IVentaServicio getServicio() {
		return this.ventaServicio;
	}

	public PanelGrillaVenta getController() {
		return this;
	}	

	public TableView<Venta> getTblVenta() {
		return tblVenta;
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
		if (event.getSource().equals(btnAgregar)) {
			new PanelVenta(PanelGrillaVenta.this);
		}
		if (event.getSource().equals(btnEditar)) {
			btnEditarAction();					
		}
		if (event.getSource().equals(btnEliminar)) {
			
			Venta itemSelected = tblVenta.getSelectionModel().getSelectedItem();
			if(itemSelected != null){
				try {
					ventaServicio.delete(itemSelected);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			loadGrilla();
		}
	}
	
	private void btnEditarAction(){
		int itemSelected = tblVenta.getSelectionModel().getSelectedItem().getIdventa();
		if(itemSelected > 0){
			new PanelVenta(PanelGrillaVenta.this, itemSelected);
		}
	}
}