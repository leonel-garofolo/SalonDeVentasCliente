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
import org.salondeventas.cliente.desktop.modelo.Pantalla;

public class PanelPantalla extends BorderPane implements EventHandler<ActionEvent>{
	private boolean modoEdit = false;
	private PanelGrillaPantalla father;
	private ResourceBundle resource;

	@FXML
	private PanelControlesEdit panelControlesEdit;
	
	@FXML
	private VBox vBoxMsg;


	@FXML
	private NumberField txtidpantalla;

	@FXML
	private StringField txtnombre;

	public PanelPantalla(PanelGrillaPantalla father) {
		this.modoEdit = false;
		this.father = father;
		initComponentes();
    }

	public PanelPantalla(PanelGrillaPantalla father, int id) {
		this.modoEdit = true;
		this.father = father;
		initComponentes();
		loadEntity(id);		
    }

	private void loadEntity(int id) {
		try {
			Pantalla unPantalla = new Pantalla();
			unPantalla.setIdpantalla(id);
			unPantalla =father.getServicio().load(unPantalla);
			loadForm(unPantalla);
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

	public void loadForm(Pantalla pantalla){
		if(pantalla !=null){
			if(pantalla.getIdpantalla() != null){
				txtidpantalla.setValue(pantalla.getIdpantalla());
			}
			if(pantalla.getNombre() != null){
				txtnombre.setValue(pantalla.getNombre());
			}
		}
	}

	private Pantalla getPantalla() {
		Pantalla unPantalla = new Pantalla();
		try{
			unPantalla.setIdpantalla(Integer.valueOf(txtidpantalla.getText()));
		}catch (NumberFormatException e) {
			unPantalla.setIdpantalla(null);
		}
		unPantalla.setNombre(txtnombre.getText());
		
		Label label = null;	
		vBoxMsg.getChildren().clear();
		Validator validator =PropertyResourceBundleMessageInterpolator.getValidation();
	    Set<ConstraintViolation<Pantalla>> inputErrors = validator.validate(unPantalla); 
	    for(ConstraintViolation<Pantalla> error: inputErrors){	    	
	    	label = new Label();
	    	label.setText(error.getMessage());
	    	vBoxMsg.getChildren().addAll(label);	    	
	    }
	    if(vBoxMsg.getChildren().size() > 0){
	    	return null;
	    }
		return unPantalla;
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(panelControlesEdit.getBtnGuardar())){
			Pantalla unPantalla = getPantalla();
			if(unPantalla != null){
				try {
					if(modoEdit){
						father.getServicio().update(unPantalla);
					}else{
						father.getServicio().insert(unPantalla);
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