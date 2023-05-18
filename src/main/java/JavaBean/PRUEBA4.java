/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaBean;

import DataAccessLayer.DocenteDAO;
import DataAccessLayer.PagoMatriculaDAO;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.time.LocalDate;

/**
 *
 * @author black
 */
public class PRUEBA4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection con = null;
        CallableStatement cstm = null;
        DocenteDAO docDAO = new DocenteDAO();

        try {
            Docente obj1 = new Docente();

            obj1.setDocente_id(3);
            obj1.setNombres("Jose Olaf");
            obj1.setApellido_materno("Martinez");
            obj1.setApellido_paterno("Castro");
            obj1.setContacto("222456789");
            obj1.setDni("12345888");
            System.out.println(docDAO.buscarPorId(1).getNombres());

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
