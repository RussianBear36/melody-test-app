package com.haulmont.melody.listeners;

import com.haulmont.cuba.core.sys.events.AppContextStartedEvent;
import com.haulmont.cuba.core.sys.events.AppContextStoppedEvent;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

@Component("javamelody_AppLifecycleEventListenerWeb_ApplicationContextListener")
public class AppLifecycleEventListenerWeb {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AppLifecycleEventListenerWeb.class);

    @EventListener
    public void applicationContextStarted(AppContextStartedEvent event) throws MalformedURLException, UnknownHostException {
        // name of the application in the collect server (if null, "contextPath_hostname" will be used)
        String applicationName = InetAddress.getLocalHost().getHostName();
        // url of the collect server
        URL collectServerUrl = new URL("http://javamelody:1337/");
        // url of the application node to be called by the collect server to collect data

        String address = InetAddress.getLocalHost().getHostAddress();


        URL applicationNodeUrl = new URL("http://" + address + ":" + 8080 + "/app");

        net.bull.javamelody.MonitoringFilter.registerApplicationNodeInCollectServer(
                applicationName, collectServerUrl, applicationNodeUrl);

        log.info("ADD LISTENING FOR METRICS WEB\n" +
                " AppName: " + applicationName +
                " CollectServerUrl: " + collectServerUrl +
                " ApplicationNodeUrl: " + applicationNodeUrl);
    }

    @EventListener
    public void applicationContextStopped(AppContextStoppedEvent event) throws IOException {
        net.bull.javamelody.MonitoringFilter.unregisterApplicationNodeInCollectServer();
    }
}