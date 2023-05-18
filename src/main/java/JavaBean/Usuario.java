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
public class Usuario {
    
    private int usuario_id;
    private String nombres;
    private String apellido_materno;
    private String apellido_paterno;
    private String dni;
    private String clave;
    private String rol;
    private String apellidosNombres;

    public Usuario(){
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) throws Exception {
        if (nombres == null || nombres.isEmpty()) {
            throw new Exception("El campo Nombres no puede estar vacío");
        } else {
            if (nombres.length() > 20) {
                throw new Exception("El campo Nombres no puede sobrepasar los 20 caracteres");
            }
        }
        this.nombres = nombres;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) throws Exception {
        if (apellido_materno == null || apellido_materno.isEmpty()) {
            throw new Exception("El campo Apellido Materno no puede estar vacío");
        } else {
            if (apellido_materno.length() > 20) {
                throw new Exception("El campo Apellido Materno no puede sobrepasar los 20 caracteres");
            }
        }
        this.apellido_materno = apellido_materno;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) throws Exception {
        if (apellido_paterno == null || apellido_paterno.isEmpty()) {
            throw new Exception("El campo Apellido Paterno no puede estar vacío");
        } else {
            if (apellido_paterno.length() > 20) {
                throw new Exception("El campo Apellido Paterno no puede sobrepasar los 20 caracteres");
            }
        }
        this.apellido_paterno = apellido_paterno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws Exception {
        if (dni == null || dni.isEmpty()) {
            throw new Exception("El campo DNI no puede estar en blanco");
        } else {
            if (!Validator.isDNI(dni)) {
                throw new Exception("Error en el formato de DNI");
            }
        }
        this.dni = dni;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) throws Exception {
        if (clave == null || clave.isEmpty()) {
            throw new Exception("El campo Clave no puede estar vacío");
        } else {
            if (clave.length() < 8 || clave.length() > 20) {
                throw new Exception("La longitud de la Clave debe estar entre 8 y 20 caracteres");
            }
        }
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) throws Exception{
        if (( rol == null ) && ( rol.isEmpty() )) {
            throw new Exception("El campo rol esta vacio");
        }
        this.rol = rol;
    }

    public String getApellidosNombres() {
        return apellidosNombres;
    }

    public void setApellidosNombres(String apellidosNombres) {
        this.apellidosNombres = apellidosNombres;
    }
}
