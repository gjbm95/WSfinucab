
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
            assertNotNull(resulta);
 
    
    }
    /**
     * Test of eliminarCategoria method, of class Modulo4Client.
     */
    @Test
    public void testEliminarCategoria() {
            System.out.println("Test Eliminar Categoria");
            String datosCategoria = "{ \"c_usuario\" : 1, \"c_nombre\" : \"Prueba8\",\"c_descripcion\" : \"UCAB\",\"c_ingreso\" : true,\"c_estado\" : true }";
            datosCategoria = URLEncoder.encode(datosCategoria);

            Modulo4Client instance = new Modulo4Client();
            String result = instance.registrarCategoria(datosCategoria);
            String eliminar = instance.eliminarCategoria(result);
            String resulta = instance.buscarCategoria(result);
            assertEquals("", resulta);
    
    
    }
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
        String codmodCategoria = URLEncoder.encode(modCategoria);
        
        String modificar = instance.modificarCategoria(codmodCategoria);
        String entidadmodificada = instance.buscarCategoria(result);
        String CategoriaArray[] = entidadmodificada.split(",");
        String CategoriaArrayAux[] = CategoriaArray[3].split(":");
        
            assertNotEquals("PruebaMod", CategoriaArray[1]);

    }
    /**
     * Test of RegistroCategoria method, of class Modulo4Client.
     */
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
    /**
     * Test of VisualizarCategoria method, of class Modulo4Client.
     */
    @Test
    public void testVisualizarCategoria() {
      System.out.println("Test Visualizar Categoria");
        String datosCategoria = "{ \"c_usuario\" : 1, \"c_nombre\" : \"Prueba4\",\"c_descripcion\" : \"UCAB\",\"c_ingreso\" : true,\"c_estado\" : true }";
        datosCategoria = URLEncoder.encode(datosCategoria);
        Modulo4Client instance = new Modulo4Client();
        String result1 = instance.registrarCategoria(datosCategoria);
        String datosCategoria1 = "{ \"c_usuario\" : 1, \"c_nombre\" : \"Prueba5\",\"c_descripcion\" : \"UCAB\",\"c_ingreso\" : true,\"c_estado\" : true }";
        datosCategoria1 = URLEncoder.encode(datosCategoria1);
        String result2 = instance.registrarCategoria(datosCategoria1);
        String resultVisualizar = instance.VisualizarCategoria("1");
        String ArrayCategoria[] = resultVisualizar.split(",");
        String ArrayCategoriaAux1[] = ArrayCategoria[18].split(":");
        String ArrayCategoriaAux2[] = ArrayCategoria[24].split(":");
        String compararConc = ArrayCategoriaAux1[1]+ArrayCategoriaAux2[1];
        String insertadosConc = result1+result2;
        
        assertEquals(insertadosConc, compararConc);
     }
}
