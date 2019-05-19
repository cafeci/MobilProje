package com.example.fatihn.tabloyutview02;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Myholder> {

    List<VeriKisilerModel> dataModelArrayList;
    Context context;

    public RecycleAdapter(Context context,List<VeriKisilerModel> dataModelArrayList) {

        this.context=context;
        this.dataModelArrayList = dataModelArrayList;

    }

    class Myholder extends RecyclerView.ViewHolder{

        TextView v_id,v_adi,v_telefonu,v_adres,v_borc;
        public int position=0;

        public Myholder(View itemView) {
            super(itemView);
            v_id=(TextView) itemView.findViewById(R.id.v_id);
            v_adi= (TextView) itemView.findViewById(R.id.v_adi);
            v_telefonu = (TextView) itemView.findViewById(R.id.v_telefon);
            v_adres=(TextView) itemView.findViewById(R.id.v_adres);
            v_borc=(TextView) itemView.findViewById(R.id.v_borc);
        }
    }


    public void updateList(List<VeriKisilerModel> models){
        dataModelArrayList=models;
        notifyDataSetChanged();
    }
    //Listelenecek itemlarda gosterilecek arayuz elemanlarının yerlestirilecegi xml dosyasi
    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kisilerview,null);
         return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(final Myholder holder, int position) {
        final VeriKisilerModel dataModel = dataModelArrayList.get(position);
        holder.v_id.setText(dataModel.getId());
        holder.v_adi.setText(dataModel.getAd());
        holder.v_telefonu.setText(dataModel.getTelefon());
        holder.v_adres.setText(dataModel.getAdres());
        holder.v_borc.setText(dataModel.getTutar()+"-TL");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dataModel.setSelected(!dataModel.isSelected());
                //holder.view.setBackgroundColor(dataModel..isSelected() ? Color.CYAN : Color.WHITE);
                Toast.makeText(context, "Seçilen :"+dataModel.getAd(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,MainBorcSayfa.class);
                intent.putExtra("id",dataModel.getId());
                intent.putExtra("ad",dataModel.getAd());
                intent.putExtra("telefon",dataModel.getTelefon());
                intent.putExtra("adres",dataModel.getAdres());
                intent.putExtra("tutar",dataModel.getTutar());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
}
