package com.haulmont.melody;

import com.haulmont.cuba.core.sys.servlet.ServletRegistrationManager;
import com.haulmont.cuba.core.sys.servlet.events.ServletContextInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.Enumeration;

@Component
public class ServletsCreator {

    @Inject
    private ServletRegistrationManager servletRegistrationManager;

    @EventListener
    public void createServlet(ServletContextInitializedEvent event) {
        Servlet servlet = servletRegistrationManager.createServlet(event.getApplicationContext(), "com.haulmont.cuba.core.sys.remoting.RemotingServlet");
        ServletRegistration.Dynamic registration = event.getSource().addServlet("remoting", servlet);
        registration.addMapping("/remoting/*");
        registration.setLoadOnStartup(1);
    }
}
