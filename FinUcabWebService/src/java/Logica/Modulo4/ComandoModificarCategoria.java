/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo4;

import BaseDatosDAO.DAO;
import BaseDatosDAO.FabricaDAO;
import Dominio.Entidad;
import Exceptions.FinUCABException;
import Logica.Comando;

/**
 *
 * @author Jeffrey
 */
public class ComandoModificarCategoria extends Comando{
    public Entidad categoria;
    
    public ComandoModificarCategoria(Entidad categoria){
        this.categoria = categoria;
    }

    @Override
    public void ejecutar() throws FinUCABException {
        DAO dao = FabricaDAO.instanciasDaoCategoria();
        Entidad respuesta = dao.modificar(categoria);
        /*if (respuesta != null){
            return respuesta;
        }
        else {
            return "Error";
        }
           */ 

    }
    
    
}