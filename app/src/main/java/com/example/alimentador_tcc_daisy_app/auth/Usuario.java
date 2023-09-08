package com.example.alimentador_tcc_daisy_app.auth;

import java.util.Date;

public class Usuario {
    String id;
    String nome;
    String email;
    String senha;

    //NECESSÀRIO CONSTRUTOR PADRÂO EM BRANCO
    public Usuario(){}


    public Usuario(String nome, String senha, String email) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    //NECESSÀRIO GET E SET
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email){this.email = email;}

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha){this.senha = senha;}
}
