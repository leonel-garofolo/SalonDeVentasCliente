/*
 * Created on 13 nov 2016 ( Time 20:13:48 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a composite Primary Key  


package org.salondeventas.cliente.desktop.modelo;

import java.io.Serializable;
import javax.validation.constraints.* ;
import org.hibernate.validator.constraints.*;

public class Perfilespantallas implements Serializable {

    private static final long serialVersionUID = 1L;

    private PerfilespantallasKey compositePrimaryKey ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Perfilespantallas() {
		super();
		this.compositePrimaryKey = new PerfilespantallasKey();       
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
    public void setIdperfiles( Integer idperfiles ) {
        this.compositePrimaryKey.setIdperfiles( idperfiles ) ;
    }
    public Integer getIdperfiles() {
        return this.compositePrimaryKey.getIdperfiles() ;
    }
    public void setIdpantallas( Integer idpantallas ) {
        this.compositePrimaryKey.setIdpantallas( idpantallas ) ;
    }
    public Integer getIdpantallas() {
        return this.compositePrimaryKey.getIdpantallas() ;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

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
        return sb.toString(); 
    } 

}
