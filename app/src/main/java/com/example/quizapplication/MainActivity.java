package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity; // AndroidX import
import androidx.cardview.widget.CardView; // AndroidX import
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView play = findViewById(R.id.cardview1);
        // Set a click listener on that View
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playIntent = new Intent(MainActivity.this, PlayActivity.class);
                // Start the new activity
                startActivity(playIntent);
            }
        });

        CardView howTo = findViewById(R.id.cardview2);
        // Set a click listener on that View
        howTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent howToIntent = new Intent(MainActivity.this, HowTo.class);
                // Start the new activity
                startActivity(howToIntent);
            }
        });
    }
}
