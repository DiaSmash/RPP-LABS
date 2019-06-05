package com.example.lab3_v1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewAdapter>
{
    private ArrayList<ArrayList<String>> names;

    public CardAdapter(ArrayList<ArrayList<String>> names)
    {
        this.names = names;
    }

    @NonNull
    @Override
    public CardViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_view, parent, false);

        return new CardViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewAdapter holder, int position)
    {
        TextView idText = holder.id;
        idText.setText((this.names.get(position)).get(0));

        TextView nameText = holder.name;
        nameText.setText((this.names.get(position)).get(1));

        TextView dateAddText = holder.dateAdd;
        dateAddText.setText((this.names.get(position)).get(2));
    }

    @Override
    public int getItemCount()
    {
        return names.size();
    }

    class CardViewAdapter extends RecyclerView.ViewHolder
    {
        public View linearLayout;
        private TextView id;
        private TextView name;
        private TextView dateAdd;

        public CardViewAdapter(View itemView)
        {
            super(itemView);
            id = itemView.findViewById(R.id.student_id);
            name = itemView.findViewById(R.id.student_name);
            dateAdd = itemView.findViewById(R.id.date_add);
            linearLayout = itemView.findViewById(R.id.line);
        }

    }
}
