/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Connection.UConnection;
import JavaBean.pagoPensiones;
import JavaBean.pagoVarios;
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
public class PagoVariosDAO {
    private Connection con;
    CallableStatement cstm = null; 
    
    public void insertarPagoVarios(pagoVarios pagoVar) throws Exception{
        try {
            con = UConnection.getConnection();
            String sql = "call sp_pagoVarios_insertar(?,?,?,?)";
            cstm=con.prepareCall(sql);
            cstm.setDate(1, Date.valueOf(pagoVar.getFecha()) );
            cstm.setDouble(2, pagoVar.getMonto());
            
            if(pagoVar.getObservacion().isEmpty()) cstm.setNull(3,java.sql.Types.VARCHAR);
            else cstm.setString(3, pagoVar.getObservacion());
            
            cstm.setInt(4, pagoVar.getAlumno_id());
            
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
    
    public void actualizarPagoVarios(pagoVarios pagoVar) throws Exception{
        try {
            con = UConnection.getConnection();
            String sql = "call sp_pagoVarios_actualizar(?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, pagoVar.getPago_varios_id());
            
            cstm.setDate(2, Date.valueOf(pagoVar.getFecha()) );
            cstm.setDouble(3, pagoVar.getMonto());
            
            if(pagoVar.getObservacion().isEmpty()) cstm.setNull(4,java.sql.Types.VARCHAR);
            else cstm.setString(4, pagoVar.getObservacion());
            
            cstm.setInt(5, pagoVar.getAlumno_id());
            
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
    
    public void eliminarPagoVariosPorId(int id) throws Exception{     
        try {
            con=UConnection.getConnection();
            String sql= "call sp_pagoVarios_eliminar (?)";
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

    public pagoVarios buscarPagoVariosPorAlumnoId(int id) throws Exception{
        pagoVarios pagoVar = null;
        ResultSet rs = null;
        try {
            con = UConnection.getConnection();
            String sql = "call sp_pagoVarios_por_alumno_id (?)";
            cstm = con.prepareCall(sql);
            cstm.setInt(1, id);
            
            rs = cstm.executeQuery();
            
            while(rs.next()){
                pagoVar = new pagoVarios();
                pagoVar.setPago_varios_id(rs.getInt("pago_varios_id")); 
                pagoVar.setFecha(rs.getDate("fecha").toLocalDate());
                pagoVar.setMonto(rs.getDouble("monto"));
                
                if (!rs.getString("observacion").isEmpty()) 
                    pagoVar.setObservacion(rs.getString("observacion"));     
                
                pagoVar.setAlumno_id(rs.getInt("alumno_id"));
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
        return pagoVar;
    }
    
    public ArrayList<pagoVarios> listarPagoVarios() throws Exception{
        pagoVarios pagoVar = null;
        ResultSet rs = null;
        ArrayList<pagoVarios> pagoVars = new ArrayList<>();
        try {
            con = UConnection.getConnection();
            String sql = "call sp_pagoVarios_listar();";
            
            cstm = con.prepareCall(sql);
            
            rs = cstm.executeQuery();
            
            while(rs.next()){
                pagoVar = new pagoVarios();
                pagoVar.setPago_varios_id(rs.getInt("pago_varios_id"));
                pagoVar.setFecha(rs.getDate("fecha").toLocalDate());
                pagoVar.setMonto(rs.getDouble("monto"));
                
                if (!rs.getString("descripcion").isEmpty()) 
                    pagoVar.setObservacion(rs.getString("descripcion"));      
                
                pagoVar.setAlumno_id(rs.getInt("alumno_id"));
                
                pagoVars.add(pagoVar);
              
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
        return pagoVars;
    }
}
