/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jah.listener;

import java.time.LocalDateTime;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author etherdesign
 */
public class CustomHttpSessionListener implements HttpSessionListener {
    
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        //ServletContext sc = event.;
        
        //sc.setAttribute("userCount", appStartTime);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        // nothing to do here
    }
    
}
