
// This Bean has a basic Primary Key (not composite) 

package org.salondeventas.cliente.desktop.modelo;

import java.io.Serializable;


public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer    idusuario    ;

    private String     clave        ;
    private String     nombre       ;


    public Usuario() {
		super();
    }
    
    public void setIdusuario( Integer idusuario ) {
        this.idusuario = idusuario ;
    }
    public Integer getIdusuario() {
        return this.idusuario;
    }

    //--- DATABASE MAPPING : clave ( VARCHAR ) 
    public void setClave( String clave ) {
        this.clave = clave;
    }
    public String getClave() {
        return this.clave;
    }

    //--- DATABASE MAPPING : nombre ( VARCHAR ) 
    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return this.nombre;
    }


}
