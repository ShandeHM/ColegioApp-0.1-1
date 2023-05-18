/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Connection.UConnection;
import JavaBean.Apoderado;
import JavaBean.Matricula;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author black
 */
public class MatriculaDAO {
    private Connection con;
    CallableStatement cstm = null; 
    
    public void insertarMatricula(Matricula matricula) throws Exception{
        try {
            con = UConnection.getConnection();
            String sql = "call sp_matricula_insertar(?,?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            
            cstm.registerOutParameter(1, java.sql.Types.INTEGER);
            cstm.setDate(2, Date.valueOf(matricula.getFecha()));
            cstm.setString(3, String.valueOf(matricula.getGrado() ));
            cstm.setString(4, String.valueOf(matricula.getNivel() ));
            cstm.setString(5, String.valueOf(matricula.getTurno() ));
            cstm.setInt(6, matricula.getAlumno_id());
            
            cstm.execute();
        } catch (Exception e) {
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        }
        finally{
            try {
               if(cstm!=null)cstm.close();             
            } catch (Exception e) {
                Bitacora.registrar(e);
            }       
        }
    }
    
    public void actualizarMatricula(Matricula matricula) throws Exception{
        try {
            con = UConnection.getConnection();
            String sql = "call sp_matricula_actualizar(?,?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            
            cstm.setInt(1, matricula.getMatricula_id());
            cstm.setDate(2, Date.valueOf(matricula.getFecha()));
            cstm.setString(3, String.valueOf(matricula.getGrado() ));
            cstm.setString(4, String.valueOf(matricula.getNivel() ));
            cstm.setString(5, String.valueOf(matricula.getTurno() ));
            cstm.setInt(6, matricula.getAlumno_id());
            
            cstm.executeUpdate();
        } catch (Exception e) {
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        }
        finally{
            try {
               if(cstm!=null)cstm.close();             
            } catch (Exception e) {
                Bitacora.registrar(e);
            }       
        }
    }
    
    public void eliminarMatricula(int id) throws Exception{     
        try {
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_matricula_eliminar(?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, id);
         
            cstm.executeUpdate();
        }catch (Exception e) {           
            Bitacora.registrar(e);            
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
            
        }finally{
            try {
                if(cstm!=null)cstm.close();             
            } catch (Exception e) {
                Bitacora.registrar(e);
            }        
        }        
    }

    public Matricula buscarMatriculaPorId(int id) throws Exception{
        Matricula matricula = null;
        ResultSet rs = null;
        try {
            con = UConnection.getConnection();
            String sql = "call sp_matricula_buscar_por_id (?)";
            cstm = con.prepareCall(sql);
            cstm.setInt(1, id);
            
            rs = cstm.executeQuery();
            
            while(rs.next()){
                matricula = new Matricula();
                matricula.setMatricula_id(rs.getInt("matricula_id"));
                matricula.setFecha(rs.getDate("fecha").toLocalDate());
                matricula.setGrado(rs.getString("grado").charAt(0));
                matricula.setNivel(rs.getString("nivel").charAt(0));
                matricula.setTurno(rs.getString("turno").charAt(0));
                matricula.setAlumno_id(rs.getInt("alumno_id"));
            }
        } catch (Exception e) {
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        }
        finally{
            try {
                if(rs!=null)rs.close();
                if(cstm!=null)cstm.close();                
            } catch (Exception e) {
                Bitacora.registrar(e);
            }    
        }
        return matricula;
    }
    
    public Matricula buscarMatriculaPorAlumnoId(int id) throws Exception{
        Matricula matricula = null;
        ResultSet rs = null;
        try {
            con = UConnection.getConnection();
            String sql = "call sp_matricula_buscar_por_alumno_id (?)";
            cstm = con.prepareCall(sql);
            cstm.setInt(1, id);
            
            rs = cstm.executeQuery();
            
            while(rs.next()){
                matricula = new Matricula();
                matricula.setMatricula_id(rs.getInt("matricula_id"));
                matricula.setFecha(rs.getDate("fecha").toLocalDate());
                matricula.setGrado(rs.getString("grado").charAt(0));
                matricula.setNivel(rs.getString("nivel").charAt(0));
                matricula.setTurno(rs.getString("turno").charAt(0));
                matricula.setAlumno_id(rs.getInt("alumno_id"));
            }
        } catch (Exception e) {
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        }
        finally{
            try {
                if(rs!=null)rs.close();
                if(cstm!=null)cstm.close();                
            } catch (Exception e) {
                Bitacora.registrar(e);
            }    
        }
        return matricula;
    }
    
    public ArrayList<Matricula> ListarMatriculas() throws Exception{
        Matricula matricula = null;
        ResultSet rs = null;
        
        ArrayList<Matricula> matriculas = new ArrayList<>();
        try {
            con = UConnection.getConnection();
            String sql = "select * from matricula";
            cstm = con.prepareCall(sql);
            
            rs = cstm.executeQuery();
            
            while(rs.next()){
                matricula = new Matricula();
                matricula.setMatricula_id(rs.getInt("matricula_id"));
                matricula.setFecha(rs.getDate("fecha").toLocalDate());
                matricula.setGrado(rs.getString("grado").charAt(0));
                matricula.setNivel(rs.getString("nivel").charAt(0));
                matricula.setTurno(rs.getString("turno").charAt(0));
                matricula.setAlumno_id(rs.getInt("alumno_id"));
                
                matriculas.add(matricula);
            }
        } catch (Exception e) {
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        }
        finally{
            try {
                if(rs!=null)rs.close();
                if(cstm!=null)cstm.close();                
            } catch (Exception e) {
                Bitacora.registrar(e);
            }    
        }
        return matriculas;
    }
}
