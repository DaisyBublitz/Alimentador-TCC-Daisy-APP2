package com.example.alimentador_tcc_daisy_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Olá TCC 2023!").addOnCompleteListener(task -> {
            if (task.isSuccessful())
                Toast.makeText(getBaseContext(),"Firebase OK", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getBaseContext(),"Erro Firebase", Toast.LENGTH_LONG).show();
        });*/
    }
}
