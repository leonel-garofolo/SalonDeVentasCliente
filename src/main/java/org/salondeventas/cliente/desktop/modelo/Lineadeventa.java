
// This Bean has a composite Primary Key  


package org.salondeventas.cliente.desktop.modelo;

import java.io.Serializable;


public class Lineadeventa implements Serializable {

    private static final long serialVersionUID = 1L;

	private Integer    idlineadeventa ;
	private Integer    idproducto   ;
	private Integer    idventa      ;


    private Venta venta       ;
    private Producto producto    ;

    public Lineadeventa() {
		super();
    }
    
    public void setIdlineadeventa( Integer idlineadeventa ) {
        this.idlineadeventa = idlineadeventa ;
    }
    public Integer getIdlineadeventa() {
        return this.idlineadeventa;
    }
    public void setIdproducto( Integer idproducto ) {
        this.idproducto = idproducto ;
    }
    public Integer getIdproducto() {
        return this.idproducto;
    }
    public void setIdventa( Integer idventa ) {
        this.idventa = idventa ;
    }
    public Integer getIdventa() {
        return this.idventa;
    }


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

}
