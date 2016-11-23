package org.salondeventas.cliente.desktop.servicios.impl;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.salondeventas.cliente.desktop.modelo.informes.VCantProdVendidos;
import org.salondeventas.cliente.desktop.servicios.IVCantProdVendidosServicio;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class VCantProdVendidosServicio extends Services<VCantProdVendidos> implements IVCantProdVendidosServicio {	
		
	
	
	@Override
	public VCantProdVendidos load(VCantProdVendidos vCantProdVendidos) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "vcantprodvendidos/load");		
		String stringJson = mapper.writeValueAsString(vCantProdVendidos);
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
	public List<VCantProdVendidos> loadAll() throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "vcantprodvendidos/loadall");		
		
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