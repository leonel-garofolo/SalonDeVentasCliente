package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.Usuario;
import org.salondeventas.cliente.desktop.servicios.IUsuarioServicio;
import org.salondeventas.cliente.desktop.servicios.impl.UsuarioServicio;
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

public class PanelGrillaUsuario extends BorderPane implements Initializable, IPanelControllerGrilla<IUsuarioServicio>, EventHandler<ActionEvent> {
	private IUsuarioServicio usuarioServicio;
	private Tab tab;
	private ResourceBundle resource;

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

	private ObservableList<Usuario> data;
	
	@FXML	
	private TableView<Usuario> tblUsuario;

	@FXML
	private NumberField txtidusuario;
	@FXML
	private StringField txtclave;
	@FXML
	private StringField txtnombre;
	
	public PanelGrillaUsuario(Tab tab) {
		this.tab = tab;
		this.resource = ResourceBundle.getBundle("i18n.ValidationMessages");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.getClass().getSimpleName() + ".fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.setResources(resource);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

	public void initialize(URL location, ResourceBundle resources) {
		usuarioServicio = new UsuarioServicio();
		tblUsuario.setOnMousePressed(new EventHandler<MouseEvent>() {
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
			data = FXCollections.observableArrayList(usuarioServicio.loadAll());
			tblUsuario.setItems(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public IUsuarioServicio getUsuarioServicio() {
		return usuarioServicio;
	}

	public void setUsuarioServicio(IUsuarioServicio usuarioServicio) {
		this.usuarioServicio = usuarioServicio;
	}	

	@Override
	public IUsuarioServicio getServicio() {
		return this.usuarioServicio;
	}

	public PanelGrillaUsuario getController() {
		return this;
	}	

	public TableView<Usuario> getTblUsuario() {
		return tblUsuario;
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
			
			ObservableList<Usuario> filter= data;

			if(!txtidusuario.getText().trim().equals("")){
				filter=filter.filtered(p -> p.getIdusuario() != null && p.getIdusuario() == Integer.valueOf(txtidusuario.getText()));
			}
			if(!txtclave.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getClave() != null && p.getClave().contains(txtclave.getText()));
			}
			if(!txtnombre.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getNombre() != null && p.getNombre().contains(txtnombre.getText()));
			}
			tblUsuario.setItems(new SortedList<>(filter));							
		}
		
		if (event.getSource().equals(btnLimpiar)) {			
			for(Node node: hButtonFilter.getChildren()){
				if(node instanceof TextField){
					((TextField)node).setText("");
				}								
			}		
			tblUsuario.setItems(data);							
		}

		if (event.getSource().equals(panelControlesABM.getBtnAgregar())) {
			new PanelUsuario(PanelGrillaUsuario.this);
		}
		if (event.getSource().equals(panelControlesABM.getBtnEditar())) {
			btnEditarAction();					
		}
		if (event.getSource().equals(panelControlesABM.getBtnEliminar())) {
			
			Usuario itemSelected = tblUsuario.getSelectionModel().getSelectedItem();
			if(itemSelected != null){
				try {
					usuarioServicio.delete(itemSelected);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			loadGrilla();
		}
	}
	
	private void btnEditarAction(){
		int itemSelected = tblUsuario.getSelectionModel().getSelectedIndex();
		if(itemSelected >= 0){
			new PanelUsuario(PanelGrillaUsuario.this, tblUsuario.getSelectionModel().getSelectedItem().getIdusuario());
		}
	}
}