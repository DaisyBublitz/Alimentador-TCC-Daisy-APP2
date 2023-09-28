package com.example.alimentador_tcc_daisy_app;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomeViewModel extends ViewModel {
    private MutableLiveData<Double> peso;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference pesoRef = database.getReference("PESO");;
    DatabaseReference encherRef = database.getReference("SERVIR");

    public LiveData<Double> getPeso() {
        if (peso == null) {
            peso = new MutableLiveData<>();
            carregarPeso();
        }
        return peso;
    }

    private void carregarPeso() {
        pesoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Livro l = snapshot.getValue(Livro.class);
                //listaLivros.getValue().clear();
                Double pesoLido = snapshot.getValue(Double.class);
                peso.setValue(pesoLido);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("TCC2023", "Failed to read value.", error.toException());
            }
        });
    }

    public void encherValor(double value){
      //  String valor = String.valueOf(value);

        encherRef.setValue(value).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("TCC2023", "Falha ao encher", task.getException());
            } else {
                Log.d("TCC2023", "Enviado valor para encher");
            }
        });
    }

}