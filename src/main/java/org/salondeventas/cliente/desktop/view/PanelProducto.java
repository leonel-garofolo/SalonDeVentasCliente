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
import java.time.LocalDate;


import org.salondeventas.cliente.desktop.PropertyResourceBundleMessageInterpolator;
import org.salondeventas.cliente.desktop.modelo.Producto;

public class PanelProducto extends BorderPane implements EventHandler<ActionEvent>{
	private boolean modoEdit = false;
	private PanelGrillaProducto father;
	
	@FXML
	private VBox vBoxMsg;


	@FXML
	private TextField txtidproducto;

	@FXML
	private TextField txtdetalle;

	@FXML
	private TextField txtnombre;

	public PanelProducto(PanelGrillaProducto father) {
		this.modoEdit = false;
		this.father = father;
		initComponentes();
    }

	public PanelProducto(PanelGrillaProducto father, int id) {
		this.modoEdit = true;
		this.father = father;
		initComponentes();
		loadEntity(id);		
    }

	private void loadEntity(int id) {
		try {
			Producto unProducto = new Producto();
			unProducto.setIdproducto(id);
			unProducto =father.getServicio().load(unProducto);
			loadForm(unProducto);
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

	public void loadForm(Producto producto){
		if(producto !=null){
			txtidproducto.setText(String.valueOf(producto.getIdproducto()));
			txtdetalle.setText(producto.getDetalle());
			txtnombre.setText(producto.getNombre());
		}
	}

	private Producto getProducto() {
		Producto unProducto = new Producto();
		try{
			unProducto.setIdproducto(Integer.valueOf(txtidproducto.getText()));
		}catch (NumberFormatException e) {
			unProducto.setIdproducto(null);
		}
		unProducto.setDetalle(txtdetalle.getText());
		unProducto.setNombre(txtnombre.getText());
		
		Label label = null;	
		vBoxMsg.getChildren().clear();
		Validator validator =PropertyResourceBundleMessageInterpolator.getValidation();
	    Set<ConstraintViolation<Producto>> inputErrors = validator.validate(unProducto); 
	    for(ConstraintViolation<Producto> error: inputErrors){	    	
	    	label = new Label();
	    	label.setText(error.getMessage());
	    	vBoxMsg.getChildren().addAll(label);	    	
	    }
	    if(vBoxMsg.getChildren().size() > 0){
	    	return null;
	    }
		return unProducto;
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(father.btnGuardar)){
			Producto unProducto = getProducto();
			if(unProducto != null){
				try {
					if(modoEdit){
						father.getServicio().update(unProducto);
					}else{
						father.getServicio().insert(unProducto);
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