/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaBean;

/**
 *
 * @author LAB-USR-HUAN-A0305
 */
public class HistorialNotas {
    private int historial_id;
    private int alumno_id;
    private int curso_id;
    private double promedio;
   
    public HistorialNotas(){
        
    }
    
    public HistorialNotas(int historial_id, int alumno_id, int curso_id) {
        this.historial_id = historial_id;
        this.alumno_id = alumno_id;
        this.curso_id = curso_id;
    }

    public HistorialNotas(int historial_id, int alumno_id, int curso_id, int promedio) {
        this.historial_id = historial_id;
        this.alumno_id = alumno_id;
        this.curso_id = curso_id;
        this.promedio = promedio;
    }
    
    public int getHistorial_id() {
        return historial_id;
    }

    public void setHistorial_id(int historial_id) {
        this.historial_id = historial_id;
    }

    public int getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(int alumno_id) {
        this.alumno_id = alumno_id;
    }

    public int getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(int curso_id) {
        this.curso_id = curso_id;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio){
        if(promedio<0){
            System.out.println("El promedio debe ser mayor o igual a 0");
        }else{
            this.promedio = promedio;
        }
    }
}
