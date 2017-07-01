/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.net.URLEncoder;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Junior
 */
public class Modulo4ClientTest {

    public Modulo4ClientTest() {
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
     * Test of getPruebaJson method, of class Modulo4Client.
     */
    //@Test
    //public void testGetPruebaJson() {
    //System.out.println("getPruebaJson");
    //Modulo4Client instance = new Modulo4Client();
    //String expResult = "";
    //String result = instance.getPruebaJson();
    //assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    //  fail("The test case is a prototype.");
    //}
    /**
     * Test of buscarCategoria method, of class Modulo4Client.
     */
    @Test
    public void testBuscarCategoria() {
        System.out.println("Test Buscar Categoria");
        String datosCategoria = "{ \"c_usuario\" : 1, \"c_nombre\" : \"Prueba2\",\"c_descripcion\" : \"UCAB\",\"c_ingreso\" : true,\"c_estado\" : true }";
        datosCategoria = URLEncoder.encode(datosCategoria);

        Modulo4Client instance = new Modulo4Client();
        String result = instance.registrarCategoria(datosCategoria);
        String resulta = instance.buscarCategoria(result);
        String CategoriaArray[] = resulta.split(",");
        String CategoriaArrayAux[] = CategoriaArray[0].split(":");

            assertEquals(CategoriaArrayAux[1],result );
 
    
    }
    /**
     * Test of postJson method, of class Modulo4Client.
     */
    //@Test
    // public void testPostJson() {
    //System.out.println("postJson");
    //Object requestEntity = null;
    //Modulo4Client instance = new Modulo4Client();
    //Response expResult = null;
    //Response result = instance.postJson(requestEntity);
    //assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    //  fail("The test case is a prototype.");
    //}
    /**
     * Test of eliminarCategoria method, of class Modulo4Client.
     */
    //@Test
    //public void testEliminarCategoria() {
    //System.out.println("eliminarCategoria");
    //String datosCategoria = "";
    //Modulo4Client instance = new Modulo4Client();
    //String expResult = "";
    //String result = instance.eliminarCategoria(datosCategoria);
    //assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    //  fail("The test case is a prototype.");
    //}
    /**
     * Test of getPruebaDataBase method, of class Modulo4Client.
     */
    //@Test
    //public void testGetPruebaDataBase() {
    //System.out.println("getPruebaDataBase");
    //Modulo4Client instance = new Modulo4Client();
    //String expResult = "";
    //String result = instance.getPruebaDataBase();
    //assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    //  fail("The test case is a prototype.");
    //}
    /**
     * Test of modificarCategoria method, of class Modulo4Client.
     */
    @Test
    public void testModificarCategoria() {
        System.out.println("Test Modificar Categoria");
        String datosCategoria = "{ \"c_usuario\" : 1, \"c_nombre\" : \"Prueba3\",\"c_descripcion\" : \"UCAB\",\"c_ingreso\" : true,\"c_estado\" : true }";
        datosCategoria = URLEncoder.encode(datosCategoria);

        Modulo4Client instance = new Modulo4Client();
        String result = instance.registrarCategoria(datosCategoria);
        String resulta = instance.buscarCategoria(result);
        
        String modCategoria = "{ \"c_id\":"+ result+",\"c_usuario\" : 1, \"c_nombre\" : \"PruebaMod\",\"c_descripcion\" : \"UCAB\",\"c_ingreso\" : true,\"c_estado\" : true }";
        modCategoria = URLEncoder.encode(modCategoria);
        String modificar = instance.modificarCategoria(modCategoria);
        
            assertEquals(modCategoria, modificar);

    }
    /**
     * Test of VisualizarCategoria method, of class Modulo4Client.
     */
    //@Test
    //public void testVisualizarCategoria() {
    //  System.out.println("VisualizarCategoria");
    // String datosCategoria = "";
    // Modulo4Client instance = new Modulo4Client();
    //String expResult = "";
    //String result = instance.VisualizarCategoria(datosCategoria);
    //assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    //fail("The test case is a prototype.");
    // }
    /**
     * Test of registrarCategoria method, of class Modulo4Client.
     */
    //@Test
    //public void testRegistrarCategoria() {
    //   System.out.println("registrarCategoria");
    //   String datosCategoria = "";
    //   Modulo4Client instance = new Modulo4Client();
    //   String expResult = "";
    //   String result = instance.registrarCategoria(datosCategoria);
    //  assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    // fail("The test case is a prototype.");
    //}
    /**
     * Test of close method, of class Modulo4Client.
     */
    //@Test
    // public void testClose() {
    //   System.out.println("close");
    // Modulo4Client instance = new Modulo4Client();
    //instance.close();
    // TODO review the generated test code and remove the default call to fail.
    //fail("The test case is a prototype.");
    //}
    @Test
    public void testRegistroCategoria() {
        System.out.println("Test Registrar Categoria");
        String datosCategoria = "{ \"c_usuario\" : 1, \"c_nombre\" : \"Prueba\",\"c_descripcion\" : \"UCAB\",\"c_ingreso\" : true,\"c_estado\" : true }";
        datosCategoria = URLEncoder.encode(datosCategoria);

        Modulo4Client instance = new Modulo4Client();
        String result = instance.registrarCategoria(datosCategoria);
        String resulta = instance.buscarCategoria(result);
        String CategoriaArray[] = resulta.split(",");
        String CategoriaArrayAux[] = CategoriaArray[0].split(":");

        assertEquals(result, CategoriaArrayAux[1]);

    }

}
