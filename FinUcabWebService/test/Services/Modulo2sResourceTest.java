/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BaseDatosDAO.Conexion;
import Dominio.Usuario;
import Logica.Comando;
import Logica.FabricaComando;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alejandro Negrin
 */
public class Modulo2sResourceTest {

        private static Connection conn;

    public Modulo2sResourceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws SQLException, ClassNotFoundException {        
        Class.forName("org.postgresql.Driver");      
         conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres"
                ,"postgres"
                ,"desarrollo123");
             String query = "INSERT INTO usuario(u_id,u_usuario,u_nombre,"
                + "u_apellido,u_password,u_pregunta,u_respuesta,u_correo)  "
                + "VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pr = conn.prepareStatement(query);
        pr.setInt(1, -1);
        pr.setString(2, "PRUEBA");
        pr.setString(3, "NOMBRE PRUEBA");
        pr.setString(4, "APELLIDO PRUEBA");
        pr.setString(5, "CLAVE PRUEBA");
        pr.setString(6, "PREGNTA PRUEBA");
        pr.setString(7, "RESPUESTAPRUEBA");
        pr.setString(8, "CORREOPRUEBA");
        pr.execute();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() throws SQLException {
                String query = "delete from usuario where u_id = ?";
        PreparedStatement pr = conn.prepareStatement(query);
        pr.setInt(1, -1);
        pr.execute();
    }

    /**
     * Test of actualizarDatosUsuario method, of class Modulo2sResource.
     */
    @Test
    public void testActualizarDatosUsuario() throws SQLException {
        System.out.println("actualizarDatosUsuario");
        String datosCuenta = "{ \"u_id\" : \"-1\" , \"u_usuario\" : \"UuarioDePrueba\" ,"
                + " \"u_nombre\" : \"Alejandro\""
                + ", \"u_apellido\" : \"Negrin\", \"u_correo\" :"
                + " \"aledavid21@hotmail.com\", "
                + "\"u_pregunta\" : \"Nombre de mi mama\" ,"
                + " \"u_respuesta\" : \"/alejandra\", "
                + "\"u_password\" : \"123456\" }";
        Modulo2sResource instance = new Modulo2sResource();
        String result = instance.actualizarDatosUsuario(datosCuenta);
        assertEquals("1", result);
        System.out.println("hola");
    }


}
