package com.example.mp07_statson;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mp07_statson.Model.Equipo;
import com.example.mp07_statson.Model.FirebaseVar;
import com.example.mp07_statson.Model.Jugador;
import com.example.mp07_statson.Model.Partido;
import com.example.mp07_statson.databinding.FragmentListaPartidosBinding;
import com.example.mp07_statson.databinding.ViewholderPartidoBinding;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tyrantgit.explosionfield.ExplosionField;


public class ListaPartidosFragment extends BaseFragment {

    private FragmentListaPartidosBinding binding;
    private ExplosionField mExplosionField;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentListaPartidosBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mExplosionField = ExplosionField.attach2Window(requireActivity());

        PartidosbdAdapter partidosbdAdapter = new PartidosbdAdapter();
        db.collection(FirebaseVar.USUARIOS).document(auth.getUid()).collection(FirebaseVar.PARTIDOS)
                .orderBy("fecha",Query.Direction.DESCENDING).addSnapshotListener((value, error) -> {
                    List<Partido> partidos = new ArrayList<>();
                    for(DocumentSnapshot documentSnapshot: value){
                        partidos.add(documentSnapshot.toObject(Partido.class));
                    }
                    partidosbdAdapter.establecerPartidoList(partidos);
                });
        binding.listaPartidos.setAdapter(partidosbdAdapter);

        viewmodel.actualizarEquipoLocal.observe(getViewLifecycleOwner(), aBoolean -> {

        });
        viewmodel.actualizarEquipoVisitante.observe(getViewLifecycleOwner(), aBoolean -> {

        });

        binding.botonComeBackPartidos.setOnClickListener(view1 -> nav.popBackStack());
    }

    public class PartidosbdAdapter extends RecyclerView.Adapter<PartidoViewHolder>{
        List<Partido> partidosList;

        @NonNull
        @Override
        public PartidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PartidoViewHolder(ViewholderPartidoBinding.inflate(getLayoutInflater()), parent, false);
        }

        @Override
        public void onBindViewHolder(@NonNull PartidoViewHolder holder, int position) {
            Partido partido = partidosList.get(position);

            holder.binding.puntosLocal.setText(String.valueOf(partido.puntosLocal));
            holder.binding.nombreLocal.setText(partido.nombreEquipoLocal);
            holder.binding.nombreVisitante.setText(partido.nombreEquipoVisitante);
            holder.binding.puntosVisitante.setText(String.valueOf(partido.puntosVisitante));
            Glide.with(requireView()).load(partido.imagenEquipoLocal).into(holder.binding.fotoLocal);
            Glide.with(requireView()).load(partido.imagenEquipoVisitante).into(holder.binding.fotoVisitante);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM");
            String strDate = dateFormat.format(partido.fecha);
            holder.binding.fecha.setText(strDate);

            holder.binding.recycler.setOnLongClickListener(v->{

                createDialog(partido, v);
                return false;
            });

            holder.binding.recycler.setOnClickListener(view ->{
                db.collection(FirebaseVar.USUARIOS).document(auth.getUid()).collection(FirebaseVar.PARTIDOS).document(partido.idPartido).collection(FirebaseVar.JUGADORESLOCALES)
                    .orderBy("dorsal").addSnapshotListener((value, error) -> {
                    List<Jugador> jugadors = new ArrayList<>();
                        for(DocumentSnapshot documentSnapshot: value){
                            jugadors.add(documentSnapshot.toObject(Jugador.class));
                        }
                        partidoviewmodel.jugadoresEquipoLocal = new ArrayList<>();
                        partidoviewmodel.jugadoresEquipoLocal = jugadors;

                    db.collection(FirebaseVar.USUARIOS).document(auth.getUid()).collection(FirebaseVar.PARTIDOS).document(partido.idPartido).collection(FirebaseVar.JUGADORESVISITANTES)
                            .orderBy("dorsal").addSnapshotListener((value2, error2) -> {
                        List<Jugador> jugadorsV = new ArrayList<>();
                        for(DocumentSnapshot documentSnapshot: value2){
                            jugadorsV.add(documentSnapshot.toObject(Jugador.class));
                        }
                        partidoviewmodel.jugadoresEquipoVisitante = new ArrayList<>();
                        partidoviewmodel.jugadoresEquipoVisitante = jugadorsV;
                        viewmodel.partido = partido;
                        nav.navigate(R.id.action_listaPartidosFragment_to_partidoFragment);
                    });

                });
            });
        }

        @Override
        public int getItemCount() {
            return partidosList == null ? 0 : partidosList.size();
        }

        public void establecerPartidoList(List<Partido> partidosList) {
            this.partidosList = partidosList;
            notifyDataSetChanged();
        }

        public Partido obtenerPartido (int posicion){
            return partidosList.get(posicion);
        }
    }

    private void createDialog(Partido partido, View v) {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(requireActivity());
        alertDlg.setMessage("¿Estás seguro que deseas eliminar el partido?");
        alertDlg.setCancelable(false);

        alertDlg.setPositiveButton("Si", (dialog, which) ->{
            db.collection(FirebaseVar.USUARIOS).document(auth.getUid()).collection(FirebaseVar.PARTIDOS).document(partido.idPartido).delete();
            mExplosionField.explode(v);
        });

        alertDlg.setNegativeButton("No", (dialog, which) -> {});

        alertDlg.show();
    }

    static class PartidoViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderPartidoBinding binding;

        public PartidoViewHolder(ViewholderPartidoBinding binding, ViewGroup parent, boolean b) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}