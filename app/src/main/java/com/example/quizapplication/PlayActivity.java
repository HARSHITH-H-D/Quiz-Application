package com.example.quizapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;  // AndroidX import for AppCompatActivity
import androidx.cardview.widget.CardView;       // AndroidX import for CardView
import android.view.View;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Physics card
        CardView web = findViewById(R.id.web_card);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phyIntent = new Intent(PlayActivity.this, WebDevActivity.class);
                startActivity(phyIntent);
            }
        });

        // Chemistry card
        CardView net = findViewById(R.id.net_card);
        net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chemIntent = new Intent(PlayActivity.this, NetworkActivity.class);
                startActivity(chemIntent);
            }
        });

        // AIML card (replacing Biology)
        CardView aiml = findViewById(R.id.aiml_card);
        aiml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aimlIntent = new Intent(PlayActivity.this, AIMLActivity.class);  // Changed from BiologyActivity to AIMLActivity
                startActivity(aimlIntent);
            }
        });

        // More topics card
        CardView more = findViewById(R.id.more_card);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Patience is the key";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}
