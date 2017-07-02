/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions.Presupuesto;

import Exceptions.FinUCABException;
import Logica.Modulo5.ModificarPagoException;

/**
 *
 * @author CHRISTIAN
 */
public class ModificarPresupuestoException extends FinUCABException{
    
    public ModificarPresupuestoException(int code){
        super(code,ModificarPresupuestoException.class);
    }
    
    public ModificarPresupuestoException(int code, String message){
        super(code, message,ModificarPresupuestoException.class);
    }
    
}
