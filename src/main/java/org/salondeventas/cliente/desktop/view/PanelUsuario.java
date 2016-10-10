package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import org.salondeventas.cliente.desktop.PropertyResourceBundleMessageInterpolator;
import org.salondeventas.cliente.desktop.modelo.Usuario;

public class PanelUsuario extends BorderPane implements EventHandler<ActionEvent>{
	private boolean modoEdit = false;
	private PanelGrillaUsuario father;
	
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
			loadForm(unUsuario);
		} catch (Exception e) {
			Label label = new Label();
	    	label.setText("Se ha producido un error en el servidor. Intente mas tarde.");
	    	vBoxMsg.getChildren().addAll(label);
			e.printStackTrace();
		}
	}

	private void initComponentes(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.getClass().getSimpleName() + ".fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.setResources(ResourceBundle.getBundle("i18n.ValidationMessages"));

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

	public void loadForm(Usuario usuario){
		if(usuario !=null){
			txtidusuario.setText(String.valueOf(usuario.getIdusuario()));
			txtclave.setText(usuario.getClave());
			txtnombre.setText(usuario.getNombre());
		}
	}

	private Usuario getUsuario() {
		Usuario unUsuario = new Usuario();
		try{
			unUsuario.setIdusuario(Integer.valueOf(txtidusuario.getText()));
		}catch (NumberFormatException e) {
			unUsuario.setIdusuario(null);
		}
		unUsuario.setClave(txtclave.getText());
		unUsuario.setNombre(txtnombre.getText());
		
		Label label = null;	
		Validator validator =PropertyResourceBundleMessageInterpolator.getValidation();
	    Set<ConstraintViolation<Usuario>> inputErrors = validator.validate(unUsuario); 
	    for(ConstraintViolation<Usuario> error: inputErrors){	    	
	    	label = new Label();
	    	label.setText(error.getMessage());
	    	vBoxMsg.getChildren().addAll(label);	    	
	    }
	    if(vBoxMsg.getChildren().size() > 0){
	    	return null;
	    }
		return unUsuario;
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