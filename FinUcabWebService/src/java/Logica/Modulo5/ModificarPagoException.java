/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo5;

import Exceptions.FinUCABException;

/**
 *
 * @author Ramon
 */
public class ModificarPagoException extends FinUCABException {
    
    public ModificarPagoException(int code, String message){
        super(code, message,ModificarPagoException.class);
    }
    
}
