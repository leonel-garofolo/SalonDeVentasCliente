package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.salondeventas.cliente.desktop.PropertyResourceBundleMessageInterpolator;
import org.salondeventas.cliente.desktop.modelo.Lineadeventa;
import org.salondeventas.cliente.desktop.modelo.Producto;
import org.salondeventas.cliente.desktop.modelo.Venta;
import org.salondeventas.cliente.desktop.servicios.ILineadeventaServicio;
import org.salondeventas.cliente.desktop.servicios.IProductoServicio;
import org.salondeventas.cliente.desktop.servicios.impl.LineadeventaServicio;
import org.salondeventas.cliente.desktop.servicios.impl.ProductoServicio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PanelVenta extends BorderPane implements EventHandler<ActionEvent>{
	private boolean modoEdit = false;
	private PanelGrillaVenta father;	
	private IProductoServicio productoServicio;
	private ILineadeventaServicio iLineadeventaServicio;
	
	@FXML
	private VBox vBoxMsg;


	@FXML
	private TextField txtidventa;

	@FXML
	private DatePicker dprfecha;

	@FXML
	private DatePicker dprfechaPago;

	@FXML
	private TextField txttotal;
	
	@FXML
	private TableView<Lineadeventa> tblLineaDeVentas;
	
	@FXML
	private TableColumn colProducto;
	
	@FXML
	private TableColumn colImporte;
	
	@FXML
	private Button btnAgregar;
	
	private ObservableList<Lineadeventa> data ;

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
        productoServicio = new ProductoServicio();   
        iLineadeventaServicio = new LineadeventaServicio();
        
        this.setTop(father.generarPanelFormulario());
        this.setLeft(null);
        this.setRight(null);
        father.btnGuardar.setOnAction(this);        
        father.btnCancelar.setOnAction(this);
        father.getTab().setContent(this);
        btnAgregar.setOnAction(this);
        
		dprfecha.setValue(LocalDate.now());			
		dprfechaPago.setValue(LocalDate.now());			
	}

	@SuppressWarnings("unchecked")
	public void loadForm(Venta venta){
		if(venta !=null){
			if(venta.getIdventa() != null){
				txtidventa.setText(String.valueOf(venta.getIdventa()));
			}
			if(venta.getFecha() != null){
				dprfecha.setValue(new java.sql.Date(venta.getFecha().getTime()).toLocalDate());		
			}
			if(venta.getFechaPago() != null){
				dprfechaPago.setValue(new java.sql.Date(venta.getFechaPago().getTime()).toLocalDate());		
			}
			if(venta.getTotal() != null){
				txttotal.setText(String.valueOf(venta.getTotal()));
			}
			
			 							
			data = FXCollections.observableArrayList(venta.getListOfLineadeventa());
			tblLineaDeVentas.setItems(data);
			
			ObservableList<Producto> productos = null;
			try {
				productos = FXCollections.observableArrayList (productoServicio.loadAll());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						  
			if(productos != null){
				colProducto.setCellFactory(ComboBoxTableCell.forTableColumn(null,productos));
				
				colProducto.setOnEditCommit(
				    new EventHandler<CellEditEvent<Lineadeventa, Producto>>() {			      

						@Override
						public void handle(CellEditEvent<Lineadeventa, Producto> t) {
							Producto prodSelected = t.getNewValue();							
							((Lineadeventa) t.getTableView().getItems().get(t.getTablePosition().getRow())).setIdventa(Integer.valueOf(txtidventa.getText()));
							((Lineadeventa) t.getTableView().getItems().get(t.getTablePosition().getRow())).setIdproducto(prodSelected.getIdproducto());							
							((Lineadeventa) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPrecio(prodSelected.getPrecio());
							((Lineadeventa) t.getTableView().getItems().get(t.getTablePosition().getRow())).setProducto(prodSelected);
							
							tblLineaDeVentas.getItems().get(t.getTablePosition().getRow()).setPrecio(prodSelected.getPrecio());
						};
				    }
				);
				
			}	
			/*
			colImporte.setCellFactory(column -> {
			    return new TableCell<String, Lineadeventa>() {
			        @Override
			        protected void updateItem(Lineadeventa item, boolean empty) {
			            super.updateItem(item, empty);

			            if (item == null || empty) {
			                setText(null);
			                setStyle("");
			            } else {
			                // Format date.
			                setText(String.valueOf(item.getPrecio()));

			                    setTextFill(Color.CHOCOLATE);
			                    setStyle("-fx-background-color: yellow");
			              
			            }
			        }
			    };
			});
			*/
		}
	}

	private Venta getVenta() {
		Venta unVenta = new Venta();
		try{
			unVenta.setIdventa(Integer.valueOf(txtidventa.getText()));
		}catch (NumberFormatException e) {
			unVenta.setIdventa(null);
		}
		unVenta.setFecha(java.sql.Date.valueOf(dprfecha.getValue()));
		unVenta.setFechaPago(java.sql.Date.valueOf(dprfechaPago.getValue()));				
		
		Label label = null;	
		vBoxMsg.getChildren().clear();
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
	
	private List<Lineadeventa> getLineasDeVentas(Venta unaVenta) {
		Label label = null;	
		vBoxMsg.getChildren().clear();
		Validator validator =PropertyResourceBundleMessageInterpolator.getValidation();
		List<Lineadeventa> lineasDeVentas = new ArrayList<Lineadeventa>();
		for(Lineadeventa unaLineaDeVenta: tblLineaDeVentas.getItems()){			
			unaLineaDeVenta.setIdventa(unaVenta.getIdventa());		
			lineasDeVentas.add(unaLineaDeVenta);
			Set<ConstraintViolation<Lineadeventa>> inputErrors = validator.validate(unaLineaDeVenta); 
		    for(ConstraintViolation<Lineadeventa> error: inputErrors){	    	
		    	label = new Label();
		    	label.setText(error.getMessage());
		    	vBoxMsg.getChildren().addAll(label);	    	
		    }
		    if(vBoxMsg.getChildren().size() > 0){
		    	return null;
		    }
		}
		return 	lineasDeVentas;				    
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
					
					List<Lineadeventa> lineas=getLineasDeVentas(unVenta);
					for(Lineadeventa linea: lineas){
						iLineadeventaServicio.insert(linea);
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
		
		if(event.getSource().equals(btnAgregar)){
			data.add(new Lineadeventa());			
		}
	}
}