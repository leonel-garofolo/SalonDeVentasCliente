/*
 * Created on 16 oct 2016 ( Time 00:44:53 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a composite Primary Key  


package org.salondeventas.cliente.desktop.modelo;

import java.io.Serializable;
import javax.validation.constraints.* ;
import org.hibernate.validator.constraints.*;
import java.math.BigDecimal;

public class Lineadeventa implements Serializable {

    private static final long serialVersionUID = 1L;

    private LineadeventaKey compositePrimaryKey ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    private BigDecimal precio       ;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    private Venta venta       ;

    private Producto producto    ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Lineadeventa() {
		super();
		this.compositePrimaryKey = new LineadeventaKey();       
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
    public void setIdlineadeventa( Integer idlineadeventa ) {
        this.compositePrimaryKey.setIdlineadeventa( idlineadeventa ) ;
    }
    public Integer getIdlineadeventa() {
        return this.compositePrimaryKey.getIdlineadeventa() ;
    }
    public void setIdproducto( Integer idproducto ) {
        this.compositePrimaryKey.setIdproducto( idproducto ) ;
    }
    public Integer getIdproducto() {
        return this.compositePrimaryKey.getIdproducto() ;
    }
    public void setIdventa( Integer idventa ) {
        this.compositePrimaryKey.setIdventa( idventa ) ;
    }
    public Integer getIdventa() {
        return this.compositePrimaryKey.getIdventa() ;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : precio ( DECIMAL ) 
    public void setPrecio( BigDecimal precio ) {
        this.precio = precio;
    }
    public BigDecimal getPrecio() {
        return this.precio;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setVenta( Venta venta ) {
        this.venta = venta;
    }
    public Venta getVenta() {
        return this.venta;
    }

    public void setProducto( Producto producto ) {
        this.producto = producto;
    }
    public Producto getProducto() {
        return this.producto;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        if ( compositePrimaryKey != null ) {  
            sb.append(compositePrimaryKey.toString());  
        }  
        else {  
            sb.append( "(null-key)" ); 
        }  
        sb.append("]:"); 
        sb.append(precio);
        return sb.toString(); 
    } 

}
