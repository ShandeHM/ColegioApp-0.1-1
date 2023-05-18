/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaBean;

import DataAccessLayer.ApoderadoDAO;
import DataAccessLayer.MatriculaDAO;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.time.LocalDate;

/**
 *
 * @author black
 */
public class PRUEBA2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        Connection con=null;
        CallableStatement cstm = null;  
        MatriculaDAO matDAO = new MatriculaDAO();
        
        try {
            Matricula obj1 = new Matricula();
        
            obj1.setMatricula_id(1);
            obj1.setFecha(LocalDate.now());
            obj1.setGrado('B');
            obj1.setNivel('Z');
            obj1.setTurno('N');
            obj1.setAlumno_id(1);
            matDAO.actualizarMatricula(obj1);
            
            System.out.println(matDAO.ListarMatriculas().get(0).getMatricula_id());
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
    
