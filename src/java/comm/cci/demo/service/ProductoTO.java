package comm.cci.demo.service;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author natal
 */

public class ProductoTO implements Serializable {
    private int id;
    private String codigo;
    private String nombreProducto;
    private String descripcionProducto;
    private String imagen;
    private double precio;
    private String categoriaProducto;

    public ProductoTO() {
    }

    public ProductoTO(int id, String codigo, String nombreProducto, String descripcionProducto, String imagen, double precio, String categoriaProducto) {
        this.id = id;
        this.codigo = codigo;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.imagen = imagen;
        this.precio = precio;
        this.categoriaProducto = categoriaProducto;
    }

    public ProductoTO(String codigo, String nombreProducto, String descripcionProducto, double precio, String categoriaProducto) {
        this.codigo = codigo;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precio = precio;
        this.categoriaProducto = categoriaProducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    @Override
    public String toString() {
        return "ProductoTO{" + "id=" + id + ", codigo=" + codigo + ", nombreProducto=" + nombreProducto + ", descripcionProducto=" + descripcionProducto + ", imagen=" + imagen + ", precio=" + precio + ", categoriaProducto=" + categoriaProducto + '}';
    }

    
    
    
}
