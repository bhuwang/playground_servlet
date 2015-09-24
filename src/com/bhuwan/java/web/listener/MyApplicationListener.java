package com.bhuwan.java.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionListener
 * 
 * @author Bhuwan Guatam <bhuwangautam@lftechnology.com>
 */
@WebListener
public class MyApplicationListener implements HttpSessionListener {

    /**
     * Default constructor.
     */
    public MyApplicationListener() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("MyApplicationListener#sessionCreated method ::{" + httpSessionEvent.getSession().getId()
                + " Created at:: " + httpSessionEvent.getSession().getCreationTime() + "}");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("MyApplicationListener#sessionDestroyed method ::{" + httpSessionEvent.getSession().getId()
                + "}");

    }

}
