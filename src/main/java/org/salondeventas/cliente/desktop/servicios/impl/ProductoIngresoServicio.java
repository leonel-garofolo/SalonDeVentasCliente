package org.salondeventas.cliente.desktop.servicios.impl;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.salondeventas.cliente.desktop.modelo.ProductoIngreso;
import org.salondeventas.cliente.desktop.servicios.IProductoIngresoServicio;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class ProductoIngresoServicio extends Services<ProductoIngreso> implements IProductoIngresoServicio {	
		
	@Override
	public String insert(ProductoIngreso productoIngreso) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "productoingreso/insert");		
		String stringJson = mapper.writeValueAsString(productoIngreso);
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
	public String update(ProductoIngreso productoIngreso) throws Exception {	
		client = Client.create();
		webResource = client.resource(USER_URI + "productoingreso/update");		
		String stringJson = mapper.writeValueAsString(productoIngreso);
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
	public String delete(ProductoIngreso productoIngreso) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "productoingreso/delete");		
		String stringJson = mapper.writeValueAsString(productoIngreso);
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
	public ProductoIngreso load(ProductoIngreso productoIngreso) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "productoingreso/load");		
		String stringJson = mapper.writeValueAsString(productoIngreso);
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
	public List<ProductoIngreso> loadAll() throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "productoingreso/loadall");		
		
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