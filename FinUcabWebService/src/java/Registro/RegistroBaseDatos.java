/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registro;

import BaseDatosDAO.Seguridad;

/**
 *
 * @author Junior
 */
public class RegistroBaseDatos {
    private static final Seguridad seguridad = Seguridad.obtenerInstancia();
    public static String nombreDB =seguridad.obtenerUsuarioDB();
    public static String contrasenaDB = seguridad.obtenerContrasenaDB();
    public static String url = seguridad.obtenerServerDB();
    
    
    public static final String AGREGAR_PRESUPUESTO = " select agregarpresupuesto (?,?::real,?,?,?,?)";
    public static final String MODIFICAR_PRESUPUESTO = " select modificarpresupuesto (?,?::real,?,?,?,?,?)";
    public static final String LISTAR_PRESUPUESTOS = " select * from obtenerlistapresupuesto(?)";
    public static final String OBTENER_PRESUPUESTO = " select * from obtenerpresupuesto(?)";
    public static final String ELIMINAR_PRESUPUESTO = " select eliminarpresupuesto(?)";
    public static final String VERIFICAR_NOMBRE = " select verificarnombre(?)";

    public static final String AGREGAR_PAGO = "{ call AgregarPago(?,?,?,?) }";
    public static final String MODIFICAR_PAGO = "{ call ModificarPago(?,?,?,?,?) }";
    public static final String LISTAR_PAGOS = "{ call ListaPagos(?) }";
    public static final String OBTENER_PAGO = "{ call ConsultarPago(?) }";
    
    public static final String AGREGAR_USUARIO = "{ call Registrar(?,?,?,?,?,?,?) }";
    public static final String ACTUALIZAR_CLAVE = "{ call ActualizarClave(?,?) }";
    public static final String VERIFICAR_USUARIO = "{ call verificarUsuario(?) }";
    public static final String INICIO_SESION = "{ call iniciarSesion(?,?) }";
    public static final String RECUPERAR_CLAVE = "{ call recuperarclave(?) }";
    
            
    
}
