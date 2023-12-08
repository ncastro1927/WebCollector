package comm.cci.demo.controller;

import comm.cci.demo.service.ServicioUsuario;
import comm.cci.demo.service.ServicioPerfil;
import comm.cci.demo.service.UsuarioTO;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Barri
 */

@ManagedBean(name = "perfilController")
@SessionScoped
public class PerfilController implements Serializable{
    //atributos
    @ManagedProperty("#{loginController}")
    private LoginController loginController;
    private UsuarioTO selectedUsuario;
    private String clave;
    private String claveNueva;
    
    public PerfilController() {
    }
    
     //imprime los datos del usuario
    public void ingresar() throws ClassNotFoundException{
         ServicioUsuario servicioUsuario = new ServicioUsuario();
         UsuarioTO selectedUsuario = servicioUsuario.validarUsuario(loginController.getUsuario(),loginController.getClave());
            if(selectedUsuario.getTipo().equals("Cliente")){
                this.redireccionar("/faces/PerfilCliente.xhtml");
             }else{
                 this.redireccionar("/faces/PerfilAdmin.xhtml");
             }    
           
    }
    
       //imprime los datos del usuario
    public void cambiarClave() throws ClassNotFoundException{
         ServicioUsuario servicioUsuario = new ServicioUsuario();
         UsuarioTO selectedUsuario = servicioUsuario.validarUsuario(loginController.getUsuario(),loginController.getClave());
            if(selectedUsuario.getClave().equals(clave)){
                ServicioPerfil servicioPerfil = new ServicioPerfil();
                servicioPerfil.modificar(claveNueva, selectedUsuario.getUsuario());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Clave actualizada"));
             }else{
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Clave actual incorrecta"));
             }    
           
    }
    

       //redirecciona
    public void redireccionar(String ruta){
        HttpServletRequest request;
        try{
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        }catch(Exception e){
        
        }
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public UsuarioTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(UsuarioTO selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }
    
   
    
    
}
