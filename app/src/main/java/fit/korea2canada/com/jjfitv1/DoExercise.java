package fit.korea2canada.com.jjfitv1;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DoExercise extends AppCompatActivity {
    ProgressBar progressBar;
    ProgressBar pgBar;
    TextView mTextCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_exercise);
    }

    public void onBtnClick(View v) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i=0; i<=100;) {
                    try{
                        sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    pgBar.setProgress(i);
                    i=i+10;
                }

                //숫자 카운트 다운
                new CountDownTimer(30000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        mTextCount.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        mTextCount.setText("done!");
                    }
                }.start();
            }
        };
        thread.start();
    }

    //써클 프로그레스 이미지
    public void onBtnClick2(View v) {
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
//        progressBar = (ProgressBar) view.findViewBy(R.id.progressBar2);
        ObjectAnimator animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 500); // see this max value coming back here, we animale towards that value
        animation.setDuration (30000); //in milliseconds
        animation.setInterpolator (new DecelerateInterpolator());
        animation.start ();
    }
}
