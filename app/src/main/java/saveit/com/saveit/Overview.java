package saveit.com.saveit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    private DecimalFormat df = new DecimalFormat("#.##");

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference clothRef = db.collection("Clothing");
    private CollectionReference foodRef = db.collection("Food");
    private CollectionReference houseRef = db.collection("Housing");
    private CollectionReference medRef = db.collection("Medical");
    private CollectionReference transportRef = db.collection("Transport");
    private CollectionReference otherRef = db.collection("Other");
    private CollectionReference totalsRef = db.collection("Totals");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Reference to xml file
        setContentView(R.layout.overview);
        final Button btnSum = (Button)findViewById(R.id.totalAmt);
        final Button[] btnAmt = { (Button)findViewById(R.id.clothingAmt), (Button)findViewById(R.id.foodAmt),
                (Button)findViewById(R.id.housingAmt), (Button)findViewById(R.id.medicalAmt),
                (Button)findViewById(R.id.transportAmt), (Button)findViewById(R.id.otherAmt)};

        final Button[] btnPerc = { (Button)findViewById(R.id.clothingPerc), (Button)findViewById(R.id.foodPerc),
                (Button)findViewById(R.id.housingPerc), (Button)findViewById(R.id.medicalPerc),
                (Button)findViewById(R.id.transportPerc), (Button)findViewById(R.id.otherPerc)};

        final Button btnRemain = (Button)findViewById(R.id.remainAmt);
        final Button btnTotal = (Button)findViewById(R.id.totalAllocated);

        // get data for amounts
        // set amount and left text
        //following code is repeated for every category...
        clothRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    //once connected do the following...
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, Object> record = new HashMap<>();
                        double temp = 0;

                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            //holds data grabbed from database
                            record = documentSnapshot.getData();
                            //adds cost of each entry
                            temp += (double) record.get("Amount");
                        }
                        //sets area corresponding to category to total amount spent
                        btnAmt[0].setText("" + df.format(temp));
                        //sum
                        double sum = 0;
                        //grab number in sum field
                        sum = Double.parseDouble(btnSum.getText().toString());
                        //adds cost of category to whatever was in the spent field
                        sum+=temp;
                        //set sum to spent field
                        btnSum.setText("" + df.format(sum));

                        //update remaining, $ left, and total
                        totalsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Map<String, Object> record = new HashMap<>();
                                double used = 0;
                                //how much money spent
                                used = Double.parseDouble(btnAmt[0].getText().toString());
                                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                    record = documentSnapshot.getData();
                                    double total = 0;
                                    double update = 0;

                                    //get budgeted amount from database
                                    total = Long.valueOf(record.get("Clothing").toString()).doubleValue();
                                    //get total field
                                    update = Double.parseDouble(btnTotal.getText().toString());
                                    //add total of categories together
                                    update += total;
                                    //set total field
                                    btnTotal.setText("" + df.format(update));

                                    //category budget - how much was spent
                                    total -= used;

                                    //set remaining of category budget
                                    btnPerc[0].setText("" + df.format(total));

                                    //get total remain field
                                    update = Double.parseDouble(btnRemain.getText().toString());
                                    //add remainder of category budget to field
                                    update += total;
                                    btnRemain.setText("" + df.format(update));
                                }
                            }
                        });
                    }
                });

        //all the following are repeats of above except for corresponding category
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

                        totalsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Map<String, Object> record = new HashMap<>();
                                double used = 0;
                                used = Double.parseDouble(btnAmt[1].getText().toString());
                                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                    record = documentSnapshot.getData();
                                    double total = 0;
                                    double update = 0;

                                    total = Long.valueOf(record.get("Food").toString()).doubleValue();
                                    update = Double.parseDouble(btnTotal.getText().toString());
                                    update += total;
                                    btnTotal.setText("" + df.format(update));

                                    total -= used;

                                    btnPerc[1].setText("" + df.format(total));

                                    update = Double.parseDouble(btnRemain.getText().toString());
                                    update += total;
                                    btnRemain.setText("" + df.format(update));
                                }
                            }
                        });
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

                        totalsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Map<String, Object> record = new HashMap<>();
                                double used = 0;
                                used = Double.parseDouble(btnAmt[2].getText().toString());
                                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                    record = documentSnapshot.getData();
                                    double total = 0;
                                    double update = 0;

                                    total = Long.valueOf(record.get("Housing").toString()).doubleValue();
                                    update = Double.parseDouble(btnTotal.getText().toString());
                                    update += total;
                                    btnTotal.setText("" + df.format(update));

                                    total -= used;

                                    btnPerc[2].setText("" + df.format(total));

                                    update = Double.parseDouble(btnRemain.getText().toString());
                                    update += total;
                                    btnRemain.setText("" + df.format(update));
                                }
                            }
                        });
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

                        totalsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Map<String, Object> record = new HashMap<>();
                                double used = 0;
                                used = Double.parseDouble(btnAmt[3].getText().toString());
                                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                    record = documentSnapshot.getData();
                                    double total = 0;
                                    double update = 0;

                                    total = Long.valueOf(record.get("Medical").toString()).doubleValue();
                                    update = Double.parseDouble(btnTotal.getText().toString());
                                    update += total;
                                    btnTotal.setText("" + df.format(update));

                                    total -= used;

                                    btnPerc[3].setText("" + df.format(total));

                                    update = Double.parseDouble(btnRemain.getText().toString());
                                    update += total;
                                    btnRemain.setText("" + df.format(update));
                                }
                            }
                        });
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

                        totalsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Map<String, Object> record = new HashMap<>();
                                double used = 0;
                                used = Double.parseDouble(btnAmt[4].getText().toString());
                                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                    record = documentSnapshot.getData();
                                    double total = 0;
                                    double update = 0;

                                    total = Long.valueOf(record.get("Transport").toString()).doubleValue();
                                    update = Double.parseDouble(btnTotal.getText().toString());
                                    update += total;
                                    btnTotal.setText("" + df.format(update));

                                    total -= used;

                                    btnPerc[4].setText("" + df.format(total));

                                    update = Double.parseDouble(btnRemain.getText().toString());
                                    update += total;
                                    btnRemain.setText("" + df.format(update));
                                }
                            }
                        });
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

                        totalsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                Map<String, Object> record = new HashMap<>();
                                double used = 0;
                                used = Double.parseDouble(btnAmt[5].getText().toString());
                                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                    record = documentSnapshot.getData();
                                    double total = 0;
                                    double update = 0;

                                    total = Long.valueOf(record.get("Other").toString()).doubleValue();
                                    update = Double.parseDouble(btnTotal.getText().toString());
                                    update += total;
                                    btnTotal.setText("" + df.format(update));

                                    total -= used;

                                    btnPerc[5].setText("" + df.format(total));

                                    update = Double.parseDouble(btnRemain.getText().toString());
                                    update += total;
                                    btnRemain.setText("" + df.format(update));
                                }
                            }
                        });
                    }
                });
    }
}
