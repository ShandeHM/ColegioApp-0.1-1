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
public class pagoVarios {
    private int pago_varios_id;
    private LocalDate fecha;
    private double monto;
    private String observacion;
    
    private int alumno_id;

    public pagoVarios(){
    }

    public int getPago_varios_id() {
        return pago_varios_id;
    }

    public void setPago_varios_id(int pago_varios_id) {
        this.pago_varios_id = pago_varios_id;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) throws Exception{
        if(observacion == null || observacion.isEmpty()){
            throw new Exception("El campo observacion no debe estar vacia");
        }
        this.observacion = observacion;
    }

    public int getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(int alumno_id) {
        this.alumno_id = alumno_id;
    }
    
    
    
}
