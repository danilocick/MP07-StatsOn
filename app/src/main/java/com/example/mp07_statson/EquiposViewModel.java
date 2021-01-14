package com.example.mp07_statson;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class EquiposViewModel extends AndroidViewModel {
    private final EquiposRepositorio equiposRepositorio;


    public EquiposViewModel(@NonNull Application application) {
        super(application);

        equiposRepositorio = new EquiposRepositorio(application);
    }

    public void insertar(int idEquipo, String nombre) {
        equiposRepositorio.insertar(idEquipo,nombre);
    }

    public void delete(Equipo equipo) {
        equiposRepositorio.delete(equipo);
    }

    public LiveData<List<Equipo>> obtener() {
        return equiposRepositorio.obtener();
    }


    MutableLiveData<Equipo> jugadorMutableLiveData = new MutableLiveData<>();
    void seleccionar(Equipo equipo){
        jugadorMutableLiveData.setValue(equipo);
    }

    MutableLiveData<Equipo> seleccionado(){
        return jugadorMutableLiveData;
    }

}
