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
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.javafx.controls.panels.PanelControlesEdit;

import org.javafx.controls.customs.DecimalField;
import org.javafx.controls.customs.NumberField;
import org.javafx.controls.customs.StringField;
import org.salondeventas.cliente.desktop.PropertyResourceBundleMessageInterpolator;
import org.salondeventas.cliente.desktop.modelo.Permiso;

public class PanelPermiso extends BorderPane implements EventHandler<ActionEvent>{
	private boolean modoEdit = false;
	private PanelGrillaPermiso father;
	private ResourceBundle resource;

	@FXML
	private PanelControlesEdit panelControlesEdit;
	
	@FXML
	private VBox vBoxMsg;


	@FXML
	private StringField txtidpermiso;

	@FXML
	private StringField txtnombre;

	@FXML
	private StringField txtdescripcion;

	public PanelPermiso(PanelGrillaPermiso father) {
		this.modoEdit = false;
		this.father = father;
		initComponentes();
    }

	public PanelPermiso(PanelGrillaPermiso father, int id) {
		this.modoEdit = true;
		this.father = father;
		initComponentes();
		loadEntity(id);		
    }

	private void loadEntity(int id) {
		try {
			Permiso unPermiso = new Permiso();
			unPermiso.setIdpermiso(String.valueOf(id));
			unPermiso =father.getServicio().load(unPermiso);
			loadForm(unPermiso);
		} catch (Exception e) {
			Label label = new Label();
	    	label.setText("Se ha producido un error en el servidor. Intente mas tarde.");
	    	vBoxMsg.getChildren().addAll(label);
			e.printStackTrace();
		}
	}

	private void initComponentes(){
		this.resource = ResourceBundle.getBundle("i18n.ValidationMessages");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.getClass().getSimpleName() + ".fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.setResources(this.resource);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
                
        this.setLeft(null);
        this.setRight(null);
        panelControlesEdit.getBtnGuardar().setOnAction(this);        
        panelControlesEdit.getBtnCancelar().setOnAction(this);
        father.getTab().setContent(this);
        
	}

	public void loadForm(Permiso permiso){
		if(permiso !=null){
			if(permiso.getIdpermiso() != null){
				txtidpermiso.setValue(permiso.getIdpermiso());
			}
			if(permiso.getNombre() != null){
				txtnombre.setValue(permiso.getNombre());
			}
			if(permiso.getDescripcion() != null){
				txtdescripcion.setValue(permiso.getDescripcion());
			}
		}
	}

	private Permiso getPermiso() {
		Permiso unPermiso = new Permiso();
		unPermiso.setIdpermiso(txtidpermiso.getText());
		unPermiso.setNombre(txtnombre.getText());
		unPermiso.setDescripcion(txtdescripcion.getText());
		
		Label label = null;	
		vBoxMsg.getChildren().clear();
		Validator validator =PropertyResourceBundleMessageInterpolator.getValidation();
	    Set<ConstraintViolation<Permiso>> inputErrors = validator.validate(unPermiso); 
	    for(ConstraintViolation<Permiso> error: inputErrors){	    	
	    	label = new Label();
	    	label.setText(error.getMessage());
	    	vBoxMsg.getChildren().addAll(label);	    	
	    }
	    if(vBoxMsg.getChildren().size() > 0){
	    	return null;
	    }
		return unPermiso;
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(panelControlesEdit.getBtnGuardar())){
			Permiso unPermiso = getPermiso();
			if(unPermiso != null){
				try {
					if(modoEdit){
						father.getServicio().update(unPermiso);
					}else{
						father.getServicio().insert(unPermiso);
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
		if(event.getSource().equals(panelControlesEdit.getBtnCancelar())){
			father.reLoad();    
		}
	}
}