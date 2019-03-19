package saveit.com.saveit;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PieChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        chart = findViewById(R.id.chart);

        // Example of using pie chart from MPAndroidChart
        List<PieEntry> pieVal = new ArrayList<>();          // make a list of values in the chart and labels
        pieVal.add(new PieEntry(1f, "red"));
        pieVal.add(new PieEntry(2f, "green"));
        pieVal.add(new PieEntry(3f, "blue"));

        PieDataSet pieDataSet = new PieDataSet(pieVal, "RGB");  // set data and name to pieDataSet

        int[] color = {Color.RED, Color.GREEN, Color.BLUE};     // making an int[] for the colors
        pieDataSet.setColors(color);    // setting colors for the pie chart

        PieData pieData = new PieData(pieDataSet);
        chart.setData(pieData); // set data to pie chart
        chart.invalidate();     // refresh

    }
}
