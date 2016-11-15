package org.salondeventas.cliente.desktop.servicios.impl;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.salondeventas.cliente.desktop.modelo.Pantalla;
import org.salondeventas.cliente.desktop.servicios.IPantallaServicio;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class PantallaServicio extends Services<Pantalla> implements IPantallaServicio {	
		
	@Override
	public String insert(Pantalla pantalla) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "pantalla/insert");		
		String stringJson = mapper.writeValueAsString(pantalla);
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
	public String update(Pantalla pantalla) throws Exception {	
		client = Client.create();
		webResource = client.resource(USER_URI + "pantalla/update");		
		String stringJson = mapper.writeValueAsString(pantalla);
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
	public String delete(Pantalla pantalla) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "pantalla/delete");		
		String stringJson = mapper.writeValueAsString(pantalla);
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
	public Pantalla load(Pantalla pantalla) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "pantalla/load");		
		String stringJson = mapper.writeValueAsString(pantalla);
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
	public List<Pantalla> loadAll() throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "pantalla/loadall");		
		
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