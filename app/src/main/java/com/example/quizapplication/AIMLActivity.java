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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupWindow;

public class AIMLActivity extends AppCompatActivity {

    String doBetter = "You can do better!" + System.getProperty("line.separator") + System.getProperty("line.separator") + "Try again?";
    String poor = "Brush up your knowledge, maybe?";
    String congrats = "Well done!" + System.getProperty("line.separator") + "You are awesome!";

    // Question RadioButtons
    RadioButton question1_choice1, question1_choice2, question1_choice3, question1_choice4;
    RadioButton question2_choice1, question2_choice2, question2_choice3, question2_choice4;
    RadioButton question3_choice1, question3_choice2, question3_choice3, question3_choice4;
    RadioButton question4_choice1, question4_choice2, question4_choice3, question4_choice4;
    RadioButton question5_choice1, question5_choice2, question5_choice3, question5_choice4;

    int final_score = 0;
    private FloatingActionButton fabCalculateScore, fabReset;
    private Animation fabOpen, fabClose, rotateForward, rotateBackward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aiml);

        // Initialize ScrollView
        ScrollView scrollView = findViewById(R.id.scroll_view);
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });

        // Initialize FABs
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

        // Load animations
        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotateForward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

        // FAB click listeners
        fabCalculateScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateScore();
            }
        });

        fabReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetQuiz();
            }
        });
    }

    private void calculateScore() {
        final_score = 0;

        // Scoring logic for questions
        if (question1_choice2.isChecked()) final_score++;
        if (question2_choice1.isChecked()) final_score++;
        if (question3_choice3.isChecked()) final_score++;
        if (question4_choice2.isChecked()) final_score++;
        if (question5_choice3.isChecked()) final_score++;

        showScorePopup();
    }

    private void showScorePopup() {
        // Inflate the popup layout
        LayoutInflater inflater = (LayoutInflater) AIMLActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup, null);

        // Set text based on score
        TextView popupMessage = layout.findViewById(R.id.popup);
        if (final_score <= 2) {
            popupMessage.setText(poor);
        } else if (final_score <= 4) {
            popupMessage.setText(doBetter);
        } else {
            popupMessage.setText(congrats);
        }

        // Show popup window
        PopupWindow popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        // Toast message
        Toast.makeText(getApplicationContext(), "Your Score: " + final_score, Toast.LENGTH_LONG).show();
    }

    private void resetQuiz() {
        // Reset all questions
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
        Toast.makeText(AIMLActivity.this, "Quiz Reset", Toast.LENGTH_SHORT).show();
    }
}
