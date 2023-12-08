/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comm.cci.demo.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author Barri
 */
public class ServicioFactura extends Servicio{
    
      public void insertarFactura(FacturaTO facturaTO) throws ClassNotFoundException {

        try {
            PreparedStatement stmt = super.getConexion().prepareStatement("INSERT INTO factura( nombre, direccion, correo, pago, productos, total, usuario ) VALUES (?,?,?,?,?,?,?)");

            stmt.setString(1, facturaTO.getNombre());
            stmt.setString(2, facturaTO.getDireccion());
            stmt.setString(3, facturaTO.getCorreo());
            stmt.setString(4, facturaTO.getPago());
            stmt.setString(5, facturaTO.getProductos().toString());
            stmt.setDouble(6, facturaTO.getTotal());
            stmt.setString(7, facturaTO.getUsuario());
            

            stmt.execute();
            //Cierro todas las conexiones con la BD para que este no se sobrecargue y se cierre
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al insertar datos: " + ex.getMessage());
        }
    }
    
}
