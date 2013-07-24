/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamere.rest.manager;

/**
 *
 * @author tamere
 */
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;
import com.tamere.rest.api.RestServicesInterface;
import com.tamere.rest.manager.objects.ServletInfo;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import org.osgi.framework.Bundle;
import org.osgi.service.http.NamespaceException;

public class RestServicesTracker extends ServiceTracker {

    private final LogService log;
    private HttpService httpService;
    private static Map<String, ServletInfo> availableServices = new HashMap<String, ServletInfo>();

    public RestServicesTracker(BundleContext context, LogService log, HttpService httpService) {
        super(context, RestServicesInterface.class.getName(), null);
        this.log = log;
        this.httpService = httpService;


    }

    @Override
    public Object addingService(ServiceReference reference) {
        RestServicesInterface restService = (RestServicesInterface) context.getService(reference);

        String alias = reference.getProperty(RestServicesInterface.ROOT_PATH).toString();
        log.log(LogService.LOG_INFO, "Starting to track JAX-RS for " + restService.getClass().getName()
                + " In bundle " + reference.getBundle().getSymbolicName() + " and the root path is " + alias);

        ServletContainer servlet = processService(reference.getBundle(), restService.getClass(), alias);


        try {
            httpService.registerServlet(alias, servlet, null, new BundleHttpContext(reference.getBundle()));
        } catch (ServletException ex) {
            log.log(LogService.LOG_ERROR, "Error registering servlet.", ex);
            return null;
        } catch (NamespaceException ex) {
            log.log(LogService.LOG_ERROR, "Error registering servlet.", ex);
            return null;
        }
        return restService;
    }

    @Override
    public void removedService(ServiceReference reference, Object service) {
        log.log(LogService.LOG_INFO, "Stopping tracking JAX-RS bundles");

    }

    private ServletContainer processService(Bundle bundle, Class serviceClass, String alias) {

        Set<Class<?>> classes = new HashSet<Class<?>>();
        ServletContainer servlet;
        ServletInfo servletInfo;
        BundleApplication application;
        if (availableServices.containsKey(alias)) {
            log.log(LogService.LOG_INFO, "Alias already exists will handle it");
            servletInfo = availableServices.get(alias);
            servlet = servletInfo.getServlet();
            servlet.destroy();
            httpService.unregister(alias);
            application = servletInfo.getApplication();
            application.getClasses().add(serviceClass);
            servletInfo.setApplication(application);
            availableServices.remove(alias);

        } else {
            log.log(LogService.LOG_INFO, "New alias, will create it");
            classes.add(serviceClass);
            application = new BundleApplication(classes);
            servletInfo = new ServletInfo();
            servletInfo.setAlias(alias);
            servletInfo.setApplication(application);
        }

        servlet = new ServletContainer(application);
        servletInfo.setServlet(servlet);
        availableServices.put(alias, servletInfo);
        return servlet;
    }
}
