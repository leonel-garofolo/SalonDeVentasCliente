package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.Empresa;
import org.salondeventas.cliente.desktop.servicios.IEmpresaServicio;
import org.salondeventas.cliente.desktop.servicios.impl.EmpresaServicio;

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

public class PanelGrillaEmpresa extends PanelControlesABM implements Initializable, IPanelControllerGrilla<IEmpresaServicio>, EventHandler<ActionEvent> {
	private IEmpresaServicio empresaServicio;
	private Node top;
	private Node center;
	private Node bottom;
	private Tab tab;
	@FXML
	private BorderPane pnlBorder;
	
	@FXML
	private TextField txtBuscar;
	
	@FXML	
	private TableView<Empresa> tblEmpresa;
	
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

		pnlBorder.setTop(generarPanel());		
		this.btnAgregar.setOnAction(this);
		this.btnEditar.setOnAction(this);
		this.btnEliminar.setOnAction(this);
	}
	
	public void loadGrilla(){
		try {
			final ObservableList<Empresa> data =
			        FXCollections.observableArrayList(empresaServicio.loadAll());
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
	public BorderPane getPnlBorder() {
		return pnlBorder;
	}

	public void setPnlBorder(BorderPane pnlBorder) {
		this.pnlBorder = pnlBorder;
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
		if (event.getSource().equals(btnAgregar)) {
			new PanelEmpresa(PanelGrillaEmpresa.this);
		}
		if (event.getSource().equals(btnEditar)) {
			btnEditarAction();					
		}
		if (event.getSource().equals(btnEliminar)) {
			
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