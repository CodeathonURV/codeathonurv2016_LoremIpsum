package com.codeathonurv2016.loremipsum.welcomeurv;

import java.util.Vector;

/**
 * Created by david on 14/2/16.
 */
public class AlmacenEventosArray implements AlmacenEventos{
    private Vector puntuaciones;
    public AlmacenEventosArray() {
        puntuaciones= new Vector();
        puntuaciones.add("Evento 1");
        puntuaciones.add("Evento 2");
        puntuaciones.add("Evento 3");
    }
    public void guardarPuntuacion(int puntos, String nombre, long fecha) {
        puntuaciones.add(0, puntos + " "+ nombre);
    }
    public Vector listaEventos(int cantidad) {
        return  puntuaciones;
    }
}
