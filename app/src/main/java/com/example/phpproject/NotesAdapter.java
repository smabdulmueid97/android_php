package com.example.phpproject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    private Context context;
    private List<NotesModel> notesModelList;

    public NotesAdapter(Context context) {
        this.context = context;
        notesModelList=new ArrayList<>();
    }

    public void add(NotesModel notesModel) {
        notesModelList.add(notesModel);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext()).inflate (R.layout.note_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(notesModelList.get(position).getTitle());
        holder.description.setText(notesModelList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return notesModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
        }
    }
}

