/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaBean;

import Utilities.Validator;


/**
 *
 * @author LAB-USR-HUAN-A0305
 */
public class Docente {
    private int docente_id;
    private String nombres;
    private String apellido_materno;
    private String apellido_paterno;
    private String dni;
    private String apellidosNombres;
    
    private String contacto;
    
    public Docente() {
    }

    public int getDocente_id() {
        return docente_id;
    }

    public void setDocente_id(int docente_id){
        this.docente_id = docente_id;
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

    public String getApellidosNombres() {
        return apellidosNombres;
    }

    public void setApellidosNombres(String apellidosNombres) {
        this.apellidosNombres = apellidosNombres;
    }
}
