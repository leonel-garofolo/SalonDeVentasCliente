package org.salondeventas.cliente.desktop.servicios.impl;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.salondeventas.cliente.desktop.modelo.Licencias;
import org.salondeventas.cliente.desktop.servicios.ILicenciasServicio;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class LicenciasServicio extends Services<Licencias> implements ILicenciasServicio {	
		
	@Override
	public String insert(Licencias licencias) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "usuario/insert");		
		String stringJson = mapper.writeValueAsString(licencias);
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
	public String update(Licencias licencias) throws Exception {	
		client = Client.create();
		webResource = client.resource(USER_URI + "usuario/update");		
		String stringJson = mapper.writeValueAsString(licencias);
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
	public String delete(Licencias licencias) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "usuario/delete");		
		String stringJson = mapper.writeValueAsString(licencias);
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
	public Licencias load(Licencias licencias) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "usuario/load");		
		String stringJson = mapper.writeValueAsString(licencias);
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
	public List<Licencias> loadAll() throws Exception {		
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