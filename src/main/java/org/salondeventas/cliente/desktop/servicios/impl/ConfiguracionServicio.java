package org.salondeventas.cliente.desktop.servicios.impl;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.salondeventas.cliente.desktop.modelo.Configuracion;
import org.salondeventas.cliente.desktop.servicios.IConfiguracionServicio;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class ConfiguracionServicio extends Services<Configuracion> implements IConfiguracionServicio {	
		
	@Override
	public String insert(Configuracion configuracion) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "configuracion/insert");		
		String stringJson = mapper.writeValueAsString(configuracion);
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
	public String update(Configuracion configuracion) throws Exception {	
		client = Client.create();
		webResource = client.resource(USER_URI + "configuracion/update");		
		String stringJson = mapper.writeValueAsString(configuracion);
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
	public String delete(Configuracion configuracion) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "configuracion/delete");		
		String stringJson = mapper.writeValueAsString(configuracion);
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
	public Configuracion load(Configuracion configuracion) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "configuracion/load");		
		String stringJson = mapper.writeValueAsString(configuracion);
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
	public List<Configuracion> loadAll() throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "configuracion/loadall");		
		
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