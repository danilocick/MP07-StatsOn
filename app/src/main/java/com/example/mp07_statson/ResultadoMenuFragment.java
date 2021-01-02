package com.example.mp07_statson;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.mp07_statson.databinding.FragmentResultadoMenuBinding;
import com.example.mp07_statson.databinding.ViewholderJugadorMiTeamBinding;

import java.util.List;

public class ResultadoMenuFragment extends Fragment {

    private NavController navController;
    private FragmentResultadoMenuBinding binding;
    private JugadoresMiTMViewModel jugadoresViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentResultadoMenuBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        jugadoresViewModel = new ViewModelProvider(requireActivity()).get(JugadoresMiTMViewModel.class);

        //ComeBack
        binding.botonComeBackMiTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_resultadoMenuFragment_to_menuFragment);
            }
        });

        //Ir anyadirjugador
        binding.botonanyadirjugadorMiTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_resultadoMenuFragment_to_addJugadorMTFragment);
            }
        });


        //obtener datos de los jugadores
        JugadoresAdapter jugadoresAdapter = new JugadoresAdapter();
        binding.listaJugadoresMiTM.setAdapter(jugadoresAdapter);

        jugadoresViewModel.obtener().observe(getViewLifecycleOwner(), jugadors -> {
            jugadoresAdapter.establecerJugadorList(jugadors);
        });


    }

    //adaptador bd
    class JugadoresAdapter extends RecyclerView.Adapter<JugadorViewHolder>{

        List<Jugador> jugadorList;
        @NonNull
        @Override
        public JugadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new JugadorViewHolder(ViewholderJugadorMiTeamBinding.inflate(getLayoutInflater()), parent, false);
        }

        @Override
        public void onBindViewHolder(@NonNull JugadorViewHolder holder, int position) {
            Jugador jugador = jugadorList.get(position);
            Glide.with(holder.itemView).load(jugador.imagen).into(holder.binding.imagenJugadorMiTeam);
            holder.binding.nombreJugador.setText(jugador.nombre);
            holder.binding.dorsalJugador.setText(jugador.dorsal);
        }

        @Override
        public int getItemCount() {
            return jugadorList == null ? 0 : jugadorList.size();
        }

        void establecerJugadorList(List<Jugador> jugadorList){
            this.jugadorList=jugadorList;
            notifyDataSetChanged();
        }
    }


    //clase para acceder a los campos de viewholder_jugador_miteam
    class JugadorViewHolder extends RecyclerView.ViewHolder{
        ViewholderJugadorMiTeamBinding binding;

        public JugadorViewHolder(@NonNull ViewholderJugadorMiTeamBinding binding, ViewGroup parent, boolean b){
            super(binding.getRoot());
            this.binding=binding;
        }

    }


    /*
    //Ir jugadorstats
    class JugadorStatsViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderJugadorMiTeamBinding binding;

        public JugadorStatsViewHolder(ViewholderJugadorMiTeamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    class JugadorStatsAdapter extends RecyclerView.Adapter<JugadorStatsViewHolder> {

        List<Jugador> jugadors;

        @NonNull
        @Override
        public JugadorStatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new JugadorStatsViewHolder(ViewholderJugadorMiTeamBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull JugadorStatsViewHolder holder, int position) {

            Jugador jugador = jugadors.get(position);

            holder.binding.nombreJugador.setText(jugador.nombre);
            holder.binding.dorsalJugador.setText(jugador.dorsal);
            holder.binding.imagenJugadorMiTeam.setImageAlpha(Integer.parseInt(jugador.imagen));
        }

        @Override
        public int getItemCount() {
            return jugadors != null ? jugadors.size() : 0;
        }

        public void establecerLista(List<Jugador> jugador){
            this.jugadors = jugador;
            notifyDataSetChanged();
        }
    }*/
}