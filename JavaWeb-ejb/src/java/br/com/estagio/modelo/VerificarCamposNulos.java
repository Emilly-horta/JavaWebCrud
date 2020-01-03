/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estagio.modelo;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author emilly
 */
@Named
@RequestScoped
public class VerificarCamposNulos {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void saveMessage(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucessfull"));
    }
    
    
}
