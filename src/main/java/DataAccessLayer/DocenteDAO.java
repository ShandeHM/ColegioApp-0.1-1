/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import Connection.UConnection;
import JavaBean.Docente;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DocenteDAO {
    
    public ArrayList<Docente> buscarPorDocente(String cadena) throws Exception{
     
        ArrayList<Docente>docentes=new ArrayList<>();
        Docente docente=null;
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_docente_buscar_por_docente(?)";
            cstm=con.prepareCall(sql);
            cstm.setString(1, cadena);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
            
            while(rs.next()){
                docente = new Docente();
                docente.setDocente_id(rs.getInt("docente_id"));
                docente.setDni(rs.getString("dni"));
                //docente.setApellidosNombres(rs.getString("apellidos_nombres"));
                String[] nombres = rs.getString("apellidos_nombres").replace(",", "").split(" ");
                System.out.println(rs.getString("apellidos_nombres"));
                docente.setApellido_paterno(nombres[0]);
                docente.setApellido_materno(nombres[1]);
                
                if (nombres.length < 4) docente.setNombres(nombres[2]);
                else docente.setNombres(nombres[2]+" "+nombres[3]);     
                
                
                if(!rs.getString("contacto").isEmpty()) 
                    docente.setContacto(rs.getString("contacto"));
                
                
                docentes.add(docente);
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
        return docentes;     
     }
    
    public Docente buscarPorId(int id) throws Exception{        

        Docente docente=null;

        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;

        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_docente_buscar_por_id(?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, id);

            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones

            while(rs.next()){
                docente = new Docente();
                docente.setDocente_id(rs.getInt("docente_id"));
                docente.setDni(rs.getString("dni"));
                docente.setApellido_paterno(rs.getString("apellido_paterno"));
                docente.setApellido_materno(rs.getString("apellido_materno"));
                docente.setNombres(rs.getString("nombres"));
                docente.setApellidosNombres(rs.getString("apellidos_nombres")); 
            }

        } catch (Exception e) {         
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
        return docente;     
    }   
    
    public void insertar(Docente docente) throws Exception{

        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;

        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_docente_insertar(?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            cstm.setString(1, docente.getDni());
            cstm.setString(2, docente.getApellido_paterno());
            cstm.setString(3, docente.getApellido_materno());
            cstm.setString(4, docente.getNombres());
            cstm.setString(5, docente.getContacto());

            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones

            

        } catch (Exception e) {         
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
    }   
    
    public void actualizar(Docente docente) throws Exception{

        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;

        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_docente_actualizar(?,?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, docente.getDocente_id());
            cstm.setString(2, docente.getDni());
            cstm.setString(3, docente.getApellido_paterno());
            cstm.setString(4, docente.getApellido_materno());
            cstm.setString(5, docente.getNombres());
            cstm.setString(6, docente.getContacto());

            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones

            

        } catch (Exception e) {         
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
    }   
    
    public void eliminar(int idx) throws Exception{

        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;

        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_docente_eliminar(?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, idx);

            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones

            

        } catch (Exception e) {         
            System.out.println(e);
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
    }   
    
    
    public ArrayList<Docente> listar() throws Exception{        

        Docente docente=null;
        ArrayList<Docente> docentes = new ArrayList();
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;

        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_docente_listar()";
            cstm=con.prepareCall(sql);

            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones

            while(rs.next()){
                docente = new Docente();
                docente.setDocente_id(rs.getInt("docente_id"));
                docente.setDni(rs.getString("dni"));
                docente.setApellido_paterno(rs.getString("apellido_paterno"));
                docente.setApellido_materno(rs.getString("apellido_materno"));
                docente.setNombres(rs.getString("nombres"));
                docente.setContacto(rs.getString("contacto")); 
                docentes.add(docente);
            }

        } catch (Exception e) {         
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
        return docentes;     
    } 
}