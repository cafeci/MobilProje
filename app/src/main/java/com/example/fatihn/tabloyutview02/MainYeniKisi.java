package com.example.fatihn.tabloyutview02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainYeniKisi extends AppCompatActivity {
    VeritabaniHelper vt;
    Button btnKaydet;
    EditText txtAd, txtTelefon,txtAdres,txtTutar;
    String adi, telefonu,adresi,tutari;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_yeni_kisi);

        txtAd = (EditText) findViewById(R.id.etAd);
        txtTelefon = (EditText) findViewById(R.id.txtTelefon);
        txtAdres=(EditText) findViewById(R.id.txtAdres);
        txtTutar=(EditText) findViewById(R.id.txtTutar);
        btnKaydet = (Button) findViewById(R.id.btnKaydet);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //show= (Button) findViewById(R.id.show);
        vt = new VeritabaniHelper(this);

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adi = txtAd.getText().toString().trim();
                telefonu =txtTelefon.getText().toString().trim();
                adresi=txtAdres.getText().toString().trim();
                tutari=txtTutar.getText().toString().trim();
                //Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();
                if (adi.isEmpty()) {
                    Toast.makeText(MainYeniKisi.this, "İlgili Alanları Girmeli siniz!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                       boolean durum= vt.veriEkle(adi, telefonu, adresi, tutari);
                        txtAd.setText("");
                        txtTelefon.setText("");
                        txtAdres.setText("");
                        txtTutar.setText("");
                       if(durum==true)   Toast.makeText(MainYeniKisi.this,"Yeni Kişi Eklendi.",Toast.LENGTH_LONG).show();
                    }
                    catch (Exception hata){
                        Toast.makeText(MainYeniKisi.this,"Hata "+hata,Toast.LENGTH_LONG).show();
                    }
                    finally {
                        vt.close();
                    }


                }
            }
        });
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
