/*
 * Created on 9 oct 2016 ( Time 11:29:08 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a composite Primary Key  


package org.salondeventas.cliente.desktop.modelo;

import java.io.Serializable;
import javax.validation.constraints.* ;
import org.hibernate.validator.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

public class Precioproducto implements Serializable {

    private static final long serialVersionUID = 1L;

    private PrecioproductoKey compositePrimaryKey ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
		@NotNull
		    private Date       fecha        ;
		@NotNull
		    private BigDecimal importe      ;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    private Producto producto    ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Precioproducto() {
		super();
		this.compositePrimaryKey = new PrecioproductoKey();       
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
    public void setIdprecioproducto( Integer idprecioproducto ) {
        this.compositePrimaryKey.setIdprecioproducto( idprecioproducto ) ;
    }
    public Integer getIdprecioproducto() {
        return this.compositePrimaryKey.getIdprecioproducto() ;
    }
    public void setIdproducto( Integer idproducto ) {
        this.compositePrimaryKey.setIdproducto( idproducto ) ;
    }
    public Integer getIdproducto() {
        return this.compositePrimaryKey.getIdproducto() ;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
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


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
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
        sb.append(fecha);
        sb.append("|");
        sb.append(importe);
        return sb.toString(); 
    } 

}
