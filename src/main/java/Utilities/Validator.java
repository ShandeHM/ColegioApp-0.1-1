/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author LAB-USR-HUAN-A0305
 */
public class Validator {
    public static boolean isDNI(String cadena){
        Pattern patron = Pattern.compile("[0-9]{8}");
        Matcher mat = patron.matcher(cadena);
        return mat.matches();
    }
    
    public static boolean isNumeroTelefono(String cadena) {
        Pattern patron = Pattern.compile("[0-9]{9}");
        Matcher mat = patron.matcher(cadena);
        return mat.matches();
    }
    
    public static boolean isCorreoElectronico(String cadena){
        Pattern patron = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
        Matcher mat = patron.matcher(cadena);
        return mat.matches();
    } 
       
    public static boolean isDouble(String cadena){        
       try {
             Double.parseDouble(cadena);
             return true;
            } catch (NumberFormatException ex){
              return false;
            }        
    }
    
    public static boolean isInteger(String cadena){        
       try {
             Integer.parseInt(cadena);
             return true;
            } catch (NumberFormatException ex){
              return false;
            }        
    } 
    
    public static boolean isStringCorrect(){
        
        return true;
    }
    
}
