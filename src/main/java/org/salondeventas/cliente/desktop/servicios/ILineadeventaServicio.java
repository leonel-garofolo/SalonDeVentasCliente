package org.salondeventas.cliente.desktop.servicios;

import org.salondeventas.cliente.desktop.modelo.Lineadeventa;

public interface ILineadeventaServicio extends IServicio<Lineadeventa, java.lang.Long>{
	public Lineadeventa load(Integer idlineadeventa, Integer idproducto, Integer idventa) throws Exception;	
}
