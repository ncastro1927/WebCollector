/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comm.cci.demo.controller;

import comm.cci.demo.service.ProductoServicio;
import comm.cci.demo.service.ProductoTO;
import comm.cci.demo.service.TiendaTO;
import comm.cci.demo.service.UsuarioTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author natal
 */
@ManagedBean(name = "productoController")
@SessionScoped
public class ProductoController implements Serializable {
    @ManagedProperty("#{loginController}")
    private LoginController loginController;
    
    private List<ProductoTO> listaRetorno1 = new ArrayList<ProductoTO>();
    private List<ProductoTO> listaCarrito = new ArrayList<ProductoTO>();
    private ProductoTO selectedProducto;
    private int idTienda;
    private int cantidadAgregar;
    List<TiendaTO> listaTiendas = new ArrayList<TiendaTO>();
    private TiendaTO selectedTienda;
    private int cantidadTotal;
    private double subtotalCarrito;
    private String user;

    public ProductoController() throws ClassNotFoundException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        String idTiendaParam = externalContext.getRequestParameterMap().get("idTienda");

        // Verificación para asegurarse de que idTiendaParam no sea nulo
        if (idTiendaParam != null && !idTiendaParam.isEmpty()) {
            try {
                this.idTienda = Integer.parseInt(idTiendaParam);
                ProductoServicio productoServicio = new ProductoServicio();
                this.listaRetorno1 = productoServicio.demeProducto(idTienda);
            } catch (NumberFormatException e) {
                // Manejar el caso en el que la conversión falla
                System.err.println("Error al convertir el parámetro idTienda a entero.");
            }
        } else {
            // Manejar el caso en el que idTiendaParam es nulo o vacío
            System.err.println("El parámetro idTienda es nulo o vacío.");
        }
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

    public void deleteProductoCarrito() throws ClassNotFoundException {
        ProductoServicio productoServicio = new ProductoServicio();
        int cantidadCarrito = selectedProducto.getCantidadEnCarrito();
        int nuevaCantidad = selectedProducto.getCantidadDisponible() + cantidadCarrito;

        // Actualizar la cantidad disponible en la base de datos
        selectedProducto.setCantidadDisponible(nuevaCantidad);
        productoServicio.actualizarCantidadProducto(selectedProducto);

        // Eliminar el producto del carrito
        listaCarrito.remove(selectedProducto);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Eliminado del Carrito"));
    }

    public void agregarCarrito() throws ClassNotFoundException {
        int cantidadDisponible = selectedProducto.getCantidadDisponible();
        int cantidadActual = selectedProducto.getCantidadDisponible();
        int nuevaCantidad = cantidadActual - cantidadAgregar;

        if (cantidadAgregar > 0 && cantidadAgregar <= cantidadDisponible) {
            // Verificar si hay suficiente cantidad disponible
            if (cantidadDisponible >= cantidadAgregar) {
                // Buscar si el producto ya está en el carrito
                boolean encontrado = false;
                for (ProductoTO producto : listaCarrito) {
                    if (producto.equals(selectedProducto)) {
                        producto.setCantidadEnCarrito(producto.getCantidadEnCarrito() + cantidadAgregar);
                        encontrado = true;
                        break;
                    }
                }

                // Si no se encontró, agregarlo al carrito
                if (!encontrado) {
                    selectedProducto.setCantidadEnCarrito(cantidadAgregar);
                    listaCarrito.add(selectedProducto);
                }

                // Actualizar la cantidad disponible
                ProductoServicio productoServicio = new ProductoServicio();
                selectedProducto.setCantidadDisponible(nuevaCantidad);
                productoServicio.actualizarCantidadProducto(selectedProducto);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto(s) agregado(s) al Carrito"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No hay suficiente cantidad disponible para agregar al carrito."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cantidad seleccionada no es válida."));
        }
    }

    public int calcularCantidadTotalCarrito() {
        cantidadTotal = 0;
        for (ProductoTO producto : listaCarrito) {
            cantidadTotal += producto.getCantidadEnCarrito();
        }
        return cantidadTotal;
    }

    public double calcularSubtotalCarrito() {
        subtotalCarrito = 0.0;
        for (ProductoTO producto : listaCarrito) {
            subtotalCarrito += producto.getSubtotal();
        }
        return subtotalCarrito;
    }
    
    public String obtenerUser(){
             this.user = loginController.getUsuario();
             return user;
    }
        
    public void pagina() throws ClassNotFoundException{ 
        this.redireccionar("/faces/Factura.xhtml");

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
    

    public List<ProductoTO> getListaRetorno1() throws ClassNotFoundException {
        
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

    public List<ProductoTO> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(List<ProductoTO> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }

    public int getCantidadAgregar() {
        return cantidadAgregar;
    }

    public void setCantidadAgregar(int cantidadAgregar) {
        this.cantidadAgregar = cantidadAgregar;
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

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public double getSubtotalCarrito() {
        return subtotalCarrito;
    }

    public void setSubtotalCarrito(double subtotalCarrito) {
        this.subtotalCarrito = subtotalCarrito;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

 
    

}
