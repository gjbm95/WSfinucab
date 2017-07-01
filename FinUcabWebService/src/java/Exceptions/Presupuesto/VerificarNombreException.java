/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions.Presupuesto;

import Exceptions.FinUCABException;

/**
 *
 * @author CHRISTIAN
 */
public class VerificarNombreException extends FinUCABException{
    
    public VerificarNombreException(int code){
        super(code,VerificarNombreException.class);
    }
    
    public VerificarNombreException(int code, String message){
        super(code, message,VerificarNombreException.class);
    }
    
}
