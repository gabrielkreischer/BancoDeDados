package com.gabrielkreischer.bancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            // Criar ou acessar o banco de dados chamado app
            SQLiteDatabase bancoDeDados = openOrCreateDatabase("app", MODE_PRIVATE, null);
            // cria a tabela se não existir
            bancoDeDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas( id INT PRIMARY KEY NOT NULL, nome VARCHAR(255), idade INT)");
            // insere os dados
            bancoDeDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES('GABRIEL', 33), ('Felipe', 22), ('Pedro', 41)");
            // Recupera valores
            Cursor cursor = bancoDeDados.rawQuery("SELECT nome, idade FROM pessoas",null);

            int indiceNome = cursor.getColumnIndex("nome");

            cursor.moveToFirst();
            while (cursor != null){
                Log.d("O nome é: ", cursor.getString(indiceNome));
                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}