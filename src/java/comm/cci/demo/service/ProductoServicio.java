/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comm.cci.demo.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author natal
 */
public class ProductoServicio extends Servicio {

    public List<ProductoTO> demeProducto;

    public ProductoServicio() {
    }

    public List<ProductoTO> demeProducto(int idTienda) throws ClassNotFoundException {

        List<ProductoTO> listaRetorno1 = new ArrayList<ProductoTO>();
        try {

            PreparedStatement stmt = super.getConexion().prepareStatement("SELECT idproducto, codigo, nombre, descripcion, imagen, precio, categoria, cantidadDisponible  FROM producto WHERE idTienda = ?");
            stmt.setInt(1, idTienda);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idproducto = rs.getInt("idproducto");
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String imagen = rs.getString("imagen");
                Double precio = rs.getDouble("precio");
                String categoria = rs.getString("categoria");
                String cantidadDisponible = rs.getString("cantidadDisponible");
                ProductoTO productoTO = new ProductoTO(idTienda, codigo, nombre, descripcion, imagen, precio, categoria, cantidadDisponible, idTienda);
                productoTO.setId(idproducto);
                productoTO.setCodigo(codigo);
                productoTO.setNombreProducto(nombre);
                productoTO.setDescripcionProducto(descripcion);
                productoTO.setImagen(imagen);
                productoTO.setPrecio(precio);
                productoTO.setCategoriaProducto(categoria);
                productoTO.setCantidadDisponible(cantidadDisponible);
                productoTO.setIdTienda(idTienda);
                listaRetorno1.add(productoTO);

            }

            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaRetorno1;

    }

    public void insertar(ProductoTO productoTO) {
        try {

            if (!existente(productoTO.getCodigo())) {
                PreparedStatement stmt = super.getConexion().prepareStatement("INSERT INTO producto (idproducto, codigo, nombre, descripcion, imagen, precio, categoria, cantidadDisponible, idTienda) VALUES (?,?,?,?,?,?,?,?,?)");

                stmt.setInt(1, productoTO.getId());
                stmt.setString(2, productoTO.getCodigo());
                stmt.setString(3, productoTO.getNombreProducto());
                stmt.setString(4, productoTO.getDescripcionProducto());
                stmt.setString(5, productoTO.getImagen());
                stmt.setDouble(6, productoTO.getPrecio());
                stmt.setString(7, productoTO.getCategoriaProducto());
                stmt.setString(8, productoTO.getCantidadDisponible());
                stmt.setInt(9, productoTO.getIdTienda());
                
                stmt.execute();
                stmt.close();

            } else {
                PreparedStatement stmt = super.getConexion().prepareStatement("UPDATE producto SET codigo=?, nombre=? , descripcion=?, imagen=?, precio=?, categoria=?, cantidadDisponible=?  where idproducto=?");

                stmt.setString(1, productoTO.getCodigo());
                stmt.setString(2, productoTO.getNombreProducto());
                stmt.setString(3, productoTO.getDescripcionProducto());
                stmt.setString(4, productoTO.getImagen());
                stmt.setDouble(5, productoTO.getPrecio());
                stmt.setString(6, productoTO.getCategoriaProducto());
                stmt.setString(7, productoTO.getCantidadDisponible());
                stmt.setInt(8, productoTO.getId());

                stmt.execute();
            }

        } catch (Exception ex) {
            System.out.println("Error al abrir ConexiÃ³n: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminar(ProductoTO productoTO) throws ClassNotFoundException {
        try {

            PreparedStatement stmt = super.getConexion().prepareStatement("DELETE FROM producto WHERE idproducto = ?");

            stmt.setInt(1, productoTO.getId());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private boolean existente(String codigo) throws ClassNotFoundException {

        try {

            PreparedStatement stmt = super.getConexion().prepareStatement("SELECT COUNT(*) FROM producto WHERE codigo = ?");
            stmt.setString(1, codigo);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                int count = resultado.getInt(1);
                return count > 0;
            }

        } catch (SQLException ex) {
            System.out.println("Error al actualizar" + ex.getMessage());
        }

        return false;
    }
}
