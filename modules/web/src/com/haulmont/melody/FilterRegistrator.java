package com.haulmont.melody;

import com.haulmont.cuba.core.sys.AbstractWebAppContextLoader;
import com.haulmont.cuba.core.sys.servlet.ServletRegistrationManager;
import com.haulmont.cuba.core.sys.servlet.events.ServletContextInitializedEvent;
import com.haulmont.cuba.web.sys.CubaApplicationServlet;
import com.haulmont.cuba.web.sys.CubaDispatcherServlet;
import com.vaadin.server.communication.JSR356WebsocketInitializer;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.*;
import java.util.EnumSet;
import java.util.Enumeration;

@Component
public class FilterRegistrator {

    @Inject
    private ServletRegistrationManager servletRegistrationManager;

    @EventListener
    public void init(ServletContextInitializedEvent event) throws ServletException {

        initAppServlet(event);
        initDispatcherServlet(event);
        initHttpFilter(event);

    }

    protected void initDispatcherServlet(ServletContextInitializedEvent event) {
        CubaDispatcherServlet cubaDispatcherServlet = (CubaDispatcherServlet) servletRegistrationManager.createServlet(
                event.getApplicationContext(),
                "com.haulmont.cuba.web.sys.CubaDispatcherServlet");
        try {
            cubaDispatcherServlet.init(
                    new AbstractWebAppContextLoader.CubaServletConfig("dispatcher", event.getSource()));
        } catch (ServletException e) {
            throw new RuntimeException("An error occurred while initializing dispatcher servlet", e);
        }
        ServletRegistration.Dynamic cubaDispatcherServletReg = event.getSource()
                .addServlet("dispatcher", cubaDispatcherServlet);
        cubaDispatcherServletReg.setLoadOnStartup(1);
        cubaDispatcherServletReg.addMapping("/dispatch/*");
    }

    protected void initAppServlet(ServletContextInitializedEvent event) {
        CubaApplicationServlet cubaServlet = (CubaApplicationServlet) servletRegistrationManager.createServlet(
                event.getApplicationContext(),
                "com.haulmont.cuba.web.sys.CubaApplicationServlet");
        cubaServlet.setClassLoader(Thread.currentThread().getContextClassLoader());
        ServletRegistration.Dynamic registration = event.getSource()
                .addServlet("app_servlet", cubaServlet);
        registration.setLoadOnStartup(0);
        registration.setAsyncSupported(true);
        registration.addMapping("/*");
        JSR356WebsocketInitializer.initAtmosphereForVaadinServlet(registration, event.getSource());
        try {
            cubaServlet.init(new AbstractWebAppContextLoader.CubaServletConfig("app_servlet", event.getSource()));
        } catch (ServletException e) {
            throw new RuntimeException("An error occurred while initializing app_servlet servlet", e);
        }
    }

    protected void initHttpFilter(ServletContextInitializedEvent event) throws ServletException {
        ServletContext context = event.getSource();
        Filter filter = servletRegistrationManager.createFilter(event.getApplicationContext(), "com.haulmont.cuba.web.sys.CubaHttpFilter");
        filter.init(new FilterConfig() {
            @Override
            public String getFilterName() {
                return "cuba_filter";
            }

            @Override
            public ServletContext getServletContext() {
                return context;
            }

            @Override
            public String getInitParameter(String name) {
                return null;
            }

            @Override
            public Enumeration<String> getInitParameterNames() {
                return null;
            }
        });
        FilterRegistration.Dynamic httpFilter = context.addFilter("cuba_filter", filter);
        httpFilter.setAsyncSupported(true);
        httpFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC), true , "/*");
    }
}
