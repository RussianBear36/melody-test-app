package com.haulmont.melody;

import com.haulmont.cuba.core.sys.remoting.ClusteredHttpInvokerRequestExecutor;
import com.haulmont.cuba.core.sys.remoting.CubaRemoteInvocation;
import com.haulmont.cuba.core.sys.remoting.HttpServiceProxy;

//@Component
public class MyListener{
//
//    @Inject
//    private ServletRegistrationManager servletRegistrationManager;
//
//    @EventListener
//    public void init(ServletContextInitializedEvent event) {
//        Filter filter = servletRegistrationManager.createFilter(event.getApplicationContext(), "net.bull.javamelody.MonitoringFilter");
//        FilterRegistration.Dynamic registration = event.getSource().addFilter("javamelody", filter);
//        registration.setAsyncSupported(true);
//        registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC), true, "/*");
//    }



//void ggg() {
//    ClusteredHttpInvokerRequestExecutor;
//    HttpServiceProxy;
//    CubaRemoteInvocation;
//}


//    implements AppContext.Listener
//    public MyListener() {
//        AppContext.addListener(this);
//    }
//
//    @Override
//    public void applicationStarted() {
//        ExecutorService service;
//        if (AppContext.getAppComponents().getBlock().equals("core")) {
//            System.out.println("\n\n\n");
//            System.out.println("IN CORE MODULE");
//            System.out.println("\n\n\n");
//            service = Executors.newFixedThreadPool(1);
//            //service.submit(new JavaMelodyRegistrarCore()).get();
//            service.shutdown();
//        }
//        if (AppContext.getAppComponents().getBlock().equals("web")) {
//            System.out.println("\n\n\n");
//            System.out.println("IN WEB MODULE");
//            System.out.println("\n\n\n");
//            service = Executors.newFixedThreadPool(1);
//            //service.submit(new JavaMelodyRegistrar()).get();
//            service.shutdown();
//        }
//        //System.out.println(AppContext.getApplicationContext().getApplicationName());
//        System.out.println(AppContext.getProperty("cuba.webAppUrl"));
//        System.out.println(AppContext.getProperty("cuba.webContextName"));
//        System.out.println(AppContext.getProperty("cuba.webPort"));
//        System.out.println(AppContext.getProperty("cuba.webHostName"));
//        try {
//            System.out.println(InetAddress.getLocalHost().getHostAddress());
//            System.out.println(InetAddress.getLocalHost().getHostName());
//            System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void applicationStopped() {
//
//    }
}
