package fit.korea2canada.com.jjfitv1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.lylc.widget.circularprogressbar.CircularProgressBar;

public class DoExercise extends AppCompatActivity {
    TextView txtWorkName;
    WebView  workImg;
    CircularProgressBar progressBar;

    private String[] workoutTitle = new String[] {
            "1. Jumping Jack", "2. Wall Sit" , "3. Push-up", "4. Abdominal Crunch", "5. Step-up onto Chair", "6. Squat",
            "7. Triceps Dip on Chair", "8. Plank", "9. High Knees", "10. Lunge", "11. Push-up and Rotation", "12. Side Plank"
    };
    private String[] workImageName = new String[] {
            "workout1.html", "workout2.html" , "workout3.html", "workout4.html", "workout5.html", "workout6.html",
            "workout7.html", "workout8.html", "workout9.html", "workout10.html", "workout12.html", "workout12.html"
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
                progressBar.setTitle("Done");
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
