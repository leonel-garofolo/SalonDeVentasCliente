package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.Usuario;
import org.salondeventas.cliente.desktop.servicios.IUsuarioServicio;
import org.salondeventas.cliente.desktop.servicios.impl.UsuarioServicio;

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

public class PanelGrillaUsuario extends PanelControlesABM implements Initializable, IPanelControllerGrilla<IUsuarioServicio>, EventHandler<ActionEvent> {
	private IUsuarioServicio usuarioServicio;
	private Node top;
	private Node center;
	private Node bottom;
	private Tab tab;
	@FXML
	private BorderPane pnlBorder;
	
	@FXML
	private TextField txtBuscar;
	
	@FXML	
	private TableView<Usuario> tblUsuario;
	
	public PanelGrillaUsuario(Tab tab) {
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
		usuarioServicio = new UsuarioServicio();
		tblUsuario.setOnMousePressed(new EventHandler<MouseEvent>() {
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
			final ObservableList<Usuario> data =
			        FXCollections.observableArrayList(usuarioServicio.loadAll());
			tblUsuario.setItems(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public IUsuarioServicio getUsuarioServicio() {
		return usuarioServicio;
	}

	public void setUsuarioServicio(IUsuarioServicio usuarioServicio) {
		this.usuarioServicio = usuarioServicio;
	}

	@Override
	public BorderPane getPnlBorder() {
		return pnlBorder;
	}

	public void setPnlBorder(BorderPane pnlBorder) {
		this.pnlBorder = pnlBorder;
	}

	@Override
	public IUsuarioServicio getServicio() {
		return this.usuarioServicio;
	}

	public PanelGrillaUsuario getController() {
		return this;
	}	

	public TableView<Usuario> getTblUsuario() {
		return tblUsuario;
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
			new PanelUsuario(PanelGrillaUsuario.this);
		}
		if (event.getSource().equals(btnEditar)) {
			btnEditarAction();					
		}
		if (event.getSource().equals(btnEliminar)) {
			
			Usuario itemSelected = tblUsuario.getSelectionModel().getSelectedItem();
			if(itemSelected != null){
				try {
					usuarioServicio.delete(itemSelected);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			loadGrilla();
		}
	}
	
	private void btnEditarAction(){
		int itemSelected = tblUsuario.getSelectionModel().getSelectedItem().getIdusuario();
		if(itemSelected > 0){
			new PanelUsuario(PanelGrillaUsuario.this, itemSelected);
		}
	}
}