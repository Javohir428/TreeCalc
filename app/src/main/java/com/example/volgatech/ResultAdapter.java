package com.example.volgatech;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private Context context;
    private List<Tree> treeList;

    public ResultAdapter(Context context, List<Tree> treeList) {
        this.context = context;
        this.treeList = treeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_result,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        String poroda = treeList.get(position).getPoroda();
        int rh = treeList.get(position).getRh();
        int stupen = treeList.get(position).getStupen();
        int diametr = treeList.get(position).getDiametr();
        int kol_del = treeList.get(position).getDel_kol();
        int kol_pol = treeList.get(position).getPol_del_kol();
        int kol_drov = treeList.get(position).getDrova_kol();

        Sortiment sortiment = new DatabaseHelper(context).getSortiment(poroda, rh, stupen);

        float obshiy_zapas = (kol_drov+kol_del+kol_pol)*sortiment.getV();
        float zap_krup = sortiment.getV() * kol_del * sortiment.getKr() / 100;
        float zap_sred = kol_del*sortiment.getV()*(sortiment.getSr1() + sortiment.getSr2())/100;
        float zap_mel = kol_del*sortiment.getV()*sortiment.getMl()/100;
        float zap_del = zap_krup + zap_sred + zap_mel;


        if(position %2 == 1)
        {
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#E8EAF6"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#BBDEFB"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }

        viewHolder.poroda.setText(poroda);
        viewHolder.rh.setText(String.valueOf(rh));
        viewHolder.stupen.setText(String.valueOf(stupen));
        viewHolder.diametr.setText(String.valueOf(diametr));
        viewHolder.kol_del.setText(String.valueOf(kol_del));
        viewHolder.kol_pol.setText(String.valueOf(kol_pol));
        viewHolder.kol_drov.setText(String.valueOf(kol_drov));
        viewHolder.obshiy_zapas.setText(String.valueOf(obshiy_zapas));
        viewHolder.zapas_krup.setText(String.valueOf(zap_krup));
        viewHolder.zapas_sred.setText(String.valueOf(zap_sred));
        viewHolder.zapas_mel.setText(String.valueOf(zap_mel));
        viewHolder.zapas_del.setText(String.valueOf(zap_del));

    }

    @Override
    public int getItemCount() {
        return treeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView poroda,rh,stupen,diametr,kol_del,kol_pol,kol_drov,obshiy_zapas,zapas_krup,zapas_sred,zapas_mel,zapas_del;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poroda = itemView.findViewById(R.id.poroda_t);
            rh = itemView.findViewById(R.id.rh_t);
            stupen = itemView.findViewById(R.id.st_t);
            diametr = itemView.findViewById(R.id.diametr_t);
            kol_del = itemView.findViewById(R.id.kol_del_t);
            kol_pol = itemView.findViewById(R.id.kol_pol_t);
            kol_drov = itemView.findViewById(R.id.kol_drov_t);
            obshiy_zapas = itemView.findViewById(R.id.ob_z_t);
            zapas_krup = itemView.findViewById(R.id.zap_krup_t);
            zapas_sred = itemView.findViewById(R.id.zap_sred_t);
            zapas_mel = itemView.findViewById(R.id.zap_mel_t);
            zapas_del = itemView.findViewById(R.id.zap_del_t);
        }
    }
}