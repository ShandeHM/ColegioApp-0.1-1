/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaBean;

import DataAccessLayer.CursoDAO;
import DataAccessLayer.DocenteDAO;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;


/**
 *
 * @author black
 */
public class PRUEBA5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection con = null;
        CallableStatement cstm = null;
        CursoDAO curDAO = new CursoDAO();

        try {
            Curso obj1 = new Curso();
            
            obj1.setCurso_id(1);
            obj1.setNombre("Curso borrable");
            obj1.setGrado('A');
            obj1.setNivel('B');
            obj1.setArea_id(1);

            System.out.println(curDAO.buscarPorCurso("Curso").get(0).getNombre());
            System.out.println( curDAO.buscarPorCursoId(2).getNombre() );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (cstm != null) {
                    cstm.close();
                }
            } catch (Exception e) {
                Bitacora.registrar(e);
            }
        }
    }
    
    
}
