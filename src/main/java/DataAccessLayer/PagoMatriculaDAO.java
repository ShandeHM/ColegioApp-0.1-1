/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Connection.UConnection;
import JavaBean.Apoderado;
import JavaBean.pagoMatricula;
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
public class PagoMatriculaDAO {
    private Connection con;
    CallableStatement cstm = null; 
    
    public void insertarPagoMatricula(pagoMatricula pagoMat) throws Exception{
        try {
            con = UConnection.getConnection();
            String sql = "call sp_pagoMatricula_insertar(?,?,?,?)";
            cstm=con.prepareCall(sql);
            cstm.setDate(1, Date.valueOf(pagoMat.getFecha()) );
            cstm.setDouble(2, pagoMat.getMonto());
            
            if(pagoMat.getObservacion().isEmpty()) cstm.setNull(3,java.sql.Types.VARCHAR);
            else cstm.setString(3, pagoMat.getObservacion());
            
            cstm.setInt(4, pagoMat.getAlumno_id());
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
    
    public void actualizarPagoMatricula(pagoMatricula pagoMat) throws Exception{
        try {
            con = UConnection.getConnection();
                String sql = "call sp_pagoMatricula_actualizar(?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, pagoMat.getPago_matricula_id());
            
            cstm.setDate(2, Date.valueOf(pagoMat.getFecha()) );
            cstm.setDouble(3, pagoMat.getMonto());
            
            if(pagoMat.getObservacion().isEmpty()) cstm.setNull(4,java.sql.Types.VARCHAR);
            else cstm.setString(4, pagoMat.getObservacion());
            
            cstm.setInt(5, pagoMat.getAlumno_id());
            
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
    
    public void eliminarPagoMatriculaPorId(int id) throws Exception{     
        try {
            con=UConnection.getConnection();
            String sql= "call sp_pagoMatricula_eliminar (?)";
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

    public pagoMatricula buscarPagoMatriculaPorAlumnoId(int id) throws Exception{
        pagoMatricula pagoMat = null;
        ResultSet rs = null;
        try {
            con = UConnection.getConnection();
            String sql = "call sp_pagoMatricula_por_alumno_id (?)";
            cstm = con.prepareCall(sql);
            cstm.setInt(1, id);
            
            rs = cstm.executeQuery();
            
            while(rs.next()){
                pagoMat = new pagoMatricula();
                pagoMat.setPago_matricula_id(rs.getInt("pago_matricula_id")); 
                pagoMat.setFecha(rs.getDate("fecha").toLocalDate());
                pagoMat.setMonto(rs.getDouble("monto"));
                
                if (!rs.getString("observacion").isEmpty()) 
                    pagoMat.setObservacion(rs.getString("observacion"));     
                
                pagoMat.setAlumno_id(rs.getInt("alumno_id"));
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
        return pagoMat;
    }
    
    
    public ArrayList<pagoMatricula> listarPagoMatricula() throws Exception{
        pagoMatricula pagoMat = null;
        ResultSet rs = null;
        
        ArrayList<pagoMatricula> pagoMats = new ArrayList<>();
        try {
            
            con = UConnection.getConnection();
            String sql = "call sp_pagoMatricula_listar();";
            
            cstm = con.prepareCall(sql);

            rs = cstm.executeQuery();
            
            while(rs.next()){
                pagoMat = new pagoMatricula();
                pagoMat.setPago_matricula_id(rs.getInt("pago_matricula_id")); 
                pagoMat.setFecha(rs.getDate("fecha").toLocalDate());
                pagoMat.setMonto(rs.getDouble("monto"));
                
                if (!rs.getString("observacion").isEmpty()) 
                    pagoMat.setObservacion(rs.getString("observacion"));     
                
                pagoMat.setAlumno_id(rs.getInt("alumno_id"));
                
                
                pagoMats.add(pagoMat);
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
        return pagoMats;
    }
}
