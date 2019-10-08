package com.simple.money.transfer;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

import com.simple.money.transfer.common.ApplicationProperties;
import com.simple.money.transfer.common.datasource.H2DataSource;

/**
 * Main Method to start the application
 *
 */
public class MoneyTransferApiMain 
{
    public static void main( String[] args ) throws Exception
    {
    	int serverPort = ApplicationProperties.getIntegerProperty("server.port", 8080);
    	
    	H2DataSource source = new H2DataSource();
    	// Initialize Data source
    	source.initialize();
    	
    	//Start Server
    	startServer( serverPort );
    }
    
    private static void startServer(int port) throws Exception{
    	Server server = new Server(8080);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/*");
		servletHolder.setInitParameter(ServerProperties.PROVIDER_PACKAGES, 
				          "com.simple.money.transfer.account.controller;com.simple.money.transfer.payout.controller");
    }
}
