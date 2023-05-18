    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author LAB-USR-HUAN-A0305
 */
public class Bitacora {
     private final static Logger log= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
   public static void registrar(Exception ex){
        try {
                FileHandler fh= new FileHandler("bitacora.log",true);        
                fh.setLevel(Level.SEVERE);
                log.addHandler(fh);                
                //https://www.youtube.com/watch?v=O4JXLzqWJCY&list=PLxErEhSBHScGfYHUGKKa-yiWUmyq_XBrE&index=4
                log.setUseParentHandlers(false);
                log.severe(ex.getMessage()+"****"+ex.getStackTrace().toString());
            } catch (Exception e) {
                log.log(Level.SEVERE,"Problemas al intentar escribir la bitacora"+ex.getMessage());
            }
    }
    
}
