package com.iamchiwon.appversionchecker;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;

public class OfyHelper implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ObjectifyService.register(AppVersion.class);
	}

}
