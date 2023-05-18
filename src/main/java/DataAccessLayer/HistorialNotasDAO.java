/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Connection.UConnection;
import JavaBean.HistorialNotas;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author xbest
 */
public class HistorialNotasDAO {
    public void insertar(HistorialNotas hnotas) throws Exception{        
        
        Connection con=null;
        CallableStatement cstm = null;    
        
        try {
            int cont = 0;
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_historial_notas_insertar(?,?,?)";
            cstm=con.prepareCall(sql);
            
            
            if(hnotas.getAlumno_id()<=0){
                cont++;
            }
            
            if(hnotas.getCurso_id()<=0){
                cont++;
            }
          
            if(cont==0){
                cstm.setInt(1, hnotas.getAlumno_id());
                cstm.setInt(2, hnotas.getCurso_id());
                cstm.setDouble(3, hnotas.getPromedio());
                cstm.execute();
            }else{
               throw new Exception("No se pudo registrar el historial notas");
            }           
        } catch (Exception e) {
            System.out.println(e);
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
    
    public void eliminar(int id) throws Exception{    
        
        Connection con=null;
        CallableStatement cstm = null;    
        
        try {
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_historial_notas_eliminar(?)";
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
    
    public void actualizar(HistorialNotas hnotas) throws Exception{        
        
        Connection con=null;
        CallableStatement cstm = null;    
        
        try {
            int cont = 0;
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_historial_notas_actualizar(?,?,?,?)";
            cstm=con.prepareCall(sql);
            
            
            if(hnotas.getAlumno_id()<=0){
                cont++;
            }
            
            if(hnotas.getCurso_id()<=0){
                cont++;
            }
          
            if(cont==0){
                cstm.setInt(1, hnotas.getHistorial_id());
                cstm.setInt(2, hnotas.getAlumno_id());
                cstm.setInt(3, hnotas.getCurso_id());
                cstm.setDouble(4, hnotas.getPromedio());
                cstm.execute();
            }else{
               throw new Exception("No se pudo actualizar el historial notas");
            }           
        } catch (Exception e) {
            System.out.println(e);
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
    
    public HistorialNotas buscarPorAlumnoId(int cadena) throws Exception{
     
        HistorialNotas hnotas=new HistorialNotas();
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_historial_notas_buscar_por_alumno_id(?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, cadena);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
            
            
            hnotas.setHistorial_id(rs.getInt("historial_notas_id"));
            hnotas.setAlumno_id(rs.getInt("alumno_id"));
            hnotas.setCurso_id(rs.getInt("curso_id"));
            hnotas.setPromedio(rs.getDouble("promedio"));
            
            
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
        return hnotas;     
     }
    
    public HistorialNotas buscarPorCursoId(int cadena) throws Exception{
     
        HistorialNotas hnotas=new HistorialNotas();
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_historial_notas_buscar_por_curso_id(?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, cadena);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
            
            
            hnotas.setHistorial_id(rs.getInt("historial_notas_id"));
            hnotas.setAlumno_id(rs.getInt("alumno_id"));
            hnotas.setCurso_id(rs.getInt("curso_id"));
            hnotas.setPromedio(rs.getDouble("promedio"));
            
            
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
        return hnotas;     
     }
    
    public ArrayList<HistorialNotas> historialNotasListar() throws Exception{
     
        ArrayList<HistorialNotas>hnotas=new ArrayList<>();
        HistorialNotas hnota=null;
        
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
                hnota = new HistorialNotas();
                
                hnota.setHistorial_id(rs.getInt("historial_notas_id"));
                hnota.setAlumno_id(rs.getInt("alumno_id"));
                hnota.setCurso_id(rs.getInt("curso_id"));
                hnota.setPromedio(rs.getDouble("promedio"));
                
                
                hnotas.add(hnota);
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
        return hnotas;     
     }
}
