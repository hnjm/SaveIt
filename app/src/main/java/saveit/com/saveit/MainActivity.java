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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private PieChart chart;
    private float fontSize = 18f;
    private String[] category = {"Clothing", "Food", "Housing", "Medical", "Transport", "Other"};   // setting categories
    private List<PieEntry> pieVal = new ArrayList<>();  // to store data pie entries
    private PieDataSet pieDataSet;  // to send data to the pie chart
    private int[] color = {Color.RED, Color.BLUE, Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.BLACK};     // making an int[] for the colors
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference clothRef = db.collection("Clothing");
    private CollectionReference foodRef = db.collection("Food");
    private CollectionReference houseRef = db.collection("Housing");
    private CollectionReference medRef = db.collection("Medical");
    private CollectionReference transportRef = db.collection("Transport");
    private CollectionReference otherRef = db.collection("Other");

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chart = findViewById(R.id.chart);

        // Example of using pie chart from MPAndroidChart
        // make a list of values in the chart and labels
        clothRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, Object> record = new HashMap<>();
                        float amt = 0;
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            record = documentSnapshot.getData();
                            amt += Float.valueOf(String.valueOf(record.get("Amount")));
                        }
                        pieDataSet.addEntry(new PieEntry(amt, category[0]));
                        PieData pieData = new PieData(pieDataSet);
                        chart.setData(pieData);
                        chart.invalidate();     // refresh
                    }
                });

        foodRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, Object> record = new HashMap<>();
                        float amt = 0;
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            record = documentSnapshot.getData();
                            amt += Float.valueOf(String.valueOf(record.get("Amount")));
                        }
                        pieDataSet.addEntry(new PieEntry(amt, category[1]));
                        PieData pieData = new PieData(pieDataSet);
                        chart.setData(pieData);
                        chart.invalidate();     // refresh
                    }
                });

        houseRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, Object> record = new HashMap<>();
                        float amt = 0;
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            record = documentSnapshot.getData();
                            amt += Float.valueOf(String.valueOf(record.get("Amount")));
                        }
                        pieDataSet.addEntry(new PieEntry(amt, category[2]));
                        PieData pieData = new PieData(pieDataSet);
                        chart.setData(pieData);
                        chart.invalidate();     // refresh
                    }
                });

        medRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, Object> record = new HashMap<>();
                        float amt = 0;
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            record = documentSnapshot.getData();
                            amt += Float.valueOf(String.valueOf(record.get("Amount")));
                        }
                        pieDataSet.addEntry(new PieEntry(amt, category[3]));
                        PieData pieData = new PieData(pieDataSet);
                        chart.setData(pieData);
                        chart.invalidate();     // refresh
                    }
                });

        transportRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, Object> record = new HashMap<>();
                        float amt = 0;
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            record = documentSnapshot.getData();
                            amt += Float.valueOf(String.valueOf(record.get("Amount")));
                        }
                        pieDataSet.addEntry(new PieEntry(amt, category[4]));
                        PieData pieData = new PieData(pieDataSet);
                        chart.setData(pieData);
                        chart.invalidate();     // refresh
                    }
                });

        otherRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, Object> record = new HashMap<>();
                        float amt = 0;
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            record = documentSnapshot.getData();
                            amt += Float.valueOf(String.valueOf(record.get("Amount")));
                        }
                        pieDataSet.addEntry(new PieEntry(amt, category[5]));
                        PieData pieData = new PieData(pieDataSet);
                        chart.setData(pieData);
                        chart.invalidate();     // refresh
                    }
                });

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
        chart.getLegend().setEnabled(true);         // remove legend
        chart.setHoleRadius(20f);                   // radius of the empty space in the middle of the chart
        chart.setTransparentCircleAlpha(0);         // makes center circle transparent
        chart.setTouchEnabled(false);               // prevents click

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

    public void goToEditAmounts(View view){
        // Add Intent to open another activity (Add Expense)
        Intent editTotals = new Intent(this, EditTotals.class);
        startActivity(editTotals);
    }
}
