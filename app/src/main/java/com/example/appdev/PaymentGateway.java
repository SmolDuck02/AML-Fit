package com.example.appdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class PaymentGateway extends AppCompatActivity {
    Button savepayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);

        savepayment = findViewById(R.id.savepayment);

        savepayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentGateway.this, "Saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentGateway.this, SuccessfulActivity.class);
                startActivity(intent);
            }
        });
    }
}