package com.haulmont.melody.listeners;

import com.haulmont.cuba.core.sys.events.AppContextStartedEvent;
import com.haulmont.cuba.core.sys.events.AppContextStoppedEvent;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;


import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.UUID;

@Component("javamelody_AppLifecycleEventListenerCore_ApplicationContextListener")
public class AppLifecycleEventListenerCore {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AppLifecycleEventListenerCore.class);

    @EventListener
    public void applicationContextStarted(AppContextStartedEvent event) throws MalformedURLException, UnknownHostException {
        // name of the application in the collect server (if null, "contextPath_hostname" will be used)
        String applicationName = InetAddress.getLocalHost().getHostName();
        // url of the collect server
        URL collectServerUrl = new URL("http://javamelody:1337/");
        // url of the application node to be called by the collect server to collect data

        String address = InetAddress.getLocalHost().getHostAddress();


        URL applicationNodeUrl = new URL("http://" + address + ":" + 8079 + "/app-core");

        net.bull.javamelody.MonitoringFilter.registerApplicationNodeInCollectServer(
                applicationName, collectServerUrl, applicationNodeUrl);

        log.info("ADD LISTENING FOR METRICS \n" +
                " AppName: " + applicationName +
                " CollectServerUrl: " + collectServerUrl +
                " ApplicationNodeUrl: " + applicationNodeUrl);
    }

    @EventListener
    public void applicationContextStopped(AppContextStoppedEvent event) throws IOException {
        net.bull.javamelody.MonitoringFilter.unregisterApplicationNodeInCollectServer();
    }
}