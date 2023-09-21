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
import android.widget.EditText;

import com.example.alimentador_tcc_daisy_app.R;
import com.example.alimentador_tcc_daisy_app.model.Usuario;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEditCadastroFragment extends Fragment {
    private FirebaseAuth mAuth; //acessa os recursos de autenticação do Firebase
    private DatabaseReference mDatabase; //acessa os recursos do banco de dados do Firebase

    //Variáveis para referenciar os elementos da UI
    private EditText nomePetEditText;
    private EditText idadePetEditText;
    private EditText pesoPetEditText;
    private EditText nomeEditText;
    private EditText telefoneEditText;
    private EditText emailEditText;
    private EditText senhaEditText;

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
        nomePetEditText = view.findViewById(R.id.nomePetEditText);
        idadePetEditText = view.findViewById(R.id.idadePetEditText);
        pesoPetEditText = view.findViewById(R.id.pesoPetEditText);
        nomeEditText = view.findViewById(R.id.nomeDonoEditText);
        telefoneEditText = view.findViewById(R.id.telefoneDonoEditText);
        emailEditText = view.findViewById(R.id.emailDonoEditText);
        senhaEditText = view.findViewById(R.id.senhaDonoEditText);
        Button cadastrar = view.findViewById(R.id.cadastrarButtonFragmentAddEdit);
        cadastrar.setOnClickListener(v -> fazerCadastro());
        return view;
    }

    private void fazerCadastro() {
        //leitura dos dados
        String email = emailEditText.getText().toString();
        String senha = senhaEditText.getText().toString();
        //cria um novo login firebase
        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("TCC2023", "Falha ao criar novo Login: ", task.getException());
            } else {
                Log.d("TCC2023", "Login criado com sucesso");
                //Le o UUI do novo usuário criado
                String uui = task.getResult().getUser().getUid();
                //E cria um novo usuario
                criarUsuario(uui);
            }
        });
    }

    private void criarUsuario(String uui) {
        //leitura dos dados do pet
        String nomePet = nomePetEditText.getText().toString();
        String idadePet = idadePetEditText.getText().toString();
        String pesoPet = pesoPetEditText.getText().toString();
        //leitura dos dados dono
        String nome = nomeEditText.getText().toString();
        String telefone = telefoneEditText.getText().toString();
        String email = emailEditText.getText().toString();
        Usuario user = new Usuario(nomePet, idadePet, pesoPet, nome, telefone, email);

        mDatabase.child(uui).setValue(user).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("TCC2023", "Falha ao criar dados do usuario: ", task.getException());
            } else {
                Log.d("TCC2023", "Usuario criado com sucesso");
                NavController nav = Navigation.findNavController(getView());
                nav.navigate(R.id.action_addEditCadastroFragment_to_homeFragment);            }
        });
    }
}