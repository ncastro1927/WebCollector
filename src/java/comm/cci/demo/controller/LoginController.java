/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comm.cci.demo.controller;

import comm.cci.demo.service.ServicioUsuario;
import comm.cci.demo.service.UsuarioTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Barri
 */

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable{
    //atributos
    private String usuario;
    private String clave;
    private String tipo;
    private  ArrayList<UsuarioTO> listaUsuario  = new ArrayList<UsuarioTO>();
    private UsuarioTO selectedUsuario;

    //constructor 
    public LoginController() {
    }
    
    //ingresa usuarios
    public void ingresar() throws ClassNotFoundException{
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        UsuarioTO selectedUsuario = servicioUsuario.validarUsuario(this.getUsuario(),this.getClave());
        
        if(selectedUsuario!=null){
            
             if(selectedUsuario.getTipo().equals("Cliente")){
                this.redireccionar("/faces/TiendasCliente.xhtml");
             }else{
                 this.redireccionar("/faces/TiendasAdmin.xhtml");
             }      
            
         }else{
             FacesContext.getCurrentInstance().addMessage("sticky-key",new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campos invalidos","Usuario o Clave incorrectos"));
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

    //elimina usuario
    public void deleteUser() throws ClassNotFoundException{
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        servicioUsuario.eliminar(this.selectedUsuario);
        this.listaUsuario = servicioUsuario.retornarUsuario();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario eliminado"));
    }
    
    //crea un nuevo usuario
    public void openNew(){
       this.selectedUsuario = new UsuarioTO();
       
    }
    
    //getters y setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public ArrayList<UsuarioTO> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(ArrayList<UsuarioTO> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public UsuarioTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(UsuarioTO selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
