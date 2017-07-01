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
public class AgregarPagoException extends FinUCABException {
    
    public AgregarPagoException(int code){
        super(code, AgregarPagoException.class);
    }
    
    public AgregarPagoException(int code, String message){
        super(code, message,AgregarPagoException.class);
    }
}
