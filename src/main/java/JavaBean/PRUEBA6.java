/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaBean;

import DataAccessLayer.AlumnoApoderadoDAO;
import DataAccessLayer.AsistenciaAlumnoDAO;
import DataAccessLayer.CursoDAO;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.time.LocalDate;

/**
 *
 * @author black
 */
public class PRUEBA6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection con = null;
        CallableStatement cstm = null;
        AlumnoApoderadoDAO alumApoDAO = new AlumnoApoderadoDAO();
        
        AsistenciaAlumnoDAO asisAlumDAO = new AsistenciaAlumnoDAO();

        try {
            alumnoApoderado obj1 = new alumnoApoderado();
            
            obj1.setAlumno_id(2);
            obj1.setApoderado_id(1);
            obj1.setParentesco("Tio");

           alumApoDAO.actualizarApoderado(obj1);
            System.out.println(alumApoDAO.buscarApoderadoPorAlumnoId(1).getParentesco());
            
            AsistenciaAlumno asisAlum = new AsistenciaAlumno();
            
            asisAlum.setAlumno_id(1);
            asisAlum.setAsistencia_alumno_id(1);
            asisAlum.setFecha(LocalDate.now());
            asisAlum.setObservacion("Llego tarde");
            
            asisAlumDAO.actualizarAsistenciaAlumno(asisAlum);
            System.out.println(asisAlumDAO.buscarAsistenciaAlumnoPorAlumnoId(1).size());
            
            System.out.println(asisAlumDAO.listarAsistenciaAlumno().size());
           
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
