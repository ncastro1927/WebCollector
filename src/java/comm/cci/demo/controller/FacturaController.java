/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comm.cci.demo.controller;

import comm.cci.demo.service.FacturaTO;
import comm.cci.demo.service.ProductoTO;
import comm.cci.demo.service.ServicioFactura;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Barri
 */
@ManagedBean(name = "facturaController")
@SessionScoped
public class FacturaController implements Serializable{
    @ManagedProperty("#{productoController}")

    private ProductoController productoController;
    private String nombre;
    private String direccion;
    private String correo;
    private String pago;
    private List<ProductoTO> productos = new ArrayList<ProductoTO>();
    private double total;
    private String usuario;
    private FacturaTO selectedFactura;
    
    
      public List<ProductoTO>  productos(){
        this.productos= productoController.getListaCarrito();
        return productos;
        
    }
    
    
    public double totalPrecio(){
        
        this.total = productoController.getSubtotalCarrito();
        return total;
        
    }
    
    public void facturar() throws ClassNotFoundException{
         ServicioFactura servicioFactura = new ServicioFactura();
         usuario = productoController.obtenerUser();
         selectedFactura = new FacturaTO(nombre,direccion,correo,pago,productos,total,usuario);
         servicioFactura.insertarFactura(selectedFactura);
         this.redireccionar("/faces/CompraRealizada.xhtml");
         
    }
    
    public void menu(){
          this.redireccionar("/faces/index.xhtml");
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
    public ProductoController getProductoController() {
        return productoController;
    }

    public void setProductoController(ProductoController productoController) {
        this.productoController = productoController;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public List<ProductoTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoTO> productos) {
        this.productos = productos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public FacturaTO getSelectedFactura() {
        return selectedFactura;
    }

    public void setSelectedFactura(FacturaTO selectedFactura) {
        this.selectedFactura = selectedFactura;
    }

  


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
