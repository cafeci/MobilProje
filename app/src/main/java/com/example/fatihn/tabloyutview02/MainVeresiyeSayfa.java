package com.example.fatihn.tabloyutview02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class MainVeresiyeSayfa extends AppCompatActivity {

    ImageView btnYeniKisi;
    VeritabaniHelper database;
    RecyclerView recyclerView;
    RecycleAdapter adapter;
    List<VeriKisilerModel> datamodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_veresiye_sayfa);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnYeniKisi = findViewById(R.id.btnYeniKisi);
        datamodel =new ArrayList<VeriKisilerModel>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        veriListele();

        adapter = new RecycleAdapter(this,datamodel);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        btnYeniKisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intebnt= new Intent(MainVeresiyeSayfa.this,MainYeniKisi.class);
                startActivity(intebnt);
            }
        });
    }

    private void veriListele(){
        database = new VeritabaniHelper(MainVeresiyeSayfa.this);
        datamodel=  database.veriGetir();
        adapter =new RecycleAdapter(this, datamodel);
        Log.i("veriler",""+datamodel);
        RecyclerView.LayoutManager reLayoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void updateList(){
        datamodel=database.veriGetir();
        RecycleAdapter adapter= (RecycleAdapter) recyclerView.getAdapter();
        adapter.updateList(datamodel);
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateList();
    }

    public void refreshList(View view) {
        updateList();
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
