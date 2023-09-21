package com.example.alimentador_tcc_daisy_app.model;

import java.util.Date;

public class Usuario {
    String id;
    String nomePet;
    String idadePet;
    String pesoPet;
    String nome;
    String email;
    String senha;

    //NECESSÀRIO CONSTRUTOR PADRÂO EM BRANCO
    public Usuario(){
        // Construtor vazio necessário para o Firebase Realtime Database
    }

    public Usuario(String nomePet, String idadePet, String pesoPet, String nome, String email, String senha) {
        this.nomePet = nomePet;
        this.idadePet = idadePet;
        this.pesoPet = pesoPet;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getIdadePet() {
        return idadePet;
    }

    public void setIdadePet(String idadePet) {
        this.idadePet = idadePet;
    }

    public String getPesoPet() {
        return pesoPet;
    }

    public void setPesoPet(String pesoPet) {
        this.pesoPet = pesoPet;
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
