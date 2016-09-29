
// This Bean has a basic Primary Key (not composite) 

package org.salondeventas.cliente.desktop.modelo;

import java.io.Serializable;

import java.util.Date;

public class Licencias implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer    numero       ;

    private Date       fechaDesde   ;
    private Date       fechaHasta   ;
    private Boolean    usada        ;


    public Licencias() {
		super();
    }
    
    public void setNumero( Integer numero ) {
        this.numero = numero ;
    }
    public Integer getNumero() {
        return this.numero;
    }

    //--- DATABASE MAPPING : fecha_desde ( DATETIME ) 
    public void setFechaDesde( Date fechaDesde ) {
        this.fechaDesde = fechaDesde;
    }
    public Date getFechaDesde() {
        return this.fechaDesde;
    }

    //--- DATABASE MAPPING : fecha_hasta ( DATETIME ) 
    public void setFechaHasta( Date fechaHasta ) {
        this.fechaHasta = fechaHasta;
    }
    public Date getFechaHasta() {
        return this.fechaHasta;
    }

    //--- DATABASE MAPPING : usada ( BIT ) 
    public void setUsada( Boolean usada ) {
        this.usada = usada;
    }
    public Boolean getUsada() {
        return this.usada;
    }


}
