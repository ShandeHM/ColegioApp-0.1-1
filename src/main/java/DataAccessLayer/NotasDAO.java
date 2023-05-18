/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;

import Connection.UConnection;
import JavaBean.Notas;
import Utilities.Bitacora;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Anthony
 */
public class NotasDAO {
    public void insertar(Notas notas) throws Exception{        
        
        Connection con=null;
        CallableStatement cstm = null;    
        
        try {
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_notas_insertar(?,?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            int cont = 0;
            if(notas.getNota1()>20 || notas.getNota1() < 0){
                cont++;
            }
            if(notas.getNota2()>20 || notas.getNota2() < 0){
                cont++;
            }
            if(notas.getNota3()>20 || notas.getNota3() < 0){
                cont++;
            }
            if(notas.getNota4()>20 || notas.getNota4() < 0){
                cont++;
            }
            if(notas.getNota5()>20 || notas.getNota5() < 0){
                cont++;
            }
            
          
           if(cont==0){
                cstm.setInt(1, notas.getHistorial_notas_id());
                cstm.setDouble(2, notas.getNota1());
                cstm.setDouble(3, notas.getNota2());
                cstm.setDouble(4, notas.getNota3());
                cstm.setDouble(5, notas.getNota4());
                cstm.setDouble(6, notas.getNota5());
                cstm.executeUpdate();
           }else{
               throw new Exception("No se pudo registrar las notas");
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
            sql="call sp_notas_eliminar(?)";
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
    
    public void actualizar(Notas notas) throws Exception{        
        
        Connection con=null;
        CallableStatement cstm = null;    
        
        try {
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_notas_actualizar(?,?,?,?,?,?)";
            cstm=con.prepareCall(sql);
            int cont = 0;
            if(notas.getNota1()>20 || notas.getNota1() < 0){
                cont++;
            }
            if(notas.getNota2()>20 || notas.getNota2() < 0){
                cont++;
            }
            if(notas.getNota3()>20 || notas.getNota3() < 0){
                cont++;
            }
            if(notas.getNota4()>20 || notas.getNota4() < 0){
                cont++;
            }
            if(notas.getNota5()>20 || notas.getNota5() < 0){
                cont++;
            }
            
          
           if(cont==0){
                cstm.setInt(1, notas.getHistorial_notas_id());
                cstm.setDouble(2, notas.getNota1());
                cstm.setDouble(3, notas.getNota2());
                cstm.setDouble(4, notas.getNota3());
                cstm.setDouble(5, notas.getNota4());
                cstm.setDouble(6, notas.getNota5());
                cstm.executeUpdate();
           }else{
               throw new Exception("No se pudo actualizar las notas");
           }           
        } catch (Exception e) {
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
    
    public Notas buscarPorHistorialNotasId(int cadena) throws Exception{
     
        Notas notas=new Notas();
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_notas_por_historial_notas_id(?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, cadena);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
            
            
            notas.setHistorial_notas_id(rs.getInt("historial_notas_id"));
            notas.setNota1(rs.getDouble("nota1"));
            notas.setNota2(rs.getDouble("nota2"));
            notas.setNota3(rs.getDouble("nota3"));
            notas.setNota4(rs.getDouble("nota4"));
            notas.setNota5(rs.getDouble("nota5"));
            
            
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
        return notas;     
     }
    
    public ArrayList<Notas> notasListar() throws Exception{
     
        ArrayList<Notas>notas=new ArrayList<>();
        Notas nota=null;
        
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
                nota = new Notas();
                nota.setHistorial_notas_id(rs.getInt("historial_notas_id"));
                nota.setNota1(rs.getDouble("nota1"));
                nota.setNota2(rs.getDouble("nota2"));
                nota.setNota3(rs.getDouble("nota3"));
                nota.setNota4(rs.getDouble("nota4"));
                nota.setNota5(rs.getDouble("nota5"));
                
                notas.add(nota);
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
        return notas;     
     }
    
    public double determinarPromedio(int cadena) throws Exception{
        
        Connection con=null;
        CallableStatement cstm = null;  
        ResultSet rs=null;
        double nota = -1;
        
        try {            
            con=UConnection.getConnection();
            String sql="";            
            sql="call sp_notas_calcular_promedio(?)";
            cstm=con.prepareCall(sql);
            cstm.setInt(1, cadena);
         
            rs=cstm.executeQuery(); //se puede usar .execute() para todas las operaciones         
            
            nota = rs.getDouble(1);
            
            
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
        return nota;     
    }
}
