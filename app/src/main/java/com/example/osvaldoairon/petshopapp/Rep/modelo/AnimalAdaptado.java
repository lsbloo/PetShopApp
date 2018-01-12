package com.example.osvaldoairon.petshopapp.Rep.modelo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by osvaldoairon on 12/01/2018.
 */
import com.example.osvaldoairon.petshopapp.R;

public class AnimalAdaptado extends BaseAdapter {

    private Context ctx;
    private ArrayList<Animal> listAnimal;

    public AnimalAdaptado(Context ctx , ArrayList<Animal> animal) {
        this.ctx = ctx;
        listAnimal = animal;

    }

    @Override
    public int getCount() {
        return listAnimal.size();
    }

    @Override
    public Object getItem(int position) {

        return listAnimal.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Animal animal = listAnimal.get(position);
        byte[] fotoArray;
        Bitmap raw;

        fotoArray = animal.getFoto();

        View linha = LayoutInflater.from(ctx).inflate(R.layout.activity_main,parent,false);

        ImageView imgAnimal = (ImageView)linha.findViewById(R.id.imgAnimal);

        TextView nomeAnimal = (TextView)linha.findViewById(R.id.nome);
        TextView especieAnimal = (TextView)linha.findViewById(R.id.especie);
        TextView racaAnimal = (TextView)linha.findViewById(R.id.raca);
        TextView sexoAnimal = (TextView)linha.findViewById(R.id.sexo);
        TextView nascimentoAnimal = (TextView)linha.findViewById(R.id.nascimento);
        TextView informacaoAnimal = (TextView)linha.findViewById(R.id.informacao);


        Resources res = ctx.getResources();

        if(fotoArray != null){
            raw = BitmapFactory.decodeByteArray(fotoArray,0,fotoArray.length);
            imgAnimal.setImageBitmap(raw);
        }
        nomeAnimal.setText("Nome: " + animal.getNome());
        racaAnimal.setText("Raca: " + animal.getRaca());
        especieAnimal.setText("Especie: " + animal.getEspecie());
        sexoAnimal.setText("Sexo: " + animal.getSexo());
        nascimentoAnimal.setText("Nascimento: " + animal.getNascimento());
        informacaoAnimal.setText("Informacao: " + animal.getInformacao());



        return linha;

    }
}
