package com.example.osvaldoairon.petshopapp;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;
import android.app.Dialog;
import android.app.DatePickerDialog;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Calendar;
import android.widget.DatePicker;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.text.DateFormat;
import android.widget.Toast;
import android.net.Uri;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.*;
import java.io.*;
import android.graphics.Matrix;
import android.Manifest;
import android.app.*;
import android.support.v4.app.ActivityCompat;
import android.content.pm.*;

import com.example.osvaldoairon.petshopapp.Rep.PetRepositorio;
import com.example.osvaldoairon.petshopapp.Rep.SQLPet;
import com.example.osvaldoairon.petshopapp.Rep.modelo.Animal;
import com.example.osvaldoairon.petshopapp.R.mipmap.*;
public class cadActivity extends AppCompatActivity {

    private EditText nome;


    private EditText raca;
    private CheckBox masculino;
    private CheckBox feminino;
    private EditText informacao;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private AutoCompleteTextView especie;
    private EditText edt_data;
    private CheckBox box_foto;
    private ImageView view_foto;

    private byte[] foto;
    private PetRepositorio petrep;

    private final int IMAGEM_INTERNA = 12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);

        verificarPermissao(cadActivity.this);
        final Calendar myCalendar = Calendar.getInstance();
        foto = null;
        petrep = new PetRepositorio(this);

        nome = (EditText)findViewById(R.id.edt_nome);
        raca = (EditText)findViewById(R.id.edt_raca);
        masculino =(CheckBox)findViewById(R.id.box_masculino);
        feminino=(CheckBox)findViewById(R.id.box_feminino);
        edt_data = (EditText) findViewById(R.id.edt_data);
        especie = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        informacao = (EditText)findViewById(R.id.edt_inf);


        box_foto = (CheckBox)findViewById(R.id.box_foto);

        List<String> especies = new ArrayList<String>();
        especies.add("Cachorro");
        especies.add("Gato");
        especies.add("Boi");
        especies.add("Ovelha");
        especies.add("Porco");
        especies.add("Cavalo");
        especies.add("Cobra");
        especies.add("Peixe");
        especies.add("Coelho");

        ArrayAdapter adapter = new ArrayAdapter(cadActivity.this, android.R.layout.simple_dropdown_item_1line, especies);

        especie.setAdapter(adapter);


        box_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pegarImagem(v);
                if(box_foto.isChecked()){
                    box_foto.setChecked(false);
                }

            }
        });

    }

    public void pegarImagem(View v){
        Intent at = new Intent(Intent.ACTION_GET_CONTENT);
        at.setType("image/");
        startActivityForResult(at,IMAGEM_INTERNA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == IMAGEM_INTERNA){
            if(resultCode == RESULT_OK){
                Uri imagemSelecionada = data.getData();

                String[] colunas = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(imagemSelecionada,colunas,null,null,null);

                cursor.moveToFirst();

                int indiceColuna = cursor.getColumnIndex(colunas[0]);

                String caminho_img = cursor.getString(indiceColuna);

                cursor.close();

                Bitmap bitmap = BitmapFactory.decodeFile(caminho_img);

                view_foto = (ImageView)findViewById(R.id.img_foto);

                //view_foto.setImageBitmap(bitmap);
                view_foto.setImageBitmap(redimensionarImagemBitmap(cadActivity.this,bitmap,100,100));
                foto = transformarImageViewtobyte(view_foto);



            }
        }
    }
    private static String[] PERMISSION_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE ,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static void verificarPermissao(Activity activity){

        int permissao = ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permissao != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSION_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    public byte[] transformarImageViewtobyte(ImageView v){
        // Pega o getDrawble da imageview e faz o cast para o bitmap
        //  em seguida da um bitmap compress com 3 parametros
        // o formato da img a qualidade e o bytearray de saida
        // por fim retorna a imagem em byte

        Bitmap bitmap = ((BitmapDrawable)v.getDrawable()).getBitmap();


        ByteArrayOutputStream saida = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG,100,saida);

        byte[] img = saida.toByteArray();

        return img;
    }

    private static Bitmap redimensionarImagemBitmap(Context context, Bitmap bmpOriginal,
                                      float newWidth, float newWeight) {
        Bitmap novoBmp = null;
        int w = bmpOriginal.getWidth();
        int h = bmpOriginal.getHeight();
        float densityFactor = context.getResources().getDisplayMetrics().density;
        float novoW = newWidth * densityFactor;
        float novoH = newWeight * densityFactor;
        float scalaW = novoW / w;
        float scalaH = novoH / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scalaW, scalaH);
        novoBmp = Bitmap.createBitmap(bmpOriginal, 0, 0, w, h, matrix, true);
        return novoBmp;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.save){
            String sexo = "";
            //Salvar os dados !;
            if(masculino.isChecked()){
                sexo = "masculino";
                masculino.setChecked(false);
            }
            if(feminino.isChecked()){
                sexo = "feminino";
                feminino.setChecked(false);
            }
            if(nome.getText().toString().isEmpty() || especie.getText().toString().isEmpty() || raca.getText().toString().isEmpty() || sexo.isEmpty() || informacao.getText().toString().isEmpty() || edt_data.getText().toString().isEmpty()){
                Toast.makeText(this, "Digite todos os campos !", Toast.LENGTH_SHORT).show();
            }
            else{
                Animal animal = new Animal(nome.getText().toString(),especie.getText().toString(),raca.getText().toString(),sexo,edt_data.getText().toString(),informacao.getText().toString(),foto);
                petrep.insertDb(animal);
                Toast.makeText(this, "Dados Salvos !", Toast.LENGTH_SHORT).show();
                limparCampos();




            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusave,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void limparCampos(){
        nome.setText("");
        especie.setText("");
        raca.setText("");
        edt_data.setText("");
        informacao.setText("");
        view_foto.setImageDrawable(null);
    }

}
