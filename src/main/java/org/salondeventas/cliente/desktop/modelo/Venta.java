
// This Bean has a basic Primary Key (not composite) 

package org.salondeventas.cliente.desktop.modelo;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer    idventa      ;

    private Date       fecha        ;
    private Date       fechaPago    ;

    private List<Lineadeventa> listOfLineadeventa;

    public Venta() {
		super();
    }
    
    public void setIdventa( Integer idventa ) {
        this.idventa = idventa ;
    }
    public Integer getIdventa() {
        return this.idventa;
    }

    //--- DATABASE MAPPING : fecha ( DATETIME ) 
    public void setFecha( Date fecha ) {
        this.fecha = fecha;
    }
    public Date getFecha() {
        return this.fecha;
    }

    //--- DATABASE MAPPING : fecha_pago ( DATETIME ) 
    public void setFechaPago( Date fechaPago ) {
        this.fechaPago = fechaPago;
    }
    public Date getFechaPago() {
        return this.fechaPago;
    }


    public void setListOfLineadeventa( List<Lineadeventa> listOfLineadeventa ) {
        this.listOfLineadeventa = listOfLineadeventa;
    }
    public List<Lineadeventa> getListOfLineadeventa() {
        return this.listOfLineadeventa;
    }

}
