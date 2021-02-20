package com.haulmont.melody.listeners;

import com.haulmont.cuba.core.sys.events.AppContextStartedEvent;
import com.haulmont.cuba.core.sys.events.AppContextStoppedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;


import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.UUID;

@Component("javamelody_AppLifecycleEventListener_ApplicationContextListener")
public class AppLifecycleEventListener {

    @Autowired
    Environment environment;

    @EventListener
    public void applicationContextStarted(AppContextStartedEvent event) throws MalformedURLException, UnknownHostException {
        // name of the application in the collect server (if null, "contextPath_hostname" will be used)
        String applicationName = null;
        // url of the collect server
        URL collectServerUrl = new URL("http://localhost:1337/");
        // url of the application node to be called by the collect server to collect data

        String address = InetAddress.getLocalHost().getHostAddress();


        URL applicationNodeUrl = new URL("http://" + address + ":" + 8079 + "/app-core");

        net.bull.javamelody.MonitoringFilter.registerApplicationNodeInCollectServer(
                applicationName, collectServerUrl, applicationNodeUrl);
    }

    @EventListener
    public void applicationContextStopped(AppContextStoppedEvent event) throws IOException {
        net.bull.javamelody.MonitoringFilter.unregisterApplicationNodeInCollectServer();
    }
}