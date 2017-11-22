package fit.korea2canada.com.jjfitv1;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class WeightActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {
    TextView textview;
    ImageButton mWeightplus;
    ImageButton mWeightgoalset;

    private View chart;
    private String[] mMonth = new String[] {
            "Jan", "Feb" , "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };


    //그래픽 적용위해 aChartEngine 추가
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        textview = (TextView)findViewById(R.id.txtWeight);
        mWeightplus = (ImageButton) findViewById(R.id.btnWeightPlus);
        mWeightgoalset = (ImageButton) findViewById(R.id.btnWeightSet);

        //Draw Chart
        drawChart();

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

    private void drawChart(){
        int[] x = { 1,2,3,4,5,6,7,8 };
        int[] weight = { 69, 67, 68, 67, 66, 66, 65, 61};
        int[] set = {60, 60, 60, 60, 60, 60, 60, 60,};

        // Creating an  XYSeries for Weight
        XYSeries weightSeries = new XYSeries("Weight");
        XYSeries setSeries = new XYSeries("Goal Weight");

        // Adding data to Weight
        for(int i=0;i<x.length;i++){
            weightSeries.add(x[i], weight[i]);
            setSeries.add(x[i],set[i]);
        }

        // Creating a dataset to hold each series
        XYMultipleSeriesDataset xyMultipleSeriesDataset = new XYMultipleSeriesDataset();
        // Adding Weight Series to the dataset
        xyMultipleSeriesDataset.addSeries(weightSeries);
        xyMultipleSeriesDataset.addSeries(setSeries);

        // Creating XYSeriesRenderer to customize weightSeries
        XYSeriesRenderer weightRenderer = new XYSeriesRenderer();
        weightRenderer.setColor(Color.RED);
        weightRenderer.setPointStyle(PointStyle.CIRCLE);
        weightRenderer.setFillPoints(true);
        weightRenderer.setLineWidth(4);
        weightRenderer.setDisplayChartValues(true);

        // Creating XYSeriesRenderer to customize weight Set
        XYSeriesRenderer setRenderer = new XYSeriesRenderer();
        setRenderer.setColor(Color.GREEN);
        setRenderer.setPointStyle(PointStyle.CIRCLE);
        setRenderer.setFillPoints(true);
        setRenderer.setLineWidth(4);
        setRenderer.setDisplayChartValues(true);


        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setMarginsColor(Color.argb(0x00, 0x01, 0x01, 0x01));
//        multiRenderer.setChartTitle("Weight Chart");
        multiRenderer.setXTitle("Year 2017");
        multiRenderer.setYTitle("Kg");
        multiRenderer.setZoomButtonsVisible(true);


        for(int i=0;i<x.length;i++){
            multiRenderer.addXTextLabel(i+1, mMonth[i]);
        }

        // Adding wRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        multiRenderer.addSeriesRenderer(weightRenderer);
        multiRenderer.addSeriesRenderer(setRenderer);

        // Getting a reference to LinearLayout of the MainActivity Layout
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_container);

        // Creating a Line Chart
        chart = ChartFactory.getLineChartView(getBaseContext(), xyMultipleSeriesDataset, multiRenderer);

        // Adding the Line Chart to the LinearLayout
        chartContainer.addView(chart);
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
