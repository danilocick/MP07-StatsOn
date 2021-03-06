package com.example.mp07_statson;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mp07_statson.databinding.FragmentCreateAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class CreateAccountFragment extends BaseFragment {

    private FragmentCreateAccountBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentCreateAccountBinding.inflate(inflater, container, false)).getRoot();
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.login.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);

            auth.createUserWithEmailAndPassword(binding.email.getText().toString(), binding.contrasenya.getText().toString()).addOnCompleteListener(task -> {
                binding.progressBar.setVisibility(View.INVISIBLE);
                if (task.isSuccessful()) {
                    Toast.makeText(requireActivity().getApplicationContext(),
                            "Registro Completado",
                            Toast.LENGTH_LONG).show();
                    nav.popBackStack();
                } else {
                    Toast.makeText(requireActivity().getApplicationContext(),
                            task.getException().getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        });


    }
}