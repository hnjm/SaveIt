package saveit.com.saveit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpendingDetail extends Activity {

    private Spinner spinner;
    private LinearLayout display;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference clothRef = db.collection("Clothing");
    private CollectionReference foodRef = db.collection("Food");
    private CollectionReference houseRef = db.collection("Housing");
    private CollectionReference medRef = db.collection("Medical");
    private CollectionReference transportRef = db.collection("Transport");
    private CollectionReference otherRef = db.collection("Other");

    // Data to populate page
    private HashMap<String, HashMap> allData = new HashMap<String, HashMap>();
    private HashMap<String, ArrayList> clothData = new HashMap<String, ArrayList>();
    private HashMap<String, ArrayList> foodData = new HashMap<String, ArrayList>();
    private HashMap<String, ArrayList> houseData = new HashMap<String, ArrayList>();
    private HashMap<String, ArrayList> medData = new HashMap<String, ArrayList>();
    private HashMap<String, ArrayList> transportData = new HashMap<String, ArrayList>();
    private HashMap<String, ArrayList> otherData = new HashMap<String, ArrayList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        // Load data beforehand and populate when prompted
//        clothRef.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Map<String, Object> record = new HashMap<>();
//
//                        ArrayList<String> items = new ArrayList<String>();
//                        ArrayList<String> locations = new ArrayList<String>();
//                        ArrayList<String> dates = new ArrayList<String>();
//                        ArrayList<Double> amounts = new ArrayList<Double>();
//
//                        // Get all items, locations, dates, and amounts per each document in col.
//                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
//                            record = documentSnapshot.getData();
//                            items.add((String) record.get("Item"));
//                            locations.add((String) record.get("Location"));
//                            dates.add((String) record.get("Date"));
//                            amounts.add((Double) record.get("Amount"));
//                        }
//                        // Add them to corresponding HashMap
//                        clothData.put("items", items);
//                        clothData.put("locations", locations);
//                        clothData.put("dates", dates);
//                        clothData.put("amounts", amounts);
//                        allData.put("Clothing", clothData);
//
//                    }
//                });
//
//        foodRef.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Map<String, Object> record = new HashMap<>();
//
//                        ArrayList<String> items = new ArrayList<String>();
//                        ArrayList<String> locations = new ArrayList<String>();
//                        ArrayList<String> dates = new ArrayList<String>();
//                        ArrayList<Double> amounts = new ArrayList<Double>();
//
//                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
//                            record = documentSnapshot.getData();
//                            items.add((String) record.get("Item"));
//                            locations.add((String) record.get("Location"));
//                            dates.add((String) record.get("Date"));
//                            amounts.add((Double) record.get("Amount"));
//                        }
//                        // Add them to corresponding HashMap
//                        foodData.put("items", items);
//                        foodData.put("locations", locations);
//                        foodData.put("dates", dates);
//                        foodData.put("amounts", amounts);
//                        allData.put("Food", foodData);
//                    }
//                });
//
//        houseRef.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Map<String, Object> record = new HashMap<>();
//
//                        ArrayList<String> items = new ArrayList<String>();
//                        ArrayList<String> locations = new ArrayList<String>();
//                        ArrayList<String> dates = new ArrayList<String>();
//                        ArrayList<Double> amounts = new ArrayList<Double>();
//
//                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
//                            record = documentSnapshot.getData();
//                            items.add((String) record.get("Item"));
//                            locations.add((String) record.get("Location"));
//                            dates.add((String) record.get("Date"));
//                            amounts.add((Double) record.get("Amount"));
//                        }
//
//                        // Add them to corresponding HashMap
//                        houseData.put("items", items);
//                        houseData.put("locations", locations);
//                        houseData.put("dates", dates);
//                        houseData.put("amounts", amounts);
//                        allData.put("Housing", houseData);
//                    }
//                });
//
//        medRef.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Map<String, Object> record = new HashMap<>();
//
//                        ArrayList<String> items = new ArrayList<String>();
//                        ArrayList<String> locations = new ArrayList<String>();
//                        ArrayList<String> dates = new ArrayList<String>();
//                        ArrayList<Double> amounts = new ArrayList<Double>();
//
//                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
//                            record = documentSnapshot.getData();
//                            items.add((String) record.get("Item"));
//                            locations.add((String) record.get("Location"));
//                            dates.add((String) record.get("Date"));
//                            amounts.add((Double) record.get("Amount"));
//                        }
//                        medData.put("items", items);
//                        medData.put("locations", locations);
//                        medData.put("dates", dates);
//                        medData.put("amounts", amounts);
//                        allData.put("Medical", medData);
//                    }
//                });
//
//        transportRef.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Map<String, Object> record = new HashMap<>();
//
//                        ArrayList<String> items = new ArrayList<String>();
//                        ArrayList<String> locations = new ArrayList<String>();
//                        ArrayList<String> dates = new ArrayList<String>();
//                        ArrayList<Double> amounts = new ArrayList<Double>();
//
//                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
//                            record = documentSnapshot.getData();
//                            items.add((String) record.get("Item"));
//                            locations.add((String) record.get("Location"));
//                            dates.add((String) record.get("Date"));
//                            amounts.add((Double) record.get("Amount"));
//                        }
//                        transportData.put("items", items);
//                        transportData.put("locations", locations);
//                        transportData.put("dates", dates);
//                        transportData.put("amounts", amounts);
//                        allData.put("Transport", transportData);
//                    }
//                });
//
//        otherRef.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Map<String, Object> record = new HashMap<>();
//
//                        ArrayList<String> items = new ArrayList<String>();
//                        ArrayList<String> locations = new ArrayList<String>();
//                        ArrayList<String> dates = new ArrayList<String>();
//                        ArrayList<Double> amounts = new ArrayList<Double>();
//
//                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
//                            record = documentSnapshot.getData();
//                            items.add((String) record.get("Item"));
//                            locations.add((String) record.get("Location"));
//                            dates.add((String) record.get("Date"));
//                            amounts.add((Double) record.get("Amount"));
//                        }
//                        otherData.put("items", items);
//                        otherData.put("locations", locations);
//                        otherData.put("dates", dates);
//                        otherData.put("amounts", amounts);
//                        allData.put("Other", otherData);
//                    }
//                });





        // Reference to xml file
        setContentView(R.layout.spending_detail);
        spinner = findViewById(R.id.category_spinner);
        display = findViewById(R.id.record_view);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                display.removeAllViews();
                if(i != 0){ // first item in spinner is not a category

                    // Use this as key to get data needed
                    String category = spinner.getSelectedItem().toString(); // get category

                    // Loop through all documents within list with whereEqualTo

                    // display data by adding TextView to LinearLayout
                    for (int j = 0; j < allData.get(category).get("items").size(); j++) {
                        String temp = items.get(j) + "\n";
                        temp += locations.get(j) + "\n";
                        temp += date.get(j) + "\n";
                        temp += "$" + amount.get(j) + "\n";

                        TextView text = createNewTextView(temp);

                        if(j % 2 == 0){
                            text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                        }
                        else{
                            text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryLight));
                        }
                        display.addView(text);
                    }

                }
                else{
                    TextView text = createNewTextView("Please choose category");
                    text.setTextSize(30f);
                    text.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    display.addView(text);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    // Returns a TextView with String text
    private TextView createNewTextView(String text) {
        final TextView textView = new TextView(this);
        textView.setText(text);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setTextSize(18f);
        textView.setLineSpacing(3, 1);
        return textView;
    }

    public void onBack(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
