package saveit.com.saveit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PieChart chart;
    private float ftSize = 22f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chart = findViewById(R.id.chart);
        // Example of using pie chart from MPAndroidChart
        List<PieEntry> pieVal = new ArrayList<>();          // make a list of values in the chart and labels
        pieVal.add(new PieEntry(1f, "one"));
        pieVal.add(new PieEntry(2f, "two"));
        pieVal.add(new PieEntry(3f, "three"));
        PieDataSet pieDataSet = new PieDataSet(pieVal, "RGB");  // set data and name to pieDataSet
        int[] color = {Color.RED, Color.BLUE, Color.DKGRAY};     // making an int[] for the colors
        pieDataSet.setColors(color);    // setting colors for the pie chart
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(ftSize);   // set % value font size
        pieData.setValueTextColor(Color.WHITE); // set value font color
        pieDataSet.setValueFormatter(new PercentFormatter());   // use % formatting
        chart.setData(pieData); // set data to pie chart
        chart.setUsePercentValues(true);    // use %
        chart.setEntryLabelTextSize(ftSize); // set pie chart description font size
        chart.getDescription().setEnabled(false);   // remove description next to chart
        chart.getLegend().setEnabled(false);        // remove legend
        chart.setHoleRadius(30f);   // radius of the empty space in the middle of the chart
        chart.setTransparentCircleAlpha(0); // makes center circle transparent
        //chart.setCenterText("Summary"); // set text in the middle of the chart
        //chart.setCenterTextSize(ftSize); // set size of center text
        chart.invalidate();     // refresh

    }

    public void goToAddExpense(View view){
        // Add Intent to open another activity (add Expense)
        Intent addExpensePage = new Intent(this, AddExpense.class);
        startActivity(addExpensePage);
    }

}
