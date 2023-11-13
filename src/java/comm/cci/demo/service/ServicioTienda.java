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
 * @author jguzm
 */
public class ServicioTienda extends Servicio{
    
    public List<TiendaTO> demeTiendas() throws ClassNotFoundException { //Metodo Que retorna la lista de todas las tiendas desde la bd

        List<TiendaTO> listaTiendas = new ArrayList<TiendaTO>(); //Array destinado a guardas todas las tiendas

        try {
            PreparedStatement stmt = super.getConexion().prepareStatement("SELECT idTienda,nomTienda,categoria,descripcion,fk_idUser FROM tienda");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) { //Este while lo que hace es eterse columna por colunma de la BD para ver que info tiene adentro
                String idTienda = rs.getString("idTienda");
                String nomTienda = rs.getString("nomTienda");
                String categoriaTienda = rs.getString("categoria");
                String descripcionTienda = rs.getString("descripcion");     
                String fk_idUser = rs.getString("fk_idUser");

                TiendaTO tiendaTO = new TiendaTO(idTienda,nomTienda,categoriaTienda,descripcionTienda,fk_idUser);
                listaTiendas.add(tiendaTO);

                System.out.println("Info Tiendas: " + idTienda + " , " + nomTienda + " , " + descripcionTienda + " , " + categoriaTienda +" , " + fk_idUser); //imprime oda la info que recolecto de las columnas
            }

            rs.close(); //Cierro todas las conexiones con la BD para que este no se sobrecargue y se cierre
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime el lugar donde ocurrio el error
        }

        return listaTiendas;
    }
    
    public void insertarTienda(TiendaTO tiendaTO) throws ClassNotFoundException {

        try {
 
            PreparedStatement stmt = super.getConexion().prepareStatement("INSERT INTO tienda(nomTienda,categoria,descripcion) VALUES (?,?,?)");
            stmt.setString(1, tiendaTO.getNomTienda());
            stmt.setString(2, tiendaTO.getCategoriaTienda());
            stmt.setString(3, tiendaTO.getDescripcionTienda());
            
            
            stmt.execute();
            //Cierro todas las conexiones con la BD para que este no se sobrecargue y se cierre
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al insertar datos: " + ex.getMessage());
        }
    }
    
}
