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
import org.salondeventas.cliente.desktop.modelo.Producto;

public class PanelProducto extends BorderPane implements EventHandler<ActionEvent>{
	private boolean modoEdit = false;
	private PanelGrillaProducto father;

	@FXML
	private PanelControlesEdit panelControlesEdit;
	
	@FXML
	private VBox vBoxMsg;


	@FXML
	private TextField txtidproducto;

	@FXML
	private TextField txtnombre;

	@FXML
	private TextField txtcodbarras;

	@FXML
	private TextField txtmininventario;

	@FXML
	private TextField txtprecio;

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
                
        this.setLeft(null);
        this.setRight(null);
        panelControlesEdit.getBtnGuardar().setOnAction(this);        
        panelControlesEdit.getBtnCancelar().setOnAction(this);
        father.getTab().setContent(this);
        
	}

	public void loadForm(Producto producto){
		if(producto !=null){
			if(producto.getIdproducto() != null){
				txtidproducto.setText(String.valueOf(producto.getIdproducto()));
			}
			if(producto.getNombre() != null){
				txtnombre.setText(producto.getNombre());
			}
			if(producto.getCodbarras() != null){
				txtcodbarras.setText(producto.getCodbarras());
			}
			if(producto.getMininventario() != null){
				txtmininventario.setText(String.valueOf(producto.getMininventario()));
			}
			if(producto.getPrecio() != null){
				txtprecio.setText(String.valueOf(producto.getPrecio()));
			}
		}
	}

	private Producto getProducto() {
		Producto unProducto = new Producto();
		try{
			unProducto.setIdproducto(Integer.valueOf(txtidproducto.getText()));
		}catch (NumberFormatException e) {
			unProducto.setIdproducto(null);
		}
		unProducto.setNombre(txtnombre.getText());
		unProducto.setCodbarras(txtcodbarras.getText());
		try{
			unProducto.setMininventario(Integer.valueOf(txtmininventario.getText()));
		}catch (NumberFormatException e) {
			unProducto.setMininventario(null);
		}
		
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
		if(event.getSource().equals(panelControlesEdit.getBtnGuardar())){
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
		if(event.getSource().equals(panelControlesEdit.getBtnCancelar())){
			father.reLoad();    
		}
	}
}