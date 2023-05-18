/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Connection.UConnection;
import JavaBean.AsistenciaDocente;
import JavaBean.Notas;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author xbest
 */
public class AsistenciaDocenteDAO {
    public void insertar(AsistenciaDocente adocente) throws Exception{        
        
        Connection con=null;
        CallableStatement cstm = null;    
        
        try {
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_asistenciaDocente_insertar(?,?,?,?)";
            cstm=con.prepareCall(sql);
            //LocalTime horaActual = LocalTime.now();
            cstm.setTime(1, adocente.getHora_ingreso());
            cstm.setTime(2, adocente.getHora_salida());
            cstm.setDate(3,java.sql.Date.valueOf(adocente.getFecha()));
            cstm.setInt(4, adocente.getDocente_id());
            
            System.out.println("Ingresando datos");
            System.out.println(java.sql.Date.valueOf(adocente.getFecha()));
            System.out.println(adocente.getHora_ingreso());
            System.out.println("+++++++++++++++");
            
            cstm.execute();
        } catch (Exception e) {
            Bitacora.registrar(e);
            //throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
            throw e;
        }finally{
            try {
               if(cstm!=null)cstm.close();             
            } catch (Exception e) {
                Bitacora.registrar(e);
            }        
        }      
    }
    
    public void eliminar(int id) throws Exception{    
        
        Connection con=null;
        CallableStatement cstm = null;    
        
        try {
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_asistenciaDocente_eliminar(?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, id);
         
            cstm.executeUpdate(); //se puede usar .execute() para todas las operaciones
             
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
    
    public void actualizar(AsistenciaDocente adocente) throws Exception{        
        
        Connection con=null;
        CallableStatement cstm = null;    
        
        try {
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_asistenciaDocente_actualizar(?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, adocente.getAsistencia_id());
            cstm.setTime(2, adocente.getHora_ingreso());
            cstm.setTime(3, adocente.getHora_salida());
            cstm.setDate(4,java.sql.Date.valueOf(adocente.getFecha()));
            cstm.setInt(5,adocente.getDocente_id());
            
                      
        } catch (Exception e) {
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
    
    public AsistenciaDocente buscarPorDocenteId(int cadena) throws Exception{
     
        AsistenciaDocente adocente = new AsistenciaDocente();
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_asistenciaDocente_buscar_por_docente_id(?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, cadena);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
            
            
            adocente.setAsistencia_id(rs.getInt("asistencia_id"));
            adocente.setHora_ingreso(rs.getTime("hora_ingreso"));
            adocente.setHora_salida(rs.getTime("hora_salida"));
            adocente.setFecha(rs.getDate("fecha").toLocalDate());
            adocente.setDocente_id(rs.getInt("docente_id"));
            
        }catch (Exception e) {         
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        }finally{
            try {
                if(rs!=null)rs.close();
                if(cstm!=null)cstm.close();                
            } catch (Exception e) {
                Bitacora.registrar(e);
            }        
        }        
        return adocente;     
     }
    
    public ArrayList<AsistenciaDocente> asistenciaDocenteListar() throws Exception{
     
        ArrayList<AsistenciaDocente> adocentes =new ArrayList<>();
        Notas nota=null;
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_notas_listar()";
            cstm=con.prepareCall(sql);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
            
            while(rs.next()){
                AsistenciaDocente adocente = new AsistenciaDocente();
                adocente.setAsistencia_id(rs.getInt("asistencia_id"));
                adocente.setHora_ingreso(rs.getTime("hora_ingreso"));
                adocente.setHora_salida(rs.getTime("hora_salida"));
                adocente.setFecha(rs.getDate("fecha").toLocalDate());
                adocente.setDocente_id(rs.getInt("docente_id"));
                
                adocentes.add(adocente);
            }
            
        }catch (Exception e) {         
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        }finally{
            try {
                if(rs!=null)rs.close();
                if(cstm!=null)cstm.close();                
            } catch (Exception e) {
                Bitacora.registrar(e);
            }        
        }        
        return adocentes;     
     }
}
