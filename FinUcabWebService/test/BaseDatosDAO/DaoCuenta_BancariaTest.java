/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import Dominio.Entidad;
import Dominio.ListaEntidad;
import Logica.Modulo2.AgregarFallidoException;
import Logica.Modulo2.EliminarFallidoException;
import Logica.Modulo2.ModificarFallidoException;
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
public class DaoCuenta_BancariaTest {
    
    public DaoCuenta_BancariaTest() {
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
     * Test of agregar method, of class DaoCuenta_Bancaria.
     */
    @Test
    public void testAgregar() throws AgregarFallidoException {
        System.out.println("agregar");
        Entidad e = null;
        DaoCuenta_Bancaria instance = new DaoCuenta_Bancaria();
        Entidad expResult = null;
        Entidad result = instance.agregar(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificar method, of class DaoCuenta_Bancaria.
     */
    @Test
    public void testModificar() throws ModificarFallidoException {
        System.out.println("modificar");
        Entidad e = null;
        DaoCuenta_Bancaria instance = new DaoCuenta_Bancaria();
        Entidad expResult = null;
        Entidad result = instance.modificar(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consultar method, of class DaoCuenta_Bancaria.
     */
    @Test
    public void testConsultar() {
        System.out.println("consultar");
        int id = 0;
        DaoCuenta_Bancaria instance = new DaoCuenta_Bancaria();
        Entidad expResult = null;
        Entidad result = instance.consultar(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminar method, of class DaoCuenta_Bancaria.
     */
    @Test
    public void testEliminar() throws EliminarFallidoException {
        System.out.println("eliminar");
        int id = 0;
        DaoCuenta_Bancaria instance = new DaoCuenta_Bancaria();
        int expResult = 0;
        int result = instance.eliminar(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCuentasXUsuario method, of class DaoCuenta_Bancaria.
     */
    @Test
    public void testGetCuentasXUsuario() {
        System.out.println("getCuentasXUsuario");
        int id = 0;
        DaoCuenta_Bancaria instance = new DaoCuenta_Bancaria();
        String expResult = "";
        String result = instance.getCuentasXUsuario(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSaldoTotal method, of class DaoCuenta_Bancaria.
     */
    @Test
    public void testGetSaldoTotal() {
        System.out.println("getSaldoTotal");
        int id = 0;
        DaoCuenta_Bancaria instance = new DaoCuenta_Bancaria();
        String expResult = "";
        String result = instance.getSaldoTotal(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consultarTodos method, of class DaoCuenta_Bancaria.
     */
    @Test
    public void testConsultarTodos() {
        System.out.println("consultarTodos");
        int idUsuario = 0;
        DaoCuenta_Bancaria instance = new DaoCuenta_Bancaria();
        ListaEntidad expResult = null;
        ListaEntidad result = instance.consultarTodos(idUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
