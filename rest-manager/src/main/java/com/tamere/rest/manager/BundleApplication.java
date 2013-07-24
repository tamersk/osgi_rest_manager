/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tamere.rest.manager;

import java.util.Set;

import javax.ws.rs.core.Application;


public class BundleApplication extends Application {
	private final Set<Class<?>> classes;

	public BundleApplication(Set<Class<?>> classes) {
		this.classes = classes;
	}

	@Override
	public Set<Class<?>> getClasses() {
		return this.classes;
	}
}
