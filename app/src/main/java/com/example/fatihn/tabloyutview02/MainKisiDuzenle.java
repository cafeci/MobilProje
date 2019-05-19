package com.example.fatihn.tabloyutview02;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainKisiDuzenle extends AppCompatActivity {

    Button btnGuncelle;
    TextView etKisiID;
    EditText etAd, etTelefon, etAdres, etTutar;
    String id, adi, telefonu, adresi, tutari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kisi_duzenle);

        etKisiID = (TextView) findViewById(R.id.etKisiID);
        etAd = (EditText) findViewById(R.id.etAd);
        etTelefon = (EditText) findViewById(R.id.etTelefon);
        etAdres = (EditText) findViewById(R.id.etAdres);
        etTutar = (EditText) findViewById(R.id.etTutar);
        btnGuncelle = (Button) findViewById(R.id.btnGuncelle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*Intent i=getIntent();
        adi=i.getStringExtra("ad");
       */
        Init();

        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adi = etAd.getText().toString();
                telefonu = etTelefon.getText().toString().trim();
                adresi = etAdres.getText().toString().trim();
                tutari = etTutar.getText().toString().trim();
                id=etKisiID.getText().toString();

                //Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();
                if (adi.isEmpty()) {
                    Toast.makeText(MainKisiDuzenle.this, "İlgili Alanları Girmeli siniz!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        VeritabaniHelper vt = new VeritabaniHelper(getApplicationContext());
                        vt.veriGuncelle(adi, telefonu, adresi, tutari, id);
                        Toast.makeText(MainKisiDuzenle.this, "Güncellendi", Toast.LENGTH_LONG).show();

                    } catch (Exception hata) {
                        // Toast.makeText(MainKisiDuzenle.this, "id " + id, Toast.LENGTH_LONG).show();
                        //  Toast.makeText(MainKisiDuzenle.this, "Hata " + hata, Toast.LENGTH_LONG).show();
                        showMessage("Hata", "Kayıt işleminde hata oluştu !");
                        onBackPressed();
                    } finally {
                        //  vt.close();
                    }
                }
            }
        });

    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private void Init() {
        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        etKisiID.setText(id);
        etAd.setText(extras.getString("ad"));
        etTelefon.setText(extras.getString("telefon"));
        etAdres.setText(extras.getString("adres"));
        etTutar.setText(extras.getString("tutar"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void finish() {
        super.finish();
    }
}
