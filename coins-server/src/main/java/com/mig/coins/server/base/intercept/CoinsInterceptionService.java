package com.mig.coins.server.base.intercept;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import org.aopalliance.intercept.ConstructorInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.Filter;
import org.jvnet.hk2.annotations.Service;

import com.mig.coins.server.base.Constants.PackagesIntercept;
import com.mig.coins.server.base.intercept.annotations.Transactional;

// PDTE Documentar
/**
 * Clase encargada de aplicar los interceptores de Ss. 
 * Intercepta los m�todos de un recurso solicitado si pertenece al paquete indicado en getDescriptorFilter()
 *  y aplica el interceptor si cumple la anotaci�n indicada en getMethodInterceptors(). 
 * @author jjimenezg
 */
@Service
public class CoinsInterceptionService implements org.glassfish.hk2.api.InterceptionService {

    private static final TransactionalResourceInterceptor TRANSACTIONAL_RESOURCE_INTERCEPTOR = new TransactionalResourceInterceptor();
       
    private static final Log LOG=LogFactory.getLog(CoinsInterceptionService.class);
  
    private static final List<MethodInterceptor> TRANSACTIONAL_RESOURCE_METHOD_INTERCEPTORS = Collections.<MethodInterceptor>singletonList(TRANSACTIONAL_RESOURCE_INTERCEPTOR);
    private static final List<MethodInterceptor> TRANSACTIONAL_RESOURCE_METHOD_DEFAULT =null;
    private static final List<ConstructorInterceptor> TRANSACTIONAL_RESOURCE_CONSTRUCTOR_DEFAULT =null;
  
    
    @Override
    public Filter getDescriptorFilter() {
        // We're only interested in classes of services package.
        return new Filter() {
            @Override
            public boolean matches(final Descriptor d) {
                final String clazz = d.getImplementation();
                LOG.error("JJG clazz "+clazz+"   "+Boolean.toString(clazz.startsWith(PackagesIntercept.SERVICES.getValue())));
                return clazz.startsWith(PackagesIntercept.SERVICES.getValue());
            }
        };
    }

    @Override
    public List<MethodInterceptor> getMethodInterceptors(final Method method) {
    	// Solo me quedo con los metodos con la anotación @Transactional.
    	if (method.isAnnotationPresent(Transactional.class)) {
    		LOG.error("Detectada petición de Transacción ");
    		return TRANSACTIONAL_RESOURCE_METHOD_INTERCEPTORS;
    	}
    	return TRANSACTIONAL_RESOURCE_METHOD_DEFAULT;
    	//         return null;
    }

	@Override
	public List<ConstructorInterceptor> getConstructorInterceptors(
			Constructor<?> constructor) {
		return TRANSACTIONAL_RESOURCE_CONSTRUCTOR_DEFAULT;
	}


}