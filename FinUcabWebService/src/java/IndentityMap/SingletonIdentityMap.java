/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndentityMap;

import BaseDatosDAO.Singleton.*;
import BaseDatosDAO.DAO;
import BaseDatosDAO.FabricaDAO;

/**
 *
 * @author Ramon
 */
public class SingletonIdentityMap {
    
    private SingletonIdentityMap() {
    }
    
    public static IdentityMap getInstance() {
        
        return SingletonIdentityMapHolder.INSTANCE;
    }
    
    private static class SingletonIdentityMapHolder {

        private static final IdentityMap INSTANCE = FabricaIdentityMap.obtenerIdentityMap();
    }
}
