// ENTITY_java.vm
package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.salondeventas.cliente.PropertyResourceBundleMessageInterpolator;
import org.salondeventas.cliente.desktop.modelo.Usuario;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class PanelUsuario extends BorderPane implements EventHandler<ActionEvent>{

	private PanelGrillaUsuario father;	
	
	private FXMLLoader fxmlLoader;
	
	private boolean modoEdit = false;
	
	@FXML
	private VBox vBoxMsg;
	
	@FXML
	private TextField txtidusuario;

	@FXML
	private TextField txtclave;

	@FXML
	private TextField txtnombre;
	
	public PanelUsuario(PanelGrillaUsuario father) {
		this.modoEdit = false;
		this.father = father;
		initComponentes();
    }
	
	public PanelUsuario(PanelGrillaUsuario father, int id) {
		this.modoEdit = true;
		this.father = father;
		initComponentes();
		loadEntity(id);		
    }
	
	private void loadEntity(int id) {
		try {
			Usuario unUsuario = new Usuario();
			unUsuario.setIdusuario(id);
			unUsuario =father.getServicio().load(unUsuario);
			setDatos(unUsuario);
		} catch (Exception e) {
			Label label = new Label();
	    	label.setText("Se ha producido un error en el servidor. Intente mas tarde.");
	    	vBoxMsg.getChildren().addAll(label);
			e.printStackTrace();
		}
	}

	private void initComponentes(){
		fxmlLoader = new FXMLLoader(getClass().getResource(this.getClass().getSimpleName() + ".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        this.setTop(father.generarPanelFormulario());
        this.setLeft(null);
        this.setRight(null);
        father.btnGuardar.setOnAction(this);        
        father.btnCancelar.setOnAction(this);
        father.getTab().setContent(this);
	}	
	
	public void setDatos(Usuario usuario){
		if(usuario !=null){
			txtidusuario.setText(usuario.getIdusuario().toString());
			txtnombre.setText(usuario.getNombre());
			txtclave.setText(usuario.getClave());
		}
	}

	private Usuario getUsuario() {
		Usuario usu = new Usuario();
		try{
			usu.setIdusuario(Integer.valueOf(txtidusuario.getText()));
		}catch (NumberFormatException e) {
			usu.setIdusuario(null);
		}
		
		usu.setNombre(txtnombre.getText());
		usu.setClave(txtclave.getText());
		
		Label label = null;	
		Validator validator =PropertyResourceBundleMessageInterpolator.getValidation();
	    Set<ConstraintViolation<Usuario>> inputErrors = validator.validate(usu); 
	    for(ConstraintViolation<Usuario> error: inputErrors){	    	
	    	label = new Label();
	    	label.setText(error.getMessage());
	    	vBoxMsg.getChildren().addAll(label);	    	
	    }
	    if(vBoxMsg.getChildren().size() > 0){
	    	return null;
	    }
		return usu;
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(father.btnGuardar)){
			Usuario unUsuario = getUsuario();
			if(unUsuario != null){
				try {
					if(modoEdit){
						father.getServicio().update(unUsuario);
					}else{
						father.getServicio().insert(unUsuario);
					}
					
					father.reLoad();    
				} catch (Exception e) {
					Label label = new Label();
			    	label.setText("Se ha producido un error en el servidor. Intente mas tarde.");
			    	vBoxMsg.getChildren().addAll(label);
					e.printStackTrace();			
				}
			}		
		}
		if(event.getSource().equals(father.btnCancelar)){
			father.reLoad();    
		}
	}
}