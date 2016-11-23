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
import org.salondeventas.cliente.desktop.modelo.Perfiles;

public class PanelPerfiles extends BorderPane implements EventHandler<ActionEvent>{
	private boolean modoEdit = false;
	private PanelGrillaPerfiles father;
	private ResourceBundle resource;

	@FXML
	private PanelControlesEdit panelControlesEdit;
	
	@FXML
	private VBox vBoxMsg;


	@FXML
	private NumberField txtidperfiles;

	@FXML
	private StringField txtnombre;

	public PanelPerfiles(PanelGrillaPerfiles father) {
		this.modoEdit = false;
		this.father = father;
		initComponentes();
    }

	public PanelPerfiles(PanelGrillaPerfiles father, int id) {
		this.modoEdit = true;
		this.father = father;
		initComponentes();
		loadEntity(id);		
    }

	private void loadEntity(int id) {
		try {
			Perfiles unPerfiles = new Perfiles();
			unPerfiles.setIdperfiles(id);
			unPerfiles =father.getServicio().load(unPerfiles);
			loadForm(unPerfiles);
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

	public void loadForm(Perfiles perfiles){
		if(perfiles !=null){
			if(perfiles.getIdperfiles() != null){
				txtidperfiles.setValue(perfiles.getIdperfiles());
			}
			if(perfiles.getNombre() != null){
				txtnombre.setValue(perfiles.getNombre());
			}
		}
	}

	private Perfiles getPerfiles() {
		Perfiles unPerfiles = new Perfiles();
		try{
			unPerfiles.setIdperfiles(Integer.valueOf(txtidperfiles.getText()));
		}catch (NumberFormatException e) {
			unPerfiles.setIdperfiles(null);
		}
		unPerfiles.setNombre(txtnombre.getText());
		
		Label label = null;	
		vBoxMsg.getChildren().clear();
		Validator validator =PropertyResourceBundleMessageInterpolator.getValidation();
	    Set<ConstraintViolation<Perfiles>> inputErrors = validator.validate(unPerfiles); 
	    for(ConstraintViolation<Perfiles> error: inputErrors){	    	
	    	label = new Label();
	    	label.setText(error.getMessage());
	    	vBoxMsg.getChildren().addAll(label);	    	
	    }
	    if(vBoxMsg.getChildren().size() > 0){
	    	return null;
	    }
		return unPerfiles;
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(panelControlesEdit.getBtnGuardar())){
			Perfiles unPerfiles = getPerfiles();
			if(unPerfiles != null){
				try {
					if(modoEdit){
						father.getServicio().update(unPerfiles);
					}else{
						father.getServicio().insert(unPerfiles);
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