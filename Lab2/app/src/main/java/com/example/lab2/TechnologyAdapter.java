package com.example.lab2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class TechnologyAdapter extends RecyclerView.Adapter<TechnologyAdapter.ItemHolder>
{
    private int itemCount;
    private Context context;

    public TechnologyAdapter(Context context)
    {
        super();

        this.context = context;
        itemCount = DataHolder.getInstance().getTechologiesSize();
    }

    @Override
    public TechnologyAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);

        return new TechnologyAdapter.ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int index)
    {
        holder.textView.setText(DataHolder.getInstance().getTechnology(index).getName());
        DataHolder.getInstance().loadTechnologyImage(index, holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, TechnologyInfoActivity.class);
                intent.putExtra("index", index);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return itemCount;
    }

    protected class ItemHolder extends RecyclerView.ViewHolder
    {
        private final TextView textView;
        private final ImageView imageView;

        ItemHolder(View itemView)
        {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
