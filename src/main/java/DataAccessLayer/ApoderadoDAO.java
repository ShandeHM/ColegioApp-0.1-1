/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import JavaBean.Apoderado;
import Connection.UConnection;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author black
 */
//(Con la base de datos)

public class ApoderadoDAO {
    private Connection con;
    CallableStatement cstm = null; 
    
    public void insertarApoderado(Apoderado apoderado) throws Exception{
        try {
            con = UConnection.getConnection();
            String sql = "call sp_apoderado_insertar(?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            cstm.setString(1, apoderado.getDni());
            cstm.setString(2, apoderado.getApellido_paterno());
            cstm.setString(3, apoderado.getApellido_materno());
            cstm.setString(4, apoderado.getNombres());
            
            if( apoderado.getContacto().isEmpty() ) cstm.setNull(5,java.sql.Types.VARCHAR);
            else cstm.setString(5, apoderado.getContacto());
            
            cstm.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(e.getMessage().contains("idx_apoderado_dni")) {
                throw new Exception("El DNI ingresado ya existe en la base de datos");
            }
            if(e.getMessage().contains("idx_apoderado_electronico")) {
                throw new Exception("El Correo electrónico ingresado ya existe en la base de datos");
            }
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
    
    public void actualizarApoderado(Apoderado apoderado) throws Exception{
        try {
            con = UConnection.getConnection();
            String sql = "call sp_apoderado_actualizar(?,?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, apoderado.getApoderado_id());
            cstm.setString(2, apoderado.getDni());
            cstm.setString(3, apoderado.getApellido_paterno());
            cstm.setString(4, apoderado.getApellido_materno());
            cstm.setString(5, apoderado.getNombres());
            
            if( apoderado.getContacto().isEmpty() ) cstm.setNull(6,java.sql.Types.VARCHAR);
            else cstm.setString(6, apoderado.getContacto());
            
            cstm.executeUpdate();
        } catch (Exception e) {
            if(e.getMessage().contains("idx_apoderado_dni")) {
                throw new Exception("El DNI ingresado ya existe en la base de datos");
            }
            if(e.getMessage().contains("idx_apoderado_electronico")) {
                throw new Exception("El Correo electrónico ingresado ya existe en la base de datos");
            }
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
    
    public void eliminarApoderado(int id) throws Exception{     
        try {
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_apoderado_eliminar(?)";
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

    public Apoderado buscarApoderadoPorId(int id) throws Exception{
        Apoderado apoderado = null;
        ResultSet rs = null;
        try {
            con = UConnection.getConnection();
            String sql = "call sp_apoderado_buscar_por_id (?)";
            cstm = con.prepareCall(sql);
            cstm.setInt(1, id);
            
            rs = cstm.executeQuery();
            
            while(rs.next()){
                apoderado = new Apoderado();
                apoderado.setApoderado_id(rs.getInt("apoderado_id"));
                apoderado.setDni(rs.getString("dni"));  
                apoderado.setNombres(rs.getString("nombres"));
                apoderado.setApellido_materno(rs.getString("apellido_materno"));
                apoderado.setApellido_paterno(rs.getString("apellido_paterno"));
                if (!rs.getString("contacto").isEmpty()) 
                    apoderado.setContacto(rs.getString("contacto"));        
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
        return apoderado;
    }
    
    public Apoderado buscarApoderadoPorDNI(String dni) throws Exception{
        Apoderado apoderado = null;
        ResultSet rs = null;
        try {
            con = UConnection.getConnection();
            String sql = "call sp_apoderado_buscar_por_dni (?)";
            cstm = con.prepareCall(sql);
            cstm.setString(1, dni);
            
            rs = cstm.executeQuery();
            
            while(rs.next()){
                apoderado = new Apoderado();
                apoderado.setApoderado_id(rs.getInt("apoderado_id"));
                apoderado.setDni(rs.getString("dni"));  
                
                String[] nombres = rs.getString("apoderado").replace(",", "").split(" ");
                apoderado.setApellido_paterno(nombres[0]);
                apoderado.setApellido_materno(nombres[1]);
                
                if (nombres.length < 4) apoderado.setNombres(nombres[2]);
                else apoderado.setNombres(nombres[2]+" "+nombres[3]);        
                
                if (!rs.getString("contacto").isEmpty()) 
                    apoderado.setContacto(rs.getString("contacto")); 
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
        return apoderado;
    }
    
    public ArrayList<Apoderado> buscarApoderadoPorNombre(String nombre) throws Exception{
        Apoderado apoderado = null;
        ResultSet rs = null;
        
        ArrayList<Apoderado> apoderados = new ArrayList<>();
        try {
            con = UConnection.getConnection();
            String sql = "call sp_apoderado_buscar_por_apoderado (?)";
            cstm = con.prepareCall(sql);
            cstm.setString(1, nombre);
            
            rs = cstm.executeQuery();
            
            while(rs.next()){
                apoderado = new Apoderado();
                apoderado.setApoderado_id(rs.getInt("apoderado_id"));
                apoderado.setDni(rs.getString("dni"));  
                
                String[] nombres = rs.getString("apellidos_nombres").replace(",", "").split(" ");
                apoderado.setApellido_paterno(nombres[0]);
                apoderado.setApellido_materno(nombres[1]);
                
                if (nombres.length < 4) apoderado.setNombres(nombres[2]);
                else apoderado.setNombres(nombres[2]+" "+nombres[3]);  
                
                if (!rs.getString("contacto").isEmpty()) 
                    apoderado.setContacto(rs.getString("contacto")); 
                
                apoderados.add(apoderado);
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
        return apoderados;
    }
}