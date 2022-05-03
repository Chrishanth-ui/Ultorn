package com.cs.ultron.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cs.ultron.R;
import com.cs.ultron.Model.Card;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {


    public Context mCtx;
    private ArrayList<Card> cardList;
    private SelectedCallBack selectedCallBack;

    public CardAdapter( Context mCtx, ArrayList<Card> cardList, SelectedCallBack selectedCallBack) {
        this.mCtx = mCtx;
        this.cardList = cardList;
        this.selectedCallBack = selectedCallBack;

    }



    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        @SuppressLint("InflateParams") View view  =  inflater.inflate(R.layout.dashboard_items,null);
        return new CardViewHolder(view);

    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {

        Card card = cardList.get(position);

        holder.textViewTitle.setText(card.getTitle());
        holder.imageView.setImageResource(card.getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCallBack.itemSelected(card.getId());
            }
        });



    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewTitle;
        CardView cardView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
