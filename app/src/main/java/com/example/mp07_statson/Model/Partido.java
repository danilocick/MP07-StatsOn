package com.example.mp07_statson.Model;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Partido {
    @PrimaryKey(autoGenerate = true)
    public String idPartido;
    public String nombreEquipoLocal;
    public String nombreEquipoVisitante;

    public String imagenEquipoLocal;
    public String imagenEquipoVisitante;


    public int puntosLocal, rebotesLocal, asistenciasLocal, robosLocal, perdidasLocal,
            t1masLocal, t1menosLocal, t2masLocal, t2menosLocal, t3masLocal, t3menosLocal,
            rebotesDefLocal, rebotesOfLocal, faltasRecibidasLocal, faltasCometidasLocal,
            taponesRealizadosLocal, taponesRecibidosLocal;

    public int puntosVisitante, rebotesVisitante, asistenciasVisitante, robosVisitante, perdidasVisitante,
            t1masVisitante, t1menosVisitante, t2masVisitante, t2menosVisitante, t3masVisitante, t3menosVisitante,
            rebotesDefVisitante, rebotesOfVisitante, faltasRecibidasVisitante, faltasCometidasVisitante,
            taponesRealizadosVisitante, taponesRecibidosVisitante;

    public int puntosLocalPrimerCuarto,puntosLocalSegundoCuarto,puntosLocalTercerCuarto,puntosLocalQuartoCuarto;
    public int puntosVisitantePrimerCuarto,puntosVisitanteSegundoCuarto,puntosVisitenteTercerCuarto,puntosVisitanteQuartoCuarto;

    public Partido(String idLocal, String idVisitante, int puntosLocal, int puntosVisitante) {
        this.nombreEquipoLocal = idLocal;
        this.nombreEquipoVisitante = idVisitante;
        this.puntosLocal = puntosLocal;
        this.puntosVisitante = puntosVisitante;
    }

    public Partido() {
    }
}