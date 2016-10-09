package org.salondeventas.cliente.desktop.servicios;

import org.salondeventas.cliente.desktop.modelo.Licencias;

public interface ILicenciasServicio extends IServicio<Licencias, java.lang.Long>{
	public Licencias load(Integer numero) throws Exception;	
}
