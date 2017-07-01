/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import BaseDatosDAO.Conexion;
import Registro.RegistroBaseDatos;
import Services.Modulo2sResource;
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
public class ClientPUM2Test {
    private static Connection conn;
    public ClientPUM2Test() {
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
    public static void tearDownClass() throws SQLException {
        String query = "delete from usuario where u_id = ?";
        PreparedStatement pr = conn.prepareStatement(query);
        pr.setInt(1, -1);
        pr.execute();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
   /**
     * Test of actualizarDatosUsuario method, of class ClientPUM2.
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
        ClientPUM2 instance = new ClientPUM2();
        String result = instance.actualizarDatosUsuario(datosCuenta);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE u_id = -1");
        rs.next();
        assertEquals(rs.getString("u_usuario"), "UuarioDePrueba");       
    }


    /**
     * Test of close method, of class ClientPUM2.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        ClientPUM2 instance = new ClientPUM2();
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
