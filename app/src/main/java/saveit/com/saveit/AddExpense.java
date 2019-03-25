package saveit.com.saveit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.support.annotation.NonNull;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
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
    private EditText textTitle, textCost;
    private Spinner spinCat;

    // Keys for map below
    private static final String KEY_TITLE = "Title";
    private static final String KEY_COST = "Amount";
    private static final String KEY_CAT = "Category";
    private static String date = Calendar.getInstance().getTime().toString();

    // Access a Cloud Firestore instance from your Activity
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Reference to xml file
        setContentView(R.layout.add_expense);

        // Reference all inputs in form from xml
        textTitle = (EditText) findViewById(R.id.expenseTitle);
        textCost = (EditText) findViewById(R.id.expenseCost);
        spinCat = (Spinner) findViewById(R.id.expense_category);
    }

    public void submitNewExpense(View v){
        // check title
        String title = textTitle.getText().toString();
        if(title.trim().equals("")){
            Toast.makeText(AddExpense.this, "Please enter a valid title", Toast.LENGTH_SHORT).show();
            return;
        }

        // check valid cost
        if(textCost.getText().toString().trim().equals("")){
            Toast.makeText(AddExpense.this, "Please enter a valid amount spent", Toast.LENGTH_SHORT).show();
            return;
        }
        double cost = Double.parseDouble(textCost.getText().toString());

        // check category
        String category = spinCat.getSelectedItem().toString();
        if(category.equals("Category")){
            Toast.makeText(AddExpense.this, "Please pick a category", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save value in a "container" to send over to FirebaseFirestore database
        Map<String, Object> record = new HashMap<>();
        record.put(KEY_TITLE, title);
        record.put(KEY_COST, cost);
        record.put(KEY_CAT, category);


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
