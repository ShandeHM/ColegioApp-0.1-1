/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaBean;

/**
 *
 * @author LAB-USR-HUAN-A0305
 */
public class Area {
    
    private int area_id;
    private String area_nombre;

    public Area() {
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getArea_nombre() {
        return area_nombre;
    }

    public void setArea_nombre(String area_nombre) throws Exception{
        if( (area_nombre==null) || (area_nombre.isEmpty()) ){
            throw new Exception("El campo Nombre no puede estar vacio");
        }
        
        this.area_nombre = area_nombre;
    }
    
    
}
