/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo4;

import BaseDatosDAO.DAO;
import BaseDatosDAO.FabricaDAO;
import Dominio.Categoria;
import Logica.Comando;

/**
 *
 * @author Jeffrey
 */

public class ComandoConsultarCategoria extends Comando{
    private int idusuario;
    
    public ComandoConsultarCategoria(int usuario){
        this.idusuario = usuario;
    }

    @Override
    public void ejecutar() {
     
        DAO dao = FabricaDAO.instanciasDaoCategoria();
        Object response = dao.consultar(idusuario);
        //return response;
        
    }
    
    
}
