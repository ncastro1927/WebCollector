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

/**
 *
 * @author Barri
 */
@ManagedBean(name = "registroController")
@SessionScoped
public class RegistroController implements Serializable{
    //atributos
    private String usuario;
    private String clave;
    private String tipo;
    private  ArrayList<UsuarioTO> listaUsuario  = new ArrayList<UsuarioTO>();
    private UsuarioTO selectedUsuario;

    
    public RegistroController() {
    }
    
        public void registrar() throws ClassNotFoundException{
        boolean validar = false;
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        this.listaUsuario = servicioUsuario.retornarUsuario();
        
        for(UsuarioTO user :listaUsuario) {
            if(user.getUsuario().equals(selectedUsuario.getUsuario())){                       
                 validar=true;
            }
        }
        if(validar==false){
            if(selectedUsuario.getTipo()==null){
                
                selectedUsuario.setTipo("Cliente");
                servicioUsuario.insertar(selectedUsuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario agregado"));
                
            }else{
                servicioUsuario.insertar(selectedUsuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario agregado"));
            }
        }else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error" ,"Ese usuario ya existe, intente con otro"));
        }      
         
    }
        
    public void openNew(){
       this.selectedUsuario = new UsuarioTO();
       
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    
}
