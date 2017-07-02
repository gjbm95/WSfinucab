/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasModulo4;

import BaseDatosDAO.DAOCategoria;
import BaseDatosDAO.FabricaDAO;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeffrey
 */
public class PruebasModulo4 {
    
    public PruebasModulo4() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void TestAgregar() {
     
         Entidad categoria = FabricaEntidad.obtenerCategoria(1,"sueldo","suedo del mes",true,true);
         DAOCategoria dao = FabricaDAO.instanciasDaoCategoria();
         dao.agregar(categoria);
         
         
     }
}
