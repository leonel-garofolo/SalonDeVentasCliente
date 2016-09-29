package org.salondeventas.cliente.desktop.servicios;

import java.io.Serializable;
import java.util.List;

public interface IServicio<T, ID extends Serializable> {
	
	public String agregar(T entity) throws Exception;
	public String borrar(T entity) throws Exception;	
	public T obtener(long id) throws Exception;
	public String actualizar(T entity) throws Exception;
	public List<T> obtenerTodos() throws Exception;
}