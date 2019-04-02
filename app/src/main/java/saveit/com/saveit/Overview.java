package saveit.com.saveit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Overview extends Activity {

    private int cat = 6;        // 6 categories: Clothing, Food, Housing, Medical, Transport, Other
    private double[] amount = new double[cat];
    private double[] percent = new double[cat];
    private DecimalFormat df = new DecimalFormat("#.##");

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
        setContentView(R.layout.overview);
        final Button btnSum = (Button)findViewById(R.id.totalAmt);
        final Button[] btnAmt = { (Button)findViewById(R.id.clothingAmt), (Button)findViewById(R.id.foodAmt),
                (Button)findViewById(R.id.housingAmt), (Button)findViewById(R.id.medicalAmt),
                (Button)findViewById(R.id.transportAmt), (Button)findViewById(R.id.otherAmt)};

        Button[] btnPerc = { (Button)findViewById(R.id.clothingPerc), (Button)findViewById(R.id.foodPerc),
                (Button)findViewById(R.id.housingPerc), (Button)findViewById(R.id.medicalPerc),
                (Button)findViewById(R.id.transportPerc), (Button)findViewById(R.id.otherPerc)};

        // get data for amount and percent
        // set amount and percent text
        // mock data

        clothRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, Object> record = new HashMap<>();
                        double temp = 0;

                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            record = documentSnapshot.getData();
                            temp += (double) record.get("Amount");
                        }
                        btnAmt[0].setText("" + df.format(temp));
                        double sum = 0;
                        sum = Double.parseDouble(btnSum.getText().toString());
                        sum+=temp;
                        btnSum.setText("" + df.format(sum));
                    }
                });

        foodRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, Object> record = new HashMap<>();
                        double temp = 0;
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            record = documentSnapshot.getData();
                            temp += (double) record.get("Amount");
                        }
                        btnAmt[1].setText("" + df.format(temp));
                        double sum = 0;
                        sum = Double.parseDouble(btnSum.getText().toString());
                        sum+=temp;
                        btnSum.setText("" + df.format(sum));
                    }
                });

        houseRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, Object> record = new HashMap<>();
                        double temp = 0;
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            record = documentSnapshot.getData();
                            temp += (double) record.get("Amount");
                        }
                        btnAmt[2].setText("" + df.format(temp));
                        double sum = 0;
                        sum = Double.parseDouble(btnSum.getText().toString());
                        sum+=temp;
                        btnSum.setText("" + df.format(sum));
                    }
                });

        medRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, Object> record = new HashMap<>();
                        double temp = 0;
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            record = documentSnapshot.getData();
                            temp += (double) record.get("Amount");
                        }
                        btnAmt[3].setText("" + df.format(temp));
                        double sum = 0;
                        sum = Double.parseDouble(btnSum.getText().toString());
                        sum+=temp;
                        btnSum.setText("" + df.format(sum));
                    }
                });

        transportRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, Object> record = new HashMap<>();
                        double temp = 0;
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            record = documentSnapshot.getData();
                            temp += (double) record.get("Amount");
                        }
                        btnAmt[4].setText("" + df.format(temp));
                        double sum = 0;
                        sum = Double.parseDouble(btnSum.getText().toString());
                        sum+=temp;
                        btnSum.setText("" + df.format(sum));
                    }
                });

        otherRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, Object> record = new HashMap<>();
                        double temp = 0;
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            record = documentSnapshot.getData();
                            temp += (double) record.get("Amount");
                        }
                        btnAmt[5].setText("" + df.format(temp));
                        double sum = 0;
                        sum = Double.parseDouble(btnSum.getText().toString());
                        sum+=temp;
                        btnSum.setText("" + df.format(sum));
                    }
                });
    }

}
