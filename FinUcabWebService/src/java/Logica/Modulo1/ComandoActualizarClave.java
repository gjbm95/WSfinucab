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
public class ComandoActualizarClave extends Comando{
    String usuario, clave;

    public ComandoActualizarClave(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }
    
    @Override
    public void ejecutar(){
        Modulo1DAO dao = FabricaDAO.instanciarModulo1Dao();
     
        int respuesta = dao.ActualizarClave(usuario,clave);
        if(respuesta == 1){
            Modulo1sResource.resultado = "Clave Modificada";
        }else if(respuesta == 0){
            Modulo1sResource.resultado = "Error";
        }
       
        
        //return null;
    }
}
