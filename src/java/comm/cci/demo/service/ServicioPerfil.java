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
public class ServicioPerfil extends Servicio{
    
      //modifica usuarios
     public void modificar(String clave, String usuario) throws ClassNotFoundException {
        try {
            PreparedStatement stmt = super.getConexion().prepareStatement("UPDATE users SET clave = ? WHERE usuario=?");
            stmt.setString(1, clave);
            stmt.setString(2, usuario);
            stmt.execute();

            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error al MODIFICAR usuario: " + ex.getMessage());
        }

    }
    
}
