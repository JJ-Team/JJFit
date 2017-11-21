package fit.korea2canada.com.jjfitv1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;
import android.widget.TextView;

public class WeightActivity extends AppCompatActivity {
    NumberPicker numberpicker;
    NumberPicker numberpicker2;
    TextView textview;
    TextView textview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        numberpicker = (NumberPicker)findViewById(R.id.numberPicker1);
        numberpicker2 = (NumberPicker)findViewById(R.id.numberPicker3);

        textview = (TextView)findViewById(R.id.textView1);
        textview2 = (TextView)findViewById(R.id.textView2);

        numberpicker.setMinValue(0);
        numberpicker.setMaxValue(150);

        numberpicker2.setMinValue(0);
        numberpicker2.setMaxValue(10);

        numberpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {


                textview.setText("Selected Value is : " + newVal);
            }
        });

        numberpicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {


                textview2.setText("." + newVal);
            }
        });
    }
}
