/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamere.rest.manager.objects;

/**
 *
 * @author tamere
 */
import com.tamere.rest.manager.BundleApplication;
import com.sun.jersey.spi.container.servlet.ServletContainer;

public class ServletInfo {
    private ServletContainer servlet;
    private BundleApplication application;
    private String alias;

    public ServletContainer getServlet() {
        return servlet;
    }

    public void setServlet(ServletContainer servlet) {
        this.servlet = servlet;
    }

    public BundleApplication getApplication() {
        return application;
    }

    public void setApplication(BundleApplication application) {
        this.application = application;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    
}
