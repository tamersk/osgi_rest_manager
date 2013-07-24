/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamere.rest.manager;

import com.tamere.rest.api.RestServicesInterface;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;

/**
 *
 * @author tamere
 */
public class HttpServiceTracker  extends ServiceTracker implements ServiceListener {

    private final LogService log;
    private HttpService httpService;
    private RestServicesTracker restTracker;
    private BundleContext bundleContext;
    private Map<String, List<Class<?>>> restClasse; 

    public HttpServiceTracker(BundleContext context, LogService log) {
        super(context, HttpService.class.getName(), null);
        this.log = log;
        bundleContext = context;



    }

    @Override
    public Object addingService(ServiceReference reference) {
        httpService = (HttpService) bundleContext.getService(reference);

      /*  String serviceFilter;
        serviceFilter = "(&(objectClass=" + RestServicesInterface.class.getName()
                 + "))";
        ServiceReference[] serviceReferences;
        try {
            serviceReferences = bundleContext.getServiceReferences(null,
                    serviceFilter);
            if (serviceReferences != null) {
                for (int i = 0; i < serviceReferences.length; i++) {
                    String rootPath = serviceReferences[i].getProperty(RestServicesInterface.ROOT_PATH).toString();
                    log.log(LogService.LOG_INFO, "Starting to track JAX-RS for " + ((RestServicesInterface) bundleContext.getService(serviceReferences[i])).getClass().getName()
                            + " In bundle " + serviceReferences[i].getBundle().getSymbolicName() + " and the root path is " + rootPath);
                }
            }else{
                //setup the listener
            }
            //
        } catch (InvalidSyntaxException ex) {
            Logger.getLogger(HttpServiceTracker.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        
         restTracker = new RestServicesTracker(bundleContext, this.log, httpService);
         restTracker.open();
         
        return httpService;
    }

    @Override
    public void removedService(ServiceReference reference, Object service) {
        log.log(LogService.LOG_INFO, "Stopping tracking JAX-RS bundles");

    }

    public void serviceChanged(ServiceEvent se) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
