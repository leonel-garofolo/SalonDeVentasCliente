// ENTITY_java.vm
package org.salondeventas.desktop.view.cliente;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class PanelUsuario extends GridPane implements Initializable, ILoadPanel {
	
	private static final long serialVersionUID = 1L;

	private PanelGrillaUsuario father;	
	
	private FXMLLoader fxmlLoader;
	
	@FXML
	private TextField txtidusuario;

	@FXML
	private TextField txtclave;

	@FXML
	private TextField txtnombre;
	
	public PanelUsuario(PanelGrillaUsuario father) {
		this.father = father;
		fxmlLoader = new FXMLLoader(getClass().getResource(
        		"/org/salondeventas/desktop/view/cliente/" + this.getClass().getSimpleName() + ".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
    }

	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setDatos(Usuario usuario){
		if(usuario !=null){
			txtnombre.setText(usuario.getNombre());
			txtclave.setText(usuario.getClave());
		}
	}

	@FXML
	private void guardar(ActionEvent event) {		
		father.reLoad();    		
	}

	public FXMLLoader getFxmlLoader() {
		return fxmlLoader;
	}

	public void setFxmlLoader(FXMLLoader fxmlLoader) {
		this.fxmlLoader = fxmlLoader;
	}
}