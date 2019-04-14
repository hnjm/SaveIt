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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                    switch(category){
                        case "Clothing":
                            clothRef.get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            Map<String, Object> record = new HashMap<>();
                                            int color = 0;
                                            for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                                record = documentSnapshot.getData();
                                                // display data by adding TextView to LinearLayout
                                                String temp = record.get("Item") + "\n";
                                                temp += record.get("Location") + "\n";
                                                temp += record.get("Date") + "\n";
                                                temp += "$" + record.get("Amount") + "\n";

                                                TextView text = createNewTextView(temp);

                                                if(color % 2 == 0){
                                                    text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                                                }
                                                else{
                                                    text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryLight));
                                                }
                                                display.addView(text);
                                                color++;
                                            }

                                        }
                                    });
                            break;

                        case "Food":
                            foodRef.get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            Map<String, Object> record = new HashMap<>();
                                            int color = 0;
                                            for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                                record = documentSnapshot.getData();
                                                // display data by adding TextView to LinearLayout
                                                String temp = record.get("Item") + "\n";
                                                temp += record.get("Location") + "\n";
                                                temp += record.get("Date") + "\n";
                                                temp += "$" + record.get("Amount") + "\n";

                                                TextView text = createNewTextView(temp);

                                                if(color % 2 == 0){
                                                    text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                                                }
                                                else{
                                                    text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryLight));
                                                }
                                                display.addView(text);
                                                color++;
                                            }

                                        }
                                    });
                            break;

                        case "Housing":
                            houseRef.get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            Map<String, Object> record = new HashMap<>();
                                            int color = 0;
                                            for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                                record = documentSnapshot.getData();
                                                // display data by adding TextView to LinearLayout
                                                String temp = record.get("Item") + "\n";
                                                temp += record.get("Location") + "\n";
                                                temp += record.get("Date") + "\n";
                                                temp += "$" + record.get("Amount") + "\n";

                                                TextView text = createNewTextView(temp);

                                                if(color % 2 == 0){
                                                    text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                                                }
                                                else{
                                                    text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryLight));
                                                }
                                                display.addView(text);
                                                color++;
                                            }

                                        }
                                    });
                            break;

                        case "Medical":
                            medRef.get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            Map<String, Object> record = new HashMap<>();
                                            int color = 0;
                                            for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                                record = documentSnapshot.getData();
                                                // display data by adding TextView to LinearLayout
                                                String temp = record.get("Item") + "\n";
                                                temp += record.get("Location") + "\n";
                                                temp += record.get("Date") + "\n";
                                                temp += "$" + record.get("Amount") + "\n";

                                                TextView text = createNewTextView(temp);

                                                if(color % 2 == 0){
                                                    text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                                                }
                                                else{
                                                    text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryLight));
                                                }
                                                display.addView(text);
                                                color++;
                                            }

                                        }
                                    });
                            break;

                        case "Transport":
                            transportRef.get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            Map<String, Object> record = new HashMap<>();
                                            int color = 0;
                                            for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                                record = documentSnapshot.getData();
                                                // display data by adding TextView to LinearLayout
                                                String temp = record.get("Item") + "\n";
                                                temp += record.get("Location") + "\n";
                                                temp += record.get("Date") + "\n";
                                                temp += "$" + record.get("Amount") + "\n";

                                                TextView text = createNewTextView(temp);

                                                if(color % 2 == 0){
                                                    text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                                                }
                                                else{
                                                    text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryLight));
                                                }
                                                display.addView(text);
                                                color++;
                                            }

                                        }
                                    });
                            break;

                        case "Other":
                            otherRef.get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            Map<String, Object> record = new HashMap<>();
                                            int color = 0;
                                            for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                                record = documentSnapshot.getData();
                                                // display data by adding TextView to LinearLayout
                                                String temp = record.get("Item") + "\n";
                                                temp += record.get("Location") + "\n";
                                                temp += record.get("Date") + "\n";
                                                temp += "$" + record.get("Amount") + "\n";

                                                TextView text = createNewTextView(temp);

                                                if(color % 2 == 0){
                                                    text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                                                }
                                                else{
                                                    text.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryLight));
                                                }
                                                display.addView(text);
                                                color++;
                                            }

                                        }
                                    });
                            break;
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
