
// This Bean has a basic Primary Key (not composite) 

package org.salondeventas.cliente.desktop.modelo;

import java.io.Serializable;


public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer    idempresa    ;

    private String     descripcion  ;
    private String     direccion    ;
    private byte[]     logo         ;
    private String     nombre       ;
    private String     telefono     ;


    public Empresa() {
		super();
    }
    
    public void setIdempresa( Integer idempresa ) {
        this.idempresa = idempresa ;
    }
    public Integer getIdempresa() {
        return this.idempresa;
    }

    //--- DATABASE MAPPING : descripcion ( VARCHAR ) 
    public void setDescripcion( String descripcion ) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return this.descripcion;
    }

    //--- DATABASE MAPPING : direccion ( VARCHAR ) 
    public void setDireccion( String direccion ) {
        this.direccion = direccion;
    }
    public String getDireccion() {
        return this.direccion;
    }

    //--- DATABASE MAPPING : logo ( LONGBLOB ) 
    public void setLogo( byte[] logo ) {
        this.logo = logo;
    }
    public byte[] getLogo() {
        return this.logo;
    }

    //--- DATABASE MAPPING : nombre ( VARCHAR ) 
    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return this.nombre;
    }

    //--- DATABASE MAPPING : telefono ( VARCHAR ) 
    public void setTelefono( String telefono ) {
        this.telefono = telefono;
    }
    public String getTelefono() {
        return this.telefono;
    }


}
