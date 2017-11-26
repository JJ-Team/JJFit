package fit.korea2canada.com.jjfitv1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.lylc.widget.circularprogressbar.CircularProgressBar;

public class DoExercise extends AppCompatActivity {
    TextView txtWorkName;
    WebView  workImg;
    CircularProgressBar progressBar;
    int     curWorkout = 0;
    MediaPlayer mPlayer;

    String  htmlStranding = "standing.html";
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
        txtWorkName.setText(workoutTitle[curWorkout]);
        workImg = (WebView)findViewById(R.id.imgWorkout);
        workImg.loadUrl( "file:///android_asset/" + htmlStranding);
        progressBar = (CircularProgressBar) findViewById(R.id.progBar);
        progressBar.setProgress(curWorkout);
        progressBar.setTitle("Ready");
        progressBar.setSubTitle("");
    }

   public void onClickProgress(View view) {
        if(curWorkout < 12) {
            progressBar.animateProgressTo(0, 15000, 15000, new CircularProgressBar.ProgressAnimationListener() {
                @Override
                public void onAnimationStart() {
                    workImg.loadUrl("file:///android_asset/" + workImageName[curWorkout]);
                    progressBar.setSubTitle("Sec");
                    progressBar.setEnabled(false);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPlayer = MediaPlayer.create(DoExercise.this ,  R.raw.countdown5to1);
                            mPlayer.start();
                        }
                    }, 9000);
                }

                @Override
                public void onAnimationFinish() {
                    curWorkout++;
                    progressBar.setEnabled(true);
                    workImg.loadUrl( "file:///android_asset/" + htmlStranding);
                    if(curWorkout == 12) {
                        progressBar.setTitle("Done");
                    }else{
                        txtWorkName.setText(workoutTitle[curWorkout]);
                        progressBar.setProgress(0);
                        progressBar.setTitle("Ready");
                        progressBar.setSubTitle("");
                    }
                    progressBar.setSubTitle("");
                }

                @Override
                public void onAnimationProgress(int progress) {
                    progressBar.setTitle(Integer.toString(progress / 1000));
                }
            });
        }
        else{
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}
