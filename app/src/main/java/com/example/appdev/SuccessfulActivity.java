package com.example.appdev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SuccessfulActivity extends AppCompatActivity implements View.OnClickListener{

   // TextView messageTextView;

    Button backToHomeButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful);

//        messageTextView = findViewById(R.id.messageTextView);
//
//        Intent intent = getIntent();
//        messageTextView.setText(intent.getStringExtra("addedToCart"));

        backToHomeButton = findViewById(R.id.btnHome);
        backToHomeButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        return;
    }
}
