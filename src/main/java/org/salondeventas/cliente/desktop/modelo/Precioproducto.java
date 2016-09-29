
// This Bean has a basic Primary Key (not composite) 

package org.salondeventas.cliente.desktop.modelo;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Precioproducto implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer    idprecioproducto ;

    private Date       fecha        ;
    private BigDecimal importe      ;

    private List<Producto> listOfProducto;

    public Precioproducto() {
		super();
    }
    
    public void setIdprecioproducto( Integer idprecioproducto ) {
        this.idprecioproducto = idprecioproducto ;
    }
    public Integer getIdprecioproducto() {
        return this.idprecioproducto;
    }

    //--- DATABASE MAPPING : fecha ( DATETIME ) 
    public void setFecha( Date fecha ) {
        this.fecha = fecha;
    }
    public Date getFecha() {
        return this.fecha;
    }

    //--- DATABASE MAPPING : importe ( DECIMAL ) 
    public void setImporte( BigDecimal importe ) {
        this.importe = importe;
    }
    public BigDecimal getImporte() {
        return this.importe;
    }


    public void setListOfProducto( List<Producto> listOfProducto ) {
        this.listOfProducto = listOfProducto;
    }
    public List<Producto> getListOfProducto() {
        return this.listOfProducto;
    }

}
