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
    private static final String error_string_vacia = "Se ha proporcionado una cadena de caracteres vacio";
    private static final String error_numero_vacia = "Se ha proporcionado un numero nulo";
    private static final String error_entidad_nula = "La Entidad proporcionada es nula";
    private static final String error_string_nula = "La cadena de caracteres proporcionda es nula";
     
    public static final HashMap<Integer,String> errores;
    static{
        HashMap<Integer,String> errorsLocal = new HashMap<>();
        errorsLocal.put(1, error_parametros);
        errorsLocal.put(2, error_respuesta_vacia);
        errorsLocal.put(3, error_string_vacia);
        errorsLocal.put(4, error_string_nula);
        errorsLocal.put(5, error_entidad_nula);
        errorsLocal.put(6, error_numero_vacia);
        
        errores = (HashMap<Integer, String>) Collections.unmodifiableMap(errorsLocal);
        
    }
    
     
}
