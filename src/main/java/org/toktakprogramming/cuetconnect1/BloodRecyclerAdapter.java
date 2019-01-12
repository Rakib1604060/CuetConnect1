package org.toktakprogramming.cuetconnect1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BloodRecyclerAdapter extends RecyclerView.Adapter<BloodRecyclerAdapter.ViewHolder>{
    public ArrayList<BloodItem>arrayList;
    public Context context;

    public BloodRecyclerAdapter(ArrayList<BloodItem> arrayList,Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Vl=LayoutInflater.from(parent.getContext()).inflate(R.layout.bloodrecycler,parent,false);

        return new ViewHolder(Vl);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BloodItem bloodItem=arrayList.get(position);
        holder.blood.setText(bloodItem.getBloodGroup());
        holder.id.setText(bloodItem.getId());
        holder.name.setText(bloodItem.getName());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

         public TextView name;
         public TextView id;
         public TextView blood;
        public ViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.bloodDonerName);
            id=itemView.findViewById(R.id.blood_id);
            blood=itemView.findViewById(R.id.bloodgrouptext);

        }
    }
}
