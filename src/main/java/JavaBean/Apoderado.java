/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaBean;

import Utilities.Validator;

/**
 *
 * @author black
 */
public class Apoderado {
    private int apoderado_id;
    private String nombres;
    private String apellido_materno;
    private String apellido_paterno;
    private String dni;
    
    private String contacto;
    
    public Apoderado() {
    }

    public int getApoderado_id() {
        return apoderado_id;
    }

    public void setApoderado_id(int apoderado_id){
        this.apoderado_id = apoderado_id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombre) throws Exception {
        if( (nombre==null) || (nombre.isEmpty()) ){
            throw new Exception("El campo Nombres no puede estar vacío");           
        }
        else{
            if(nombre.length() > 20){
                throw new Exception("El campo Nombres no puede sobrepasar los 20 caracteres");
            }
        }
        
        this.nombres = nombre;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) throws Exception{
        if( (apellido_materno==null) || (apellido_materno.isEmpty()) ){
            throw new Exception("El campo Apellido Materno no puede estar vacío");
            
        }
        else{
            if(apellido_materno.length() > 20){
                throw new Exception( "El campo Apellido Materno no puede sobrepasar los 20 caracteres");
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
            throw new Exception("El campo Apellido Paterno no puede estar vacío");
            
        }
        else{
            if(apellido_paterno.length() > 20){
                throw new Exception("El campo Apellido Paterno no puede sobrepasar los 20 caracteres");
            }
        }
        
        this.apellido_paterno = apellido_paterno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws Exception{
        if(dni==null ||dni.isEmpty()){
           throw new Exception("El campo DNI no puede estar vacío"); 
        }else{
            if(!Validator.isDNI(dni)){
              throw new Exception("Error en el formato de DNI");
            }
        }        
        this.dni = dni;
    }
    
     public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) throws Exception{
        if(contacto==null ||contacto.isEmpty()){
           throw new Exception("El campo Contacto no puede estar en blanco"); 
        }else if(!Validator.isNumeroTelefono(contacto)){
           throw new Exception("Error en el formato Contacto"); 
        }
        this.contacto = contacto;
    }
    
}
