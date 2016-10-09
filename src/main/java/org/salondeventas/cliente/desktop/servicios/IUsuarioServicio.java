package org.salondeventas.cliente.desktop.servicios;

import org.salondeventas.cliente.desktop.modelo.Usuario;

public interface IUsuarioServicio extends IServicio<Usuario, java.lang.Long>{
	public Usuario load(Integer idusuario) throws Exception;

	public Usuario load(Usuario unUsuario) throws Exception;	
}
