package com.example.mp07_statson.ViewModel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mp07_statson.Model.Jugador;
import com.example.mp07_statson.Model.JugadoresMiTMRepositorio;

import java.util.List;

public class JugadoresMiTMViewModel extends AndroidViewModel {
    private final JugadoresMiTMRepositorio jugadoresRepositorio;


    public JugadoresMiTMViewModel(@NonNull Application application) {
        super(application);

        jugadoresRepositorio = new JugadoresMiTMRepositorio(application);
    }

    public void insertar(String nombre, String dorsal, Uri imagenSeleccionada) {
        jugadoresRepositorio.insertar(nombre, dorsal, imagenSeleccionada);
    }

    public void delete(Jugador jugador) {
        jugadoresRepositorio.delete(jugador);
    }

    public LiveData<List<Jugador>> obtener() {
        return jugadoresRepositorio.obtener();
    }


    MutableLiveData<Jugador> jugadorMutableLiveData = new MutableLiveData<>();
    public void seleccionar(Jugador jugador){
        jugadorMutableLiveData.setValue(jugador);
    }

    public MutableLiveData<Jugador> seleccionado(){
        return jugadorMutableLiveData;
    }

}