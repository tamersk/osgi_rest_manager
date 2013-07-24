package com.tamere.rest.manager;

import com.tamere.rest.manager.util.LogTracker;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private LogTracker logTracker;
    
    private HttpServiceTracker httpService;

    public void start(BundleContext context) throws Exception {
        // TODO add activation code here
        logTracker = new LogTracker(context);
        logTracker.open();

        
         httpService = new HttpServiceTracker(context, logTracker);
         httpService.open();
    
         
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
