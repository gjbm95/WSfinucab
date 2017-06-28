/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;

/**
 *
 * @author Ramon
 */
public class ListaEntidad extends Entidad{
    
    private ArrayList<Entidad> lista;
    
    public ListaEntidad( ArrayList<Entidad> lista){
        this.lista = lista;
    }
    
    public ArrayList<Entidad> getLista(){        
        return lista;
    }
    
}
