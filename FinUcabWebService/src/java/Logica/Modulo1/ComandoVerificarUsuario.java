/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo1;

import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Modulo1DAO;
import Logica.Comando;
import Services.Modulo1sResource;

/**
 *
 * @author Oswaldo
 */
public class ComandoVerificarUsuario extends Comando{
    String usuario;

    public ComandoVerificarUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public void ejecutar(){
        Modulo1DAO dao = FabricaDAO.instanciarModulo1Dao();
        int respuesta = dao.verificarUsuario(usuario);
        if(respuesta == 1){
            Modulo1sResource.resultado = "No Disponible";
        }else if(respuesta == 0){
            Modulo1sResource.resultado = "Usuario Disponible";
        }
        //return null;
    }
}
