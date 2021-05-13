package com.example.mp07_statson;

import android.os.Environment;

import com.example.mp07_statson.Model.Jugador;
import com.example.mp07_statson.Model.Partido;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerarCSV {

    public File generarCSV(Partido partido, List<Jugador> jugadoresEquipoLocal, List<Jugador> jugadoresEquipoVisitante) throws IOException {

        List<String[]> list = getContent(jugadoresEquipoLocal, jugadoresEquipoVisitante);

        // default all fields are enclosed in double quotes
        // default separator is a comma
        File s = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);


        File file = File.createTempFile("partido_"+partido.nombreEquipoLocal+"_"+partido.nombreEquipoVisitante, ".csv");
        FileWriter fw = new FileWriter(file);
        CSVWriter writer = new CSVWriter(fw);
        writer.writeAll(list);
        writer.close();

        return file;
    }

    private List<String[]> getContent(List<Jugador> jugadoresEquipoLocal, List<Jugador> jugadoresEquipoVisitante) {
        String[] header = {"Dorsal", "Nombre", "Min", "PTS", "TL", "","","T2", "","","T3","","", "Rebotes","","", "Faltas","", "Balones","", "Tapones","",
                "Pasos", "Dobles", "V.T", "As", "Val"};

        String[] header2 = {"", "", "", "", "TLA", "TLI", "TL%", "T2A", "T2I", "T2%", "T3A", "T3I", "T3%", "TOT", "DEF", "OF", "COM", "REC",
                "REC", "PER", "REC", "COM", "", "", "", "", ""};

        List<String[]> list = new ArrayList<>();
        list.add(header);
        list.add(header2);
        for (int i = 0; i < jugadoresEquipoLocal.size(); i++) {
//            int tli= jugadoresEquipoLocal.get(i).t1mas+ jugadoresEquipoLocal.get(i).t1menos;
//            int t2i= jugadoresEquipoLocal.get(i).t2mas+ jugadoresEquipoLocal.get(i).t2menos;
//            int t3i= jugadoresEquipoLocal.get(i).t3mas+ jugadoresEquipoLocal.get(i).t2menos;
//
//            int tlpor = tli / jugadoresEquipoLocal.get(i).t1mas;
//            int t2por = t2i / jugadoresEquipoLocal.get(i).t2mas;
//            int t3por = t3i / jugadoresEquipoLocal.get(i).t3mas;


            String[] jugador = {
                    jugadoresEquipoLocal.get(i).dorsal+"",
                    jugadoresEquipoLocal.get(i).nombre+"", "",
                    jugadoresEquipoLocal.get(i).puntos+"",
                    jugadoresEquipoLocal.get(i).t1mas+"","","",
//                    tli+"",
//                    tlpor+"",
                    jugadoresEquipoLocal.get(i).t2mas+"","","",
//                    t2i+"",
//                    t2por+"",
                    jugadoresEquipoLocal.get(i).t3mas+"","","",
//                    t3i+"",
//                    t3por+"",
                    jugadoresEquipoLocal.get(i).rebotes+"",
                    jugadoresEquipoLocal.get(i).rebotesDef+"",
                    jugadoresEquipoLocal.get(i).rebotesOf+"",
                    jugadoresEquipoLocal.get(i).faltasCometidas+"",
                    jugadoresEquipoLocal.get(i).faltasRecibidas+"",
                    jugadoresEquipoLocal.get(i).robos+"",
                    jugadoresEquipoLocal.get(i).perdidas+"",
                    jugadoresEquipoLocal.get(i).taponesRecibidos+"",
                    jugadoresEquipoLocal.get(i).tapones+"",
                    "","","",
                    jugadoresEquipoLocal.get(i).asistencias+"",
                    ""};
            list.add(jugador);
        }

        list.add(header);
        list.add(header2);
        for (int i = 0; i < jugadoresEquipoVisitante.size(); i++) {
//            int tli=jugadoresEquipoVisitante.get(i).t1mas+jugadoresEquipoVisitante.get(i).t1menos;
//            int t2i=jugadoresEquipoVisitante.get(i).t2mas+jugadoresEquipoVisitante.get(i).t2menos;
//            int t3i=jugadoresEquipoVisitante.get(i).t3mas+jugadoresEquipoVisitante.get(i).t2menos;
//
//            int tlpor = tli / jugadoresEquipoVisitante.get(i).t1mas;
//            int t2por = t2i / jugadoresEquipoVisitante.get(i).t2mas;
//            int t3por = t3i / jugadoresEquipoVisitante.get(i).t3mas;


            String[] jugador = {
                    jugadoresEquipoVisitante.get(i).dorsal+"",
                    jugadoresEquipoVisitante.get(i).nombre+"", "",
                    jugadoresEquipoVisitante.get(i).puntos+"",
                    jugadoresEquipoVisitante.get(i).t1mas+"","","",
//                    tli+"",
//                    tlpor+"",
                    jugadoresEquipoVisitante.get(i).t2mas+"","","",
//                    t2i+"",
//                    t2por+"",
                    jugadoresEquipoVisitante.get(i).t3mas+"","","",
//                    t3i+"",
//                    t3por+"",
                    jugadoresEquipoVisitante.get(i).rebotes+"",
                    jugadoresEquipoVisitante.get(i).rebotesDef+"",
                    jugadoresEquipoVisitante.get(i).rebotesOf+"",
                    jugadoresEquipoVisitante.get(i).faltasCometidas+"",
                    jugadoresEquipoVisitante.get(i).faltasRecibidas+"",
                    jugadoresEquipoVisitante.get(i).robos+"",
                    jugadoresEquipoVisitante.get(i).perdidas+"",
                    jugadoresEquipoVisitante.get(i).taponesRecibidos+"",
                    jugadoresEquipoVisitante.get(i).tapones+"",
                    "","","",
                    jugadoresEquipoVisitante.get(i).asistencias+"",
                    ""};
            list.add(jugador);
        }
        return list;
    }
}