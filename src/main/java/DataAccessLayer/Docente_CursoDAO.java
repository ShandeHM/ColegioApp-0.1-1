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
public class Docente_CursoDAO {
    
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
            
          
            
            
           int numeroFilasAfectadas=cstm.executeUpdate(); //se puede usar .execute() para todas las operaciones 
           if(numeroFilasAfectadas>0){
              //alumno.setAlumnoId(cstm.getInt(1));
           }else{
               throw new Exception("No se pudo registrar");
           }           
        } catch (Exception e) {
            if(e.getMessage().contains("idx_alumno_dni")) {
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
    
    public void actualizar(Alumno alumno) throws Exception{        
        
        Connection con=null;
        CallableStatement cstm = null;    
        
        try {
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_alumno_actualizar(?,?,?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, alumno.getAlumno_id());
            if(alumno.getDni().isEmpty())cstm.setNull(2,java.sql.Types.VARCHAR);
            else cstm.setString(2, alumno.getDni());            
            cstm.setString(3, alumno.getApellido_paterno());
            cstm.setString(4, alumno.getApellido_materno());
            cstm.setString(5, alumno.getNombres());
            cstm.setDate(6, Date.valueOf(alumno.getFecha_nacimiento()));
            if(alumno.getCorreo_electrico().isEmpty())cstm.setNull(7,java.sql.Types.VARCHAR);
            else cstm.setString(7, alumno.getCorreo_electrico());
            
            cstm.executeUpdate(); //se puede usar .execute() para todas las operaciones
             
        }catch (Exception e) {         
            if(e.getMessage().contains("idx_alumno_dni")) {
                throw new Exception("El DNI ingresado ya existe en la base de datos");
            }
            if(e.getMessage().contains("idx_correo_electronico")) {
                throw new Exception("El Correo electrónico ingresado ya existe en la base de datos");
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
            sql="call sp_alumno_eliminar(?)";
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
    
    public Alumno buscarPorDNI(String dni) throws Exception{     
        
        Alumno alumno=null;
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_alumno_buscar_por_dni(?)";
            cstm=con.prepareCall(sql);
            cstm.setString(1, dni);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
            
            while(rs.next()){
                alumno = new Alumno();
                alumno.setAlumno_id(rs.getInt("alumno_id"));
                alumno.setDni(rs.getString("dni"));
                alumno.setApellidosNombres(rs.getString("alumno"));             
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
        
        return alumno;     
     } 
    
    public Alumno buscarPorId(int id) throws Exception{        
       
        Alumno alumno=null;
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_alumno_buscar_por_id(?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, id);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones
            
            while(rs.next()){
                alumno = new Alumno();
                alumno.setAlumno_id(rs.getInt("alumno_id"));
                alumno.setDni(rs.getString("dni"));
                alumno.setApellido_paterno(rs.getString("apellido_paterno"));
                alumno.setApellido_materno(rs.getString("apellido_materno"));
                alumno.setNombres(rs.getString("nombres"));
                alumno.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                alumno.setCorreo_electrico(rs.getString("correo_electronico"));                             
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
        return alumno;     
     }
    
}
