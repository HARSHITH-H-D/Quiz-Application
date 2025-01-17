package com.example.quizapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupWindow;

public class NetworkActivity extends AppCompatActivity {

    String doBetter = "You can do better!\n\nTry again?";
    String poor = "Brush up your knowledge, maybe?";
    String congrats = "Well done!\nYou are awesome!";

    // Question RadioButtons
    private RadioButton[][] questions = new RadioButton[5][4];
    private int final_score = 0;
    private FloatingActionButton fabCalculateScore, fabReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networks);

        ScrollView scrollView = findViewById(R.id.scroll_view);
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setOnTouchListener((v, event) -> {
            v.requestFocusFromTouch();
            return false;
        });

        // Initialize FAB buttons
        fabCalculateScore = findViewById(R.id.fab);
        fabReset = findViewById(R.id.fab1);

        // Initialize question RadioButtons dynamically
        for (int i = 0; i < 5; i++) {
            questions[i][0] = findViewById(getResources().getIdentifier("question" + (i + 1) + "_choice1", "id", getPackageName()));
            questions[i][1] = findViewById(getResources().getIdentifier("question" + (i + 1) + "_choice2", "id", getPackageName()));
            questions[i][2] = findViewById(getResources().getIdentifier("question" + (i + 1) + "_choice3", "id", getPackageName()));
            questions[i][3] = findViewById(getResources().getIdentifier("question" + (i + 1) + "_choice4", "id", getPackageName()));
        }

        // FAB click listeners
        fabCalculateScore.setOnClickListener(v -> calculateScore());
        fabReset.setOnClickListener(v -> resetQuiz());
    }

    private void calculateScore() {
        final_score = 0;

        // Correct answers for each question
        int[] correctAnswers = {1, 1, 2, 1, 1}; // Adjust based on your correct answer index

        for (int i = 0; i < 5; i++) {
            if (questions[i][correctAnswers[i]].isChecked()) final_score++;
        }

        showScorePopup();
    }

    private void showScorePopup() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup, null);

        // Adjusting text based on score
        TextView popupText = layout.findViewById(R.id.popup);
        if (final_score <= 2) {
            popupText.setText(poor);
        } else if (final_score >= 3 && final_score <= 4) {
            popupText.setText(doBetter);
        } else {
            popupText.setText(congrats);
        }

        // Show popup window
        float density = getResources().getDisplayMetrics().density;
        PopupWindow pw = new PopupWindow(layout, (int) density * 350, (int) density * 400, true);
        pw.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        pw.setOutsideTouchable(true);
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);

        // Toast score
        Toast.makeText(getApplicationContext(), "Your Score: " + final_score, Toast.LENGTH_LONG).show();
    }

    private void resetQuiz() {
        for (RadioButton[] question : questions) {
            for (RadioButton choice : question) {
                choice.setChecked(false);
            }
        }
        final_score = 0;
        Toast.makeText(this, "Quiz Reset", Toast.LENGTH_SHORT).show();
    }
}
