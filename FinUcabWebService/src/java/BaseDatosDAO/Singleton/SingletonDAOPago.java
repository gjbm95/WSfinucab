/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO.Singleton;

import BaseDatosDAO.DAO;
import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOPago;

/**
 *
 * @author Ramon
 */
public class SingletonDAOPago {
    
    private SingletonDAOPago() {
    }
    
    public static IDAOPago getInstance() {
        
        return SingletonDAOPagoHolder.INSTANCE;
    }
    
    private static class SingletonDAOPagoHolder {

        private static final IDAOPago INSTANCE = FabricaDAO.instanciasDAOPago();
    }
}
