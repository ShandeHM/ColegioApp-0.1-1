/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaBean;

/**
 *
 * @author black
 */
public class alumnoApoderado {
    private int alumno_id;
    private int apoderado_id;
    private String parentesco;

    public alumnoApoderado() {
    }

    public int getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(int alumno_id) {
        this.alumno_id = alumno_id;
    }

    public int getApoderado_id() {
        return apoderado_id;
    }

    public void setApoderado_id(int apoderado_id) {
        this.apoderado_id = apoderado_id;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) throws Exception{
        if (( parentesco == null ) && ( parentesco.isEmpty() )) {
            throw new Exception("El campo parentesco esta vaico");
        }
        this.parentesco = parentesco;
    }
    
    
    
}
