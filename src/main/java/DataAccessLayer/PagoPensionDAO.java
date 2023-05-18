/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Connection.UConnection;
import JavaBean.pagoMatricula;
import JavaBean.pagoPensiones;
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
public class PagoPensionDAO {
    private Connection con;
    CallableStatement cstm = null; 
    
    public void insertarPagoPension(pagoPensiones pagoPe) throws Exception{
        try {
            con = UConnection.getConnection();
            String sql = "call sp_pagoPensiones_insertar(?,?,?,?)";
            cstm=con.prepareCall(sql);
            cstm.setDate(1, Date.valueOf(pagoPe.getFecha()) );
            cstm.setDouble(2, pagoPe.getMonto());
            
            if(pagoPe.getObservacion().isEmpty()) cstm.setNull(3,java.sql.Types.VARCHAR);
            else cstm.setString(3, pagoPe.getObservacion());
            
            cstm.setInt(4, pagoPe.getAlumno_id());
            
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
    
    public void actualizarPagoPension(pagoPensiones pagoPe) throws Exception{
        try {
            con = UConnection.getConnection();
            String sql = "call sp_pagoPensiones_actualizar(?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, pagoPe.getPago_pensiones_id());
            
            cstm.setDate(2, Date.valueOf(pagoPe.getFecha()) );
            cstm.setDouble(3, pagoPe.getMonto());
            
            if(pagoPe.getObservacion().isEmpty()) cstm.setNull(4,java.sql.Types.VARCHAR);
            else cstm.setString(4, pagoPe.getObservacion());
            
            cstm.setInt(5, pagoPe.getAlumno_id());
            
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
    
    public void eliminarPagoPensionPorId(int id) throws Exception{     
        try {
            con=UConnection.getConnection();
            String sql= "call sp_pagoPensiones_eliminar (?)";
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

    public pagoPensiones buscarPagoPensionPorAlumnoId(int id) throws Exception{
        pagoPensiones pagoPen = null;
        ResultSet rs = null;
        try {
            con = UConnection.getConnection();
            String sql = "call sp_pagoPensiones_por_alumno_id (?)";
            cstm = con.prepareCall(sql);
            cstm.setInt(1, id);
            
            rs = cstm.executeQuery();
            
            while(rs.next()){
                pagoPen = new pagoPensiones();
                pagoPen.setPago_pensiones_id(rs.getInt("pago_pensiones_id")); 
                pagoPen.setFecha(rs.getDate("fecha").toLocalDate());
                pagoPen.setMonto(rs.getDouble("monto"));
                
                if (!rs.getString("observacion").isEmpty()) 
                    pagoPen.setObservacion(rs.getString("observacion"));     
                
                pagoPen.setAlumno_id(rs.getInt("alumno_id"));
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
        return pagoPen;
    }
    
    public ArrayList<pagoPensiones> listarPagoPension() throws Exception{
        pagoPensiones pagoPen = null;
        ResultSet rs = null;
        
        ArrayList<pagoPensiones> pagoPes = new ArrayList<>();
        try {
            con = UConnection.getConnection();
            String sql = "call sp_pagoPensiones_listar";
            cstm = con.prepareCall(sql);
            
            rs = cstm.executeQuery();
            
            while(rs.next()){
                pagoPen = new pagoPensiones();
                pagoPen.setPago_pensiones_id(rs.getInt("pago_pensiones_id")); 
                pagoPen.setFecha(rs.getDate("fecha").toLocalDate());
                pagoPen.setMonto(rs.getDouble("monto"));
                
                if (!rs.getString("observacion").isEmpty()) 
                    pagoPen.setObservacion(rs.getString("observacion"));     
                
                pagoPen.setAlumno_id(rs.getInt("alumno_id"));
                
                pagoPes.add(pagoPen);
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
        return pagoPes;
    }
}
