/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaBean;

import java.sql.Time;
import java.time.LocalDate;

/**
 *
 * @author LAB-USR-HUAN-A0305
 */
public class AsistenciaDocente {
    private int asistencia_id;
    private Time hora_ingreso;
    private Time hora_salida;
    private LocalDate fecha;
    private int docente_id;

    public AsistenciaDocente(){
    }

    public int getAsistencia_id() {
        return asistencia_id;
    }

    public void setAsistencia_id(int asistencia_id) {
        this.asistencia_id = asistencia_id;
    }

    public Time getHora_ingreso() {
        return hora_ingreso;
    }

    public void setHora_ingreso(Time hora_ingreso) throws Exception{
        if (hora_ingreso == null) {
            throw new Exception("El hora_salida fecha está vacía");
        }
        this.hora_ingreso = hora_ingreso;
    }

    public Time getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Time hora_salida) throws Exception{
        if (hora_salida == null) {
            throw new Exception("El hora_salida fecha está vacía");
        }
        this.hora_salida = hora_salida;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) throws Exception{
        if(fecha == null || fecha.equals(LocalDate.MIN) ){
            throw new Exception("La fecha está vacía o no ha sido inicializada");
        }
        this.fecha = fecha;
    }    

    public int getDocente_id() {
        return docente_id;
    }

    public void setDocente_id(int docente_id) {
        this.docente_id = docente_id;
    }
    
}
