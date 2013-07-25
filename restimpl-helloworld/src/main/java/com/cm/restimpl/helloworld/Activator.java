package com.cm.restimpl.helloworld;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import com.tamere.rest.api.RestServicesInterface;
import java.util.Dictionary;
import java.util.Hashtable;

public class Activator implements BundleActivator {

    private final Dictionary<String, Object> properties =
            new Hashtable<String, Object>();

    public void start(BundleContext context) throws Exception {
        // TODO add activation code here
        properties.put(RestServicesInterface.ROOT_PATH, "/test");
        context.registerService(RestServicesInterface.class.getName(), new HelloWorldResource(), properties);
        context.registerService(RestServicesInterface.class.getName(), new ByeByeResource(), properties);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
