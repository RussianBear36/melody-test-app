package com.haulmont.melody;

import com.haulmont.cuba.core.sys.servlet.ServletRegistrationManager;
import com.haulmont.cuba.core.sys.servlet.events.ServletContextInitializedEvent;
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
