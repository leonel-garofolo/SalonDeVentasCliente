package org.salondeventas.cliente.desktop.servicios.impl;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.salondeventas.cliente.desktop.modelo.Usuario;
import org.salondeventas.cliente.desktop.servicios.IUsuarioServicio;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class UsuarioServicio extends Services<Usuario> implements IUsuarioServicio {	
		
	@Override
	public String insert(Usuario usuario) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "usuario/insert");		
		String stringJson = mapper.writeValueAsString(usuario);
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
	public String update(Usuario usuario) throws Exception {	
		client = Client.create();
		webResource = client.resource(USER_URI + "usuario/update");		
		String stringJson = mapper.writeValueAsString(usuario);
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
	public String delete(Usuario usuario) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "usuario/delete");		
		String stringJson = mapper.writeValueAsString(usuario);
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
	public Usuario load(Integer idusuario) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "usuario/load");		
		
		response = webResource
				.queryParam("usuario", "leonel")
				.queryParam("clave", "123")
				.type(MediaType.APPLICATION_JSON_TYPE)					
				.post(ClientResponse.class, idusuario);
		
		if (response.getStatus() != 200) {
			throw new Exception("Failed : HTTP error code : "
			     + response.getStatus());
		}				
		
		return this.buildJsonToObject(response.getEntity(String.class));
	}
	
	@Override
	public List<Usuario> loadAll() throws Exception {		
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