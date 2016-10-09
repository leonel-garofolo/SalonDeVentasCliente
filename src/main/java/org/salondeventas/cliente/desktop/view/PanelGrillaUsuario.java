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
import javafx.scene.layout.BorderPane;

public class PanelGrillaUsuario extends PanelControlesABM implements Initializable, IPanelControllerGrilla<IUsuarioServicio> {
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
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

	public void initialize(URL location, ResourceBundle resources) {
		usuarioServicio = new UsuarioServicio();				
		loadGrilla();
		
		pnlBorder.setTop(generarPanel());
		this.top = pnlBorder.getTop();
		this.center = pnlBorder.getCenter();
		this.btnAgregar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				 PanelUsuario panel = new PanelUsuario(PanelGrillaUsuario.this);				
			}
		});
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
}