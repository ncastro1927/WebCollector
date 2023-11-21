/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comm.cci.demo.controller;

import comm.cci.demo.service.ProductoServicio;
import comm.cci.demo.service.ProductoTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;


/**
 *
 * @author natal
 */
@ManagedBean(name = "productoController")
@ViewScoped
public class ProductoController implements Serializable {

    private List<ProductoTO> listaRetorno1 = new ArrayList<ProductoTO>();
    private ProductoTO selectedProducto;
    private int idTienda;
    
    public ProductoController() throws ClassNotFoundException {
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        String idTiendaParam = externalContext.getRequestParameterMap().get("idTienda");
        this.idTienda=Integer.parseInt(idTiendaParam);
        
        ProductoServicio productoServicio = new ProductoServicio();
        this.listaRetorno1 = productoServicio.demeProducto(idTienda);
    }

    public void openNew() {
        this.selectedProducto = new ProductoTO();
    }

    public void saveProducto() throws ClassNotFoundException {
        ProductoServicio productoServicio = new ProductoServicio();
        
        //Al guardar un producto se referencia en idTienda
        this.selectedProducto.setIdTienda(idTienda);
        productoServicio.insertar(this.selectedProducto);
        this.listaRetorno1 = productoServicio.demeProducto(idTienda);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Acción realizado correctamente"));
    }

    public void deleteProducto() throws ClassNotFoundException {
        ProductoServicio productoServicio = new ProductoServicio();
        productoServicio.eliminar(this.selectedProducto);
        listaRetorno1.remove(selectedProducto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Eliminado"));
    }

    public void comprar() throws ClassNotFoundException{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Comprado"));
    }

    public List<ProductoTO> getListaRetorno1() {
        return listaRetorno1;
    }

    public void setListaRetorno1(List<ProductoTO> listaRetorno1) {
        this.listaRetorno1 = listaRetorno1;
    }

    public ProductoTO getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(ProductoTO selectedProducto) {
        this.selectedProducto = selectedProducto;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }
    
    

    
    
}
