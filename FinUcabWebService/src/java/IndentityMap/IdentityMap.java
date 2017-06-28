/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndentityMap;

import Dominio.Entidad;
import java.util.HashMap;

/**
 *
 * @author Ramon
 */
public class IdentityMap {
    
    private HashMap<String, Entidad> _cache;
    
    public IdentityMap(){
        
        this._cache = new HashMap<>();
    }
    
    public Entidad obtenerEntidad(String id){
        
        Entidad salida  = this._cache.get(id);
     
        return salida;
    }
    
    
    public void setEntidad(String id, Entidad entidad){
        
        this._cache.put(id, entidad);
     
    }
    
    
    
}
