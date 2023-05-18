/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import Connection.UConnection;
import JavaBean.Usuario;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class UsuarioDAO {
    
    public void loguin(Usuario usuario) throws Exception{
         
         
        Map<String,String>errores= new HashMap<>();
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_usuario_loguin(?)";
            cstm=con.prepareCall(sql);
            cstm.setString(1, usuario.getDni());          
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
            
            String pass="";
            if(rs.next()){
                usuario.setUsuario_id(rs.getInt("usuario_id"));                
                usuario.setApellidosNombres(rs.getString("apellidos_nombres"));
                usuario.setRol(rs.getString("rol"));                
                pass=rs.getString("clave");
            }else{   
                
                errores.put("dni",  "usuario incorrecto");
               
            }
            
           boolean resp= BCrypt.verifyer().verify(usuario.getClave().toCharArray(), pass).verified;         
          
           
           usuario.setClave("*");          
           
           
           if(!resp){            
             errores.put("clave", "clave incorrecta");
           }           
            
        }catch (Exception e) {
//            if(e.getMessage().contains("E-001")) {
//                throw new Exception("Usuario no encontrado");
//            }
//            if(e.getMessage().contains("E-002")) {
//                throw new Exception("Clave incorrecta");
//            }            
            //Bitacora.registrar(e);
            throw  e;
        }finally{
            try {
                if(rs!=null)rs.close();
                if(cstm!=null)cstm.close();                
            } catch (Exception e) {
                Bitacora.registrar(e);
            }        
        }
     }   
    
    public void insertar(Usuario usuario) throws Exception{        
        
        Connection con=null;
        CallableStatement cstm = null;    
        
        try {
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_usuario_insertar(?,?,?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            cstm.registerOutParameter(1, java.sql.Types.INTEGER);           
            cstm.setString(2, usuario.getDni());         
            cstm.setString(3, usuario.getApellido_paterno());
            cstm.setString(4, usuario.getApellido_materno());
            cstm.setString(5, usuario.getNombres());
            
                 
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, usuario.getClave().toCharArray());
            
            System.out.println(bcryptHashString.length());
            
            cstm.setString(6, bcryptHashString); 
            cstm.setString(7, usuario.getRol());
            
            
           int numeroFilasAfectadas=cstm.executeUpdate(); //se puede usar .execute() para todas las operaciones 
           if(numeroFilasAfectadas>0){
              usuario.setUsuario_id(cstm.getInt(1));
           }else{
               throw new Exception("No se pudo registrar al usuario");
           }           
        } catch (Exception e) {
            if(e.getMessage().contains("idx_usuario_dni")) {
                throw new Exception("El DNI ingresado ya existe en la base de datos");
            }         
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
    //-------------------------------------------------------------------------------------
    public void actualizar(Usuario usuario) throws Exception{        
        
        Connection con=null;
        CallableStatement cstm = null;    
        
        try {
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_usuario_actualizar(?,?,?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, usuario.getUsuario_id());           
            cstm.setString(2, usuario.getDni());            
            cstm.setString(3, usuario.getApellido_paterno());
            cstm.setString(4, usuario.getApellido_materno());
            cstm.setString(5, usuario.getNombres());            
            System.out.println("++++++++++");
            System.out.println(usuario.getApellido_paterno());
            System.out.println(String.valueOf(usuario.getUsuario_id()));
            String claveEncriptada="";
            cstm.setString(6, claveEncriptada);
            
            cstm.setString(7, usuario.getRol());
            
            cstm.execute(); //se puede usar .execute() para todas las operaciones
             
        }catch (Exception e) {         
            if(e.getMessage().contains("idx_usuario_dni")) {
                throw new Exception("El DNI ingresado ya existe en la base de datos");
            }          
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
            sql="call sp_usuario_eliminar(?)";
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
    
   
     
     public ArrayList<Usuario> buscarPorUsuario(String cadena) throws Exception{
     
        ArrayList<Usuario>usuarios=new ArrayList<>();
        Usuario usuario=null;
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_usuario_buscar_por_usuario(?)";
            cstm=con.prepareCall(sql);
            cstm.setString(1, cadena);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
            
            while(rs.next()){
                usuario = new Usuario();
                usuario.setUsuario_id(rs.getInt("usuario_id"));
                usuario.setDni(rs.getString("dni"));
                usuario.setApellidosNombres(rs.getString("apellidos_nombres"));
                
                usuarios.add(usuario);
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
        return usuarios;     
     }
     
     public Usuario buscarPorDNI(String dni) throws Exception{     
        
        Usuario usuario=null;
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_usuario_buscar_por_dni(?)";
            cstm=con.prepareCall(sql);
            cstm.setString(1, dni);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
            
            while(rs.next()){
                usuario = new Usuario();
                usuario.setUsuario_id(rs.getInt("usuario_id"));
                usuario.setDni(rs.getString("dni"));
                usuario.setApellidosNombres(rs.getString("apellidos_nombres"));             
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
        
        return usuario;     
     }     
         
      public Usuario buscarPorId(int id) throws Exception{        
       
        Usuario usuario=null;
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_usuario_buscar_por_id(?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, id);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones
            
            while(rs.next()){
                usuario = new Usuario();
                usuario.setUsuario_id(rs.getInt("usuario_id"));
                usuario.setDni(rs.getString("dni"));
                usuario.setApellido_paterno(rs.getString("apellido_paterno"));
                usuario.setApellido_materno(rs.getString("apellido_materno"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setRol(rs.getString("rol"));
                                       
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
        return usuario;     
     }

      public void cambiarClave(Usuario usuario) throws Exception{ 
          
        loguin(usuario);
        
        Connection con=null;
        CallableStatement cstm = null;    
        
        try {
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_usuario_cambiar_clave(?,?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, usuario.getUsuario_id());   
            
          //  String bcryptHashString = BCrypt.withDefaults().hashToString(12, usuario.getClaveNueva().toCharArray());
          //  usuario.setClave(bcryptHashString);
            
            cstm.setString(2, usuario.getClave());            
      
            
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
    
}
