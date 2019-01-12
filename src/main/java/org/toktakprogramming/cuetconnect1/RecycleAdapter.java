package org.toktakprogramming.cuetconnect1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> implements View.OnClickListener {
  ArrayList<RecyclerItem>recyclerItems;
  public Context context;

    public RecycleAdapter(ArrayList<RecyclerItem> recyclerItems, Context context) {
        this.recyclerItems = recyclerItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);

        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclerItem item=recyclerItems.get(position);
          holder.name.setText(item.getName());
          holder.id.setText(item.getId());
          holder.post.setText(item.getPost());
          holder.like.setText(item.getLike());


    }

    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }

    @Override
    public void onClick(View v) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
           public TextView name,id,post,like;
           public Button likeBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nameid);
            id=itemView.findViewById(R.id.idid);
            post=itemView.findViewById(R.id.postid);
            like=itemView.findViewById(R.id.likeid);

        }
    }
}
