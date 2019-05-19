package com.example.fatihn.tabloyutview02;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import static com.github.mikephil.charting.components.Legend.*;


public class MainAnaSayfa extends AppCompatActivity {

    private static String TAG="MainAnaSayfa";
    private static float toplamTutar;
    //private float[] yData={25.3f,10.6f};
    private String[] xData={"Toplam"};
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ana_sayfa);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toplamGetir();

        pieChart=(PieChart) findViewById(R.id.idPieChart);
        //pieChart.setDescription(pieChart);
        //pieChart.setDescription("Chart");
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("TOPLAM BORÇ");
        pieChart.setCenterTextSize(18);
        //pieChart.setDrawEntryLabels(true);
        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG,"secilen: Secilen Deger");
                Log.d(TAG,"secilen: "+e.toString());
                Log.d(TAG,"secilen:"+h.toString());
                int pos1=e.toString().indexOf("(sum): ");
                String sales=e.toString().substring(pos1+1);
               // for(int i=0;i<yData.length;i++){
                for(int i=0;i<1;i++){
                    pos1=i;
                    break;
                }
               // String employee=xData[pos1+1];
                String employee=xData[0];
                //Toast.makeText(MainAnaSayfa.this,"Employee"+employee+"\n"+"Satıs"+sales+"K",Toast.LENGTH_LONG).show();
                Toast.makeText(MainAnaSayfa.this,"Toplam Tutar : "+toplamTutar+"-TL",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void addDataSet() {
        Log.d(TAG,"dataset başlatıldı");
        ArrayList<PieEntry> yEnt=new ArrayList<>();
        ArrayList<String> xEnty=new ArrayList<>();
       // for(int i=0;i<yData.length;i++){
        for(int i=0;i<1;i++){
           // yEnt.add(new PieEntry(yData[i],i));
            yEnt.add(new PieEntry(toplamTutar,i));
        }
        for(int i=1;i<xData.length;i++){
            xEnty.add(xData[i]);
        }

        PieDataSet pieDataSet=new PieDataSet(yEnt,"Toplam Borçlar");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(14);

        //renkler
        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.MAGENTA);
        //colors.add(Color.GRAY);
        pieDataSet.setColors(colors);

        Legend legend=pieChart.getLegend();
        legend.setForm(LegendForm.CIRCLE);
        //legend.setPosition(LegendPosition);

        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }

    private void toplamGetir(){
        VeritabaniHelper vt=new VeritabaniHelper(getApplicationContext());
        toplamTutar=vt.toplamBorc();
        //txtToplam.setText(toplam+"-TL");
        vt.close();
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
