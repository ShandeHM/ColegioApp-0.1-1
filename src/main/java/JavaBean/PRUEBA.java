/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaBean;

import Connection.UConnection;
import DataAccessLayer.ApoderadoDAO;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author black
 */
public class PRUEBA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Alumno alumno = new Alumno();
        try {   
            alumno.setAlumno_id(1);
            alumno.setDni("12994212");
            alumno.setNombres("Martha");
            alumno.setApellido_materno("Castro");
            alumno.setApellido_paterno("Hernandez");
            alumno.setCorreo_electrico("hola@gmail.com");
            alumno.setFecha_nacimiento(LocalDate.now());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // TODO code application logic here
        Connection con=null;
        CallableStatement cstm = null;  
        ApoderadoDAO apoDAO = new ApoderadoDAO();
        
        try {
            Apoderado apoderado = new Apoderado();
            apoderado.setApoderado_id(0);
            apoderado.setNombres("Jaime");
            apoderado.setApellido_materno("Segovia");
            apoderado.setApellido_paterno("Pérez");
            apoderado.setDni("12345678");
            apoderado.setContacto("123456789");
            
            System.out.println(  apoDAO.buscarApoderadoPorNombre("").get(0).getNombres() );
            System.out.println(  apoDAO.buscarApoderadoPorNombre("").size());
        } catch (Exception e) {
            if(e.getMessage().contains("idx_alumno_dni")){
                System.out.println("2");
            }
            if(e.getMessage().contains("idx_correo_electronico")) {
                System.out.println("3");
            }
            Bitacora.registrar(e);
            System.out.println("4");
            System.out.println(e.getMessage());
        }finally{
            try {
               if(cstm!=null)cstm.close();             
            } catch (Exception e) {
                Bitacora.registrar(e);
            }        
        } 
    }
}
