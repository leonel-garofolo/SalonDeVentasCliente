package org.salondeventas.cliente.desktop.servicios.impl;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.salondeventas.cliente.desktop.modelo.Perfilespermisos;
import org.salondeventas.cliente.desktop.servicios.IPerfilespermisosServicio;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class PerfilespermisosServicio extends Services<Perfilespermisos> implements IPerfilespermisosServicio {	
		
	@Override
	public String insert(Perfilespermisos perfilespermisos) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "perfilespermisos/insert");		
		String stringJson = mapper.writeValueAsString(perfilespermisos);
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
	public String update(Perfilespermisos perfilespermisos) throws Exception {	
		client = Client.create();
		webResource = client.resource(USER_URI + "perfilespermisos/update");		
		String stringJson = mapper.writeValueAsString(perfilespermisos);
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
	public String delete(Perfilespermisos perfilespermisos) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "perfilespermisos/delete");		
		String stringJson = mapper.writeValueAsString(perfilespermisos);
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
	public Perfilespermisos load(Perfilespermisos perfilespermisos) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "perfilespermisos/load");		
		String stringJson = mapper.writeValueAsString(perfilespermisos);
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
	public List<Perfilespermisos> loadAll() throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "perfilespermisos/loadall");		
		
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