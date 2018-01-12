package com.example.osvaldoairon.petshopapp.Rep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by osvaldoairon on 12/01/2018.
 */
import com.example.osvaldoairon.petshopapp.Rep.modelo.Animal;
import java.util.ArrayList;
public class PetRepositorio implements DaoPet{
    private SQLPet pet;

    public ArrayList<Animal> animal_list = new ArrayList<Animal>();

    public PetRepositorio(Context ctx ){
        pet = new SQLPet(ctx);
    }



    public long insertDb(Animal animal){

        SQLiteDatabase db = pet.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(SQLPet.COLUNA_NOME, animal.getNome());
        cv.put(SQLPet.COLUNA_ESPECIE, animal.getEspecie());
        cv.put(SQLPet.COLUNA_RACA, animal.getRaca());
        cv.put(SQLPet.COLUNA_SEXO, animal.getSexo());
        cv.put(SQLPet.COLUNA_NASCIMENTO,animal.getNascimento());
        cv.put(SQLPet.COLUNA_INFORMACAO, animal.getInformacao());
        cv.put(SQLPet.COLUNA_FOTO, animal.getFoto());

        long id = db.insert(SQLPet.NOME_TABELA,null,cv);
        if(id != -1){
            animal.setId(id);
        }
        db.close();
        return id;
    }

    public void recuperarDados(){
        SQLiteDatabase db = pet.getWritableDatabase();
        String sql = "SELECT * FROM " + SQLPet.NOME_TABELA;
        Cursor cursor = db.rawQuery(sql,null);

        cursor.moveToFirst();

        while(cursor.moveToNext()){
            int indexColunanome = cursor.getColumnIndex(SQLPet.COLUNA_NOME);
            int indexColunaespecie = cursor.getColumnIndex(SQLPet.COLUNA_ESPECIE);
            int indexColunaraca = cursor.getColumnIndex(SQLPet.COLUNA_RACA);
            int indexColunaSexo = cursor.getColumnIndex(SQLPet.COLUNA_SEXO);
            int indexColunanascimento = cursor.getColumnIndex(SQLPet.COLUNA_NASCIMENTO);
            int indexColunainformacao = cursor.getColumnIndex(SQLPet.COLUNA_INFORMACAO);
            int indexColunafoto = cursor.getColumnIndex(SQLPet.COLUNA_FOTO);

            String nome = cursor.getString(indexColunanome);
            String especie = cursor.getString(indexColunaespecie);
            String raca = cursor.getString(indexColunaraca);
            String sexo = cursor.getString(indexColunaSexo);
            String nascimento = cursor.getString(indexColunanascimento);
            String informacao = cursor.getString(indexColunainformacao);
            byte[] foto = cursor.getBlob(indexColunafoto);

            Animal animaldb = new Animal(nome,especie,raca,sexo,nascimento,informacao,foto);
            animal_list.add(animaldb);

        }
        cursor.close();
        db.close();


    }
    public int deletarAnimal(Animal animal){
        SQLiteDatabase db = pet.getWritableDatabase();

        int line = db.delete(SQLPet.NOME_TABELA, SQLPet.COLUNA_ID + " = ?", new String[]{String.valueOf(animal.getId())});
        db.close();
        return line;
    }
    public void resetarTabela(){
        SQLiteDatabase db = pet.getWritableDatabase();
        String sql_del = "DELETE FROM " + SQLPet.NOME_TABELA;
        db.execSQL(sql_del);
        db.close();

    }
    public void resetarAdapter(){
        animal_list.clear();
    }
    public int contArray(){
        return animal_list.size();
    }

    @Override
    public long insert(Animal animal) {
        return 0;
    }

    @Override
    public int deletarAnimal() {
        return 0;
    }


    @Override
    public int countArray() {
        return 0;
    }
}
