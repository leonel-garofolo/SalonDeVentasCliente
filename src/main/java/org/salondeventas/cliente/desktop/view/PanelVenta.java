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
import org.salondeventas.cliente.desktop.modelo.Venta;

public class PanelVenta extends BorderPane implements EventHandler<ActionEvent>{
	private boolean modoEdit = false;
	private PanelGrillaVenta father;
	
	@FXML
	private VBox vBoxMsg;


	@FXML
	private TextField txtidventa;



	public PanelVenta(PanelGrillaVenta father) {
		this.modoEdit = false;
		this.father = father;
		initComponentes();
    }

	public PanelVenta(PanelGrillaVenta father, int id) {
		this.modoEdit = true;
		this.father = father;
		initComponentes();
		loadEntity(id);		
    }

	private void loadEntity(int id) {
		try {
			Venta unVenta = new Venta();
			unVenta.setIdventa(id);
			unVenta =father.getServicio().load(unVenta);
			loadForm(unVenta);
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

	public void loadForm(Venta venta){
		if(venta !=null){
			txtidventa.setText(String.valueOf(venta.getIdventa()));
			//txtfecha.setText(venta.getFecha());
			//txtfechaPago.setText(venta.getFechaPago());
		}
	}

	private Venta getVenta() {
		Venta unVenta = new Venta();
		try{
			unVenta.setIdventa(Integer.valueOf(txtidventa.getText()));
		}catch (NumberFormatException e) {
			unVenta.setIdventa(null);
		}
		//unVenta.setFecha(txtfecha.getText());
		//unVenta.setFechaPago(txtfechaPago.getText());
		
		Label label = null;	
		Validator validator =PropertyResourceBundleMessageInterpolator.getValidation();
	    Set<ConstraintViolation<Venta>> inputErrors = validator.validate(unVenta); 
	    for(ConstraintViolation<Venta> error: inputErrors){	    	
	    	label = new Label();
	    	label.setText(error.getMessage());
	    	vBoxMsg.getChildren().addAll(label);	    	
	    }
	    if(vBoxMsg.getChildren().size() > 0){
	    	return null;
	    }
		return unVenta;
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource().equals(father.btnGuardar)){
			Venta unVenta = getVenta();
			if(unVenta != null){
				try {
					if(modoEdit){
						father.getServicio().update(unVenta);
					}else{
						father.getServicio().insert(unVenta);
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