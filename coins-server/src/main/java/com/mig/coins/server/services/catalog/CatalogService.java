package com.mig.coins.server.services.catalog;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.mig.coins.main.catalog.ICatalogBusiness;
import com.mig.coins.server.services.BaseService;

@Path("/catalog")
public class CatalogService extends BaseService {

	final ICatalogBusiness business;
	
	public CatalogService() {
		super();
		business = (ICatalogBusiness)getServiceImpl(com.mig.coins.server.services.catalog.CatalogService.class);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCatalog() {
		business.showName();
		final ResponseBuilder entity=Response.status(Status.OK).entity("Mi Catalogo");
		return entity.build();
	}

}
