package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.Producto;
import org.salondeventas.cliente.desktop.servicios.IProductoServicio;
import org.salondeventas.cliente.desktop.servicios.impl.ProductoServicio;
import org.javafx.controls.panels.PanelControlesABM;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.javafx.controls.customs.DecimalField;
import org.javafx.controls.customs.NumberField;
import org.javafx.controls.customs.StringField;

public class PanelGrillaProducto extends BorderPane implements Initializable, IPanelControllerGrilla<IProductoServicio>, EventHandler<ActionEvent> {
	private IProductoServicio productoServicio;
	private Tab tab;

	@FXML
	private PanelControlesABM panelControlesABM;

	@FXML
	VBox vTop;

	@FXML
	HBox hButtonFilter;

	@FXML
	private Button btnBuscar;
	
	@FXML
	private Button btnLimpiar;

	private ObservableList<Producto> data;
	
	@FXML	
	private TableView<Producto> tblProducto;

	@FXML
	private NumberField txtidproducto;
	@FXML
	private StringField txtnombre;
	@FXML
	private StringField txtcodbarras;
	@FXML
	private NumberField txtmininventario;
	@FXML
	private DecimalField txtprecio;
	@FXML
	private NumberField txtcantidadStock;
	
	public PanelGrillaProducto(Tab tab) {
		this.tab = tab;
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.getClass().getSimpleName() + ".fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.setResources(ResourceBundle.getBundle("i18n.ValidationMessages"));
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

	public void initialize(URL location, ResourceBundle resources) {
		productoServicio = new ProductoServicio();
		tblProducto.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	btnEditarAction();          
		        }
		    }
		});
		loadGrilla();
		this.autosize();

		panelControlesABM.getBtnAgregar().setOnAction(this);
		panelControlesABM.getBtnEditar().setOnAction(this);
		panelControlesABM.getBtnEliminar().setOnAction(this);
		this.btnBuscar.setOnAction(this);
		this.btnLimpiar.setOnAction(this);
	}
	
	public void loadGrilla(){
		try {
			data = FXCollections.observableArrayList(productoServicio.loadAll());
			tblProducto.setItems(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public IProductoServicio getProductoServicio() {
		return productoServicio;
	}

	public void setProductoServicio(IProductoServicio productoServicio) {
		this.productoServicio = productoServicio;
	}	

	@Override
	public IProductoServicio getServicio() {
		return this.productoServicio;
	}

	public PanelGrillaProducto getController() {
		return this;
	}	

	public TableView<Producto> getTblProducto() {
		return tblProducto;
	}

	public Tab getTab() {
		return tab;
	}

	@Override
	public void reLoad() {
		tab.setContent(this);
		loadGrilla();
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource().equals(btnBuscar)) {	
			loadGrilla();
			
			ObservableList<Producto> filter= data;

			if(!txtidproducto.getText().trim().equals("")){
				filter=filter.filtered(p -> p.getIdproducto() != null && p.getIdproducto() == Integer.valueOf(txtidproducto.getText()));
			}
			if(!txtnombre.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getNombre() != null && p.getNombre().contains(txtnombre.getText()));
			}
			if(!txtcodbarras.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getCodbarras() != null && p.getCodbarras().contains(txtcodbarras.getText()));
			}
			if(!txtmininventario.getText().trim().equals("")){
				filter=filter.filtered(p -> p.getMininventario() != null && p.getMininventario() == Integer.valueOf(txtmininventario.getText()));
			}
			if(!txtcantidadStock.getText().trim().equals("")){
				filter=filter.filtered(p -> p.getCantidadStock() != null && p.getCantidadStock() == Integer.valueOf(txtcantidadStock.getText()));
			}
			tblProducto.setItems(new SortedList<>(filter));							
		}
		
		if (event.getSource().equals(btnLimpiar)) {			
			for(Node node: hButtonFilter.getChildren()){
				if(node instanceof TextField){
					((TextField)node).setText("");
				}								
			}		
			tblProducto.setItems(data);							
		}

		if (event.getSource().equals(panelControlesABM.getBtnAgregar())) {
			new PanelProducto(PanelGrillaProducto.this);
		}
		if (event.getSource().equals(panelControlesABM.getBtnEditar())) {
			btnEditarAction();					
		}
		if (event.getSource().equals(panelControlesABM.getBtnEliminar())) {
			
			Producto itemSelected = tblProducto.getSelectionModel().getSelectedItem();
			if(itemSelected != null){
				try {
					productoServicio.delete(itemSelected);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			loadGrilla();
		}
	}
	
	private void btnEditarAction(){
		int itemSelected = tblProducto.getSelectionModel().getSelectedIndex();
		if(itemSelected >= 0){
			new PanelProducto(PanelGrillaProducto.this, tblProducto.getSelectionModel().getSelectedItem().getIdproducto());
		}
	}
}