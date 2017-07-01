/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registro;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author Ramon
 */
public class RegistroError {
    
    public static final String error_default = "Ha ocurrido un error";
    
    private static final String error_parametros = "Ha ocurrido un error con los datos proporcionados";
    private static final String error_respuesta_vacia = "No se ha encontrado la entidad con el id proposionado";
     
    public static final HashMap<Integer,String> errores;
    static{
        HashMap<Integer,String> errorsLocal = new HashMap<>();
        errorsLocal.put(1, error_parametros);
        errorsLocal.put(1, error_parametros);
        errorsLocal.put(1, error_parametros);
        
        errores = (HashMap<Integer, String>) Collections.unmodifiableMap(errorsLocal);
        
    }
    
     
}
