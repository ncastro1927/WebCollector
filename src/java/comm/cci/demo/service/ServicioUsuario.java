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

/**
 *
 * @author Barri
 */
public class ServicioUsuario extends Servicio{
    
    //valida si el usuario ya existe 
     public UsuarioTO validarUsuario(String usuario, String clave) throws ClassNotFoundException {
        UsuarioTO usuarioTO=null;
        
        try {
            PreparedStatement stmt = super.getConexion().prepareStatement("SELECT * FROM users WHERE usuario=? AND clave=?");
            stmt.setString(1, usuario);
            stmt.setString(2, clave);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String user = rs.getString("usuario");
                String contra = rs.getString("clave");
                String tipo = rs.getString("tipo");
                
                 usuarioTO = new UsuarioTO(user,contra, tipo);
                
            }
            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Error al abrir Conexión: " + ex.getMessage());
        }

        return usuarioTO;
    }
    
     //retorna los usuarios de la base de datos
     public ArrayList<UsuarioTO>  retornarUsuario() throws ClassNotFoundException{
         ArrayList<UsuarioTO> listaRetorno = new ArrayList<UsuarioTO>();
         
        

        try {
            PreparedStatement stmt = super.getConexion().prepareStatement("SELECT usuario,clave,tipo FROM users ");
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String user = rs.getString("usuario");
                String clave = rs.getString("clave");
                String tipo = rs.getString("tipo");
                
                UsuarioTO usuarioTO = new UsuarioTO(user, clave, tipo);
                listaRetorno.add(usuarioTO);
            }
            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Error al abrir Conexión: " + ex.getMessage());
        }
      
        return listaRetorno;
    }
    
    
     //inserta usuarios
    public void insertar(UsuarioTO usuarioTO) throws ClassNotFoundException {
        try {
            PreparedStatement stmt = super.getConexion().prepareStatement("INSERT INTO users(usuario, clave,tipo) VALUES (?,?,?)");
            //stmt.setInt(1, usuarioTO.getId());
            stmt.setString(1, usuarioTO.getUsuario());
            stmt.setString(2, usuarioTO.getClave());
            stmt.setString(3, usuarioTO.getTipo());
            stmt.execute();

            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar usuario: " + ex.getMessage());
        }

    }
    
    //modifica usuarios
     public void modificar(UsuarioTO usuarioTO) throws ClassNotFoundException {
        try {
            PreparedStatement stmt = super.getConexion().prepareStatement("UPDATE users SET tipo = ?, usuario = ?, clave = ? WHERE usuario=?");
            stmt.setString(1, usuarioTO.getTipo());
            stmt.setString(2, usuarioTO.getUsuario());
            stmt.setString(3, usuarioTO.getClave());
            stmt.setString(4, usuarioTO.getUsuario());
            stmt.execute();

            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error al MODIFICAR usuario: " + ex.getMessage());
        }

    }
     
     //elimina usuarios
     public void eliminar(UsuarioTO usuarioTO) throws ClassNotFoundException {
        try {
            PreparedStatement stmt = super.getConexion().prepareStatement("DELETE FROM users WHERE usuario=?");
            stmt.setString(1, usuarioTO.getUsuario());
            stmt.execute();

            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error al ELIMINAR usuario: " + ex.getMessage());
        }

    }
    
    
}
