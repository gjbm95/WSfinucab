/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasModulo2;

import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Seguridad;
import Dominio.Cuenta_Bancaria;
import Dominio.SimpleResponse;
import Dominio.Tarjeta_Credito;
import Dominio.Usuario;
import Exceptions.FinUCABException;
import Logica.Comando;
import Logica.FabricaComando;
import Logica.Modulo2.ConversionFallidaException;
import Logica.Modulo2.MapaModulo2;
import Services.Modulo2Resource;
import Services.Modulo2sResource;
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
     */
    @Test
    public void actualizarCuentaTest() {

        try {
            String decodifico = "{ \"u_id\" : \"1\" , \"u_usuario\" : \"gjbm\" ,"
                    + " \"u_nombre\" : \"Alejandro\""
                    + ", \"u_apellido\" : \"Negrin\", \"u_correo\" :"
                    + " \"aledavid21@hotmail.com\", "
                    + "\"u_pregunta\" : \"Nombre de mi mama\" ,"
                    + " \"u_respuesta\" : \"alejandra\", "
                    + "\"u_password\" : \"1509442\" }";
            
            Modulo2sResource webservice = new Modulo2sResource();
            JsonObject usuarioJSON = webservice.stringToJSON(decodifico);
            Usuario usuario = new Usuario();
            usuario.jsonToUsuario(usuarioJSON); 
            Comando command = FabricaComando.instanciarComandoActualizarDatosUsuario(usuario);
            command.ejecutar();
            DaoUsuario dao = FabricaDAO.instanciasDaoUsuario();
            SimpleResponse respuesta = (SimpleResponse) dao.obtenerInicioSesion(usuario);
            String [ ] data = respuesta.getDescripcion().split(":-:");
            System.out.println(data[0]);
            JsonObject usuarioJSON2 = webservice.stringToJSON(data[0]);
            assertEquals(usuarioJSON2.getString("u_usuario"),usuario.getUsuario()); 
            
        } catch (FinUCABException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    /**
     * Prueba encargada de verificar el funcionamiento de convertir cadenas
     * de texto en objetos Json
     */
    @Test
    public void stringToJsonTest(){
        try {
            String decodifico = "{ \"u_id\" : \"1\" , \"u_usuario\" : \"gjbm\" ,"
                    + " \"u_nombre\" : \"Alejandro\""
                    + ", \"u_apellido\" : \"Negrin\", \"u_correo\" :"
                    + " \"aledavid21@hotmail.com\", "
                    + "\"u_pregunta\" : \"Nombre de mi mama\" ,"
                    + " \"u_respuesta\" : \"alejandra\", "
                    + "\"u_password\" : \"1509442\" }";
            Modulo2sResource webservice = new Modulo2sResource();
            JsonObject usuarioJSON = webservice.stringToJSON(decodifico);
            assertNotNull(usuarioJSON);
            assertEquals(usuarioJSON.getString("u_id"),"1");
            assertEquals(usuarioJSON.getString("u_usuario"),"gjbm");
            assertEquals(usuarioJSON.getString("u_nombre"),"Alejandro");
            assertEquals(usuarioJSON.getString("u_apellido"),"Negrin");
            assertEquals(usuarioJSON.getString("u_correo"),"aledavid21@hotmail.com");
            assertEquals(usuarioJSON.getString("u_pregunta"),"Nombre de mi mama");
            assertEquals(usuarioJSON.getString("u_respuesta"),"alejandra");
            assertEquals(usuarioJSON.getString("u_password"),"1509442");
                 
        } catch (ConversionFallidaException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    } 
    
     /**
     * Prueba encargada de verificar el funcionamiento de convertir Jsons
     *  en objetos de Tipo tarjeta 
     */
    @Test 
    public void jsonToTarjetaTest(){
        try {
            //{"":"","":"","":"","":"","":""}
            String decodifico = "{ \"tc_saldo\" : \"0.0\" , \"usuariou_id\" : \"1\" ,"
                    + " \"tc_tipo\" : \"American Express\""
                    + ", \"tc_numero\" : \"Xdk\\/10MxPSM9gdDidoZBHw==\", \"tc_fechavencimiento\" :"
                    + " \"2-7-2019\"}";
            Modulo2sResource webservice = new Modulo2sResource();
            JsonObject tarjetaJSON = webservice.stringToJSON(decodifico);
            Tarjeta_Credito tarjeta = webservice.jsonToTarjeta(tarjetaJSON);
            assertNotNull(tarjeta);
            assertEquals(tarjetaJSON.getString("tc_tipo"),"American Express");
            
        } catch (ConversionFallidaException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**
     * Prueba encargada de verificar el funcionamiento de convertir Jsons
     *  en objetos de Tipo tarjeta (Version de Modificar
     */
    @Test 
    public void jsonToTarjetaMTest(){
        try {
            //{"":"","":"","":"","":"","":""}
            String decodifico = "{\"tc_id\" : \"1\" , \"tc_saldo\" : \"0.0\" , \"usuariou_id\" : \"1\" ,"
                    + " \"tc_tipo\" : \"American Express\""
                    + ", \"tc_numero\" : \"Xdk\\/10MxPSM9gdDidoZBHw==\", \"tc_fechavencimiento\" :"
                    + " \"2-7-2019\"}";
            Modulo2sResource webservice = new Modulo2sResource();
            JsonObject tarjetaJSON = webservice.stringToJSON(decodifico);
            Tarjeta_Credito tarjeta = webservice.jsonToTarjetaM(tarjetaJSON);
            assertNotNull(tarjeta);
            assertEquals(tarjetaJSON.getString("tc_id"),"1");
            
        } catch (ConversionFallidaException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba encargada de verificar el funcionamiento de convertir Jsons
     *  en objetos de Tipo tarjeta 
     */
    @Test 
    public void jsonToCuentaTest(){
        try {
            //{"":"","":"","":"","":"","":""}
            String decodifico = "{ \"ct_numcuenta\" : \"76438346499564\" , \"usuariou_id\" : \"1\" ,"
                    + " \"ct_nombrebanco\" : \"Banco del Tesoro\" "
                    + ", \"ct_tipocuenta\": \"Cuenta de Ahorro\", \"ct_saldoactual\" :"
                    + " \"2500.0\"}";
            Modulo2sResource webservice = new Modulo2sResource();
            JsonObject cuentaJSON = webservice.stringToJSON(decodifico);
            Cuenta_Bancaria cuenta = webservice.jsonToCuenta(cuentaJSON);
            assertNotNull(cuenta);
            assertEquals(cuentaJSON.getString("ct_numcuenta"),"76438346499564");
            
        } catch (ConversionFallidaException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**
     * Prueba encargada de verificar el funcionamiento de convertir Jsons
     *  en objetos de Tipo tarjeta  (Modificar)
     */
    @Test 
    public void jsonToCuentaMTest(){
        try {
            //{"":"","":"","":"","":"","":""}
            String decodifico = "{ \"ct_id\" : \"1\", \"ct_numcuenta\" : \"76438346499564\" , \"usuariou_id\" : \"1\" ,"
                    + " \"ct_nombrebanco\" : \"Banco del Tesoro\" "
                    + ", \"ct_tipocuenta\": \"Cuenta de Ahorro\", \"ct_saldoactual\" :"
                    + " \"2500.0\"}";
            Modulo2sResource webservice = new Modulo2sResource();
            JsonObject cuentaJSON = webservice.stringToJSON(decodifico);
            Cuenta_Bancaria cuenta = webservice.jsonToCuentaM(cuentaJSON);
            assertNotNull(cuenta);
            assertEquals(cuentaJSON.getString("ct_id"),"1");
            
        } catch (ConversionFallidaException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Prueba encargada de verificar el funcionamiento del encriptamiento de 
     * datos
     */
    @Test
    public void encriptarTest(){
       Seguridad seguridad = Seguridad.obtenerInstancia();
       assertEquals("y2BNqSooOLs=",seguridad.Encriptar("casa"));
    }
    
    /**
     * Prueba encargada de verificar el funcionamiento del Desencriptamiento de 
     * datos
     */
    @Test
    public void DesencriptarTest(){
       Seguridad seguridad = Seguridad.obtenerInstancia();
       assertEquals("casa",seguridad.Desencriptar("y2BNqSooOLs="));
    }
    
    /**
     * Prueba encargada de verificar el funcionamiento del mapa de Modulo2
     * (Agregar Datos)
     */
    @Test
    public void MapaModuloAgregarTest(){
       MapaModulo2 cache = MapaModulo2.obtenerInstancia();
       cache.setEntidad("prueba",new SimpleResponse("Probando"));
       
       SimpleResponse resultado = (SimpleResponse)cache.getEntidad("prueba");
       assertEquals("Probando",resultado.getDescripcion());
    }
    
    /**
     * Prueba encargada de verificar el funcionamiento del mapa de Modulo2
     * (Modificar Datos)
     */
    @Test
    public void MapaModuloModificarTest(){
       MapaModulo2 cache = MapaModulo2.obtenerInstancia();
       cache.setEntidad("prueba",new SimpleResponse("Probando"));
       SimpleResponse resultado = (SimpleResponse)cache.getEntidad("prueba");
       assertEquals("Probando",resultado.getDescripcion());
       cache.actualizarEntidad("prueba",new SimpleResponse("Pasando"));
       resultado = (SimpleResponse)cache.getEntidad("prueba");
       assertEquals("Pasando",resultado.getDescripcion());
    }
    
    /**
     * Prueba encargada de verificar el funcionamiento del mapa de Modulo2
     * (Eliminar Datos)
     */
    @Test
    public void MapaModuloEliminarTest(){
       MapaModulo2 cache = MapaModulo2.obtenerInstancia();
       cache.setEntidad("prueba",new SimpleResponse("Probando"));
       SimpleResponse resultado = (SimpleResponse)cache.getEntidad("prueba");
       assertEquals("Probando",resultado.getDescripcion());
       cache.eliminarEntidad("prueba");
       assertNull(cache.getEntidad("prueba"));
    }
    
    /**
     * Prueba encargada de verificar el funcionamiento del mapa de Modulo2
     * (Obtener Datos)
     */
    @Test
    public void MapaModuloObtenerTest(){
       MapaModulo2 cache = MapaModulo2.obtenerInstancia();
       cache.setEntidad("prueba",new SimpleResponse("Probando"));
       SimpleResponse resultado = (SimpleResponse)cache.getEntidad("prueba");
       assertEquals("Probando",resultado.getDescripcion());   
       assertNotNull(cache.getEntidad("prueba"));
    }
    
}

