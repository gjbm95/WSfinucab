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
public class EliminarPresupuestoExeption extends FinUCABException{
    
        public EliminarPresupuestoExeption(int code){
        super(code,EliminarPresupuestoExeption.class);
    }
    
    public EliminarPresupuestoExeption(int code, String message){
        super(code, message,EliminarPresupuestoExeption.class);
    }
    
}
