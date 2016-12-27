package  com.mig.coins.server.base;

//import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

//import org.glassfish.jersey.filter.LoggingFilter;
//import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.server.ServerProperties;

import com.mig.coins.server.base.Constants.PackagesIntercept;
import com.mig.coins.server.base.intercept.CoinsInterceptionBinder;
import com.mig.coins.server.base.intercept.SessionRequestFilter;

//import com.indracompany.incms.ss.base.errorhandling.AppExceptionMapper;
//import com.indracompany.incms.ss.base.errorhandling.NotFoundExceptionMapper;
//import com.indracompany.incms.ss.base.errorhandling.UncheckedExceptionMapper;
//import com.indracompany.incms.ss.base.intercept.CorsResponseFilter;
//import com.indracompany.incms.ss.base.intercept.SsInterceptionBinder;
//import com.indracompany.incms.ss.base.intercept.SessionRequestFilter;

/**
 * Clase para el registro jersey/jaxr
 * @author jjimenezg
 */
@ApplicationPath("/")
public class Application extends ResourceConfig {

//	 private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
	 
	 
    public Application() {
    	packages(PackagesIntercept.BASE.getValue());

    	// register interceptors
    	register(new CoinsInterceptionBinder());
    	// register filters
        register(SessionRequestFilter.class);
        
        // PDTE ¿Para que sirve este filtro?
//        register(CorsResponseFilter.class);
  
        // register exception mappers
        // PDTE Tratamiento de excepctions
//     	register(AppExceptionMapper.class);
//     	register(UncheckedExceptionMapper.class);
//     	register(NotFoundExceptionMapper.class);
		// register features
//		register(JacksonFeature.class);//necesario para la conversión de exception a json
		
     	//other register
        // PDTE Ver si es necesario
//        register(new LoggingFilter(LOGGER, true));//registramos el filtro de log para ver las trazas
//        property(ServerProperties.TRACING, "ALL");//registramos el nivel de trazas
//    	property(ServerProperties.TRACING_THRESHOLD, "TRACE");
    }
}