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

public class FeedbackItemAdapter extends RecyclerView.Adapter<FeedbackItemAdapter.FeedbackItemViewHolder>{



    private OnButtonClickListener buttonClickListener;

    ArrayList<Item> feedbackItemsList;

    public FeedbackItemAdapter(ArrayList<Item> cartItemsList, OnButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
        this.feedbackItemsList = cartItemsList;
    }



    private FeedbackItemAdapter.OnItemClickListener clickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public interface OnButtonClickListener {
        void onButtonClick(int pos, Button button, Button button2, Button button3, Button button4, Button button5);

        void onButtonClick(int pos, Button buttonClicked, Button button, Button button2, Button button3, Button button4, Button button5);
    }


    @NonNull
    @Override
    public FeedbackItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_feedback, parent, false);
        return new FeedbackItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackItemViewHolder holder, int position) {
        int pos = position;
        Item currentCartItem = feedbackItemsList.get(position);

        holder.image.setImageResource(currentCartItem.getImage());
        holder.cartItemName.setText(currentCartItem.getTitle());


        holder.cartItemPrice.setText("$" + (currentCartItem.getPrice() * currentCartItem.getTimesSold()));

        holder.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickListener.onButtonClick(pos, holder.button, holder.button2, holder.button3, holder.button4, holder.button5);

            }
        });


        holder.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickListener.onButtonClick(pos, holder.button5, holder.button, holder.button2, holder.button3, holder.button4, holder.button5);
            }
        });


        holder.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickListener.onButtonClick(pos, holder.button4, holder.button, holder.button2, holder.button3, holder.button4, holder.button5);
            }
        });


        holder.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickListener.onButtonClick(pos, holder.button3, holder.button, holder.button2, holder.button3, holder.button4, holder.button5);

            }
        });


        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickListener.onButtonClick(pos, holder.button2, holder.button, holder.button2, holder.button3, holder.button4, holder.button5);
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClickListener.onButtonClick(pos, holder.button, holder.button, holder.button2, holder.button3, holder.button4, holder.button5);
            }
        });



    }

    @Override
    public int getItemCount() {
        return feedbackItemsList.size();
    }



    //for showing the format of the recyclerview, more like the xml layout of the recyclerview
    public class FeedbackItemViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView cartItemName;
        public TextView cartItemPrice;


        public Button button, button2, button3, button4, button5, buttonSave;

        public FeedbackItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cartItemImageView);
            cartItemName = itemView.findViewById(R.id.cartItemTextView);
            cartItemPrice = itemView.findViewById(R.id.textView);

            button = itemView.findViewById(R.id.button);
            button2 = itemView.findViewById(R.id.button2);
            button3 = itemView.findViewById(R.id.button3);
            button4 = itemView.findViewById(R.id.button4);
            button5 = itemView.findViewById(R.id.button5);

            buttonSave = itemView.findViewById(R.id.buttonTrack);


//            button5.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Notify the click event to the listener
//                    if (buttonClickListener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//
//                            }
//                    }
//                }
//            });

//            button4.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Notify the click event to the listener
//                    if (buttonClickListener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//
//                            Toast.makeText(v.getContext(), "Item is rated 2 stars", Toast.LENGTH_SHORT).show();
//
//
//                            button.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_outline_24));
//                            button2.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_outline_24));
//                            button3.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_outline_24));
//                            button4.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_24));
//                            button5.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_24));
//                        }
//                    }
//                }
//            });
//
//            button3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Notify the click event to the listener
//                    if (buttonClickListener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//
//                            Toast.makeText(v.getContext(), "Item is rated 3 stars", Toast.LENGTH_SHORT).show();
//
//                            button.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_outline_24));
//                            button2.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_outline_24));
//                            button3.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_24));
//                            button4.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_24));
//                            button5.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_24));
//                        }
//                    }
//                }
//            });
//
//            button2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Notify the click event to the listener
//                    if (buttonClickListener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//
//                            Toast.makeText(v.getContext(), "Item is rated 4 stars", Toast.LENGTH_SHORT).show();
//
//                            button.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_outline_24));
//                            button2.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_24));
//                            button3.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_24));
//                            button4.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_24));
//                            button5.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_24));
//                        }
//                    }
//                }
//            });
//
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Notify the click event to the listener
//                    if (buttonClickListener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//
//                            Toast.makeText(v.getContext(), "Item is rated 5 stars", Toast.LENGTH_SHORT).show();
//
//                            button.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_24));
//                            button2.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_24));
//                            button3.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_24));
//                            button4.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_24));
//                            button5.setBackground(button.getContext().getResources().getDrawable(R.drawable.baseline_star_24));
//                        }
//                    }
//                }
//            });
//

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
