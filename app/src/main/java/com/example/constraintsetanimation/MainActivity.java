package com.example.constraintsetanimation;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private boolean show = false;

    private ImageView backgroundImage;
    private ConstraintLayout constraint;
    private ConstraintSet constraintSet = new ConstraintSet();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraint = findViewById(R.id.constraint);
        backgroundImage = findViewById(R.id.backgroundImage);
        backgroundImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(show)
                    hideComponents(); // if the animation is shown, we hide back the views
                else
                    showComponents() ;// if the animation is NOT shown, we animate the views
            }

        });

    }

    private void showComponents(){
        show = true;

        constraintSet.clone(this, R.layout.activity_main_detail);

        Transition transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        transition.setDuration(1000);

        TransitionManager.beginDelayedTransition(constraint, transition);
        constraintSet.applyTo(constraint);
    }

    private void hideComponents(){
        show = false;

        constraintSet.clone(this, R.layout.activity_main);

        Transition transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        transition.setDuration(1000);


        TransitionManager.beginDelayedTransition(constraint, transition);
        constraintSet.applyTo(constraint);
    }
}