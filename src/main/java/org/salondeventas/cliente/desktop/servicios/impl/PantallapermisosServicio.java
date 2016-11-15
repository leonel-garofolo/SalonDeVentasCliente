package org.salondeventas.cliente.desktop.servicios.impl;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.salondeventas.cliente.desktop.modelo.Pantallapermisos;
import org.salondeventas.cliente.desktop.servicios.IPantallapermisosServicio;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class PantallapermisosServicio extends Services<Pantallapermisos> implements IPantallapermisosServicio {	
		
	@Override
	public String insert(Pantallapermisos pantallapermisos) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "pantallapermisos/insert");		
		String stringJson = mapper.writeValueAsString(pantallapermisos);
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
	public String update(Pantallapermisos pantallapermisos) throws Exception {	
		client = Client.create();
		webResource = client.resource(USER_URI + "pantallapermisos/update");		
		String stringJson = mapper.writeValueAsString(pantallapermisos);
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
	public String delete(Pantallapermisos pantallapermisos) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "pantallapermisos/delete");		
		String stringJson = mapper.writeValueAsString(pantallapermisos);
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
	public Pantallapermisos load(Pantallapermisos pantallapermisos) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "pantallapermisos/load");		
		String stringJson = mapper.writeValueAsString(pantallapermisos);
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
	public List<Pantallapermisos> loadAll() throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "pantallapermisos/loadall");		
		
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