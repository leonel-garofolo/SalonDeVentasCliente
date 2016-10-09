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
import javafx.scene.layout.BorderPane;

public class PanelGrillaVenta extends PanelControlesABM implements Initializable, IPanelControllerGrilla<IVentaServicio> {
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
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

	public void initialize(URL location, ResourceBundle resources) {
		ventaServicio = new VentaServicio();				
		loadGrilla();
		
		pnlBorder.setTop(generarPanel());
		this.top = pnlBorder.getTop();
		this.center = pnlBorder.getCenter();
		this.btnAgregar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				 PanelVenta panel = new PanelVenta(PanelGrillaVenta.this);				
			}
		});
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
}