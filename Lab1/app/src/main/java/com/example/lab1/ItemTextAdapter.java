package com.example.lab1;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemTextAdapter extends RecyclerView.Adapter<ItemTextAdapter.ItemHolder>
{
    private int itemCount;
    private String[] units = {"", "один", "два", "три", "четыре",
                              "пять", "шесть", "семь", "восемь", "девять"};
    private String[] unitsAlt = {"", "одна", "две", "три", "четыре", "пять",
                                 "шесть", "семь", "восемь", "девять"};
    private String[] elevens = {"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
                                "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать",
                                "девятнадцать"};
    private String[] tens = {"", "десять", "двадцать", "тридцать", "сорок", "пятьдесят",
                             "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    private String[] hundreds = {"сто", "двести", "триста", "четыреста", "пятьсот",
                                 "шестьсот", "семьсот", "восемьсот", "девятьсот"};
    private String[] thousands = {"тысяча", "тысячи", "тысяч"};
    private String million = "миллион";

    public ItemTextAdapter(int itemCount)
    {
        super();
        this.itemCount = itemCount;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position)
    {
        int number = position + 1;
        holder.textView.setText(indexToString(number));

        int color = number % 2 == 0 ? R.color.even : R.color.odd;
        int backgroundColor = ContextCompat.getColor(holder.itemView.getContext(), color);

        holder.linearLayout.setBackgroundColor(backgroundColor);
    }

    @Override
    public int getItemCount()
    {
        return itemCount;
    }

    public class ItemHolder extends RecyclerView.ViewHolder
    {
        private final TextView textView;
        private final LinearLayout linearLayout;

        ItemHolder(View itemView)
        {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            linearLayout = itemView.findViewById(R.id.line);
        }
    }

    private String indexToString(int index)
    {
        String string = "";
        if (index == 1000000)
            string = million;
        else if (index >= 1000)
        {
            string += hundredToString(index / 1000, true);
            string += ' ';
        }

        if (string != "")
        {
            int unitsT = index / 1000 % 10;
            int tensT = index / 10000 % 10;

            if (unitsT == 1)
            {
                string += thousands[0];
            }
            else if (unitsT > 1 && unitsT < 4 && tensT != 1)
            {
                string += thousands[1];
            }
            else string += thousands[2];

            string += ' ';
        }

        string += hundredToString(index % 1000, false);

        return string;
    }

    private String hundredToString(int number, boolean isAlt)
    {
        String string = "";
        int hundred = number / 100;
        if (hundred > 0)
        {
            string += hundreds[hundred - 1];
            if (number % 100 > 0)
            {
                string += ' ';
            }
        }
        number %= 100;

        if (number / 10 == 1)
        {
            string += elevens[number % 10];
        }
        else
        {
            int ten = number / 10;
            string += tens[ten];
            if (ten > 0) string += ' ';

            if (isAlt)
            {
                string += ' ' + unitsAlt[number % 10];
            }
            else string += ' ' + units[number % 10];
        }

        return string;
    }
}
