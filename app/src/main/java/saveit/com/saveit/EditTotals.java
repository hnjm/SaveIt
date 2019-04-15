package saveit.com.saveit;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.google.android.gms.tasks.OnSuccessListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.*;

public class EditTotals extends Activity {
    private DecimalFormat df = new DecimalFormat("#.##");

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference totalsRef = db.collection("Totals");
    // Get category chosen
    private Spinner spinner;
    // Populate these texts
    private TextView categorySelected;
    private EditText amountAllocate;
    private DocumentReference totRef = db.collection("Totals").document("c5jQlqKkMYTxxJpdJKVS");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Reference to xml file
        setContentView(R.layout.edit_totals);
        spinner =  findViewById(R.id.totalsCategorySelect);
        // Reference all inputs in form from xml
        categorySelected = (TextView) findViewById(R.id.totalsCategory);
        amountAllocate = (EditText) findViewById(R.id.amountToAllocate);

        // When user selects category
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) { // first item in spinner is not a category
                    // Use this as key to get data needed
                    final String category = spinner.getSelectedItem().toString();

                    categorySelected.setText(category);

                    totalsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            Map<String, Object> record = new HashMap<>();

                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                // Pull total that's currently in database
                                record = documentSnapshot.getData();
                                double total = Long.valueOf(record.get(category).toString()).doubleValue();
                                amountAllocate.setText("" + df.format(total));
                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void submitNewAmount(View v){
        final String category = spinner.getSelectedItem().toString();
        totRef.update(category, df.format(Double.parseDouble(amountAllocate.getText().toString())))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(EditTotals.this, "Record Updated", Toast.LENGTH_SHORT).show();
                        }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditTotals.this, "Error with updating record", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

