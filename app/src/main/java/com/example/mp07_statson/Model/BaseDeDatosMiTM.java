package com.example.mp07_statson.Model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import java.util.List;

/* https://developer.android.com/training/data-storage/room */
@Database(entities = {Jugador.class, Equipo.class}, version = 1, exportSchema = false)
public abstract class BaseDeDatosMiTM extends RoomDatabase{

    private static volatile BaseDeDatosMiTM db;
    public abstract JugadoresDao obtenerJugadoresDao();

    public static BaseDeDatosMiTM getInstance(final Context context){
        if (db==null){
            synchronized (BaseDeDatosMiTM.class){
                if (db==null){
                    db=Room.databaseBuilder(context, BaseDeDatosMiTM.class,"app.dbb")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return db;
    }

    @Dao
    interface JugadoresDao{
        @Insert
        void insertar (Jugador jugador);

        @Query("SELECT * FROM Jugador WHERE idEquipo=0")
        LiveData<List<Jugador>> obtenerLocal();

        @Query("SELECT * FROM Jugador")
        LiveData<List<Jugador>> obtenerVisitante();

        @Delete
        void delete(Jugador jugador);

        @Update
        void actualizar (Jugador jugador);
    }

    @Dao
    interface EquiposDao{
        @Insert
        void insertar (Equipo equipo);

        @Query("SELECT * FROM Equipo")
        LiveData<List<Equipo>> obtener();

        @Delete
        void delete (Equipo equipo);

        @Update
        void actualizar (Equipo equipo);
    }
}
