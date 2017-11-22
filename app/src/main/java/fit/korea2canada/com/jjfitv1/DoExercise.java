package fit.korea2canada.com.jjfitv1;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DoExercise extends AppCompatActivity {
    ProgressBar progressBar;
    ProgressBar pgBar;
    TextView mTextCount;

    private String[] mExeTitle = new String[] {
            "1. Jumping Jack", "2. Jumping" , "3. Jumping", "4. Jumping", "5. Jumping", "6. Jumping",
            "7. Jumping", "8. Jumping", "9. Jumping", "10. Jumping", "11. Jumping", "12. Jumping"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_exercise);


        //12가지 Workout 진행

        for (int j=0; j<3; j++) {
//            if (j = )

                //title
                TextView mTitleText = (TextView) findViewById(R.id.textView6);
                mTitleText.setText(mExeTitle[j]);

                Toast.makeText(this, mExeTitle[j], Toast.LENGTH_LONG).show();
                //Count
                startCount();


//            if (j == mExeTitle.length) {
//                return;
//            }
        }


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
    public void startCount() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
//        progressBar = (ProgressBar) view.findViewBy(R.id.progressBar2);
        ObjectAnimator animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 500); // see this max value coming back here, we animale towards that value
        animation.setDuration (30000); //in milliseconds
        animation.setInterpolator (new DecelerateInterpolator());
        animation.start ();
    }

    public void onBtnClick2(View v) {
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
//        progressBar = (ProgressBar) view.findViewBy(R.id.progressBar2);
        ObjectAnimator animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 500); // see this max value coming back here, we animale towards that value
        animation.setDuration (30000); //in milliseconds
        animation.setInterpolator (new DecelerateInterpolator());
        animation.start ();
    }
}
