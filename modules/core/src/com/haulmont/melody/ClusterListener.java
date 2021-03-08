package com.haulmont.melody;

import com.haulmont.cuba.core.app.ClusterManager;
import com.haulmont.cuba.core.app.ClusterManagerAPI;
import com.haulmont.cuba.core.sys.events.AppContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ClusterListener {
    private ClusterManagerAPI clusterManagerAPI;
    private ClusterManager clusterManager;
    public ClusterListener(ClusterManagerAPI clusterManagerAPI, ClusterManager clusterManager) {
        this.clusterManagerAPI = clusterManagerAPI;
        this.clusterManager = clusterManager;
    }


    @EventListener
    public void ggg(AppContextStartedEvent e) {
        System.out.println("\n\n\n");
        System.out.println("                   clusterManagerAPI.printMessagesStat");
        System.out.println(clusterManagerAPI.printMessagesStat());
        System.out.println("                    cirrentr View");
        System.out.println(clusterManagerAPI.getCurrentView());
        System.out.println("                    cluster Mnager");
        System.out.println(clusterManager.getChannel().getAddressAsString());
        System.out.println(clusterManager.getChannel().getState());
        System.out.println(clusterManager.getChannel().getName());
        System.out.println("\n\n\n");

        //clusterManagerAPI
//        com.haulmont.cuba.core.app.ClusterListener<MyListener> listener = new com.haulmont.cuba.core.app.ClusterListener<MyListener>() {
//            @Override
//            public void receive(MyListener message) {
//
//            }
//
//            @Override
//            public byte[] getState() {
//                return new byte[0];
//            }
//
//            @Override
//            public void setState(byte[] state) {
//
//            }
//        }
    }
}
