/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamere.rest.manager;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.Bundle;
import org.osgi.service.http.HttpContext;

public class BundleHttpContext implements HttpContext {

	private final Bundle bundle;

	public BundleHttpContext(Bundle bundle) {
		this.bundle = bundle;
	}

	@Override
	public URL getResource(String name) {
		return bundle.getEntry(name);
	}

	@Override
	public String getMimeType(String name) {
		return null;
	}

	@Override
	public boolean handleSecurity(HttpServletRequest request, HttpServletResponse response) throws IOException {
            System.out.println("This is the secureity thing in the BindelHttpContext");
		return true;
	}

}
