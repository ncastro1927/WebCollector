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
                int idTienda = rs.getInt("idTienda");
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

            if (!existente(tiendaTO.getIdTienda())) {

                PreparedStatement stmt = super.getConexion().prepareStatement("INSERT INTO tienda( nomTienda, descripcion, categoria) VALUES (?,?,?)");

                stmt.setString(1, tiendaTO.getNomTienda());
                stmt.setString(2, tiendaTO.getDescripcionTienda());
                stmt.setString(3, tiendaTO.getCategoriaTienda());

                stmt.execute();
                //Cierro todas las conexiones con la BD para que este no se sobrecargue y se cierre
                stmt.close();

            } else {
                PreparedStatement stmt = super.getConexion().prepareStatement("UPDATE tienda SET nomTienda=?, descripcion=? , categoria=? where idTienda=?");
                 stmt.setString(1, tiendaTO.getNomTienda());
                stmt.setString(2, tiendaTO.getDescripcionTienda());
                stmt.setString(3, tiendaTO.getCategoriaTienda());
                stmt.setInt(4, tiendaTO.getIdTienda());
 stmt.execute();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al insertar datos: " + ex.getMessage());
        }
    }

    private boolean existente(int idTienda) throws ClassNotFoundException {
         try{
           
           PreparedStatement stmt = super.getConexion().prepareStatement("SELECT COUNT(*) FROM tienda WHERE idTienda = ?");
           stmt.setInt(1, idTienda);
           ResultSet resultado = stmt.executeQuery();
           if(resultado.next()){
               int count = resultado.getInt(1);
               return count >0;
           }

       }catch (SQLException ex){
           System.out.println("Error al actualizar"+ ex.getMessage());
       }

       return false;
   } 
    
     public void eliminar(TiendaTO tiendaTO) throws ClassNotFoundException{
        try {
          
            PreparedStatement stmt = super.getConexion().prepareStatement("DELETE FROM tienda WHERE idTienda = ?");

            stmt.setInt(1, tiendaTO.getIdTienda());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
