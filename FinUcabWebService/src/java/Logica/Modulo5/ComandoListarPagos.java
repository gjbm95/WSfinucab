/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo5;

import BaseDatosDAO.DAO;
import BaseDatosDAO.Singleton.SingletonDAOPago;
import Logica.Comando;

/**
 *
 * @author Ramon
 */
public class ComandoListarPagos extends Comando{

    private int idUSuario;
    
    public ComandoListarPagos(int idUsuario){
        this.idUSuario = idUsuario;
    }
    
    @Override
    public void ejecutar() {        
        DAO dao = SingletonDAOPago.getInstance();
        this.response = dao.consultarTodos(idUSuario);                
       
    }
    
}
