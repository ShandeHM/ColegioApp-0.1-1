/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaBean;

import java.time.LocalDate;
/**
 *
 * @author black
 */
public class pagoMatricula {
    private int pago_matricula_id;
    private LocalDate fecha;
    private double monto;
    private int alumno_id;
    
    private String observacion;
    

    public pagoMatricula(){
    }

    public int getPago_matricula_id() {
        return pago_matricula_id;
    }

    public void setPago_matricula_id(int pago_matricula_id) {
        this.pago_matricula_id = pago_matricula_id;
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

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) throws Exception{
        if(monto <= 0){
            throw new Exception("El monto debe ser mayor a 0");
        }
        this.monto = monto;
    }

    public int getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(int alumno_id) {
        this.alumno_id = alumno_id;
    }
    
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) throws Exception{
        if(observacion == null || observacion.isEmpty()){
            throw new Exception("El campo observacion no debe estar vacia");
        }
        this.observacion = observacion;
    }
    
    
    
}
