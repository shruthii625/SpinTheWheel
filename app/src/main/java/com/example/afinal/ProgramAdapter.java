package com.example.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProgramAdapter extends RecyclerView.Adapter <ProgramAdapter.ViewHolder>{
    Context context;
    String names[];
    Integer scores[];
    int images[];
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView v1;
        TextView v2;
        TextView v3;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v1= itemView.findViewById(R.id.v1);
            v2 = itemView.findViewById(R.id.v2);
            v3=itemView.findViewById(R.id.v3);
            img= itemView.findViewById(R.id.imageView);
        }
    }

    public ProgramAdapter(Context context, String[] names, Integer[] scores, int[] images){
        this.context=context;
        this.names=names;
        this.scores=scores;
        this.images=images;
    }
    @NonNull
    @Override
    public ProgramAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(context);
        View view= inflate.inflate(R.layout.single_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramAdapter.ViewHolder holder, int position) {
        holder.v1.setText("Name: "+names[position]);
        holder.v2.setText("Score: "+scores[position]+"");
        holder.v3.setText("Position: "+(position+1)+"");
        holder.img.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }
}
