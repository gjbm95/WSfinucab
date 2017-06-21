/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

/**
 *
 * @author Oswaldo
 */
public class FabricaDAO {
    
    public static Modulo1DAO instanciarModulo1Dao(){
        return new Modulo1DAO();
    }
}
