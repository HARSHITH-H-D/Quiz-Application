package com.example.quizapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class WebDevActivity extends AppCompatActivity {

    String doBetter = "You can do better!" + System.lineSeparator() + System.lineSeparator() + "Try again?";
    String poor = "Brush up your knowledge, maybe?";
    String congrats = "Well done!" + System.lineSeparator() + System.lineSeparator() + "You are awesome!";

    // Question RadioButtons
    RadioButton question1_choice1, question1_choice2, question1_choice3, question1_choice4;
    RadioButton question2_choice1, question2_choice2, question2_choice3, question2_choice4;
    RadioButton question3_choice1, question3_choice2, question3_choice3, question3_choice4;
    RadioButton question4_choice1, question4_choice2, question4_choice3, question4_choice4;
    RadioButton question5_choice1, question5_choice2, question5_choice3, question5_choice4;

    int final_score = 0;
    private FloatingActionButton fabCalculateScore, fabReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webdev);  // Use your layout file for web development quiz

        // Initialize Floating Action Buttons
        fabCalculateScore = findViewById(R.id.fab);
        fabReset = findViewById(R.id.fab1);

        // Initialize question RadioButtons
        question1_choice1 = findViewById(R.id.question1_choice1);
        question1_choice2 = findViewById(R.id.question1_choice2);
        question1_choice3 = findViewById(R.id.question1_choice3);
        question1_choice4 = findViewById(R.id.question1_choice4);

        question2_choice1 = findViewById(R.id.question2_choice1);
        question2_choice2 = findViewById(R.id.question2_choice2);
        question2_choice3 = findViewById(R.id.question2_choice3);
        question2_choice4 = findViewById(R.id.question2_choice4);

        question3_choice1 = findViewById(R.id.question3_choice1);
        question3_choice2 = findViewById(R.id.question3_choice2);
        question3_choice3 = findViewById(R.id.question3_choice3);
        question3_choice4 = findViewById(R.id.question3_choice4);

        question4_choice1 = findViewById(R.id.question4_choice1);
        question4_choice2 = findViewById(R.id.question4_choice2);
        question4_choice3 = findViewById(R.id.question4_choice3);
        question4_choice4 = findViewById(R.id.question4_choice4);

        question5_choice1 = findViewById(R.id.question5_choice1);
        question5_choice2 = findViewById(R.id.question5_choice2);
        question5_choice3 = findViewById(R.id.question5_choice3);
        question5_choice4 = findViewById(R.id.question5_choice4);

        // FAB click listeners
        fabCalculateScore.setOnClickListener(v -> calculateScore());
        fabReset.setOnClickListener(v -> resetQuiz());
    }

    private void calculateScore() {
        final_score = 0;

        // Question 1 - Correct Answer is #2 (HTML)
        if (question1_choice2.isChecked()) final_score++;

        // Question 2 - Correct Answer is #2 (Cascading Style Sheets)
        if (question2_choice2.isChecked()) final_score++;

        // Question 3 - Correct Answer is #1 (React)
        if (question3_choice1.isChecked()) final_score++;

        // Question 4 - Correct Answer is #2 (POST)
        if (question4_choice2.isChecked()) final_score++;

        // Question 5 - Correct Answer is #2 (JavaScript Object Notation)
        if (question5_choice2.isChecked()) final_score++;

        // Show score
        showScorePopup();
    }

    private void showScorePopup() {
        // Inflating the popup layout
        LayoutInflater inflater = (LayoutInflater) WebDevActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup, null);

        // Adjusting text based on score
        if (final_score <= 2) {
            ((TextView) layout.findViewById(R.id.popup)).setText(poor);
        } else if (final_score >= 3 && final_score <= 4) {
            ((TextView) layout.findViewById(R.id.popup)).setText(doBetter);
        } else {
            ((TextView) layout.findViewById(R.id.popup)).setText(congrats);
        }

        // Show popup window
        float density = WebDevActivity.this.getResources().getDisplayMetrics().density;
        final PopupWindow pw = new PopupWindow(layout, (int) density * 350, (int) density * 400, true);
        pw.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        pw.setOutsideTouchable(true);
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);

        // Toast score
        Toast.makeText(getApplicationContext(), "Your Score: " + final_score, Toast.LENGTH_LONG).show();
    }

    private void resetQuiz() {
        // Reset RadioGroups and final score
        RadioGroup radioGroup1 = findViewById(R.id.radiogroup1);
        radioGroup1.clearCheck();

        RadioGroup radioGroup2 = findViewById(R.id.radiogroup2);
        radioGroup2.clearCheck();

        RadioGroup radioGroup3 = findViewById(R.id.radiogroup3);
        radioGroup3.clearCheck();

        RadioGroup radioGroup4 = findViewById(R.id.radiogroup4);
        radioGroup4.clearCheck();

        RadioGroup radioGroup5 = findViewById(R.id.radiogroup5);
        radioGroup5.clearCheck();

        final_score = 0;

        // Toast message
        Toast.makeText(WebDevActivity.this, "Quiz Reset", Toast.LENGTH_SHORT).show();
    }
}
