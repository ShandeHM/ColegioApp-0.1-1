/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import Connection.UConnection;
import JavaBean.Area;
import JavaBean.Curso;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class AreaDAO {

    public ArrayList<Area> listar() throws Exception {

        ArrayList<Area> areas = new ArrayList<>();
        Area area = null;

        Connection con = null;
        CallableStatement cstm = null;
        ResultSet rs = null;

        try {
            con = UConnection.getConnection();
            String sql = "";
            sql = "call sp_area_listar()";
            cstm = con.prepareCall(sql);

            rs = cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         

            while (rs.next()) {
                area = new Area();
                area.setArea_id(rs.getInt("area_id"));
                area.setArea_nombre(rs.getString("nombre"));

                areas.add(area);
            }

        } catch (Exception e) {
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (Exception e) {
                Bitacora.registrar(e);
            }
        }
        return areas;
    }

    public void insertar(Area area) throws Exception {

        Connection con = null;
        CallableStatement cstm = null;

        try {
            con = UConnection.getConnection();
            String sql = "";
            sql = "call sp_area_insertar(?,?)";
            cstm = con.prepareCall(sql);
            cstm.registerOutParameter(1, java.sql.Types.INTEGER);

            cstm.setString(2, area.getArea_nombre());

            int numeroFilasAfectadas = cstm.executeUpdate(); //se puede usar .execute() para todas las operaciones 
            if (numeroFilasAfectadas > 0) {
                area.setArea_id(cstm.getInt(1));
            } else {
                throw new Exception("No se pudo registrar el area");
            }
        } catch (Exception e) {
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        } finally {
            try {
                if (cstm != null) {
                    cstm.close();
                }
            } catch (Exception e) {
                Bitacora.registrar(e);
            }
        }
    }
    
    public void insertar2(Curso curso) throws Exception {

        Connection con = null;
        CallableStatement cstm = null;

        try {
            con = UConnection.getConnection();
            String sql = "";
            sql = "call sp_curso_insertar(?,?,?,?,?)";
            cstm = con.prepareCall(sql);
            cstm.registerOutParameter(1, java.sql.Types.INTEGER);

            cstm.setString(2, curso.getNombre());
            cstm.setString(3, String.valueOf(curso.getGrado()));
            cstm.setString(4, String.valueOf(curso.getNivel()));
            cstm.setInt(5, curso.getArea_id());

            int numeroFilasAfectadas = cstm.executeUpdate(); //se puede usar .execute() para todas las operaciones 
            if (numeroFilasAfectadas > 0) {
                curso.setCurso_id(cstm.getInt(1));
            } else {
                throw new Exception("No se pudo registrar el curso");
            }
        } catch (Exception e) {
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        } finally {
            try {
                if (cstm != null) {
                    cstm.close();
                }
            } catch (Exception e) {
                Bitacora.registrar(e);
            }
        }
    }

    public void actualizar(Area area) throws Exception {

        Connection con = null;
        CallableStatement cstm = null;

        try {
            con = UConnection.getConnection();
            String sql = "";
            sql = "call sp_area_actualizar(?,?)";
            cstm = con.prepareCall(sql);
            
            
            cstm.setInt(1, area.getArea_id());
            cstm.setString(2, area.getArea_nombre());

            cstm.execute(); //se puede usar .execute() para todas las operaciones

        } catch (Exception e) {
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        } finally {
            try {
                if (cstm != null) {
                    cstm.close();
                }
            } catch (Exception e) {
                Bitacora.registrar(e);
            }
        }
    }
    
    public void actualizar2(Curso curso) throws Exception {

        Connection con = null;
        CallableStatement cstm = null;

        try {
            con = UConnection.getConnection();
            String sql = "";
            sql = "call sp_curso_actualizar(?,?,?,?,?)";
            cstm = con.prepareCall(sql);
            cstm.registerOutParameter(1, java.sql.Types.INTEGER);

            cstm.setString(2, curso.getNombre());
            cstm.setString(3, String.valueOf(curso.getGrado()));
            cstm.setString(4, String.valueOf(curso.getNivel()));
            cstm.setInt(5, curso.getArea_id());

            cstm.executeUpdate(); //se puede usar .execute() para todas las operaciones

        } catch (Exception e) {
            Bitacora.registrar(e);
            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
        } finally {
            try {
                if (cstm != null) {
                    cstm.close();
                }
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
            sql="call sp_area_eliminar(?)";
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
     
//    public ArrayList<Curso> buscarPorCurso(String cadena) throws Exception{
//     
//        ArrayList<Curso>cursos=new ArrayList<>();
//        Curso curso=null;
//        
//        Connection con=null;
//        CallableStatement cstm = null;  
//        ResultSet rs=null;
//        
//        try {            
//            con=UConnection.getConnection();
//            String sql="";            
//            sql="call sp_curso_buscar_por_curso(?)";
//            cstm=con.prepareCall(sql);
//            cstm.setString(1, cadena);
//         
//            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
//            
//            while(rs.next()){
//                curso = new Curso();
//                curso.setCurso_id(rs.getInt("curso_id"));
//                curso.setNombre(rs.getString("nombre"));
//                curso.setGrado(rs.getString("grado").charAt(0));
//                curso.setNivel(rs.getString("nivel").charAt(0));
//                curso.setArea_id(rs.getInt("area_id"));
//                
//                cursos.add(curso);
//            }
//            
//        }catch (Exception e) {         
//            Bitacora.registrar(e);
//            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
//        }finally{
//            try {
//                if(rs!=null)rs.close();
//                if(cstm!=null)cstm.close();                
//            } catch (Exception e) {
//                Bitacora.registrar(e);
//            }        
//        }        
//        return cursos;     
//     }
  
//    public Curso buscarPorId(int id) throws Exception{        
//       
//        Curso curso=null;
//        
//        Connection con=null;
//        CallableStatement cstm = null;  
//        ResultSet rs=null;
//       
//        try {            
//            con=UConnection.getConnection();
//            String sql="";            
//            sql="call sp_curso_buscar_por_id(?)";
//            cstm=con.prepareCall(sql);
//            cstm.setInt(1, id);
//         
//            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones
//            
//            while(rs.next()){
//                curso = new Curso();
//                curso.setCurso_id(rs.getInt("curso_id"));
//                curso.setNombre(rs.getString("nombre"));
//                curso.setGrado(rs.getString("grado").charAt(0));
//                curso.setNivel(rs.getString("nivel").charAt(0));
//                curso.setArea_id(rs.getInt("area_id"));                            
//            }
//            
//        } catch (Exception e) {         
//            Bitacora.registrar(e);
//            throw new Exception("Error crítico: Comunicarse con el administrador del sistema");
//        }finally{
//            try {
//                if(rs!=null)rs.close();
//                if(cstm!=null)cstm.close();                
//            } catch (Exception e) {
//                Bitacora.registrar(e);
//            }        
//        }        
//        return curso;     
//     }
}
