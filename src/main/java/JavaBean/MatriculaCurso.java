/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaBean;

/**
 *
 * @author LAB-USR-HUAN-A0305
 */
public class MatriculaCurso {
   private int matricula_id;
   private int curso_int;
   private short bimestre;
   private double nota;
   
   
    public MatriculaCurso() {
    }

    public int getMatricula_id() {
        return matricula_id;
    }

    public void setMatricula_id(int matricula_id) {
        this.matricula_id = matricula_id;
    }

    public int getCurso_int() {
        return curso_int;
    }

    public void setCurso_int(int curso_int) {
        this.curso_int = curso_int;
    }

    public short getBimestre() {
        return bimestre;
    }

    public void setBimestre(short bimestre) {
        this.bimestre = bimestre;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
   
   
}
