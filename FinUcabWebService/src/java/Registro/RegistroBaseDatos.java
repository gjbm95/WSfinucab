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

}
