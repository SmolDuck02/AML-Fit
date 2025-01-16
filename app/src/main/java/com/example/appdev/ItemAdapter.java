package com.example.appdev;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

 public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {


    ArrayList<Item> itemList;

    public ItemAdapter(ArrayList<Item> itemList){
        this.itemList = itemList;
    }

    public void setFilteredList(ArrayList<Item> itemList){
        this.itemList = itemList;
        notifyDataSetChanged();
    }


    private OnItemClickListener clickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        clickListener = listener;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_itemss, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
      //  holder.imageVariablenimo.setImageResource(item.getText()//the getter method));))
        holder.textView.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ItemViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.textView10);  // kani na values kay para unsay nawong sa search results, ang layout kay ang activity_itemss

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
