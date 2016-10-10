package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.Producto;
import org.salondeventas.cliente.desktop.servicios.IProductoServicio;
import org.salondeventas.cliente.desktop.servicios.impl.ProductoServicio;

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

public class PanelGrillaProducto extends PanelControlesABM implements Initializable, IPanelControllerGrilla<IProductoServicio>, EventHandler<ActionEvent> {
	private IProductoServicio productoServicio;
	private Node top;
	private Node center;
	private Node bottom;
	private Tab tab;
	@FXML
	private BorderPane pnlBorder;
	
	@FXML
	private TextField txtBuscar;
	
	@FXML	
	private TableView<Producto> tblProducto;
	
	public PanelGrillaProducto(Tab tab) {
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
		productoServicio = new ProductoServicio();
		tblProducto.setOnMousePressed(new EventHandler<MouseEvent>() {
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
			final ObservableList<Producto> data =
			        FXCollections.observableArrayList(productoServicio.loadAll());
			tblProducto.setItems(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public IProductoServicio getProductoServicio() {
		return productoServicio;
	}

	public void setProductoServicio(IProductoServicio productoServicio) {
		this.productoServicio = productoServicio;
	}

	@Override
	public BorderPane getPnlBorder() {
		return pnlBorder;
	}

	public void setPnlBorder(BorderPane pnlBorder) {
		this.pnlBorder = pnlBorder;
	}

	@Override
	public IProductoServicio getServicio() {
		return this.productoServicio;
	}

	public PanelGrillaProducto getController() {
		return this;
	}	

	public TableView<Producto> getTblProducto() {
		return tblProducto;
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
			new PanelProducto(PanelGrillaProducto.this);
		}
		if (event.getSource().equals(btnEditar)) {
			btnEditarAction();					
		}
		if (event.getSource().equals(btnEliminar)) {
			
			Producto itemSelected = tblProducto.getSelectionModel().getSelectedItem();
			if(itemSelected != null){
				try {
					productoServicio.delete(itemSelected);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			loadGrilla();
		}
	}
	
	private void btnEditarAction(){
		int itemSelected = tblProducto.getSelectionModel().getSelectedItem().getIdproducto();
		if(itemSelected > 0){
			new PanelProducto(PanelGrillaProducto.this, itemSelected);
		}
	}
}