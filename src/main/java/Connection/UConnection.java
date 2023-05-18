/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author walter
 */

public class UConnection{
    
 private static Connection con=null;
 
// private static final String SERVIDOR="localhost";
// private static final String PUERTO="3306";
// private static final String BASE_DE_DATOS="colegio";
// private static final String USUARIO="root";
// private static final String CLAVE="123456";
 
 
 //=================================================================
    public static Connection getConnection() throws Exception{
        
       // DesencriptaArchivo des = new DesencriptaArchivo();        
//        des.desencriptaDatos();        
    Connection cn= null;
        try {    
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio","root","");            
        }
           catch (Exception e) {           
               System.out.println(String.valueOf(e));
        }    
        return cn;
    }
            
}


