package fit.korea2canada.com.jjfitv1;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

public class WeightActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {
    TextView textview;
    ImageButton mWeightplus;
    ImageButton mWeightgoalset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        textview = (TextView)findViewById(R.id.txtWeight);
        mWeightplus = (ImageButton) findViewById(R.id.btnWeightPlus);
        mWeightgoalset = (ImageButton) findViewById(R.id.btnWeightSet);
        mWeightplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               show();     
            }
        });
        mWeightgoalset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               show2();
            }
        });
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        Log.i("value is",""+newVal);

    }

    public void show()
    {

        final Dialog d = new Dialog(WeightActivity.this);
        d.setTitle("Your Weight");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.btnWeight1);
        Button b2 = (Button) d.findViewById(R.id.btnWeight2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker);
        final NumberPicker np2 = (NumberPicker) d.findViewById(R.id.numberPicker2);

        d.show();
        Window window = d.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);


        np.setMaxValue(120); // max value 100
        np.setMinValue(0);   // min value 0
        np.setValue(70);

        np2.setMaxValue(10); // max value 100
        np2.setMinValue(0);   // min value 0
        np2.setValue(5);

        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                textview.setText(String.valueOf("Your Weight is: " + np.getValue() + "." + np2.getValue())); //set the value to textview
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();
    }

    public void show2()
    {

        final Dialog d = new Dialog(WeightActivity.this);
        d.setTitle("Your Weight");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.btnWeight1);
        Button b2 = (Button) d.findViewById(R.id.btnWeight2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker);
        final NumberPicker np2 = (NumberPicker) d.findViewById(R.id.numberPicker2);

        d.show();
        Window window = d.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);


        np.setMaxValue(120); // max value 100
        np.setMinValue(0);   // min value 0
        np.setValue(70);

        np2.setMaxValue(10); // max value 100
        np2.setMinValue(0);   // min value 0
        np2.setValue(5);

        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                textview.setText(String.valueOf("Your Target Weight is: " + np.getValue() + "." + np2.getValue())); //set the value to textview
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();
    }


}
