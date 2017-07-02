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
public class ConsultarPresupuestoException extends FinUCABException{
    
        public ConsultarPresupuestoException(int code){
        super(code,ConsultarPresupuestoException.class);
    }
    
    public ConsultarPresupuestoException(int code, String message){
        super(code, message,ConsultarPresupuestoException.class);
    }
    
}
