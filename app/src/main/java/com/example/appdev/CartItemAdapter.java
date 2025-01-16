package com.example.appdev;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>{


    private CartItemAdapter.OnItemClickListener clickListener;
    private CartItemAdapter.OnButtonClickListener buttonClickListener;
    ArrayList<ModifiedItem> ItemsList;
    TextView totalTextView;

    public CartItemAdapter(TextView totalTextView, ArrayList<ModifiedItem> ItemsList, OnItemClickListener clickListener, OnButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
        this.ItemsList = ItemsList;
        this.totalTextView = totalTextView;
        this.clickListener = clickListener;

    }

    public interface OnItemClickListener{
        void onItemClick(ModifiedItem item);

    }


    public interface OnButtonClickListener {
        void onButtonClick(ModifiedItem item, TextView priceView, TextView quantity, String method, int pos);
        void onButtonClick(int pos, String string);

    }





    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_cart_items, parent, false);
        return new CartItemAdapter.CartItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        ModifiedItem currentItem = ItemsList.get(pos);


        holder.image.setImageResource(currentItem.getImage());
        holder.cartItemName.setText(currentItem.getTitle());
        holder.cartItemPrice.setText("$" + (currentItem.getPrice() * currentItem.getQuantity()));


        holder.itemCheckBox.setChecked(currentItem.getIsChecked());
        holder.quantity.setText(String.valueOf(currentItem.getQuantity()) + "p");


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(ItemsList.get(pos));
            }
        });

        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonClickListener.onButtonClick(ItemsList.get(pos), holder.cartItemPrice, holder.quantity, "add", pos);
                if(holder.itemCheckBox.isChecked())
                    buttonClickListener.onButtonClick(pos, "replace");
            }
        });

        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonClickListener.onButtonClick(ItemsList.get(pos), holder.cartItemPrice, holder.quantity, "subtract", pos);
                if(holder.itemCheckBox.isChecked())
                    buttonClickListener.onButtonClick(pos, "replace");
            }
        });

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonClickListener.onButtonClick(pos, "remove");


            }
        });

        holder.itemCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.itemCheckBox.isChecked()){
                    buttonClickListener.onButtonClick(pos, "selected");
                    currentItem.setIsChecked(true);
                }

                else{
                    buttonClickListener.onButtonClick(pos, "deselected");
                    currentItem.setIsChecked(false);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return ItemsList.size();
    }



    //for showing the format of the recyclerview, more like the xml layout of the recyclerview
    public class CartItemViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView cartItemName;
        public TextView cartItemPrice;
        public TextView quantity;

        public int quantityNumber;
        public double priceNumber;
        public Button minusButton, plusButton, removeButton;
        public CheckBox itemCheckBox;
        private boolean isChecked;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cartItemImageView);
            cartItemName = itemView.findViewById(R.id.cartItemTextView);

            cartItemPrice = itemView.findViewById(R.id.textView);
            priceNumber = Double.parseDouble(cartItemPrice.getText().toString().substring(1));

            quantity = itemView.findViewById(R.id.quantityTextView);
            quantityNumber = Integer.parseInt(quantity.getText().toString());

            minusButton = itemView.findViewById(R.id.buttonnMinus);
            plusButton = itemView.findViewById(R.id.buttonnPlus);

            removeButton = itemView.findViewById(R.id.buttonRemove);

            itemCheckBox = itemView.findViewById(R.id.itemCheckBox);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(clickListener!=null){
//                        int position = getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION){  //para daw di mo click  sa katong gi delete na nga item
//                            clickListener.onItemClick(position);
//                        }
//                    }
//                }
//            });

//
//            plusButton.setOnClickListener(new PlusButtonClickListener());
//            minusButton.setOnClickListener(new MinusButtonClickListener());
        }

        public boolean getIsChecked() {
            return isChecked;
        }

        public void setIsChecked(boolean checked) {
            isChecked = checked;
        }


        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }

        public TextView getCartItemName() {
            return cartItemName;
        }

        public void setCartItemName(TextView cartItemName) {
            this.cartItemName = cartItemName;
        }

        public TextView getCartItemPrice() {
            return cartItemPrice;
        }

        public void setCartItemPrice(TextView cartItemPrice) {
            this.cartItemPrice = cartItemPrice;
        }

        public TextView getQuantity() {
            return quantity;
        }

        public void setQuantity(TextView quantity) {
            this.quantity = quantity;
        }

        public int getQuantityNumber() {
            return quantityNumber;
        }

        public void setQuantityNumber(int quantityNumber) {
            this.quantityNumber = quantityNumber;
        }

        public double getPriceNumber() {
            return priceNumber;
        }

        public void setPriceNumber(double priceNumber) {
            this.priceNumber = priceNumber;
        }

        public Button getMinusButton() {
            return minusButton;
        }

        public void setMinusButton(Button minusButton) {
            this.minusButton = minusButton;
        }

        public Button getPlusButton() {
            return plusButton;
        }

        public void setPlusButton(Button plusButton) {
            this.plusButton = plusButton;
        }


//        private class PlusButtonClickListener implements View.OnClickListener {
//            int p;
//            @Override
//            public void onClick(View view) {
//                if (buttonClickListener != null) {
//                    int position = getAdapterPosition();
//                    Item currentItem = ItemsList.get(position);
//
//
//                    if (position != RecyclerView.NO_POSITION) {
//                        buttonClickListener.onButtonClick(position);
//                        quantityNumber++;
//                        quantity.setText(String.valueOf(quantityNumber));
//
//                        p = Integer.parseInt(String.valueOf(getCartItemPrice().getText()).substring(1));
//                        System.out.println(currentItem.getPrice() + "  " + p + " tttttttttttt" + getCartItemPrice().getText());
//
//                        p = p + currentItem.getPrice();
//
//                        totalTextView.setText("$" + p);
//
//                        currentItem.setPrice(p);
//                        cartItemPrice.setText("$" + p);
//                        System.out.println(currentItem.getPrice() +" ooooooooopp" + getCartItemPrice().getText());
//
//
//
//                    }
//
//                    setTotalText((String) getCartItemPrice().getText());
//                }
//            }
//        }
//
//
//        private class MinusButtonClickListener implements View.OnClickListener {
//
//            int p;
//            @Override
//            public void onClick(View view) {
//                if (buttonClickListener != null) {
//                    int position = getAdapterPosition();
//                    Item currentItem = ItemsList.get(position);
//
//
//                    if (position != RecyclerView.NO_POSITION) {
//                        if(quantityNumber>0){
//                            buttonClickListener.onButtonClick(position);
//                            quantityNumber--;
//                            quantity.setText(String.valueOf(quantityNumber));
//
//                            p = Integer.parseInt(String.valueOf(getCartItemPrice().getText()).substring(1));
//                            System.out.println(currentItem.getPrice() + "  " + p + " tttttttttttt" + getCartItemPrice().getText());
//
//                            p = p - currentItem.getPrice();
//
//                            totalTextView.setText("$" + p);
//                            currentItem.setPrice(p);
//                            cartItemPrice.setText("$" + p);
//                            System.out.println(currentItem.getPrice() +" ooooooooopp" + getCartItemPrice().getText());
//
//                        }
//                    }
//
//                    setTotalText((String) getCartItemPrice().getText());
//                }
//            }
//        }



    }

    private void setTotalText(String priceText) {
    }







}
