package com.example.osvaldoairon.petshopapp.Rep.modelo;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by osvaldoairon on 12/01/2018.
 */

public class Animal implements Serializable{

    private String nome;

    private String especie;

    private String raca;

    private String sexo;

    private String nascimento;

    private String informacao;

    private byte[] foto;

    private long id;

    public void setId(long id){
        this.id=id;
    }
    public long getId(){
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }
    public void setEspecie(String especie){
        this.especie=especie;
    }

    public String getEspecie() {
        return especie;
    }

    public void setRaca(String raca){
        this.raca=raca;
    }
    public String getRaca(){
        return raca;
    }

    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo){
        this.sexo=sexo;
    }
    public void setNascimento(String nascimento){
        this.nascimento=nascimento;
    }
    public String getNascimento(){
        return nascimento;
    }
    public void setInformacao(String informacao){
        this.informacao=informacao;
    }
    public String getInformacao(){
        return informacao;
    }
    public void setFoto(byte[] foto){
        this.foto=foto;
    }
    public byte[] getFoto(){
        return foto;
    }
    public Animal(){}

    public Animal(String nome , String especie, String raca , String sexo , String nascimento , String informacao , byte[] foto){
        setNome(nome);
        setEspecie(especie);
        setRaca(raca);
        setSexo(sexo);
        setNascimento(nascimento);
        setInformacao(informacao);
        setFoto(foto);

    }
}
