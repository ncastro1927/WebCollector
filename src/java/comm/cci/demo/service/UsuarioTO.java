/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comm.cci.demo.service;

import java.io.Serializable;

/**
 *
 * @author Barri
 */
public class UsuarioTO implements Serializable {

    //atributos
    private String usuario;
    private String clave;
    private String tipo;

    //constructor
    public UsuarioTO() {
    }

    public UsuarioTO(String usuario, String clave, String tipo) {
        this.usuario = usuario;
        this.clave = clave;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
