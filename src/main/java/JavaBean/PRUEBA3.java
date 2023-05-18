/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaBean;

import DataAccessLayer.MatriculaDAO;
import DataAccessLayer.PagoMatriculaDAO;
import DataAccessLayer.PagoPensionDAO;
import DataAccessLayer.PagoVariosDAO;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.time.LocalDate;

/**
 *
 * @author black
 */
public class PRUEBA3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // TODO code application logic here
        Connection con = null;
        CallableStatement cstm = null;
        PagoMatriculaDAO pagoMatDAO = new PagoMatriculaDAO();
        
        PagoVariosDAO pagoVarDAO = new PagoVariosDAO();
        
        PagoPensionDAO pagoPeDAO = new PagoPensionDAO();

        try {
            pagoMatricula obj1 = new pagoMatricula();

            obj1.setAlumno_id(1);
            obj1.setFecha(LocalDate.now());
            obj1.setMonto(2000.12);
            obj1.setObservacion("Se pago con efectivo");
            obj1.setPago_matricula_id(2);
            pagoMatDAO.actualizarPagoMatricula(obj1);
            
            System.out.println(pagoMatDAO.listarPagoMatricula().size());
            
            pagoPensiones obj2 = new pagoPensiones();

            obj2.setAlumno_id(1);
            obj2.setFecha(LocalDate.now());
            obj2.setMonto(60.212);
            obj2.setObservacion("Se pago con efectivo");
            obj2.setPago_pensiones_id(1);
            pagoPeDAO.actualizarPagoPension(obj2);
            
            pagoVarios obj3 = new pagoVarios();
            
            System.out.println(pagoPeDAO.listarPagoPension().size());

            obj3.setAlumno_id(1);
            obj3.setFecha(LocalDate.now());
            obj3.setMonto(30.99);
            obj3.setObservacion("Se pago con efectivo");
            obj3.setPago_varios_id(1);
            pagoVarDAO.actualizarPagoVarios(obj3);
            
            System.out.println(pagoVarDAO.listarPagoVarios().size());

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
