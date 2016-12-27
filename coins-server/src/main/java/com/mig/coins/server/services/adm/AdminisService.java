package com.mig.coins.server.services.adm;

import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.mig.coins.db.entity.Pais;
import com.mig.coins.main.adm.IAdminisBusiness;
import com.mig.coins.server.base.helper.JsonHelper;
import com.mig.coins.server.base.intercept.annotations.Transactional;
import com.mig.coins.server.services.BaseService;

@Path("/")
public class AdminisService extends BaseService {

	IAdminisBusiness business;
	
	public AdminisService() {
		super();
		business = (IAdminisBusiness)getServiceImpl(com.mig.coins.server.services.adm.AdminisService.class);
	}
	
	@GET
	@Path("/paises/")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getPaises(@QueryParam("included") List<Integer> divIncluded,
			@QueryParam("excluded") List<Integer> divExcluded) {
		
		// No se deben recibir ambos filtros a la vez
		final List<Integer> lIncluded = ((null == divIncluded) || (divIncluded.isEmpty())) 
					? null 
					: divIncluded;
		final List<Integer> lExcluded = ((null == divExcluded) || (divExcluded.isEmpty())) 
				? null 
				: divExcluded;
		if ((null != lIncluded) && (null != lExcluded)) {
			// PDTE generar exception
		}
		
		// Recuperamos los paises
		final List<Pais> lPaises = business.getListaPaises(lIncluded, lExcluded);
		
		// Lo mapea a una lista sencilla de paises
		final JSONObject result = JsonHelper.collectionToJsonObject(new Paises(), lPaises);
		
		// PDTE Generar respuesta
		final ResponseBuilder entity=Response.status(Status.OK).entity(result.toString());
		return entity.build();
	}

	@GET
	@Path("/paises/{idPais}/")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getPais(@PathParam("idPais") Integer idPais) {
		
		// No se deben recibir ambos filtros a la vez
		if ((null == idPais) || (idPais.intValue() == 0)) {
			// PDTE generar exception
		}
		
		// Recuperamos los datos del pais
		final Pais pais = business.getPais(idPais);
		
		// Lo mapea a una lista sencilla de paises
		final JSONObject result = JsonHelper.beanToJsonObject(pais);
		
		// PDTE Generar respuesta
		final ResponseBuilder entity=Response.status(Status.OK).entity(result.toString());
		return entity.build();
	}

}
