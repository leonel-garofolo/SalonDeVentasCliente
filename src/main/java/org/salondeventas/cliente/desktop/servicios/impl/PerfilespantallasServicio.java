package org.salondeventas.cliente.desktop.servicios.impl;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.salondeventas.cliente.desktop.modelo.Perfilespantallas;
import org.salondeventas.cliente.desktop.servicios.IPerfilespantallasServicio;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class PerfilespantallasServicio extends Services<Perfilespantallas> implements IPerfilespantallasServicio {	
		
	@Override
	public String insert(Perfilespantallas perfilespantallas) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "perfilespantallas/insert");		
		String stringJson = mapper.writeValueAsString(perfilespantallas);
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
	public String update(Perfilespantallas perfilespantallas) throws Exception {	
		client = Client.create();
		webResource = client.resource(USER_URI + "perfilespantallas/update");		
		String stringJson = mapper.writeValueAsString(perfilespantallas);
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
	public String delete(Perfilespantallas perfilespantallas) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "perfilespantallas/delete");		
		String stringJson = mapper.writeValueAsString(perfilespantallas);
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
	public Perfilespantallas load(Perfilespantallas perfilespantallas) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "perfilespantallas/load");		
		String stringJson = mapper.writeValueAsString(perfilespantallas);
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
	public List<Perfilespantallas> loadAll() throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "perfilespantallas/loadall");		
		
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