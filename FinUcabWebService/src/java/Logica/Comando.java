/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Dominio.Entidad;

/**
 *
 * @author Junior
 */
public abstract class Comando {
    
    protected Entidad response;
    
    public abstract void ejecutar();   
    
    public Entidad getResponse(){
        return response;
    }
    
}
