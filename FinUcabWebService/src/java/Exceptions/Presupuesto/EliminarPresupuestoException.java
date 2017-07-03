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
public class EliminarPresupuestoException extends FinUCABException{
    
        public EliminarPresupuestoException(int code){
        super(code,EliminarPresupuestoException.class);
    }
    
    public EliminarPresupuestoException(int code, String message){
        super(code, message,EliminarPresupuestoException.class);
    }
    
}
