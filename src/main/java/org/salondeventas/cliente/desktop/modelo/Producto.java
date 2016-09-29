
// This Bean has a basic Primary Key (not composite) 

package org.salondeventas.cliente.desktop.modelo;

import java.io.Serializable;

import java.util.List;

public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer    idproducto   ;

    private String     detalle      ;
    private String     nombre       ;
	// "precioproductoIdprecioproducto" (column "precioproducto_idprecioproducto") is not defined by itself because used as FK in a link 

    private Precioproducto precioproducto;
    private List<Lineadeventa> listOfLineadeventa;

    public Producto() {
		super();
    }
    
    public void setIdproducto( Integer idproducto ) {
        this.idproducto = idproducto ;
    }
    public Integer getIdproducto() {
        return this.idproducto;
    }

    //--- DATABASE MAPPING : detalle ( VARCHAR ) 
    public void setDetalle( String detalle ) {
        this.detalle = detalle;
    }
    public String getDetalle() {
        return this.detalle;
    }

    //--- DATABASE MAPPING : nombre ( VARCHAR ) 
    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return this.nombre;
    }


    public void setPrecioproducto( Precioproducto precioproducto ) {
        this.precioproducto = precioproducto;
    }
    public Precioproducto getPrecioproducto() {
        return this.precioproducto;
    }

    public void setListOfLineadeventa( List<Lineadeventa> listOfLineadeventa ) {
        this.listOfLineadeventa = listOfLineadeventa;
    }
    public List<Lineadeventa> getListOfLineadeventa() {
        return this.listOfLineadeventa;
    }

}
