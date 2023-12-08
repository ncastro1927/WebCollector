/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comm.cci.demo.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Barri
 */
public class FacturaTO implements Serializable {
    private String nombre;
    private String direccion;
    private String correo;
    private String pago;
    private List<ProductoTO> productos = new ArrayList<ProductoTO>();
    private double total;
    private String usuario;

    public FacturaTO() {
    }

    public FacturaTO(String nombre, String direccion, String correo, String pago, List<ProductoTO> productos,double total, String usuario) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.pago = pago;
        this.productos = productos;
        this.total = total;
        this.usuario = usuario;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
        
}
