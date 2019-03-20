
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection conectar(){
        
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/centro_medico?useSSL=false", "root", "");
            return cn;
            
        } catch (SQLException e) {
            System.out.println("Error en la conexion local"+e);
        }
        return (null);
    }
    
}
