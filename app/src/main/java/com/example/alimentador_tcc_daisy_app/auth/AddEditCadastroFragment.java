package com.example.alimentador_tcc_daisy_app.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.alimentador_tcc_daisy_app.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEditCadastroFragment extends Fragment {
    private FirebaseAuth mAuth; //acessa os recursos de autenticação do Firebase
    private DatabaseReference mDatabase; //acessa os recursos do banco de dados do Firebase

    //Variáveis para referenciar os elementos da UI
    private TextInputLayout nomeTextInputLayout;
    private TextInputLayout emailTextInputLayout;
    private TextInputLayout senhaTextInputLayout;

    public AddEditCadastroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_edit_cadastro, container, false);
        nomeTextInputLayout = view.findViewById(R.id.nomeInput);
        emailTextInputLayout = view.findViewById(R.id.emailInput);
        senhaTextInputLayout = view.findViewById(R.id.senhaInput);
        Button cadastrar = view.findViewById(R.id.cadastrarCadastroButton);
        cadastrar.setOnClickListener(v -> fazerCadastro());
        return view;
    }

    private void fazerCadastro() {
        //leitura dos dados
        String nome = nomeTextInputLayout.getEditText().getText().toString();
        String email = emailTextInputLayout.getEditText().getText().toString();
        String senha = senhaTextInputLayout.getEditText().getText().toString();
        //cria um novo login firebase
        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("MYMED2023", "Falha ao criar novo Login: ", task.getException());
            } else {
                Log.d("MYMED2023", "Login criado com sucesso");
                //Le o UUI do novo usuário criado
                String uui = task.getResult().getUser().getUid();
                //E cria um novo usuario
                Usuario usuario = new Usuario(nome, senha, email);
                criarUsuario(uui, usuario);
            }
        });
    }

    private void criarUsuario(String uui, Usuario usuario) {
        mDatabase.child(uui).setValue(usuario).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("MYMED2023", "Falha ao criar dados do usuario: ", task.getException());
            } else {
                Log.d("MYMED2023", "Usuario criado com sucesso");
                NavController nav = Navigation.findNavController(getView());
                nav.navigate(R.id.action_addEditCadastroFragment_to_homeFragment);            }
        });
    }
}