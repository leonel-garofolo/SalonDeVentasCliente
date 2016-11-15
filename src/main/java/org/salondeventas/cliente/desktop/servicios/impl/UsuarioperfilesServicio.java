package org.salondeventas.cliente.desktop.servicios.impl;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.salondeventas.cliente.desktop.modelo.Usuarioperfiles;
import org.salondeventas.cliente.desktop.servicios.IUsuarioperfilesServicio;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class UsuarioperfilesServicio extends Services<Usuarioperfiles> implements IUsuarioperfilesServicio {	
		
	@Override
	public String insert(Usuarioperfiles usuarioperfiles) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "usuarioperfiles/insert");		
		String stringJson = mapper.writeValueAsString(usuarioperfiles);
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
	public String update(Usuarioperfiles usuarioperfiles) throws Exception {	
		client = Client.create();
		webResource = client.resource(USER_URI + "usuarioperfiles/update");		
		String stringJson = mapper.writeValueAsString(usuarioperfiles);
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
	public String delete(Usuarioperfiles usuarioperfiles) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "usuarioperfiles/delete");		
		String stringJson = mapper.writeValueAsString(usuarioperfiles);
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
	public Usuarioperfiles load(Usuarioperfiles usuarioperfiles) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "usuarioperfiles/load");		
		String stringJson = mapper.writeValueAsString(usuarioperfiles);
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
	public List<Usuarioperfiles> loadAll() throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "usuarioperfiles/loadall");		
		
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