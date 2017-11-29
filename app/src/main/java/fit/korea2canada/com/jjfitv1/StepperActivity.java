package fit.korea2canada.com.jjfitv1;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import fit.korea2canada.com.jjfitv1.util.Util;

public class StepperActivity extends AppCompatActivity {

//    private SensorManager mSensorManager;
    private TextView total;
    private TextView today;
    boolean activityRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepper);

        // Stepper
        total = (TextView) findViewById(R.id.tvTotal);
        today = (TextView) findViewById(R.id.tvToday);


        Database db = Database.getInstance(this);
//        int total_start = db.getTotalWithoutToday();
//        int total_days = db.getDays();
//        int total = db.getTotalWithoutToday();
        int today_steps = Math.max(db.getSteps(Util.getToday()), 0);
        int total_steps = Math.max(db.getCurrentSteps() + db.getSteps(Util.getToday()), 0);

        total.setText(String.valueOf(total_steps));
        today.setText(String.valueOf(today_steps));
        db.close();

//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;
//        Sensor countSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
//        if(countSensor != null){
//            mSensorManager.registerListener(this, countSensor, mSensorManager.SENSOR_DELAY_UI);
//        } else {
//            Toast.makeText(this, "Count sensor is not available", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning = false;
    }

//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        if(activityRunning){
//            count.setText(String.valueOf(event.values[0]));
//        }
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//    }

}
