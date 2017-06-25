/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo1;

import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import Logica.Comando;
import Services.Modulo1sResource;

/**
 *
 * @author Oswaldo
 */
public class ComandoActualizarClave extends Comando{
    String usuario, clave;

    public ComandoActualizarClave(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }
    
    @Override
    public Object ejecutar(){
        DaoUsuario dao = FabricaDAO.instanciasDaoUsuario();
     
        int respuesta = dao.ActualizarClave(usuario,clave);
        if(respuesta == 5){
            Modulo1sResource.resultado = "5";
        }else {
            Modulo1sResource.resultado = "6";
        }
       
        
        return null;
    }
}
