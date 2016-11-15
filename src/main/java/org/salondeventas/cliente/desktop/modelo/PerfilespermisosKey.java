/*
 * Created on 13 nov 2016 ( Time 20:13:48 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.salondeventas.cliente.desktop.modelo;
import java.io.Serializable;


public class PerfilespermisosKey implements Serializable {
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY KEY ATTRIBUTES 
    //----------------------------------------------------------------------
    private Integer    idperfiles   ;
    
    private String     idpermisos   ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTORS
    //----------------------------------------------------------------------
    public PerfilespermisosKey() {
        super();
    }

    public PerfilespermisosKey( Integer idperfiles, String idpermisos ) {
        super();
        this.idperfiles = idperfiles ;
        this.idpermisos = idpermisos ;
    }
    
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR KEY FIELDS
    //----------------------------------------------------------------------
    public void setIdperfiles( Integer value ) {
        this.idperfiles = value;
    }
    public Integer getIdperfiles() {
        return this.idperfiles;
    }

    public void setIdpermisos( String value ) {
        this.idpermisos = value;
    }
    public String getIdpermisos() {
        return this.idpermisos;
    }


    //----------------------------------------------------------------------
    // equals METHOD
    //----------------------------------------------------------------------
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		PerfilespermisosKey other = (PerfilespermisosKey) obj; 
		//--- Attribute idperfiles
		if ( idperfiles == null ) { 
			if ( other.idperfiles != null ) 
				return false ; 
		} else if ( ! idperfiles.equals(other.idperfiles) ) 
			return false ; 
		//--- Attribute idpermisos
		if ( idpermisos == null ) { 
			if ( other.idpermisos != null ) 
				return false ; 
		} else if ( ! idpermisos.equals(other.idpermisos) ) 
			return false ; 
		return true; 
	} 


    //----------------------------------------------------------------------
    // hashCode METHOD
    //----------------------------------------------------------------------
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		
		//--- Attribute idperfiles
		result = prime * result + ((idperfiles == null) ? 0 : idperfiles.hashCode() ) ; 
		//--- Attribute idpermisos
		result = prime * result + ((idpermisos == null) ? 0 : idpermisos.hashCode() ) ; 
		
		return result; 
	} 


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
		StringBuffer sb = new StringBuffer(); 
		sb.append(idperfiles); 
		sb.append("|"); 
		sb.append(idpermisos); 
        return sb.toString();
    }
}
