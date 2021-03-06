package com.example.mp07_statson;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mp07_statson.Model.FirebaseVar;
import com.example.mp07_statson.Model.Jugador;
import com.example.mp07_statson.databinding.FragmentEquipoB2Binding;
import com.example.mp07_statson.databinding.ViewholderJugadorEquipoABinding;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class equipoBFragment extends BaseFragment {

    private FragmentEquipoB2Binding binding;
    int contador;
    JugadorAdapter jugadorAdapter = new JugadorAdapter();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentEquipoB2Binding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db.collection(FirebaseVar.USUARIOS).document(auth.getUid()).collection(FirebaseVar.EQUIPOS).document(viewmodel.idEquipoVisitante).collection(FirebaseVar.JUGADORES).orderBy("dorsal").addSnapshotListener((value, error) -> {
            List<Jugador> jugadors = new ArrayList<>();
            for(DocumentSnapshot documentSnapshot: value){
                jugadors.add(documentSnapshot.toObject(Jugador.class));
            }
            jugadorAdapter.establecerjugadores(jugadors);
            partidoviewmodel.jugadoresEquipoVisitante = new ArrayList<>();
            partidoviewmodel.jugadoresEquipoVisitante = jugadors;
        });

        binding.listaJugadoresTeamB.setAdapter(jugadorAdapter);

        binding.listaJugadoresTeamB.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent != null && view != null) {

                    int itemPosition = parent.getChildAdapterPosition(view);
                    int totalCount = parent.getAdapter().getItemCount();

                    if (itemPosition >= 0) {
                        outRect.bottom = 24;
                        outRect.right = 24;
                    }
                }
            }
        });
    }

    class JugadorAdapter extends RecyclerView.Adapter<JugadorViewHolder>{
        List<Jugador> jugadorList;

        @NonNull
        @Override
        public JugadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new JugadorViewHolder(ViewholderJugadorEquipoABinding.inflate(getLayoutInflater()), parent, false);
        }

        @Override
        public void onBindViewHolder(@NonNull JugadorViewHolder holder, int position) {
            Jugador jugador = jugadorList.get(position);
            Glide.with(holder.itemView).load(jugador.imagen).into(holder.binding.imagenJugador);
            holder.binding.nombreJugador.setText(jugador.nombre);
            holder.binding.dorsalJugador.setText(String.valueOf(jugador.dorsal));
            holder.binding.background.setOnClickListener(v->{
                if(!jugador.starter && contador<5) {
                    //holder.binding.background.setBackgroundColor(Color.rgb(200,0,0));
                    Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.boton_rojo);
                    holder.binding.background.setBackground(drawable);
                    holder.binding.dorsalJugador.setTextColor(Color.WHITE);
                    holder.binding.nombreJugador.setTextColor(Color.WHITE);
                    holder.binding.imagenJugador.setBorderColor(Color.WHITE);
                    jugador.starter = true;
                    contador++;
                }else if(jugador.starter){
                    Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.boton_blanco);
                    holder.binding.background.setBackground(drawable);
                    holder.binding.dorsalJugador.setTextColor(Color.BLACK);
                    holder.binding.nombreJugador.setTextColor(Color.BLACK);
                    holder.binding.imagenJugador.setBorderColor(Color.BLACK);
                    jugador.starter = false;
                    contador--;
                }

            });
        }

        @Override
        public int getItemCount() {
            return jugadorList == null ? 0 : jugadorList.size();
        }

        void establecerjugadores(List<Jugador> jugadorList){
            this.jugadorList = jugadorList;
            notifyDataSetChanged();
        }
    }

    //clase para acceder a los campos de viewholder_jugador_miteam
    static class JugadorViewHolder extends RecyclerView.ViewHolder{
        ViewholderJugadorEquipoABinding binding;

        public JugadorViewHolder(@NonNull ViewholderJugadorEquipoABinding binding, ViewGroup parent, boolean b){
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}