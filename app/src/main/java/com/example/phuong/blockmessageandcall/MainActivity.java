package com.example.phuong.blockmessageandcall;

import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewById(R.id.button)
    Button mBtn1;
    @ViewById(R.id.button2)
    Button mBtn2;
    @ViewById(R.id.button3)
    Button mBtn3;
    @ViewById(R.id.button4)
    Button mBtn4;
    @ViewById(R.id.button5)
    Button mBtn5;
    @ViewById(R.id.button6)
    Button mBtn6;
    @ViewById(R.id.imageView)
    ImageView mImageView;

    @Click(R.id.button)
    void Btn1Click() {
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.myanimation);
        mImageView.startAnimation(animation1);
    }

    @Click(R.id.button2)
    void Btn2Click() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        mImageView.startAnimation(animation);
    }

    @Click(R.id.button3)
    void Btn3Click() {
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        mImageView.startAnimation(animation1);
    }

    @Click(R.id.button4)
    void Btn4Click() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        mImageView.startAnimation(animation);
    }

    @Click(R.id.button5)
    void Btn5Click() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        mImageView.startAnimation(animation);
    }

    @Click(R.id.button6)
    void Btn6Click() {
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        mImageView.startAnimation(animation1);
    }
}
