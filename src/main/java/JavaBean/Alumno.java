/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaBean;

import Utilities.Validator;
import java.time.LocalDate;
/**
 *
 * @author LAB-USR-HUAN-A0305
 */
public class Alumno {
    private int alumno_id;
    private String nombres;
    private String apellido_materno;
    private String apellido_paterno;
    private String dni;
    private LocalDate fecha_nacimiento;
    private String correo_electrico;
    private String apellidosNombres;

    public Alumno() {
    }

    public int getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(int alumno_id){
        this.alumno_id = alumno_id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombre) throws Exception {
        if( (nombre==null) || (nombre.isEmpty()) ){
            throw new Exception("El campo Nombre no puede estar vacio");           
        }
        else{
            if(nombre.length() > 20){
                throw new Exception("El campo Nombre no puede sobrepasar los 20 carácteres");
            }
        }
        
        this.nombres = nombre;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) throws Exception{
        if( (apellido_materno==null) || (apellido_materno.isEmpty()) ){
            throw new Exception("El campo Apellido Materno no puede estar vacio");
            
        }
        else{
            if(apellido_materno.length() > 20){
                throw new Exception( "El campo Apellido Materno no puede sobrepasar los 20 carácteres");
            }
        }
        this.apellido_materno = apellido_materno;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) throws Exception{
        //no puede ser vacio
        if( (apellido_paterno==null) || (apellido_paterno.isEmpty()) ){
            throw new Exception("El campo Apellido Paterno no puede estar vacio");
            
        }
        else{
            if(apellido_paterno.length() > 20){
                throw new Exception("El campo Apellido Paterno no puede sobrepasar los 20 carácteres");
            }
        }
        
        this.apellido_paterno = apellido_paterno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws Exception{
        if(dni==null ||dni.isEmpty()){
           throw new Exception("El campo DNI no puede estar en blanco"); 
        }else{
            if(!Validator.isDNI(dni)){
              throw new Exception("Error en el formato de DNI");
            }
        }        
        this.dni = dni;
    }

    public LocalDate getFecha_nacimiento() {
        //no puede ser vacio
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) throws Exception{
        if( fecha_nacimiento==null ){
            throw new Exception("El campo fechaNacimiento esta vacio");
        }
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCorreo_electrico() {
        return correo_electrico;
    }

    public void setCorreo_electrico(String correo_electrico) throws Exception{
        if(( correo_electrico!=null ) && ( correo_electrico.isEmpty() )){
            if(correo_electrico.length() > 50) {
                throw new Exception("El campo Correo Electronico no puede sobrepasar los 50 caracteres");
            }
            else{
                if(!Validator.isCorreoElectronico(correo_electrico)){
                    throw new Exception("Error en el formato del Correo Electronico");
                }
            }
        }
        this.correo_electrico = correo_electrico;
    }

    public String getApellidosNombres() {
        return apellidosNombres;
    }

    public void setApellidosNombres(String apellidosNombres) {
        this.apellidosNombres = apellidosNombres;
    }      
}
