package com.example.fatihn.tabloyutview02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainArama extends AppCompatActivity {

    Button btnAra;
    RecyclerView recyclerView2;
    RecycleAdapter adapter;
    List<VeriKisilerModel> datamodel;
    EditText etAra;
    String ad;
    VeritabaniHelper vt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_arama);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnAra = findViewById(R.id.btnAra);
        datamodel =new ArrayList<VeriKisilerModel>();
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        etAra=findViewById(R.id.etAra);


        btnAra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veriListele();
            }
        });


    }

    public void veriListele(){
        vt = new VeritabaniHelper(MainArama.this);
        ad=etAra.getText().toString().trim();
        datamodel = vt.aramaYap(ad);
        adapter = new RecycleAdapter(this,datamodel);
        Log.i("veriler", "" + datamodel);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(reLayoutManager);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(adapter);
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
