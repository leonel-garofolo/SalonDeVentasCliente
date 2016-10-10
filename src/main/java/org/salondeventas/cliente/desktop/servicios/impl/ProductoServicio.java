package org.salondeventas.cliente.desktop.servicios.impl;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.salondeventas.cliente.desktop.modelo.Producto;
import org.salondeventas.cliente.desktop.servicios.IProductoServicio;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class ProductoServicio extends Services<Producto> implements IProductoServicio {	
		
	@Override
	public String insert(Producto producto) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "usuario/insert");		
		String stringJson = mapper.writeValueAsString(producto);
		response = webResource
				.queryParam("usuario", "leonel")
				.queryParam("clave", "123")
				.type(MediaType.APPLICATION_JSON_TYPE)					
				.post(ClientResponse.class, stringJson);
		
		if (response.getStatus() != 200) {
			throw new Exception("Failed : HTTP error code : "
			     + response.getStatus());
		}		
		
		return response.getEntity(String.class);
	}
	
	@Override
	public String update(Producto producto) throws Exception {	
		client = Client.create();
		webResource = client.resource(USER_URI + "usuario/update");		
		String stringJson = mapper.writeValueAsString(producto);
		response = webResource
				.queryParam("usuario", "leonel")
				.queryParam("clave", "123")
				.type(MediaType.APPLICATION_JSON_TYPE)					
				.post(ClientResponse.class, stringJson);
		
		if (response.getStatus() != 200) {
			throw new Exception("Failed : HTTP error code : "
			     + response.getStatus());
		}		
		
		return response.getEntity(String.class);
	}
	
	@Override
	public String delete(Producto producto) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "usuario/delete");		
		String stringJson = mapper.writeValueAsString(producto);
		response = webResource
				.queryParam("usuario", "leonel")
				.queryParam("clave", "123")
				.type(MediaType.APPLICATION_JSON_TYPE)					
				.post(ClientResponse.class, stringJson);
		
		if (response.getStatus() != 200) {
			throw new Exception("Failed : HTTP error code : "
			     + response.getStatus());
		}		
		
		return response.getEntity(String.class);
	}
	
	@Override
	public Producto load(Producto producto) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "usuario/load");		
		String stringJson = mapper.writeValueAsString(producto);
		response = webResource
				.queryParam("usuario", "leonel")
				.queryParam("clave", "123")
				.type(MediaType.APPLICATION_JSON_TYPE)					
				.post(ClientResponse.class, stringJson);
		
		if (response.getStatus() != 200) {
			throw new Exception("Failed : HTTP error code : "
			     + response.getStatus());
		}				
		
		return this.buildJsonToObject(response.getEntity(String.class));
	}
	
	@Override
	public List<Producto> loadAll() throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "usuario/loadall");		
		
		response = webResource
				.queryParam("usuario", "leonel")
				.queryParam("clave", "123")
				.type(MediaType.APPLICATION_JSON_TYPE)					
				.post(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			throw new Exception("Failed : HTTP error code : "
			     + response.getStatus());
		}				
		
		return this.buildJsonToObjectArray(response.getEntity(String.class));
	}
}