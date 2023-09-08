package com.example.alimentador_tcc_daisy_app.auth;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.alimentador_tcc_daisy_app.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth; //acessa os recursos de autenticação do Firebase

    //Variáveis para referenciar os elementos da UI
    private TextInputLayout login;
    private TextInputLayout password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        login = view.findViewById(R.id.emailTextInputLayout);
        password = view.findViewById(R.id.passwordTextInputLayout);
        Button loginButton = view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(view1 -> fazerLogin());
        Button cadButton = view.findViewById(R.id.cadastarButton);
        cadButton.setOnClickListener(view1 -> fazerCadastro());
        return view;
    }

    void fazerLogin() {
        String username = login.getEditText().getText().toString();
        String password = this.password.getEditText().getText().toString();
        //teste rapido para verificar dados
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Preencha os campos", Toast.LENGTH_LONG).show();
        } else {
            //Faz uma tentativa de login com os dados preenchidos na UI
            mAuth.signInWithEmailAndPassword(username, password)
                    //Método assíncrono para lidar com a resposta da solicitação
                    .addOnCompleteListener(getActivity(),
                            task -> {
                                if (task.isSuccessful()) {
                                    Log.d("TCC2023", "Login Efetuado com sucesso!!!");
                                    NavController nav = Navigation.findNavController(getView());
                                    nav.popBackStack();
                                } else {
                                    Log.w("TCC2023", "Falha ao efetuar o Login: ", task.getException());
                                }
                            });
        }
    }

    void fazerCadastro() {
        NavController nav = Navigation.findNavController(getView());
        nav.navigate(R.id.action_loginFragment_to_addEditCadastroFragment);
    }
}