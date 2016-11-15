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



import org.salondeventas.cliente.desktop.PropertyResourceBundleMessageInterpolator;
import org.salondeventas.cliente.desktop.modelo.Configuracion;

public class PanelConfiguracion extends BorderPane implements EventHandler<ActionEvent>{
	private boolean modoEdit = false;
	private PanelGrillaConfiguracion father;

	@FXML
	private PanelControlesEdit panelControlesEdit;
	
	@FXML
	private VBox vBoxMsg;


	@FXML
	private TextField txtidconfiguracion;

	@FXML
	private TextField txtcodigo;

	@FXML
	private TextField txtnombre;

	@FXML
	private TextField txtdescripcion;

	@FXML
	private TextField txtvalor;

	public PanelConfiguracion(PanelGrillaConfiguracion father) {
		this.modoEdit = false;
		this.father = father;
		initComponentes();
    }

	public PanelConfiguracion(PanelGrillaConfiguracion father, int id) {
		this.modoEdit = true;
		this.father = father;
		initComponentes();
		loadEntity(id);		
    }

	private void loadEntity(int id) {
		try {
			Configuracion unConfiguracion = new Configuracion();
			unConfiguracion.setIdconfiguracion(id);
			unConfiguracion =father.getServicio().load(unConfiguracion);
			loadForm(unConfiguracion);
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
                
        this.setLeft(null);
        this.setRight(null);
        panelControlesEdit.getBtnGuardar().setOnAction(this);        
        panelControlesEdit.getBtnCancelar().setOnAction(this);
        father.getTab().setContent(this);
        
	}

	public void loadForm(Configuracion configuracion){
		if(configuracion !=null){
			if(configuracion.getIdconfiguracion() != null){
				txtidconfiguracion.setText(String.valueOf(configuracion.getIdconfiguracion()));
			}
			if(configuracion.getCodigo() != null){
				txtcodigo.setText(configuracion.getCodigo());
			}
			if(configuracion.getNombre() != null){
				txtnombre.setText(configuracion.getNombre());
			}
			if(configuracion.getDescripcion() != null){
				txtdescripcion.setText(configuracion.getDescripcion());
			}
			if(configuracion.getValor() != null){
				txtvalor.setText(configuracion.getValor());
			}
		}
	}

	private Configuracion getConfiguracion() {
		Configuracion unConfiguracion = new Configuracion();
		try{
			unConfiguracion.setIdconfiguracion(Integer.valueOf(txtidconfiguracion.getText()));
		}catch (NumberFormatException e) {
			unConfiguracion.setIdconfiguracion(null);
		}
		unConfiguracion.setCodigo(txtcodigo.getText());
		unConfiguracion.setNombre(txtnombre.getText());
		unConfiguracion.setDescripcion(txtdescripcion.getText());
		unConfiguracion.setValor(txtvalor.getText());
		
		Label label = null;	
		vBoxMsg.getChildren().clear();
		Validator validator =PropertyResourceBundleMessageInterpolator.getValidation();
	    Set<ConstraintViolation<Configuracion>> inputErrors = validator.validate(unConfiguracion); 
	    for(ConstraintViolation<Configuracion> error: inputErrors){	    	
	    	label = new Label();
	    	label.setText(error.getMessage());
	    	vBoxMsg.getChildren().addAll(label);	    	
	    }
	    if(vBoxMsg.getChildren().size() > 0){
	    	return null;
	    }
		return unConfiguracion;
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(panelControlesEdit.getBtnGuardar())){
			Configuracion unConfiguracion = getConfiguracion();
			if(unConfiguracion != null){
				try {
					if(modoEdit){
						father.getServicio().update(unConfiguracion);
					}else{
						father.getServicio().insert(unConfiguracion);
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