/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jah.listener;

/**
 *
 * @author etherdesign
 */
import java.time.LocalDateTime;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author jlombardo
 */
public class CustomServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        LocalDateTime appStartTime = LocalDateTime.now();
        sc.setAttribute("appStartTime", appStartTime);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // nothing to do here
    }

}
