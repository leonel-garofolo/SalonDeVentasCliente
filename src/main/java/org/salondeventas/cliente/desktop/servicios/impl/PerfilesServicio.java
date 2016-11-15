package org.salondeventas.cliente.desktop.servicios.impl;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.salondeventas.cliente.desktop.modelo.Perfiles;
import org.salondeventas.cliente.desktop.servicios.IPerfilesServicio;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class PerfilesServicio extends Services<Perfiles> implements IPerfilesServicio {	
		
	@Override
	public String insert(Perfiles perfiles) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "perfiles/insert");		
		String stringJson = mapper.writeValueAsString(perfiles);
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
	public String update(Perfiles perfiles) throws Exception {	
		client = Client.create();
		webResource = client.resource(USER_URI + "perfiles/update");		
		String stringJson = mapper.writeValueAsString(perfiles);
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
	public String delete(Perfiles perfiles) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "perfiles/delete");		
		String stringJson = mapper.writeValueAsString(perfiles);
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
	public Perfiles load(Perfiles perfiles) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "perfiles/load");		
		String stringJson = mapper.writeValueAsString(perfiles);
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
	public List<Perfiles> loadAll() throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "perfiles/loadall");		
		
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