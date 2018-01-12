package com.example.osvaldoairon.petshopapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.osvaldoairon.petshopapp.Rep.PetRepositorio;
import com.example.osvaldoairon.petshopapp.Rep.SQLPet;
import com.example.osvaldoairon.petshopapp.Rep.modelo.Animal;
import com.example.osvaldoairon.petshopapp.Rep.modelo.AnimalAdaptado;

public class MainActivity extends AppCompatActivity {

    private PetRepositorio petRepositorio;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new AlertDialog.Builder(MainActivity.this);
        petRepositorio = new PetRepositorio(MainActivity.this);
        petRepositorio.recuperarDados();
        //Toast.makeText(this, ""+ petRepositorio.contArray(), Toast.LENGTH_SHORT).show();

        final AnimalAdaptado adapter = new AnimalAdaptado(MainActivity.this,petRepositorio.animal_list);


        ListView list = new ListView(MainActivity.this);
        setContentView(list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Animal animal = (Animal) parent.getItemAtPosition(position);

                dialog.setTitle("Deletar Animal");
                dialog.setMessage("Deseja deletar o Animal? ");

                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        petRepositorio.animal_list.remove(animal);
                        petRepositorio.deletarAnimal(animal);
                        Toast.makeText(MainActivity.this, "Removido !", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                });

                dialog.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Cancelado!", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.setIcon(android.R.drawable.ic_input_delete);
                dialog.create();
                dialog.show();


        }

    });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.cad){
            // Add
            Intent it = new Intent(MainActivity.this,cadActivity.class);
            startActivity(it);
        }
        if(id == R.id.remove){
            dialog.setMessage("Deseja deletar todos os dados?").setTitle("Deletar dados").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    petRepositorio.resetarTabela();
                    petRepositorio.resetarTabela();
                    Toast.makeText(MainActivity.this, "Dados Deletados!", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.setNegativeButton("Nao", null);
            dialog.setIcon(android
            .R.drawable.ic_input_delete);
            dialog.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menucad,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {

        petRepositorio.resetarAdapter();
        petRepositorio.recuperarDados();

        Log.d("RESUME",  "RESUME");
        super.onResume();
    }
}
