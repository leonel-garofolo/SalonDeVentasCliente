package org.salondeventas.cliente.desktop.servicios.impl;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.salondeventas.cliente.desktop.modelo.VInfVentasTotales;
import org.salondeventas.cliente.desktop.servicios.IVInfVentasTotalesServicio;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class VInfVentasTotalesServicio extends Services<VInfVentasTotales> implements IVInfVentasTotalesServicio {	
		
	
	
	@Override
	public VInfVentasTotales load(VInfVentasTotales vInfVentasTotales) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "vInfVentasTotales/load");		
		String stringJson = mapper.writeValueAsString(vInfVentasTotales);
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
	public List<VInfVentasTotales> loadAll() throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "vInfVentasTotales/loadall");		
		
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