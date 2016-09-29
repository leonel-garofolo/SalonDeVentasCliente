// ENTITY_java.vm
package org.salondeventas.desktop.view.cliente;

import java.awt.Panel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.Usuario;
import org.salondeventas.cliente.desktop.servicios.IUsuarioServicio;
import org.salondeventas.cliente.desktop.servicios.impl.UsuarioServicio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PanelGrillaUsuario extends Pane implements Initializable, IPanelControllerGrilla<IUsuarioServicio> {
	private IUsuarioServicio usuarioServicio;
	
	private Node top;
	private Node center;
	private Node bottom;
	
	@FXML
	private BorderPane pnlBorder;
	
	@FXML
	private TextField txtBuscar;
	
	@FXML	
	private TableView<Usuario> tblProducto;
	
	public PanelGrillaUsuario() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
        		"/org/salondeventas/desktop/view/cliente/" + this.getClass().getSimpleName() + ".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

	public void initialize(URL location, ResourceBundle resources) {
		usuarioServicio = new UsuarioServicio();				
		loadGrilla();
		
		PanelControlesABM botoneraABM = new PanelControlesABM(this, new PanelUsuario(this));	
		pnlBorder.setTop(botoneraABM);
		this.top = pnlBorder.getTop();
		this.center = pnlBorder.getCenter();
		
	}
	
	public void loadGrilla(){
		try {
			final ObservableList<Usuario> data =
			        FXCollections.observableArrayList(usuarioServicio.obtenerTodos());
			tblProducto.setItems(data);
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

	@Override
	public void reLoad() {
		pnlBorder.setTop(top);
		pnlBorder.setCenter(center);
		loadGrilla();
		
	}
}