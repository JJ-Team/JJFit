package fit.korea2canada.com.jjfitv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton WalkingButton;
    ImageButton WorkoutButton;
    ImageButton WeightButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(new Intent(this, SensorListener.class));

        setContentView(R.layout.activity_main);

        WalkingButton = (ImageButton) findViewById(R.id.btnWalking);
        WorkoutButton = (ImageButton) findViewById(R.id.btnWorkout);
        WeightButton = (ImageButton) findViewById(R.id.btnWeight);

        WalkingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StepperActivity.class);
                startActivity(intent);
            }
        });

        WorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);
                startActivity(intent);
            }
        });

        WeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WeightActivity.class);
                startActivity(intent);
            }
        });
    }


/*
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

*/

}
