package fit.korea2canada.com.jjfitv1;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private TextView count;
    boolean activityRunning;
    private Button mButton;
    private TextView mTimer;
    private ProgressBar pgBar;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = (TextView) findViewById(R.id.count);
        mTimer = (TextView) findViewById(R.id.mTimer);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        pgBar = (ProgressBar) findViewById(R.id.progressBar);

        //일단 타이머 버튼으로 테스트
        mButton = (Button) findViewById(R.id.timerButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            //30초
            @Override
            public void onClick(View v) {
                //숫자 카운트 다운
                new CountDownTimer(30000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        mTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        mTimer.setText("done!");
                    }
                }.start();
            }
        });
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

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            mSensorManager.registerListener(this, countSensor, mSensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Count sensor is not available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(activityRunning){
            count.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
