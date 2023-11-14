/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comm.cci.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Barri
 */
public class Servicio {
    
    private Connection conectar = null;
    private static String url = "jdbc:mysql://localhost:3306/bd_webcollector?serverTimezone=UTC&zeroDateTimeBehavior=convertToNull"; //Conecto la BD (Conexion CASA)
    private String usuario = "root"; //Le doy el usuario de acceso
    private String password = "123456";//Le doy la contraseña de acceso
    private String nombre;

    public Servicio() {
    }
    
    private Connection conectarBBDD() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        conectar = DriverManager.getConnection(url, usuario, password);
        System.out.println("Conexión Exitosa " + conectar);
        return conectar;
    }
    
    public Connection getConexion() throws  SQLException, ClassNotFoundException{
        if (conectar == null) 
            conectarBBDD();
        return conectar;
    }
    
    
    
    
}
