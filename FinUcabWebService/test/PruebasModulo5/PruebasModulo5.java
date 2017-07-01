/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasModulo5;

import BaseDatosDAO.DAOPago;
import BaseDatosDAO.Interfaces.IDAOPago;
import BaseDatosDAO.Singleton.SingletonDAOPago;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import Dominio.Pago;
import Exceptions.FinUCABException;
import Logica.Modulo5.ConsultarPagoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonArray;
import javax.json.JsonObject;
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
public class PruebasModulo5 {
    
    public PruebasModulo5() {
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
     * Test of agregar method, of class DAOPago.
     */
    @Test
    public void testAgregar() {
        try {
            System.out.println("agregar");
            Entidad e = null;
            
            e=  FabricaEntidad.obtenerPago( 1, "prueba", 200, "ingreso");
            
            System.out.println("ando aca");
            
            
            IDAOPago dao = SingletonDAOPago.getInstance();
            System.out.println(e+"holaaa");
            dao.agregar(e);
            System.out.println("entre2");
            Entidad resultado = dao.consultar(10);
            assertEquals(e, resultado);
        } catch (FinUCABException ex) {
            Logger.getLogger(PruebasModulo5.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 
    @Test
    public void testConsultar() {
        try {
            //System.out.println("consultar");
            int idPago = 1;
            
            
            DAOPago instance = new DAOPago();
            Entidad expResult = null;
            Entidad result = instance.consultar(1);
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (ConsultarPagoException ex) {
            Logger.getLogger(PruebasModulo5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    @Test
    public void testModificar() {
        //System.out.println("modificar");
        Entidad e = null;
        DAOPago instance = new DAOPago();
        Entidad expResult = null;
        Entidad result = instance.modificar(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testConsultar() {
        //System.out.println("consultar");
        int idPago = 0;
        DAOPago instance = new DAOPago();
        Entidad expResult = null;
        Entidad result = instance.consultar(idPago);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testConsultarTodos() {
        //System.out.println("consultarTodos");
        int idUsuario = 0;
        DAOPago instance = new DAOPago();
        ListaEntidad expResult = null;
        ListaEntidad result = instance.consultarTodos(idUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    @Test
    public void testGetUltimosPagosXUsuario() {
        //System.out.println("getUltimosPagosXUsuario");
        int id = 0;
        DAOPago instance = new DAOPago();
        JsonArray expResult = null;
        JsonArray result = instance.getUltimosPagosXUsuario(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testGetBalance() {
        //System.out.println("getBalance");
        int id = 0;
        DAOPago instance = new DAOPago();
        JsonObject expResult = null;
        JsonObject result = instance.getBalance(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
