package fit.korea2canada.com.jjfitv1;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.database.Cursor;
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
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class WeightActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {
    TextView textview;
    ImageButton mWeightplus;
    ImageButton mWeightgoalset;

    ArrayList<Integer> x = new ArrayList<Integer>();
    ArrayList<Double> weight = new ArrayList<Double>();
    ArrayList<Double> set = new ArrayList<Double>();

    private static String STIORE_NAME = "JJfit";
    private static String WEIGHT_KEY  = "curWeight";

    private float curWeight = 70.5f;
    // Chart elemnets
    XYSeries weightSeries;
    XYSeries tweightSeries;
    XYSeriesRenderer weightRenderer;
    XYSeriesRenderer tweightRenderer;
    XYMultipleSeriesRenderer ChartRenderer = new XYMultipleSeriesRenderer();

    private View chart;
    private String[] mMonth = new String[] {
            "Jan", "Feb" , "Mar", "Apr", "May", "Jun"
            , "Jul", "Aug", "Sep", "Oct", "Nov", "Doubleec"
    };



    //DB : date + weight / set data

    //그래픽 적용위해 aChartEngine 추가
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        textview = (TextView)findViewById(R.id.txtWeight);
        mWeightplus = (ImageButton) findViewById(R.id.btnWeightPlus);
        mWeightgoalset = (ImageButton) findViewById(R.id.btnWeightSet);


        if (x.size() == 0 ){
            x.addAll(Arrays.asList(1,2,3,4,5,6,7,8));
            weight.addAll(Arrays.asList(69.0,67.0,68.0, 66.0,67.5,68.0,66.0,67.5));
            set.addAll(Arrays.asList(63.0,63.0,63.0,63.5,63.5,63.5,63.5,63.5));
        }

        textview.setText( weight.get(weight.size()-1) + "kg");
        initChart();
        drawChart();
    }

    private void clearChart(){

    }


    private void initChart(){
        weightSeries = new XYSeries("Weight");
        tweightSeries = new XYSeries("Goal");
        // Creating a dataset to hold each series
        XYMultipleSeriesDataset xyMultipleSeriesDataset = new XYMultipleSeriesDataset();
        // Adding Weight Series to the dataset
        xyMultipleSeriesDataset.addSeries(weightSeries);
        xyMultipleSeriesDataset.addSeries(tweightSeries);

        weightRenderer = new XYSeriesRenderer();
        // Creating XYSeriesRenderer to customize weightSeries
        weightRenderer.setColor(Color.BLUE);
        weightRenderer.setChartValuesTextSize(30);
        weightRenderer.setPointStyle(PointStyle.CIRCLE);
        weightRenderer.setFillPoints(true);
        weightRenderer.setLineWidth(4);
        weightRenderer.setDisplayChartValues(true);

        // Creating XYSeriesRenderer to customize weight Set
        tweightRenderer = new XYSeriesRenderer();
        tweightRenderer.setColor(Color.DKGRAY);
        tweightRenderer.setChartValuesTextSize(30);
        tweightRenderer.setPointStyle(PointStyle.CIRCLE);
        tweightRenderer.setFillPoints(true);
        tweightRenderer.setLineWidth(4);
        tweightRenderer.setDisplayChartValues(true);

        ChartRenderer = new XYMultipleSeriesRenderer();
        ChartRenderer.setXLabels(0);
        ChartRenderer.setMarginsColor(Color.argb(0x00, 0x01, 0x01, 0x01));
//        multiRenderer.setChartTitle("Weight Chart");
        ChartRenderer.setXTitle("Year 2017");
        ChartRenderer.setYTitle("Kg");
        ChartRenderer.setZoomButtonsVisible(true);

        Double weightDouble = Collections.max(weight);
        ChartRenderer.setYAxisMax(weightDouble);

        for(int i=0;i<x.size();i++){
            ChartRenderer.addXTextLabel(i+1, mMonth[i]);
        }

        ChartRenderer.addSeriesRenderer(weightRenderer);
        ChartRenderer.addSeriesRenderer(tweightRenderer);
        // Getting a reference to LinearLayout of the MainActivity Layout
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_container);

        // Creating a Line Chart
        chart = ChartFactory.getLineChartView(getBaseContext(), xyMultipleSeriesDataset,
                ChartRenderer);

        // Adding the Line Chart to the LinearLayout
        chartContainer.addView(chart);
    }

    //    private void drawChart(){
    public void drawChart() {
        buildChartData();
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_container);
        chartContainer.refreshDrawableState();

    }

    public void buildChartData(){
        Log.d(Arrays.toString(x.toArray()), "x값: ");
        Log.d(Arrays.toString(weight.toArray()), "we값: ");
        Log.d(Arrays.toString(set.toArray()), "set값: ");

        weightSeries.clear();
        tweightSeries.clear();

        for(int i=0;i<x.size();i++){
            weightSeries.add(x.get(i), weight.get(i));
            tweightSeries.add(x.get(i), set.get(i));
        }
//        for(int i=x.size();i<x.size()-5;i--){
//            weightSeries.add(x.get(i), weight.get(i));
//            tweightSeries.add(x.get(i), set.get(i));
//        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        Log.i("value is",""+newVal);

    }


    @Override
    public void onRestart() {
        super.onRestart();
//        drawChart();
//        Toast.makeText(getApplicationContext(), "리스타트함", Toast.LENGTH_LONG).show();


    }
    public void onResume() {
        super.onResume();

    }

    public void onclickAddWeight(View view) {
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
        curWeight = readWeight();
        np.setValue((int)curWeight);

        np2.setMaxValue(9); // max value 100
        np2.setMinValue(0);   // min value 0
        np2.setValue((int)((curWeight - (int)curWeight)*10));

        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                textview.setText(String.valueOf(np.getValue() + "." + np2.getValue() + "kg")); //set the value to textview

                x.add(x.size()+1);
                String weightString = np.getValue() + "." + np2.getValue();
//                Toast.makeText(getApplicationContext(), weightString, Toast.LENGTH_LONG).show();

                double doubleWeight = Double.parseDouble(weightString);
                weight.add(doubleWeight);
                String setString;

                if(x.size() == 1){
                    setString = weightString;
                } else {
                    setString = String.valueOf(set.get(set.size()-1));
                }

                double doubleSet = Double.parseDouble(setString);
                set.add(doubleSet);
                curWeight = (float)doubleWeight;
                writeWeight(curWeight);
                d.dismiss();
                drawChart();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
    }

    public void onclickTargetWeight(View view) {

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
        curWeight = readWeight();
        np.setValue((int)curWeight);

        np2.setMaxValue(9); // max value 100
        np2.setMinValue(0);   // min value 0
        np2.setValue((int)((curWeight - (int)curWeight)*10));

        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                textview.setText(String.valueOf("Your Target Weight is: " + np.getValue() + "." + np2.getValue())); //set the value to textview

                String setString;
                double doubleSet = 0;
                if(x.size() == 0){
                    setString = np.getValue() + "." + np2.getValue();
                    doubleSet = Double.parseDouble(setString);
                    set.set(set.size(), doubleSet);
                    x.add(x.size()+1);
                    weight.add(0.0);
                } else {
                    setString = np.getValue() + "." + np2.getValue();
                    Toast.makeText(getApplicationContext(), setString, Toast.LENGTH_LONG).show();
                    doubleSet = Double.parseDouble(setString);
                    set.set(set.size()-1, doubleSet);
                }
                curWeight = (float)doubleSet;
                writeWeight(curWeight);

                d.dismiss();
                drawChart();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
    }

    private void writeWeight(float w){
        SharedPreferences a = getSharedPreferences(STIORE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor ed = a.edit();
        ed.putFloat(WEIGHT_KEY, w);
        ed.commit();
    }

    private float readWeight(){
        SharedPreferences a = getSharedPreferences(STIORE_NAME, MODE_PRIVATE);
        return a.getFloat(WEIGHT_KEY, curWeight);
    }

    private void readWeightFromDB(ArrayList<Integer> arrD, ArrayList<Float> arrW ){
        Cursor cursor = getContentResolver().query(JJFitProvider.CONTENT_URI, new String[] { JJDB.FIT_DATE, JJDB.FIT_WEIGHT }, null, null, JJDB.FIT_DATE);
        if(cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                arrD.clear();
                arrW.clear();
                do {
                    String d = cursor.getString(0);
                    String w = cursor.getString(1);
                    arrD.add(Integer.parseInt(d));
                    arrW.add(Float.parseFloat(w));
                } while (cursor.moveToNext());
            }
        }
    }

    private void insertWeighttoDB(){
       // getContentResolver().insert(JJFitProvider.CONTENT_URI, )
    }
    private void deleteAllNotes() {
//        getContentResolver().delete( NotesProvider.NOTE_CONTENT_URI, null, null);
    }
}
