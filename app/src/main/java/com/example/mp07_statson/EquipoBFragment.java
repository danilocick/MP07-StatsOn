package com.example.mp07_statson;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mp07_statson.Model.BaseDeDatosMiTM;
import com.example.mp07_statson.Model.Equipo;
import com.example.mp07_statson.Model.Jugador;
import com.example.mp07_statson.ViewModel.EquipoViewModel;
import com.example.mp07_statson.ViewModel.JugadoresViewModel;
import com.example.mp07_statson.databinding.FragmentEquipoBBinding;
import com.example.mp07_statson.databinding.ViewholderJugadorMiTeamBinding;

import java.util.List;


public class EquipoBFragment extends Fragment {

    private NavController navController;
    private FragmentEquipoBBinding binding;
    private JugadoresViewModel jugadorsViewModel;
    int starts=0;
    private EquipoViewModel equipoViewModel;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentEquipoBBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        equipoViewModel = new ViewModelProvider(requireActivity()).get(EquipoViewModel.class);

        //TODO: insertar nombre equipo
        int m = 4;
//        equipoViewModel.obtener(m).observe(getViewLifecycleOwner(), nombreEquipo -> binding.nombreRival.setText(nombreEquipo));

        //empezar partido
        binding.botonSiguienteERival.setOnClickListener(view1 -> {
//                navController.navigate(R.id.action_equipoBFragment_to_gameFragment);
//            if (starts == 5){
//                starts = 0;
//            }else{
//                // If name is not entered
//                Toast.makeText(requireActivity().getApplicationContext(), "Select 5 Stars, Click Photo", Toast.LENGTH_LONG).show();
//            }
        });

        //ComeBack
        binding.botonComeBack.setOnClickListener(view12 -> {
            //para volver atras
            navController.popBackStack();
        });

        jugadorsViewModel = new ViewModelProvider(requireActivity()).get(JugadoresViewModel.class);

        //Ir anyadirjugador
//        binding.botonanyadirjugador.setOnClickListener(view13 -> navController.navigate(R.id.action_equipoBFragment_to_addJugadorFragment));

        JugadoresbdTeamBAdapter adapter = new JugadoresbdTeamBAdapter();
        binding.listaJugadores.setAdapter(adapter);

        //printar jugadores
        //TODO: int m que se coja bien el numero, sin errores.
        jugadorsViewModel.obtenerJugadoresDeEquipo(m).observe(getViewLifecycleOwner(), adapter::establecerJugadoresList);

    }

    //clase para acceder a los campos de viewholder_jugador_miteam
    static class JugadorTeamBViewHolder extends RecyclerView.ViewHolder{
        ViewholderJugadorMiTeamBinding binding;

        public JugadorTeamBViewHolder(@NonNull ViewholderJugadorMiTeamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    //adaptador bd
    class JugadoresbdTeamBAdapter extends RecyclerView.Adapter<EquipoBFragment.JugadorTeamBViewHolder>{

        List<Jugador> jugadorList;

        @NonNull
        @Override
        public JugadorTeamBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new JugadorTeamBViewHolder(ViewholderJugadorMiTeamBinding.inflate(getLayoutInflater(),parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull JugadorTeamBViewHolder holder, int position) {
            Jugador jugador = jugadorList.get(position);
            Glide.with(holder.itemView).load(jugador.imagen).into(holder.binding.imagenJugador);
            holder.binding.nombreJugador.setText(jugador.nombre);
            holder.binding.dorsalJugador.setText(String.valueOf(jugador.dorsal));

            holder.binding.eliminarJugador.setOnClickListener(view -> {
                //le pasamos la informacion obtenida al viewmodel de jugadoresMiTM
                jugadorsViewModel.delete(jugador);
            });

            holder.binding.background.setOnClickListener(view -> {
                int color = Color.TRANSPARENT;
                Drawable background = holder.binding.background.getBackground();
                if (background instanceof ColorDrawable)
                    color = ((ColorDrawable) background).getColor();

                //para volver atras
                if (color ==Color.rgb(0,0,0) && starts < 5){
                    //anyadimos jugador a la base de datos:
                    jugador.setStarter(true);
                    jugadorsViewModel.actualizar(jugador);

                    holder.binding.background.setBackgroundColor(Color.rgb(218,165,32));

                    starts++;

                    System.out.println(starts);
                }else {
                    if (starts == 5){
                        Toast.makeText(requireActivity().getApplicationContext(), "You have 5 Stars", Toast.LENGTH_LONG).show();
                    }else {
                        //anyadimos jugador a la base de datos:
                        jugador.setStarter(false);
                        jugadorsViewModel.actualizar(jugador);

                        holder.binding.background.setBackgroundColor(Color.rgb(0, 0, 0));
                        starts--;
                        System.out.println(starts);


                    }
                }

            });


        }

        @Override
        public int getItemCount() {
            return jugadorList == null ? 0 : jugadorList.size();
        }

        void establecerJugadoresList(List<Jugador> jugadorList){
            this.jugadorList = jugadorList;
            notifyDataSetChanged();
        }
    }

}