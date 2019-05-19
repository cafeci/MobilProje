package com.example.fatihn.tabloyutview02;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainBorcSayfa extends AppCompatActivity {

    Button btnBorcEkle,btnAzalt;
    TextView txtID, txtAd, txtTel, txtAdres, txtBorc;
    EditText etTutar;
    Float guncelBorc;
    String borc,id;
    ImageView imgBtnEdit,imgBtnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_borc_sayfa);

        txtID = findViewById(R.id.txtID);
        txtAd = findViewById(R.id.txtAd);
        txtTel = findViewById(R.id.txtTel);
        txtAdres = findViewById(R.id.txtAdres);
        txtBorc = findViewById(R.id.txtBorc);
        etTutar = findViewById(R.id.etTutar);
        btnBorcEkle = findViewById(R.id.btnBorcEkle);
        btnAzalt = findViewById(R.id.btnAzalt);
        imgBtnEdit = findViewById(R.id.imgBtnEdit);
        imgBtnDelete=findViewById(R.id.imgBtnDelete);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Init();

        btnBorcEkle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                borc = etTutar.getText().toString();
                guncelBorc += Float.parseFloat(borc);
                try {
                    VeritabaniHelper vt = new VeritabaniHelper(getApplicationContext());
                    boolean durum=vt.borcEkle(txtID.getText().toString(),String.valueOf(guncelBorc));
                    if (durum == true)
                        Toast.makeText(MainBorcSayfa.this, "Güncellendi", Toast.LENGTH_LONG).show();
                        txtBorc.setText(guncelBorc.toString()+"-TL");
                        etTutar.setText("0");
                    //onBackPressed();
                } catch (Exception hata) {
                    //Toast.makeText(MainKisiDuzenle.this, "id " + id, Toast.LENGTH_LONG).show();
                    Toast.makeText(MainBorcSayfa.this, "Hata " + hata, Toast.LENGTH_LONG).show();
                }
            }
        });

        btnAzalt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                borc = etTutar.getText().toString();
                guncelBorc -= Float.parseFloat(borc);
                try {
                    VeritabaniHelper vt = new VeritabaniHelper(getApplicationContext());
                    vt.borcDus(txtID.getText().toString(), String.valueOf(guncelBorc));
                    Toast.makeText(MainBorcSayfa.this, "Güncellendi", Toast.LENGTH_LONG).show();
                    txtBorc.setText(guncelBorc.toString()+"-TL");
                    etTutar.setText("0");
                    //onBackPressed();
                } catch (Exception hata) {
                    //Toast.makeText(MainKisiDuzenle.this, "id " + id, Toast.LENGTH_LONG).show();
                    Toast.makeText(MainBorcSayfa.this, "Hata " + hata, Toast.LENGTH_LONG).show();
                } finally {
                    //    vt.close();
                }
            }
        });

        imgBtnEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainBorcSayfa.this, MainKisiDuzenle.class);
                intent.putExtra("id", txtID.getText().toString());
                intent.putExtra("ad", txtAd.getText().toString());
                intent.putExtra("telefon", txtTel.getText().toString());
                intent.putExtra("adres", txtAdres.getText().toString());
                intent.putExtra("tutar", txtBorc.getText().toString());
                startActivity(intent);
            }
        });

        imgBtnDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainBorcSayfa.this);
                    builder.setTitle("Kayıt Silinecektir. Onaylıyormu sunuz ?").setCancelable(false).setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//eweet butonu
                            dialog.dismiss();
                            VeritabaniHelper vt = new VeritabaniHelper(getApplicationContext());
                            vt.veriSil(txtID.getText().toString());
                            Toast.makeText(MainBorcSayfa.this, "Silindi", Toast.LENGTH_LONG).show();
                            onBackPressed();
                        }
                    }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }catch (Exception hata) {
                    //Toast.makeText(MainKisiDuzenle.this, "id " + id, Toast.LENGTH_LONG).show();
                    Toast.makeText(MainBorcSayfa.this, "Hata " + hata, Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    private void Init() {
        try {
            Bundle extras = getIntent().getExtras();
            id=extras.getString("id");
            txtID.setText(extras.getString("id").toString());
            txtAd.setText(extras.getString("ad").toString());
            txtTel.setText(extras.getString("telefon").toString());
            txtAdres.setText(extras.getString("adres").toString());
            txtBorc.setText(extras.getString("tutar") + "-TL");
            guncelBorc=Float.parseFloat(extras.getString("tutar").toString());

        } catch (Exception hata) {
            Toast.makeText(MainBorcSayfa.this, "Hata " + hata, Toast.LENGTH_LONG).show();
        }
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
