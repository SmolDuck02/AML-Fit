package com.example.appdev;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;


import androidx.appcompat.app.AppCompatActivity;

public class GetStartedActivity extends AppCompatActivity implements View.OnClickListener{
        Button getStartedButton;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_get_started);


            getStartedButton = findViewById(R.id.getStartedButton);
            getStartedButton.setVisibility(View.INVISIBLE);
            getStartedButton.setOnClickListener(this);



            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    getStartedButton.setVisibility(View.VISIBLE);
                }
            }, 3000);

        }

        public void onClick(View v) {
            Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
            intent1.putExtra("start", false);

            if(v.getId()==R.id.getStartedButton){
                    Toast.makeText(GetStartedActivity.this, "Getting Started", Toast.LENGTH_SHORT).show();
            }

            startActivity(intent1);
        }
}
