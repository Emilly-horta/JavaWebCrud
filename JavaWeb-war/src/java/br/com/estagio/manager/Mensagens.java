/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estagio.manager;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author emilly
 */
public class Mensagens {

    public static final String SuccessFull = "Operação Realizada com Sucesso";
    public static final String Failure = "Erro ao Realizar a Operação";
    public static final String Warn = "Falha na Operação";
    public static final String PermissaoNegada = "Você não tem permissão para executar esta operação !";

    public static void messagemInfo(String... message) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (message != null && message[0] != null) {
                context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, message[0], ""));
            }
        } catch (Exception e) {
            context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, Mensagens.SuccessFull, ""));
        }
    }

    public static void messagemError(String... message) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (message[0] != null) {
                context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, message[0], ""));
            }
        } catch (Exception e) {
            context.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, Mensagens.Failure, ""));
        }
    }

    public static void messagemWarn(String... message) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (message != null && message[0] != null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message[0], ""));
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Mensagens.Warn, ""));
        }
    }

    public static void redirect(String url) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getFlash().setKeepMessages(true);
        try {
            context.redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(Mensagens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void messagemInfoRedirect(String message, String url) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
        fc.getExternalContext().getFlash().setKeepMessages(true);
        try {
            context.redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(Mensagens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void messagemWarnRedirect(String message, String url) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
        fc.getExternalContext().getFlash().setKeepMessages(true);
        try {
            context.redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(Mensagens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void messagemErrorRedirect(String message, String url) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
        fc.getExternalContext().getFlash().setKeepMessages(true);
        try {
            context.redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(Mensagens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

