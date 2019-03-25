package saveit.com.saveit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
Refer to tutorials for help:
https://codinginflow.com/tutorials/android/cloud-firestore/part-2-preparations-set-document
https://firebase.google.com/docs/firestore/quickstart#add_data
**/
// Add Expense page
public class AddExpense extends Activity {

    // Reference all inputs in form from xml
    private EditText textLocation;
    private EditText textItem;
    private EditText textQuantity;
    private EditText textCost;
    // Keys for map below
    private static final String KEY_LOCATION = "Location";
    private static final String KEY_ITEM = "Item";
    private static final String KEY_QUANTITY = "Quantity";
    private static final String KEY_PRICEPERITEM = "PricePerItem";
    private static String date = Calendar.getInstance().getTime().toString();

    // Access a Cloud Firestore instance from your Activity
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Reference to xml file
        setContentView(R.layout.add_expense);


        // Reference all inputs in form from xml
        textLocation = (EditText) findViewById(R.id.textLocation);
        textItem = (EditText) findViewById(R.id.textItem);
        textQuantity = (EditText) findViewById(R.id.textQuantity);
        textCost = (EditText) findViewById(R.id.textCost);
    }

    public void submitNewExpense(View v){
        String location = textLocation.getText().toString();
        String item = textItem.getText().toString();
        int quanitity = Integer.parseInt(textQuantity.getText().toString());
        double cost = Double.parseDouble(textCost.getText().toString());


        // Save value in a "container" to send over to FirebaseFirestore database
        Map<String, Object> record = new HashMap<>();
        record.put(KEY_LOCATION, location);
        record.put(KEY_ITEM, item);
        record.put(KEY_QUANTITY, quanitity);
        record.put(KEY_PRICEPERITEM, cost);
        record.put(KEY_PRICEPERITEM, cost);
        // Reference to collection to store all of the values above
        db.collection(date)
                .add(record)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Make Toast notification to notify users
                        Toast.makeText(AddExpense.this, "Record Saved", Toast.LENGTH_SHORT).show();
                        Log.d("Document: ", documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddExpense.this, "Error with Record", Toast.LENGTH_SHORT).show();
                        Log.w("Error: ", e);
                    }
                });

    }

}
