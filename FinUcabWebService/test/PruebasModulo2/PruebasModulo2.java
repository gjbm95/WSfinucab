/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasModulo2;

import BaseDatosDAO.Conexion;
import BaseDatosDAO.DAOPresupuesto;
import BaseDatosDAO.DaoCuenta_Bancaria;
import BaseDatosDAO.DaoTarjeta_Credito;
import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Seguridad;
import Dominio.Cuenta_Bancaria;
import Dominio.Entidad;
import Dominio.SimpleResponse;
import Dominio.Tarjeta_Credito;
import Dominio.Usuario;
import Exceptions.FinUCABException;
import Logica.Comando;
import Logica.FabricaComando;
import Logica.Modulo2.AgregarFallidoException;
import Logica.Modulo2.ConversionFallidaException;
import Logica.Modulo2.EliminarFallidoException;
import Logica.Modulo2.MapaModulo2;
import Logica.Modulo2.ModificarFallidoException;
import Services.Modulo2Resource;
import Services.Modulo2sResource;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Modulo 2 - Modulo de Home Desarrolladores: Garry Jr. Bruno / Erbin Rodriguez
 * / Alejandro Negrin Descripción de la clase: Metodos del servicio web
 * destinados para las funcionalidades de Home y Tarjetas de Credito y Cuentas
 * Bancarias.
 * 
*
 */
public class PruebasModulo2 {

    public PruebasModulo2() {
    }

