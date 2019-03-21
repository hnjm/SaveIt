package saveit.com.saveit;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        setContentView(R.layout.overview);


    }
}
