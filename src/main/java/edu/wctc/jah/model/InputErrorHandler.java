/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jah.model;

/**
 *
 * @author etherdesign
 */
public class InputErrorHandler implements ErrorStrategy {

    public static String ERRORMSG = "";
    
    @Override
    public final void errorOut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
