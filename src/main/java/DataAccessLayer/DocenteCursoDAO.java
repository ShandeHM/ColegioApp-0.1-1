/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import Connection.UConnection;
import JavaBean.Alumno;
import JavaBean.Area;
import JavaBean.Curso;
import JavaBean.Docente;
import JavaBean.DocenteCurso;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DocenteCursoDAO {
    
    public ArrayList<DocenteCurso> buscarCursoAsignadoPorDNIDocente(String dni) throws Exception{
     
        ArrayList<DocenteCurso>docenteCursos=new ArrayList<>();
        DocenteCurso docenteCurso=null;
        Curso curso=null;
        Area area=null;
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_docente_curso_buscar_por_dni_docente(?)";
            cstm=con.prepareCall(sql);
            cstm.setString(1, dni);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
            
            while(rs.next()){
                docenteCurso = new DocenteCurso();
                curso= new Curso();
                area = new Area();
                
                curso.setCurso_id(rs.getInt("curso_id"));
                
                curso.setNombre(rs.getString("curso"));
                curso.setGrado(rs.getString("grado").charAt(0));
                curso.setNivel(rs.getString("nivel").charAt(0));
                
                area.setArea_nombre(rs.getString("area"));
                docenteCurso.setTurno(rs.getString("turno").charAt(0));
                
                docenteCurso.setCurso_int(curso.getArea_id());
                curso.setArea_id(area.getArea_id());
                
                
                docenteCursos.add(docenteCurso);
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
        return docenteCursos;     
     }
    
    public void insertar(DocenteCurso docenteCurso) throws Exception{        
        
        Connection con=null;
        CallableStatement cstm = null;    
        
        try {
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_docente_curso_insertar(?,?,?)";
            cstm=con.prepareCall(sql);
                    
            cstm.setInt(1, docenteCurso.getDocente_id());
            cstm.setInt(2, docenteCurso.getCurso_int());
            cstm.setString(3, String.valueOf(docenteCurso.getTurno()));
            
          
            
            
           cstm.execute(); //se puede usar .execute() para todas las operaciones 
                      
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
    
    public void actualizar(DocenteCurso docenteCurso) throws Exception{        
        
        Connection con=null;
        CallableStatement cstm = null;    
        
        try {
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_docente_curso_modificar(?,?,?)";
            cstm=con.prepareCall(sql);
            
            cstm.setInt(1, docenteCurso.getDocente_id());
            cstm.setInt(2, docenteCurso.getCurso_int());
            cstm.setString(3, String.valueOf(docenteCurso.getTurno()));
            
            
           int numeroFilasAfectadas=cstm.executeUpdate(); //se puede usar .execute() para todas las operaciones 
           if(numeroFilasAfectadas>0){
              //alumno.setAlumnoId(cstm.getInt(1));
           }else{
               throw new Exception("No se pudo registrar");
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
            sql="call sp_docente_curso_eliminar(?)";
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
    
    
    public ArrayList<DocenteCurso> listar() throws Exception{
     
        ArrayList<DocenteCurso>docenteCursos=new ArrayList<>();
        DocenteCurso docenteCurso=null;
        Curso curso=null;
        Area area = null;
        
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_docente_curso_listar(?)";
            cstm=con.prepareCall(sql);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
            
            while(rs.next()){
                docenteCurso = new DocenteCurso();
                
                docenteCurso.setDocente_id(rs.getInt("docente_id"));
                docenteCurso.setCurso_int(rs.getInt("curso_id"));                
                docenteCurso.setTurno(rs.getString("turno").charAt(0));
       
                
                docenteCursos.add(docenteCurso);
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
        return docenteCursos;     
     }
    
    
    public ArrayList<DocenteCurso> buscarCursosPorDocenteId(int id) throws Exception{
     
        ArrayList<DocenteCurso>docenteCursos=new ArrayList<>();
        DocenteCurso docenteCurso=null;
        Curso curso=null;
        Area area = null;
        
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_docente_curso_buscar_cursos_por_docente_id(?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, id);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
            
            while(rs.next()){
                docenteCurso = new DocenteCurso();
                
                docenteCurso.setDocente_id(id);
                docenteCurso.setCurso_int(rs.getInt("curso_id"));                
                docenteCurso.setTurno(rs.getString("turno").charAt(0));
                
                curso = new Curso();
                curso.setNombre(rs.getString("curso"));
                curso.setGrado(rs.getString("grado").charAt(0));
                curso.setNivel(rs.getString("nivel").charAt(0));
                docenteCurso.setCurso(curso);
                
                area = new Area();
                area.setArea_nombre(rs.getString("area"));
                curso.setArea(area);
                
       
                
                docenteCursos.add(docenteCurso);
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
        return docenteCursos;     
     }
    
}
