package com.example.mvltsevinc.mobilfinal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {
    EditText editTextID;
    EditText editTextAd;
    EditText editTextSoyad;
    Button btnEkle;
    Button btnSil;
    Button btnGuncelle;
    Button btnListele;
    String[] odalar;
    ArrayAdapter<String> adapter;
    ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        setUIElements();



        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                int id = Integer.parseInt(editTextID.getText().toString());
                String ad = editTextAd.getText().toString();
                String soyad = editTextSoyad.getText().toString();

                String sorgu = "INSERT INTO kisiler VALUES( "+id+",'"+ad+"','"+soyad+"') ";
                db.execSQL(sorgu);
            }
        });


        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                int userid = Integer.parseInt(editTextID.getText().toString());
                db.execSQL("delete from kisiler where id= "+userid+" ");

            }
        });


        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                SQLiteDatabase db = databaseHelper.getWritableDatabase();

                int id = Integer.parseInt(editTextID.getText().toString());
                String ad = editTextAd.getText().toString();
                String soyad = editTextSoyad.getText().toString();

                String sorgu = "UPDATE kisiler SET ad= '"+ad+"',soyad= '"+soyad+"' where id="+id+" ";
                db.execSQL(sorgu);
            }
        });


        btnListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                SQLiteDatabase db = databaseHelper.getReadableDatabase();

                String sorgu = "SELECT * FROM kisiler";
                Cursor okunan = db.rawQuery(sorgu,null);

                if(okunan.getCount() <= 0){
                    Toast.makeText(DatabaseActivity.this, "Kayit Bulunamadi", Toast.LENGTH_SHORT).show();
                }else{

                    odalar = new String[okunan.getCount()];
                    int i = 0;

                    while (okunan.moveToNext()){
                        int id = okunan.getInt(0);
                        String ad = okunan.getString(okunan.getColumnIndex("ad"));
                        String soyad = okunan.getString(2);
                        String satir = "ID:"+id+ "ad:"+ ad+ "soyad:"+soyad;
                        odalar[i++] = satir;
                    }

                    adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,odalar);
                    liste.setAdapter(adapter);
                }

                db.close();

            }
        });






    }

    private void setUIElements() {
        editTextID = findViewById(R.id.editTextID);
        editTextAd = findViewById(R.id.editTextAd);
        editTextSoyad = findViewById(R.id.editTextSoyad);
        btnEkle = findViewById(R.id.btnEkle);
        btnSil = findViewById(R.id.btnSil);
        btnGuncelle = findViewById(R.id.btnGuncelle);
        btnListele = findViewById(R.id.btnListele);
        liste = findViewById(R.id.liste);
    }
}
