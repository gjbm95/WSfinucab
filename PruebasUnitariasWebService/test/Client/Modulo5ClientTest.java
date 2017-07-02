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
public class Modulo5ClientTest {
    
    public Modulo5ClientTest() {
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
     * Test of getPruebaJson method, of class Modulo5Client.
     */
    /*
    @Test
    public void testGetPruebaJson() {
        System.out.println("getPruebaJson");
        Modulo5Client instance = new Modulo5Client();
        String expResult = "";
        String result = instance.getPruebaJson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postJson method, of class Modulo5Client.
     */
    /*
    @Test
    public void testPostJson() {
        System.out.println("postJson");
        Object requestEntity = null;
        Modulo5Client instance = new Modulo5Client();
        Response expResult = null;
        Response result = instance.postJson(requestEntity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarPago method, of class Modulo5Client.
     */
    
    @Test
    
    public void testModificarPago() {
       String datosPago ="{\"pg_monto\":1.0,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
        datosPago = URLEncoder.encode(datosPago);
        
        
        
        Modulo5Client instance = new Modulo5Client();
        String result = instance.registrarPago(datosPago);
        String resulta = instance.consultarPago(result);
        String datosPagomodificar ="{\"pg_id\":"+result+",\"pg_monto\":9999.0,\"pg_tipoTransaccion\":\"egreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" Gaste en la universidad\"}";
        String datosPagoMod = URLEncoder.encode(datosPagomodificar);
        String modificar = instance.modificarPago(datosPagoMod);
        String EntiMod =  instance.consultarPago(result);
        String PagoArray[] = EntiMod.split(",");
        String PagoArray2[] = PagoArray[1].split(":");
        assertEquals("9999.0", PagoArray2[1]);
        // TODO review the generated test code and remove the default call to fail.
        
    }


    /**
     * Test of getPruebaDataBase method, of class Modulo5Client.
     */
    /*
    @Test
    public void testGetPruebaDataBase() {
        System.out.println("getPruebaDataBase");
        Modulo5Client instance = new Modulo5Client();
        String expResult = "";
        String result = instance.getPruebaDataBase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarPago method, of class Modulo5Client.
     */
    
    @Test
    public void testRegistrarPagoExito() {
       
        String datosPago ="{\"pg_monto\":2.0,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
        datosPago = URLEncoder.encode(datosPago);
        
        Modulo5Client instance = new Modulo5Client();
        
        String result = instance.registrarPago(datosPago);
        
        String resulta = instance.consultarPago(result);
        String PagoArray[] = resulta.split(",");
        String Pago1Array[] = PagoArray[0].split(":");
        assertEquals(result, Pago1Array[1]);
        
    }

   
     
    
    @Test
   
    public void testConsultarPago() {
       
        String datosPago ="{\"pg_monto\":4.0,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
        
        datosPago = URLEncoder.encode(datosPago);
        
        Modulo5Client instance = new Modulo5Client();
        String result = instance.registrarPago(datosPago);
        String resulta = instance.consultarPago(result);
        String PagoArray[] = resulta.split(",");
        String Pago1Array[] = PagoArray[0].split(":");
        assertEquals(Pago1Array[1],result);
        
    
}
    
     /**
     * Test of VisualizarCategoria method, of class Modulo4Client.
     */
    @Test
    public void testVisualizarCategoria() {
        String datosPago ="{\"pg_monto\":123456789,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
        
        datosPago = URLEncoder.encode(datosPago);
        Modulo5Client instance = new Modulo5Client();
        String result1 = instance.registrarPago(datosPago);
        String datosPago1 ="{\"pg_monto\":987654321,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
       
        datosPago1 = URLEncoder.encode(datosPago1);
        String result2 = instance.registrarPago(datosPago1);
        String resultVisualizar = instance.visualizarPago("1");
        String ArrayCategoria[] = resultVisualizar.split(",");
        String insertadosConc = result1+result2;
        String ArrayCategoriaAux1[] = ArrayCategoria[12].split(":");
        String ArrayCategoriaAux2[] = ArrayCategoria[18].split(":");
        String compararConc = ArrayCategoriaAux1[1]+ArrayCategoriaAux2[1];
       
        //System.out.println(insertadosConc);
        
        assertEquals(insertadosConc, compararConc);
     }
}
