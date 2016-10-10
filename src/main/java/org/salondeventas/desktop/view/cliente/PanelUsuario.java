// ENTITY_java.vm
package org.salondeventas.desktop.view.cliente;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.Usuario;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


public class PanelUsuario extends BorderPane implements Initializable {
	
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
		initComponentes();
    }
	
	private void initComponentes(){
		fxmlLoader = new FXMLLoader(getClass().getResource(
        		"/org/salondeventas/desktop/view/cliente/" + this.getClass().getSimpleName() + ".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        father.getTblProducto().getSelectionModel().getSelectedIndex();
        this.setTop(father.generarPanelFormulario());
        this.setLeft(null);
        this.setRight(null);
        father.btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				guardar(event);				
			}
		});
        
        father.btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				cancelar(event);				
			}
		});
        father.getTab().setContent(this);
	}

	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setDatos(Usuario usuario){
		if(usuario !=null){
			txtnombre.setText(usuario.getNombre());
			txtclave.setText(usuario.getClave());
		}
	}

	private void guardar(ActionEvent event) {
		this.getUsuario();
		father.reLoad();    		
	}
	
	private void cancelar(ActionEvent event) {		
		father.reLoad();    		
	}	

	private Usuario getUsuario() {
		Usuario usu = new Usuario();
		usu.setNombre("leonel");
		
		return usu;
	}
}