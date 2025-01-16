package com.example.appdev;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TrackOrderItemAdapter extends RecyclerView.Adapter<TrackOrderItemAdapter.TrackOrderItemViewHolder>{



    private OnButtonClickListener buttonClickListener;

    ArrayList<Item> trackOrderItemsList;

    public TrackOrderItemAdapter(ArrayList<Item> cartItemsList, OnButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
        this.trackOrderItemsList = cartItemsList;
    }



    private OnItemClickListener clickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public interface OnButtonClickListener {
        void onButtonClick(Item item, int pos);
    }


    @NonNull
    @Override
    public TrackOrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_track_order, parent, false);
        return new TrackOrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackOrderItemViewHolder holder, int position) {
        int pos = position;
        Item currentCartItem = trackOrderItemsList.get(position);

        holder.image.setImageResource(currentCartItem.getImage());
        holder.cartItemName.setText(currentCartItem.getTitle());


        holder.cartItemPrice.setText("$" + (currentCartItem.getPrice() * currentCartItem.getTimesSold()));

        holder.buttonTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickListener.onButtonClick(currentCartItem, pos);

            }
        });





    }

    @Override
    public int getItemCount() {
        return trackOrderItemsList.size();
    }



    //for showing the format of the recyclerview, more like the xml layout of the recyclerview
    public class TrackOrderItemViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView cartItemName;
        public TextView cartItemPrice;


        public Button buttonTrack;

        public TrackOrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cartItemImageView);
            cartItemName = itemView.findViewById(R.id.cartItemTextView);
            cartItemPrice = itemView.findViewById(R.id.textView);

            buttonTrack = itemView.findViewById(R.id.buttonTrack);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(clickListener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){  //para daw di mo click  sa katong gi delete na nga item
                            clickListener.onItemClick(position);
                        }
                    }
                }
            });
        }



    }


}
