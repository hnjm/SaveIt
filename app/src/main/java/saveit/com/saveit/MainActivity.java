package saveit.com.saveit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PieChart chart;
    private float fontSize = 18f;
    private String[] category = {"Clothing", "Food", "Housing", "Medical", "Transport", "Other"};   // setting categories
    private List<PieEntry> pieVal = new ArrayList<>();  // to store data pie entries
    private PieDataSet pieDataSet;  // to send data to the pie chart
    private int[] color = {Color.RED, Color.BLUE, Color.DKGRAY, Color.LTGRAY, Color.MAGENTA, Color.BLACK};     // making an int[] for the colors

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chart = findViewById(R.id.chart);

        // Example of using pie chart from MPAndroidChart
        // make a list of values in the chart and labels
        pieVal.add(new PieEntry(2f, category[0]));  // value, label
        pieVal.add(new PieEntry(2f, category[1]));
        pieVal.add(new PieEntry(3f, category[2]));
        pieVal.add(new PieEntry(4f, category[3]));
        pieVal.add(new PieEntry(3f, category[4]));
        pieVal.add(new PieEntry(2f, category[5]));

        pieDataSet = new PieDataSet(pieVal, "Summary");  // set data and name to pieDataSet

        pieDataSet.setColors(color);    // setting colors for the pie chart

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(fontSize);   // set % value font size
        pieData.setValueTextColor(Color.WHITE); // set value font color
        pieDataSet.setValueFormatter(new PercentFormatter());   // use % formatting

        chart.setData(pieData);                     // set data to pie chart
        chart.setUsePercentValues(true);            // use %
        chart.setEntryLabelTextSize(fontSize);      // set pie chart description font size
        chart.getDescription().setEnabled(false);   // remove description next to chart
        chart.getLegend().setEnabled(false);        // remove legend
        chart.setHoleRadius(30f);                   // radius of the empty space in the middle of the chart
        chart.setTransparentCircleAlpha(0);         // makes center circle transparent
        //chart.setCenterText("Summary");           // set text in the middle of the chart
        //chart.setCenterTextSize(ftSize);          // set size of center text

        chart.setTouchEnabled(false); // prevent clicking

        chart.invalidate();     // refresh

    }

    public void goToAddExpense(View view){
        // Add Intent to open another activity (Add Expense)
        Intent addExpensePage = new Intent(this, AddExpense.class);
        startActivity(addExpensePage);
    }

    public void goToSpendingDetail(View view){
        // Add Intent to open Spending Detail
        Intent intent = new Intent(this, SpendingDetail.class);
        startActivity(intent);
    }

    public void goToOverview(View view){
        // Add Intent to open Overview
        Intent intent = new Intent(this, Overview.class);
        startActivity(intent);
    }
}