    @BeforeClass
    public static void setUpClass() throws SQLException {
        Connection con = Conexion.conectarADb();
        Statement st = con.createStatement();
        st.executeUpdate("INSERT INTO usuario(u_id,u_usuario,"
                + "u_password,u_nombre,u_apellido,u_correo,u_pregunta,u_respuesta)"
                + " VALUES (-1,'1PRUEBA','0000','nombreTest','apellidoTest',"
                + "'c@test.com','preguntaTest','respuestaTest');");

        st.executeUpdate("INSERT INTO cuenta_bancaria(ct_id,ct_tipocuenta,"
                + "ct_numcuenta,ct_nombrebanco,ct_saldoactual,usuariou_id)"
                + " VALUES (-1,'PRUEBA','P00000','nombreTest',15,-1);");

        st.executeUpdate("INSERT INTO tarjeta_credito(tc_id,tc_tipo,"
                + "tc_numero,tc_saldo,tc_fechavencimiento,usuariou_id)"
                + " VALUES (-1,'PRUEBA','P0000',15,'2010-10-10',-1);");

        st.executeUpdate("INSERT INTO planificacion("
                + "             pa_nombre, pa_descripcion, pa_monto, pa_fechainicio, pa_fechafin, "
                + "            pa_recurrente, pa_recurrencia, usuariou_id, categoriaca_id, pa_activo)"
                + "    VALUES ( 'plan 1', 'plan 1', 250, to_date('21/11/1995','DD/MM/YYYY'), to_date('21/12/1995','DD/MM/YYYY'), "
                + "            true, 'epe', -1, 1, true);");

        st.executeUpdate("INSERT INTO planificacion("
                + "             pa_nombre, pa_descripcion, pa_monto, pa_fechainicio, pa_fechafin,"
                + "            pa_recurrente, pa_recurrencia, usuariou_id, categoriaca_id, pa_activo)"
                + "    VALUES ( 'plan 2', 'plan 2', 22500, to_date('21/11/1995','DD/MM/YYYY'), to_date('21/12/1995','DD/MM/YYYY'), "
                + "            true, 'epe', -1, 1, true);");

        st.executeUpdate("INSERT INTO planificacion("
                + "             pa_nombre, pa_descripcion, pa_monto, pa_fechainicio, pa_fechafin, "
                + "            pa_recurrente, pa_recurrencia, usuariou_id, categoriaca_id, pa_activo)"
                + "    VALUES ( 'plan 3', 'plan 3', 500, to_date('21/11/1995','DD/MM/YYYY'), to_date('21/12/1995','DD/MM/YYYY'), "
                + "            true, 'epe', -1, 1, true);");

        st.executeUpdate("INSERT INTO pago("
                + "            pg_monto, pg_fecha, pg_descripcion, pg_tipotransaccion, "
                + "            categoriaca_id, usuariou_id)"
                + "    VALUES ( '2000', to_date('21/11/1995','DD/MM/YYYY'), 'pago2', 'ingreso', "
                + "            1, -1);"
                + "INSERT INTO pago("
                + "             pg_monto, pg_fecha, pg_descripcion, pg_tipotransaccion, "
                + "            categoriaca_id, usuariou_id)"
                + "    VALUES ( '1633', to_date('21/11/1995','DD/MM/YYYY'), 'pago3', 'egreso', "
                + "            1, -1);"
                + "INSERT INTO pago("
                + "             pg_monto, pg_fecha, pg_descripcion, pg_tipotransaccion, "
                + "            categoriaca_id, usuariou_id)"
                + "    VALUES ( '1000', to_date('21/11/1995','DD/MM/YYYY'), 'pago4', 'egreso', "
                + "            1, -1);");

        st.executeUpdate("INSERT INTO presupuesto("
                + "             pr_nombre, pr_monto, pr_fecha, pr_clasificacion, pr_duracion, "
                + "            usuariou_id, categoriaca_id)"
                + "    VALUES ( 'pre2', 100, to_date('21/11/1996','DD/MM/YYYY'), 'oo', 5, "
                + "            -1, 1);"
                + "INSERT INTO presupuesto("
                + "             pr_nombre, pr_monto, pr_fecha, pr_clasificacion, pr_duracion, "
                + "            usuariou_id, categoriaca_id)"
                + "    VALUES ( 'pre3', 300, to_date('21/11/1996','DD/MM/YYYY'), 'oo', 5, "
                + "            -1, 1);"
                + "INSERT INTO presupuesto("
                + "             pr_nombre, pr_monto, pr_fecha, pr_clasificacion, pr_duracion, "
                + "            usuariou_id, categoriaca_id)"
                + "    VALUES ( 'pre4', 12500, to_date('21/11/1996','DD/MM/YYYY'), 'oo', 5, "
                + "            -1, 1);");

        con.close();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        Connection con = Conexion.conectarADb();
        Statement st = con.createStatement();
        st.executeUpdate("DELETE FROM cuenta_bancaria WHERE ct_id = -1;");
        st.executeUpdate("DELETE FROM tarjeta_credito WHERE tc_id = -1;");
        st.executeUpdate("DELETE FROM presupuesto WHERE usuariou_id = -1;");
        st.executeUpdate("DELETE FROM planificacion WHERE usuariou_id = -1;");
        st.executeUpdate("DELETE FROM pago WHERE usuariou_id = -1;");
        st.executeUpdate("DELETE FROM usuario WHERE u_id = -1;");
        con.close();
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
            String decodifico = "{ \"u_id\" : \"-1\" , \"u_usuario\" : \"00gjbm\" ,"
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
            String[] data = respuesta.getDescripcion().split(":-:");
            System.out.println(data[0]);
            JsonObject usuarioJSON2 = webservice.stringToJSON(data[0]);
            assertEquals(usuarioJSON2.getString("u_usuario"), usuario.getUsuario());

        } catch (FinUCABException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Prueba encargada de verificar el funcionamiento de convertir cadenas de
     * texto en objetos Json
     */
    @Test
    public void stringToJsonTest() {
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
            assertEquals(usuarioJSON.getString("u_id"), "1");
            assertEquals(usuarioJSON.getString("u_usuario"), "gjbm");
            assertEquals(usuarioJSON.getString("u_nombre"), "Alejandro");
            assertEquals(usuarioJSON.getString("u_apellido"), "Negrin");
            assertEquals(usuarioJSON.getString("u_correo"), "aledavid21@hotmail.com");
            assertEquals(usuarioJSON.getString("u_pregunta"), "Nombre de mi mama");
            assertEquals(usuarioJSON.getString("u_respuesta"), "alejandra");
            assertEquals(usuarioJSON.getString("u_password"), "1509442");

        } catch (ConversionFallidaException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Prueba encargada de verificar el funcionamiento de convertir Jsons en
     * objetos de Tipo tarjeta
     */
    @Test
    public void jsonToTarjetaTest() {
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
            assertEquals(tarjetaJSON.getString("tc_tipo"), "American Express");

        } catch (ConversionFallidaException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento de convertir Jsons en
     * objetos de Tipo tarjeta (Version de Modificar
     */
    @Test
    public void jsonToTarjetaMTest() {
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
            assertEquals(tarjetaJSON.getString("tc_id"), "1");

        } catch (ConversionFallidaException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento de convertir Jsons en
     * objetos de Tipo tarjeta
     */
    @Test
    public void jsonToCuentaTest() {
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
            assertEquals(cuentaJSON.getString("ct_numcuenta"), "76438346499564");

        } catch (ConversionFallidaException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento de convertir Jsons en
     * objetos de Tipo tarjeta (Modificar)
     */
    @Test
    public void jsonToCuentaMTest() {
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
            assertEquals(cuentaJSON.getString("ct_id"), "1");

        } catch (ConversionFallidaException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento del encriptamiento de
     * datos
     */
    @Test
    public void encriptarTest() {
        Seguridad seguridad = Seguridad.obtenerInstancia();
        assertEquals("y2BNqSooOLs=", seguridad.Encriptar("casa"));
    }

    /**
     * Prueba encargada de verificar el funcionamiento del Desencriptamiento de
     * datos
     */
    @Test
    public void DesencriptarTest() {
        Seguridad seguridad = Seguridad.obtenerInstancia();
        assertEquals("casa", seguridad.Desencriptar("y2BNqSooOLs="));
    }

    /**
     * Prueba encargada de verificar el funcionamiento del mapa de Modulo2
     * (Agregar Datos)
     */
    @Test
    public void MapaModuloAgregarTest() {
        MapaModulo2 cache = MapaModulo2.obtenerInstancia();
        cache.setEntidad("prueba", new SimpleResponse("Probando"));

        SimpleResponse resultado = (SimpleResponse) cache.getEntidad("prueba");
        assertEquals("Probando", resultado.getDescripcion());
    }

    /**
     * Prueba encargada de verificar el funcionamiento del mapa de Modulo2
     * (Modificar Datos)
     */
    @Test
    public void MapaModuloModificarTest() {
        MapaModulo2 cache = MapaModulo2.obtenerInstancia();
        cache.setEntidad("prueba", new SimpleResponse("Probando"));
        SimpleResponse resultado = (SimpleResponse) cache.getEntidad("prueba");
        assertEquals("Probando", resultado.getDescripcion());
        cache.actualizarEntidad("prueba", new SimpleResponse("Pasando"));
        resultado = (SimpleResponse) cache.getEntidad("prueba");
        assertEquals("Pasando", resultado.getDescripcion());
    }

    /**
     * Prueba encargada de verificar el funcionamiento del mapa de Modulo2
     * (Eliminar Datos)
     */
    @Test
    public void MapaModuloEliminarTest() {
        MapaModulo2 cache = MapaModulo2.obtenerInstancia();
        cache.setEntidad("prueba", new SimpleResponse("Probando"));
        SimpleResponse resultado = (SimpleResponse) cache.getEntidad("prueba");
        assertEquals("Probando", resultado.getDescripcion());
        cache.eliminarEntidad("prueba");
        assertNull(cache.getEntidad("prueba"));
    }

    /**
     * Prueba encargada de verificar el funcionamiento del mapa de Modulo2
     * (Obtener Datos)
     */
    @Test
    public void MapaModuloObtenerTest() {
        MapaModulo2 cache = MapaModulo2.obtenerInstancia();
        cache.setEntidad("prueba", new SimpleResponse("Probando"));
        SimpleResponse resultado = (SimpleResponse) cache.getEntidad("prueba");
        assertEquals("Probando", resultado.getDescripcion());
        assertNotNull(cache.getEntidad("prueba"));
    }

    /**
     * Prueba encargada de verificar el funcionamiento de la funcion de agregar
     * Tarjeta de credito (Obtener Datos)
     */
    @Test
    public void AgregarTDCDaoTest() {
        Entidad tdc = new Tarjeta_Credito("visa", "10-10-2010", "NUM1234", 1500, 0, 1);
        MapaModulo2 cache = MapaModulo2.obtenerInstancia();
        cache.setEntidad("TarjetaNueva", tdc);
        DaoTarjeta_Credito dao = FabricaDAO.instanciasDaoTarjeta_Credito();
        Tarjeta_Credito td;
        try {
            td = (Tarjeta_Credito) dao.agregar(tdc);
            System.out.println("ID tDC" + tdc.getId());
            assertTrue(td.getId() > 0);
            Connection con = Conexion.conectarADb();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tarjeta_credito "
                    + "where tc_numero = 'NUM1234'");
            assertTrue(rs.next());
            assertEquals(rs.getString("tc_tipo"), "visa");
            assertEquals(rs.getString("tc_numero"), "NUM1234");
        } catch (AgregarFallidoException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento de la funcion de agregar
     * en caso de falla tarjeta de creditos (Obtener Datos)
     */
    @Test
    public void AgregarTDCDaoTestFail() {
        Entidad tdc = new Tarjeta_Credito("visa", "10-10-2010", "P0000", 1500, -1, -1);
        MapaModulo2 cache = MapaModulo2.obtenerInstancia();
        cache.setEntidad("TarjetaNueva", tdc);
        DaoTarjeta_Credito dao = FabricaDAO.instanciasDaoTarjeta_Credito();
        Tarjeta_Credito td;
        try {
            td = (Tarjeta_Credito) dao.agregar(tdc);
            System.out.println("ID tDC" + tdc.getId());
            assertEquals(0, tdc.getId());
            Connection con = Conexion.conectarADb();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tarjeta_credito "
                    + "where tc_numero = 'NUM1234'");
            con.close();
            assertFalse(rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AgregarFallidoException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento de la funcion de
     * Modificar Tarjeta de Credito (Obtener Datos)
     */
    @Test
    public void ModificarTDCDaoTest() {
        Entidad tdc = new Tarjeta_Credito("visa", "2010-10-10", "NUM1234", 1500, -1, -1);
        MapaModulo2 cache = MapaModulo2.obtenerInstancia();
        cache.setEntidad("TarjetaNueva", tdc);
        DaoTarjeta_Credito dao = FabricaDAO.instanciasDaoTarjeta_Credito();
        Tarjeta_Credito td;
        try {
            td = (Tarjeta_Credito) dao.modificar(tdc);
            Connection con = Conexion.conectarADb();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tarjeta_credito "
                    + "where tc_id = -1'");
            assertTrue(rs.next());
            assertEquals(rs.getString("tc_tipo"), "visa");
            assertEquals(rs.getString("tc_numero"), "NUM1234");
            assertEquals(rs.getString("tc_saldo"), 1500);
        } catch (SQLException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ModificarFallidoException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento de la funcion de
     * modificar en caso de falla tarjeta de creditos (Obtener Datos)
     */
    @Test
    public void ModificarTDCDaoTestFail() {
        Entidad tdc = new Tarjeta_Credito("visa", "10-10-2010", "P0000", 1500, -2, -1);
        MapaModulo2 cache = MapaModulo2.obtenerInstancia();
        cache.setEntidad("TarjetaNueva", tdc);
        DaoTarjeta_Credito dao = FabricaDAO.instanciasDaoTarjeta_Credito();
        Tarjeta_Credito td;
        try {
            td = (Tarjeta_Credito) dao.agregar(tdc);
        } catch (AgregarFallidoException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento de la funcion de eliminar
     * tarjeta de credito (Obtener Datos)
     */
    @Test
    public void EliminarTDCDaoTest() throws SQLException {
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO tarjeta_credito(tc_id,tc_tipo,"
                    + "tc_numero,tc_saldo,tc_fechavencimiento,usuariou_id)"
                    + " VALUES (-2,'PRUEBA2','P20000',15,'2010-10-10',-1);");

            ResultSet rs = st.executeQuery("Select * from tarjeta_credito where tc_id = -2");
            assertTrue(rs.next());

            DaoTarjeta_Credito dao = FabricaDAO.instanciasDaoTarjeta_Credito();

            int result = dao.eliminar(rs.getInt("tc_id"));
            assertEquals(1, result);

            rs = st.executeQuery("Select * from tarjeta_credito where tc_id = -2");
            assertFalse(rs.next());
        } catch (EliminarFallidoException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento de la funcion de eliminar
     * cuanta bancaria en caso de fallo (Obtener Datos)
     */
    @Test
    public void EliminarTDCDaoTestFail() throws SQLException {
        ResultSet rs;
        Statement st;
        try {

            DaoCuenta_Bancaria dao = FabricaDAO.instanciasDaoCuenta_Bancaria();

            int result = dao.eliminar(-2);
            assertEquals(0, result);

        } catch (EliminarFallidoException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento de la funcion de agregar
     * cuenta bancaria (Obtener Datos)
     */
    @Test
    public void AgregarCuentaBancariaDaoTest() {
        Entidad cuenta = new Cuenta_Bancaria("corriente", "PR1234", "NOMBRE PRUEBA", 1500, 0, 1);
        MapaModulo2 cache = MapaModulo2.obtenerInstancia();
        cache.setEntidad("CuentaNueva", cuenta);
        DaoCuenta_Bancaria dao = FabricaDAO.instanciasDaoCuenta_Bancaria();
        Cuenta_Bancaria ba;
        try {
            ba = (Cuenta_Bancaria) dao.agregar(cuenta);
            System.out.println("ID " + cuenta.getId());
            assertTrue(ba.getId() > 0);
            Connection con = Conexion.conectarADb();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cuenta_bancaria "
                    + "where ct_numcuenta = 'PR1234'");
            con.close();
            assertTrue(rs.next());
            assertEquals(rs.getString("ct_nombrebanco"), "NOMBRE PRUEBA");
            assertEquals(rs.getString("ct_tipocuenta"), "corriente");
            assertEquals(rs.getString("ct_nombrebanco"), "PR1234");
            assertEquals(rs.getString("ct_numcuenta"), "NOMBRE PRUEBA");

        } catch (AgregarFallidoException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de probar el funcionamiento de la funcion de agregar en
     * caso de error cuenta bancaria (Obtener Datos)
     */
    @Test
    public void AgregarCuentaBancariaDaoTestFail() {
        Entidad cuenta = new Cuenta_Bancaria("corriente", "PR1234", "NOMBRE PRUEBA", 1500, 0, 1);
        MapaModulo2 cache = MapaModulo2.obtenerInstancia();
        cache.setEntidad("CuentaNueva", cuenta);
        DaoCuenta_Bancaria dao = FabricaDAO.instanciasDaoCuenta_Bancaria();
        Cuenta_Bancaria ba;
        try {
            ba = (Cuenta_Bancaria) dao.agregar(cuenta);
            assertEquals(0, ba.getId());
            Connection con = Conexion.conectarADb();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cuenta_bancaria "
                    + "where ct_numcuenta = 'PR1234'");
            con.close();
            assertFalse(rs.next());
        } catch (FinUCABException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento de la funcion de
     * Modificar Tarjeta de Credito (Obtener Datos)
     */
    @Test
    public void ModificarCuentaDaoTest() {
        Entidad cuenta = new Cuenta_Bancaria("corriente", "PR1234", "NOMBRE PRUEBA", 1500, -1, -1);
        MapaModulo2 cache = MapaModulo2.obtenerInstancia();
        cache.setEntidad("CuentaModificada", cuenta);
        DaoCuenta_Bancaria dao = FabricaDAO.instanciasDaoCuenta_Bancaria();
        Cuenta_Bancaria ba;
        try {
            ba = (Cuenta_Bancaria) dao.modificar(cuenta);
            Connection con = Conexion.conectarADb();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cuenta_bancaria "
                    + "where ct_id = -1'");
            assertTrue(rs.next());
            assertEquals(rs.getString("ct_tipocuenta"), "corriente");
            assertEquals(rs.getString("ct_numcuenta"), "PR1234");
            assertEquals(rs.getString("ct_nombrebanco"), 1500);
            assertEquals(rs.getString("ct_saldoactual"), 1500);
        } catch (SQLException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ModificarFallidoException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento de la funcion de
     * modificar en caso de falla tarjeta de creditos (Obtener Datos)
     */
    @Test
    public void ModificarCuentaDaoTestFail() {
        Cuenta_Bancaria cuenta = new Cuenta_Bancaria("corriente", "PR1234", "NOMBRE PRUEBA", 1500, 0, 1);
        MapaModulo2 cache = MapaModulo2.obtenerInstancia();
        cache.setEntidad("CuentaModificada", cuenta);
        DaoCuenta_Bancaria dao = FabricaDAO.instanciasDaoCuenta_Bancaria();
        Cuenta_Bancaria ba;
        try {
            ba = (Cuenta_Bancaria) dao.modificar(cuenta);
        } catch (ModificarFallidoException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento de la funcion de eliminar
     * cuanta bancaria (Obtener Datos)
     */
    @Test
    public void EliminarCuentaDaoTest() throws SQLException {
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO cuenta_bancaria(ct_id,ct_tipocuenta,"
                    + "ct_numcuenta,ct_nombrebanco,ct_saldoactual,usuariou_id)"
                    + " VALUES (-2,'PRUEBA','P200000','nombreTest',15,-1);");

            ResultSet rs = st.executeQuery("Select * from cuenta_bancaria where ct_id = -2");
            assertTrue(rs.next());

            DaoCuenta_Bancaria dao = FabricaDAO.instanciasDaoCuenta_Bancaria();

            int result = dao.eliminar(rs.getInt("ct_id"));
            assertEquals(1, result);

            rs = st.executeQuery("Select * from cuenta_bancaria where ct_id = -2");
            assertFalse(rs.next());
        } catch (EliminarFallidoException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento de la funcion de eliminar
     * cuanta bancaria en caso de fallo (Obtener Datos)
     */
    @Test
    public void EliminarCuentaDaoTestFail() throws SQLException {
        ResultSet rs;
        Statement st;
        try {
            Connection conn = Conexion.conectarADb();
            st = conn.createStatement();
            DaoCuenta_Bancaria dao = FabricaDAO.instanciasDaoCuenta_Bancaria();
            int result = dao.eliminar(-2);
            assertEquals(0, result);

        } catch (EliminarFallidoException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PruebasModulo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Prueba encargada de verificar el funcionamiento de consultar todas las
     * cuentas bancarias de un usuario
     */
    @Test
    public void ConsultarCuentaTest() {

        DaoCuenta_Bancaria ba = FabricaDAO.instanciasDaoCuenta_Bancaria();
        String jsonCuentas = ba.getCuentasXUsuario(-1);
        Modulo2sResource m2 = new Modulo2sResource();
        assertNotEquals(jsonCuentas, "");

    }

    /**
     * Prueba encargada de verificar el funcionamiento de consultar todas las
     * Tarjetas de credito de un usuario
     */
    @Test
    public void ConsultarTDCTest() {

        DaoTarjeta_Credito ba = FabricaDAO.instanciasDaoTarjeta_Credito();
        String jsonTarjetas = ba.getTarjetasXUsuario(-1);
        Modulo2sResource m2 = new Modulo2sResource();
        assertNotEquals(jsonTarjetas, "");

    }

    /**
     * Prueba encargada de verificar el funcionamiento de la función para
     * obtener el saldo de las tarjetas de un usuario
     */
    @Test
    public void getSaldoTDCTest() {

        DaoTarjeta_Credito ba = FabricaDAO.instanciasDaoTarjeta_Credito();
        String saldo = ba.getSaldoTotal(-1);
        assertEquals("15", saldo);

    }

    /**
     * Prueba encargada de verificar el funcionamiento de la función para
     * obtener el saldo de las cuentas de un usuario
     */
    @Test
    public void getSaldoCuentaTest() {

        DaoCuenta_Bancaria ba = FabricaDAO.instanciasDaoCuenta_Bancaria();
        String saldo = ba.getSaldoTotal(-1);
        assertEquals("15", saldo);

    }

  

}
