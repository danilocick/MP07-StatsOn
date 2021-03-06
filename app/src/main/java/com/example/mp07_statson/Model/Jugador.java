package com.example.mp07_statson.Model;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Jugador {

    public String idJugador;

    public String nombre;
    public int segundos_jugados;
    public int minutos_jugados;
    public String min;
    public int dorsal;
    public String imagen;
    public int puntos, rebotes, asistencias, robos, perdidas, tapones,
            t1mas, t1menos, t2mas, t2menos, t3mas, t3menos,
            rebotesDef, rebotesOf, faltasRecibidas, faltasCometidas, taponesRecibidos;

    public boolean starter;


    public Jugador(String nombre, int dorsal, String imagen) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.imagen = imagen;
    }

    public Jugador() {
    }

    public Jugador(String idJugador, String nombre, int dorsal, String imagen, int puntos, int rebotes, int asistencias, int robos, int perdidas, int tapones, int t1mas, int t1menos, int t2mas, int t2menos, int t3mas, int t3menos, int rebotesDef, int rebotesOf, int faltasRecibidas, int faltasCometidas, int taponesRecibidos, boolean starter) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.imagen = imagen;
        this.puntos = puntos;
        this.rebotes = rebotes;
        this.asistencias = asistencias;
        this.robos = robos;
        this.perdidas = perdidas;
        this.tapones = tapones;
        this.t1mas = t1mas;
        this.t1menos = t1menos;
        this.t2mas = t2mas;
        this.t2menos = t2menos;
        this.t3mas = t3mas;
        this.t3menos = t3menos;
        this.rebotesDef = rebotesDef;
        this.rebotesOf = rebotesOf;
        this.faltasRecibidas = faltasRecibidas;
        this.faltasCometidas = faltasCometidas;
        this.taponesRecibidos = taponesRecibidos;
        this.starter = starter;
    }

    public Jugador(Jugador jugador) {
    }

    public void reiniciar(){
        puntos = 0;
        rebotes = 0;
        asistencias = 0;
        robos = 0;
        perdidas = 0;
        tapones = 0;
        taponesRecibidos = 0;
        t1mas = 0;
        t1menos = 0;
        t2mas = 0;
        t2menos = 0;
        t3mas = 0;
        t3menos = 0;
        rebotesDef = 0;
        rebotesOf = 0;
        faltasRecibidas = 0;
        faltasCometidas = 0;
    }

    public Map<String, Object> toHashMap(Jugador j){
        Map<String, Object> hash = new HashMap<>();

        hash.put("puntos",j.puntos);
        hash.put("rebotes",j.rebotes);
        hash.put("asistencias",j.asistencias);
        hash.put("robos",j.robos);
        hash.put("perdidas",j.perdidas);
        hash.put("tapones",j.tapones);
        hash.put("t1mas",j.t1mas);
        hash.put("t1menos",j.t1menos);
        hash.put("t2mas",j.t2mas);
        hash.put("t2menos",j.t2menos);
        hash.put("t3mas",j.t3mas);
        hash.put("t3menos",j.t3menos);
        hash.put("rebotesDef",j.rebotesDef);
        hash.put("rebotesOf",j.rebotesOf);
        hash.put("faltasRecibidas",j.faltasRecibidas);
        hash.put("faltasCometidas",j.faltasCometidas);
        hash.put("taponesRecibidos",j.taponesRecibidos);
        return hash;
    }

    @NonNull
    public String getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(String idJugador) {
        this.idJugador = idJugador;
    }
    public int getDorsal() {
        return dorsal;
    }
    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public boolean isStarter() {
        return starter;
    }
    public void setStarter(boolean starter) {
        this.starter = starter;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getRebotes() {
        return rebotes;
    }
    public void setRebotes(int rebotes) {
        this.rebotes = rebotes;
    }

    public int getAsistencias() {
        return asistencias;
    }
    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getRobos() {
        return robos;
    }
    public void setRobos(int robos) {
        this.robos = robos;
    }

    public int getPerdidas() {
        return perdidas;
    }
    public void setPerdidas(int perdidas) {
        this.perdidas = perdidas;
    }

    public int getTapones() {
        return tapones;
    }
    public void setTapones(int tapones) {
        this.tapones = tapones;
    }

    public int getT1mas() {
        return t1mas;
    }
    public void setT1mas(int t1mas) {
        this.t1mas = t1mas;
    }

    public int getT1menos() {
        return t1menos;
    }
    public void setT1menos(int t1menos) {
        this.t1menos = t1menos;
    }

    public int getT2mas() {
        return t2mas;
    }
    public void setT2mas(int t2mas) {
        this.t2mas = t2mas;
    }

    public int getT2menos() {
        return t2menos;
    }
    public void setT2menos(int t2menos) {
        this.t2menos = t2menos;
    }

    public int getT3mas() {
        return t3mas;
    }
    public void setT3mas(int t3mas) {
        this.t3mas = t3mas;
    }

    public int getT3menos() {
        return t3menos;
    }
    public void setT3menos(int t3menos) {
        this.t3menos = t3menos;
    }

    public int getRebotesDef() {
        return rebotesDef;
    }
    public void setRebotesDef(int rebotesDef) {
        this.rebotesDef = rebotesDef;
    }

    public int getRebotesOf() {
        return rebotesOf;
    }
    public void setRebotesOf(int rebotesOf) {
        this.rebotesOf = rebotesOf;
    }

    public int getFaltasRecibidas() {
        return faltasRecibidas;
    }
    public void setFaltasRecibidas(int faltasRecibidas) {
        this.faltasRecibidas = faltasRecibidas;
    }

    public int getFaltasCometidas() {
        return faltasCometidas;
    }
    public void setFaltasCometidas(int faltasCometidas) {
        this.faltasCometidas = faltasCometidas;
    }

    public int getTaponesRecibidos() {
        return taponesRecibidos;
    }
    public void setTaponesRecibidos(int taponesRecibidos) {
        this.taponesRecibidos = taponesRecibidos;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "idJugador='" + idJugador + '\'' +
                ", nombre='" + nombre + '\'' +
                ", segundos_jugados=" + segundos_jugados +
                ", minutos_jugados='" + minutos_jugados + '\'' +
                ", dorsal=" + dorsal +
                ", imagen='" + imagen + '\'' +
                ", puntos=" + puntos +
                ", rebotes=" + rebotes +
                ", asistencias=" + asistencias +
                ", robos=" + robos +
                ", perdidas=" + perdidas +
                ", tapones=" + tapones +
                ", t1mas=" + t1mas +
                ", t1menos=" + t1menos +
                ", t2mas=" + t2mas +
                ", t2menos=" + t2menos +
                ", t3mas=" + t3mas +
                ", t3menos=" + t3menos +
                ", rebotesDef=" + rebotesDef +
                ", rebotesOf=" + rebotesOf +
                ", faltasRecibidas=" + faltasRecibidas +
                ", faltasCometidas=" + faltasCometidas +
                ", taponesRecibidos=" + taponesRecibidos +
                ", starter=" + starter +
                '}';
    }
}
