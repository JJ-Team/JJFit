package fit.korea2canada.com.jjfitv1;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.lylc.widget.circularprogressbar.CircularProgressBar;

public class DoExercise extends AppCompatActivity {
    TextView txtWorkName;
    WebView  workImg;
    CircularProgressBar progressBar;

    private String[] workoutTitle = new String[] {
            "1. Jumping Jack", "2. Jumping" , "3. Jumping", "4. Jumping", "5. Jumping", "6. Jumping",
            "7. Jumping", "8. Jumping", "9. Jumping", "10. Jumping", "11. Jumping", "12. Jumping"
    };
    private String[] workImageName = new String[] {
            "workout1.html", "2. Jumping" , "3. Jumping", "4. Jumping", "5. Jumping", "6. Jumping",
            "7. Jumping", "8. Jumping", "9. Jumping", "10. Jumping", "11. Jumping", "12. Jumping"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_exercise);


        txtWorkName = (TextView)findViewById(R.id.txtWorkoutName);
        txtWorkName.setText(workoutTitle[0]);
        workImg = (WebView)findViewById(R.id.imgWorkout);
        workImg.loadUrl( "file:///android_asset/" + workImageName[0]);
        progressBar = (CircularProgressBar) findViewById(R.id.progBar);
        progressBar.animateProgressTo(0, 15000, 15000, new CircularProgressBar.ProgressAnimationListener() {
            @Override
            public void onAnimationStart() {
                progressBar.setSubTitle("Sec");
            }

            @Override
            public void onAnimationFinish() {
                progressBar.setSubTitle("");
            }

            @Override
            public void onAnimationProgress(int progress) {
                progressBar.setTitle( Integer.toString(progress/1000) );
            }
        });


/*
        //12가지 Workout 진행
        for (int j=0; j<3; j++) {
                txtWorkName = (TextView) findViewById(R.id.txtWorkoutName);
                txtWorkName.setText(mExeTitle[j]);

                Toast.makeText(this, mExeTitle[j], Toast.LENGTH_LONG).show();
                //Count
                startCount();

        }
*/

    }



    public void onClickProgress(View view) {
        /*
        progressBar = (CircularProgressBar) findViewById(R.id.progBar);
//        progressBar = (ProgressBar) view.findViewBy(R.id.progressBar2);
        ObjectAnimator animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 500); // see this max value coming back here, we animale towards that value
        animation.setDuration (30000); //in milliseconds
        animation.setInterpolator (new DecelerateInterpolator());
        animation.start ();*/
    }
}
