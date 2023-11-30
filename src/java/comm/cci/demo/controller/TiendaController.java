/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comm.cci.demo.controller;

import comm.cci.demo.service.ServicioTienda;
import comm.cci.demo.service.ServicioUsuario;
import comm.cci.demo.service.TiendaTO;
import comm.cci.demo.service.UsuarioTO;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;

/**
 *
 * @author jguzm
 */
@ManagedBean(name = "tiendaController")
@SessionScoped
public class TiendaController implements Serializable {

    //Atributos de las tiendas
    private String nomTienda;
    private String descripcionTienda;
    private String categoriaTienda;
    List<TiendaTO> listaTiendas = new ArrayList<TiendaTO>();
    private TiendaTO selectedTienda;

    //Constructor de la clase el cual se encarga de llenar la listaTiendas para evitar un nullPointerexception
    public TiendaController() throws ClassNotFoundException {
        listaTiendas = new ArrayList<TiendaTO>();
        ServicioTienda servicioTienda = new ServicioTienda();
        this.listaTiendas = servicioTienda.demeTiendas();
    }

    //OpenNew de Tienda
    public void openNew() {
        this.selectedTienda = new TiendaTO();
    }

    //Metodo par Guargar Tiendas
    public void saveTienda() throws ClassNotFoundException {

        ServicioTienda servicioTienda = new ServicioTienda();

        servicioTienda.insertarTienda(this.selectedTienda);

        this.listaTiendas = servicioTienda.demeTiendas();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tienda Agregada"));
        PrimeFaces.current().executeScript("PF('crearTiendaDialog').hide()");
    }

    public void ingresarProductosAdmin(int idTienda) throws ClassNotFoundException {
        this.redireccionar("/faces/ProductosAdmin.xhtml?idTienda=" + idTienda);
    }

    public void ingresarProductosCliente(int idTienda) throws ClassNotFoundException {
        this.redireccionar("/faces/ProductosCliente.xhtml?idTienda=" + idTienda);
    }

    public void ingresarPToString() throws ClassNotFoundException {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Método de prueba ejecutado", null));
        System.out.println("Ingrese a metodo de prueba");
    }

    public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {
            e.printStackTrace(); // O utiliza algún sistema de logging
        }
    }

    public void deleteTienda() throws ClassNotFoundException {
        ServicioTienda servicioTienda = new ServicioTienda();
        servicioTienda.eliminar(this.selectedTienda);
        listaTiendas.remove(selectedTienda);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tienda Eliminado"));

    }

    public String getNomTienda() {
        return nomTienda;
    }

    public void setNomTienda(String nomTienda) {
        this.nomTienda = nomTienda;
    }

    public String getDescripcionTienda() {
        return descripcionTienda;
    }

    public void setDescripcionTienda(String descripcionTienda) {
        this.descripcionTienda = descripcionTienda;
    }

    public String getCategoriaTienda() {
        return categoriaTienda;
    }

    public void setCategoriaTienda(String categoriaTienda) {
        this.categoriaTienda = categoriaTienda;
    }

    public List<TiendaTO> getListaTiendas() {
        return listaTiendas;
    }

    public void setListaTiendas(List<TiendaTO> listaTiendas) {
        this.listaTiendas = listaTiendas;
    }

    public TiendaTO getSelectedTienda() {
        return selectedTienda;
    }

    public void setSelectedTienda(TiendaTO selectedTienda) {
        this.selectedTienda = selectedTienda;
    }

    @Override
    public String toString() {
        return "TiendaController{" + "nomTienda=" + nomTienda + ", descripcionTienda=" + descripcionTienda + ", listaTiendas=" + listaTiendas + ", selectedTienda=" + selectedTienda + '}';
    }

}
