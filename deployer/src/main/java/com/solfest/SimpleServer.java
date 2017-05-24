package com.solfest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AllowSymLinkAliasChecker;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

public class SimpleServer{

    public static void main( String[] args ) throws Exception{
        // set up the server to run on 8100
        Server server = new Server(8100);

        // set up the web app (war file)
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        File warFile = new File("./solfest_sandbox_web-1.0.0.war");
        webapp.setWar(warFile.getAbsolutePath());
        webapp.addAliasCheck(new AllowSymLinkAliasChecker());

        // start the server and run it
        server.start();
        server.dumpStdErr();
        server.join();
    }
}
