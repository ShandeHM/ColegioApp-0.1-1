/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaBean;

/**
 *
 * @author LAB-USR-HUAN-A0305
 */
public class DocenteCurso {
    private int docente_id;
    private int curso_int;
    private Area area;
    private Curso curso;
    private char turno;

    public DocenteCurso() {
    }

    public int getDocente_id() {
        return docente_id;
    }

    public void setDocente_id(int docente_id) {
        this.docente_id = docente_id;
    }

    public int getCurso_int() {
        return curso_int;
    }

    public void setCurso_int(int curso_int) {
        this.curso_int = curso_int;
    }

    public char getTurno() {
        return turno;
    }

    public void setTurno(char turno) throws Exception{
        if(turno == ' '){
            throw new Exception("El campo turno no puede estar vacio");
        }
        this.turno = turno;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    
    
}
