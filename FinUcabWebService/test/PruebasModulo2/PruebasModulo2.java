/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasModulo2;

import Dominio.Usuario;
import Exceptions.FinUCABException;
import Logica.Comando;
import Logica.FabricaComando;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
*Modulo 2 - Modulo de Home
*Desarrolladores:
*Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
*Descripción de la clase:
*Metodos del servicio web destinados para las funcionalidades de Home y 
* Tarjetas de Credito y Cuentas Bancarias. 
*
**/
public class PruebasModulo2 {

    public PruebasModulo2() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Prueba encargada de verificar el funcionamiento de actualizar datos de
     * cuentas de usuario.
     *
     * @param decodifico String con estructura json
     * @return JsonObject del string
     */
    @Test
    public void actualizarCuentaTest() {

        try {
            String decodifico = "{ \"u_id\" : \"1\" , \"u_usuario\" : \"Eoeooeoe\" ,"
                    + " \"u_nombre\" : \"Alejandro\""
                    + ", \"u_apellido\" : \"Negrin\", \"u_correo\" :"
                    + " \"aledavid21@hotmail.com\", "
                    + "\"u_pregunta\" : \"Nombre de mi mama\" ,"
                    + " \"u_respuesta\" : \"/alejandra\", "
                    + "\"u_password\" : \"123456\" }";
            JsonObject usuarioJSON = this.stringToJSON(decodifico);
            Usuario usuario = new Usuario();
            usuario.jsonToUsuario(usuarioJSON);
            
            Comando command = FabricaComando.instanciarComandoActualizarDatosUsuario(usuario);
            command.ejecutar();
        } catch (FinUCABException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Funcion que convierte un string con estructura JSON en JsonObject
     *
     * @param decodifico String con estructura json
     * @return JsonObject del string
     */
    private JsonObject stringToJSON(String decodifico) {
        JsonReader reader = Json.createReader(new StringReader(decodifico));
        JsonObject jsonObj = reader.readObject();
        reader.close();
        return jsonObj;
    }
}

