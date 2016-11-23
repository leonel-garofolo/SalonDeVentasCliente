package org.salondeventas.cliente.desktop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.salondeventas.cliente.desktop.modelo.informes.VCantProdVendidos;
import org.salondeventas.cliente.desktop.servicios.IVCantProdVendidosServicio;
import org.salondeventas.cliente.desktop.servicios.impl.VCantProdVendidosServicio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PanelGrillaVCantProdVendidos extends BorderPane implements Initializable, IPanelControllerGrilla<IVCantProdVendidosServicio>, EventHandler<ActionEvent> {
	private IVCantProdVendidosServicio vCantProdVendidosServicio;
	private Tab tab;

	@FXML
	VBox vTop;

	@FXML
	HBox hButtonFilter;

	@FXML
	private Button btnBuscar;
	
	@FXML
	private Button btnLimpiar;

	private ObservableList<VCantProdVendidos> data;
	private ObservableList<PieChart.Data> pieChartData;
	
	@FXML	
	private TableView<VCantProdVendidos> tblVCantProdVendidos;

	@FXML
	private TextField txtnombre;
	
	public PanelGrillaVCantProdVendidos(Tab tab) {
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
		vCantProdVendidosServicio = new VCantProdVendidosServicio();		
		loadGrilla();
		this.autosize();
		
		this.btnBuscar.setOnAction(this);
		this.btnLimpiar.setOnAction(this);				

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Cantidad de Productos");
	    this.setBottom(chart);
	}
	
	public void loadGrilla(){
		try {
			data = FXCollections.observableArrayList(vCantProdVendidosServicio.loadAll());
			pieChartData =FXCollections.observableArrayList();
			for(VCantProdVendidos unDato: data){
				pieChartData.add(new Data(unDato.getNombre(), unDato.getCantidad().doubleValue()));
			}
			tblVCantProdVendidos.setItems(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public IVCantProdVendidosServicio getVCantProdVendidosServicio() {
		return vCantProdVendidosServicio;
	}

	public void setVCantProdVendidosServicio(IVCantProdVendidosServicio vCantProdVendidosServicio) {
		this.vCantProdVendidosServicio = vCantProdVendidosServicio;
	}	

	@Override
	public IVCantProdVendidosServicio getServicio() {
		return this.vCantProdVendidosServicio;
	}

	public PanelGrillaVCantProdVendidos getController() {
		return this;
	}	

	public TableView<VCantProdVendidos> getTblVCantProdVendidos() {
		return tblVCantProdVendidos;
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
			
			ObservableList<VCantProdVendidos> filter= data;

			if(!txtnombre.getText().trim().equals("")){
				filter= filter.filtered(p -> p.getNombre() != null && p.getNombre().contains(txtnombre.getText()));
			}
			tblVCantProdVendidos.setItems(new SortedList<>(filter));							
		}
		
		if (event.getSource().equals(btnLimpiar)) {			
			for(Node node: hButtonFilter.getChildren()){
				if(node instanceof TextField){
					((TextField)node).setText("");
				}								
			}		
			tblVCantProdVendidos.setItems(data);							
		}		
	}		
}