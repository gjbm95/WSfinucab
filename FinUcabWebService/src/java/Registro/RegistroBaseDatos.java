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
}
