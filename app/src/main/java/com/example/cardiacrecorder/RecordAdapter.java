package com.example.cardiacrecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder>{
    private ClickListener clickListener;

    private EditClickListener editClickListener;

    Context context;
    static ArrayList<Record> list;


    public RecordAdapter(Context context, ArrayList<Record> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recordlist,parent,false);
        return new MyViewHolder(v,clickListener,editClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Record record = list.get(position);
        holder.date.setText(record.getDate());
        holder.time.setText(record.getTime());
        holder.sys.setText(record.getSys_press());
        holder.dia.setText(record.getDia_press());
        holder.heart.setText(record.getHeart_rt());
        holder.com.setText(record.getComment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView date,time,sys,dia,heart,com;
        ImageView deleteBtn;
        ImageView editBtn;
        public MyViewHolder(@NonNull View itemView,ClickListener clickListener,EditClickListener editClickListener) {
            super(itemView);
            date = itemView.findViewById(R.id.textDate);
            time = itemView.findViewById(R.id.textTime);
            sys = itemView.findViewById(R.id.textSys);
            dia = itemView.findViewById(R.id.textDia);
            heart = itemView.findViewById(R.id.textHeart);
            com = itemView.findViewById(R.id.textCom);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
            editBtn = itemView.findViewById(R.id.edit_btn);

            itemView.setOnClickListener(this);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(getAdapterPosition());
                }
            });

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    Record record = list.get(position);
                    editClickListener.onEditClick(record);
                }
            });

        }

        @Override
        public void onClick(View v) {
//            clickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface ClickListener{
        void onItemClick(int position);
    }

    public interface EditClickListener{
        void onEditClick(Record record);
    }
    public void setOnItemClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    public void setEditClickListener(EditClickListener editClickListener) {
        this.editClickListener = editClickListener;
    }
}
