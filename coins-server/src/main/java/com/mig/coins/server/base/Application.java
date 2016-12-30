package  com.mig.coins.server.base;

//import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

//import org.glassfish.jersey.filter.LoggingFilter;
//import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.server.ServerProperties;

import com.mig.coins.server.base.Constants.PackagesIntercept;
import com.mig.coins.server.base.error.AppExceptionMapper;
import com.mig.coins.server.base.error.NotFoundExceptionMapper;
import com.mig.coins.server.base.error.UncheckedExceptionMapper;
import com.mig.coins.server.base.intercept.CoinsInterceptionBinder;
import com.mig.coins.server.base.intercept.SessionRequestFilter;

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
     	register(AppExceptionMapper.class);
     	register(UncheckedExceptionMapper.class);
     	register(NotFoundExceptionMapper.class);
     	
		// register features
//		register(JacksonFeature.class);//necesario para la conversión de exception a json
		
     	//other register
        // PDTE Ver si es necesario
//        register(new LoggingFilter(LOGGER, true));//registramos el filtro de log para ver las trazas
//        property(ServerProperties.TRACING, "ALL");//registramos el nivel de trazas
//    	property(ServerProperties.TRACING_THRESHOLD, "TRACE");
    }
}