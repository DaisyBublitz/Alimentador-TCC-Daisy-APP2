package com.example.alimentador_tcc_daisy_app;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeFragment extends Fragment {
    private FirebaseAuth mAuth; //acessa os recursos de autenticação do Firebase
    private FirebaseAuth.AuthStateListener mAuthListener; //monitora as mudanças de login

    private HomeViewModel mViewModel;

    private TextView pesoHomeDeviceTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                Log.d("TCC2023", "Usuário logado");
            } else {
                Log.d("TCC2023", "Usuário signed_out");
                Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_loginFragment);
            }
        };
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Button encher = v.findViewById(R.id.servirButton);
        encher.setOnClickListener(view ->  encher());

        Button sair = v.findViewById(R.id.sairButton);
        sair.setOnClickListener( view -> { mAuth.signOut(); });

        pesoHomeDeviceTextView = v.findViewById(R.id.pesoHomeDeviceTextView);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        mViewModel.getPeso().observe(getViewLifecycleOwner(), peso -> {
            pesoHomeDeviceTextView.setText(String.valueOf(peso));
            Log.d("TCC2023", "Peso atualizado: " + peso);
        });
    }

    void encher() {
        mViewModel.encherValor(1.0);
        Toast.makeText(getContext(),"Solicitado ao dispositivo", Toast.LENGTH_LONG).show();
    }

    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}