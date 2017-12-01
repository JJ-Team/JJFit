package fit.korea2canada.com.jjfitv1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import fit.korea2canada.com.jjfitv1.util.Util;

public class StepperActivity extends AppCompatActivity {



//    private SensorManager mSensorManager;
    private TextView total;
    private TextView today;
    boolean activityRunning;
    private View chart;


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
        initChart();
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


    private void initChart(){
        int[] steps = {4000, 4200,3500,4800,6000,3500,3000,3800,4500, 4300};
        String[] days = new String[] {
                "11", "12" , "13", "14", "15", "16", "17", "18","19","20"
        };

        XYSeries stepSeries = new XYSeries("Steps");

        int stepMax = 0;
        for(int i=0;i<steps.length;i++){
            stepMax = Math.max(steps[i], stepMax);
            stepSeries.add(i,steps[i]);
        }

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(stepSeries);

        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
        expenseRenderer.setColor(Color.CYAN); //color of the graph set to cyan
        expenseRenderer.setFillPoints(true);
        expenseRenderer.setLineWidth(2);
        expenseRenderer.setDisplayChartValues(true);
        expenseRenderer.setDisplayChartValues(true);

        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("November");
        multiRenderer.setXTitle("");
        multiRenderer.setYTitle("");
        multiRenderer.setShowLegend(false);

        /***
         * Customizing graphs
         */
        multiRenderer.setChartTitleTextSize(100);
        multiRenderer.setAxisTitleTextSize(40);
        multiRenderer.setLabelsTextSize(50);
        multiRenderer.setZoomButtonsVisible(false);
        multiRenderer.setPanEnabled(false, false);
        multiRenderer.setClickEnabled(false);
        multiRenderer.setZoomEnabled(false, false);
        multiRenderer.setShowGridY(false);
        multiRenderer.setShowGridX(false);
        //multiRenderer.setFitLegend(true);
        multiRenderer.setShowGrid(false);
        multiRenderer.setExternalZoomEnabled(false);
        multiRenderer.setAntialiasing(true);
//        multiRenderer.setInScroll(false);
        multiRenderer.setInScroll(true);
        multiRenderer.setZoomEnabled(true);
        //multiRenderer.setLegendHeight(50);

        multiRenderer.setXLabelsAlign(Paint.Align.CENTER);
        multiRenderer.setYLabelsAlign(Paint.Align.LEFT);
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
        multiRenderer.setYLabels(7);
        multiRenderer.setYAxisMax(stepMax + 1000);
        multiRenderer.setXAxisMin(-0.5);
        multiRenderer.setXAxisMax(11);
        multiRenderer.setBarSpacing(0.5);
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);
        multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
        multiRenderer.setApplyBackgroundColor(true);

        //setting the margin size for the graph in the order top, left, bottom, right
        multiRenderer.setMargins(new int[]{30, 30, 30, 30});

        for(int i=0; i< days.length;i++){
            multiRenderer.addXTextLabel(i, days[i]);
        }


        // Adding expenseRenderer to multipleRenderer
        multiRenderer.addSeriesRenderer(expenseRenderer);


        //this part is used to display graph on the xml
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chartStepper);
        //remove any views before u paint the chart
        chartContainer.removeAllViews();
        //drawing bar chart
        chart = ChartFactory.getBarChartView(StepperActivity.this, dataset, multiRenderer, BarChart.Type.DEFAULT);
        //adding the view to the linearlayout
        chartContainer.addView(chart);

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
